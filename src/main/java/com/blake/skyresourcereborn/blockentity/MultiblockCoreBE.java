package com.blake.skyresourcereborn.blockentity;

import com.blake.skyresourcereborn.multiblock.MultiblockManager;
import com.blake.skyresourcereborn.multiblock.MultiblockStructure;
import com.blake.skyresourcereborn.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.inventory.ContainerData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MultiblockCoreBE extends BlockEntity {

    private int progress = 0;
    private int maxProgress = 100;

    private final ItemStackHandler items = new ItemStackHandler(2); // 0: input, 1: output
    private final LazyOptional<IItemHandler> optional = LazyOptional.of(() -> items);

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public MultiblockCoreBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MULTIBLOCK_CORE_BE.get(), pos, state);
    }

    public ContainerData getDataAccess() {
        return dataAccess;
    }

    public ItemStackHandler getItemHandler() {
        return items;
    }

    public ItemStack getCurrentInput() {
        return findItemInput() != null ? findItemInput().getStack() : ItemStack.EMPTY;
    }

    public ItemStack getCurrentOutput() {
        return findItemOutput() != null ? findItemOutput().getStack() : ItemStack.EMPTY;
    }

    // Utility interna
    private ItemInputBE findItemInput() {
        return MultiblockManager.STRUCTURES.values().stream()
                .flatMap(structure -> structure.structure().stream())
                .filter(b -> b.blockId().getPath().equals("item_input"))
                .map(b -> this.level.getBlockEntity(this.getBlockPos().offset(b.offset())))
                .filter(be -> be instanceof ItemInputBE)
                .map(be -> (ItemInputBE) be)
                .findFirst().orElse(null);
    }

    private ItemOutputBE findItemOutput() {
        return MultiblockManager.STRUCTURES.values().stream()
                .flatMap(structure -> structure.structure().stream())
                .filter(b -> b.blockId().getPath().equals("item_output"))
                .map(b -> this.level.getBlockEntity(this.getBlockPos().offset(b.offset())))
                .filter(be -> be instanceof ItemOutputBE)
                .map(be -> (ItemOutputBE) be)
                .findFirst().orElse(null);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return optional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        optional.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        for (var entry : MultiblockManager.STRUCTURES.entrySet()) {
            MultiblockStructure structure = entry.getValue();
            boolean valid = structure.structure().stream().allMatch(offset -> {
                BlockPos checkPos = this.getBlockPos().offset(offset.offset());
                var state = this.level.getBlockState(checkPos);
                var id = ForgeRegistries.BLOCKS.getKey(state.getBlock());
                return id != null && id.equals(offset.blockId());
            });

            if (!valid) continue;

            // Trova input/output reali
            ItemInputBE inputBE = findItemInput();
            ItemOutputBE outputBE = findItemOutput();

            if (inputBE == null || outputBE == null) return;

            ItemStack current = inputBE.getStack();
            if (current.isEmpty()) return;

            for (MultiblockStructure.Recipe recipe : structure.recipes()) {
                var expectedItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(recipe.input()));
                if (expectedItem == null || !current.is(expectedItem)) continue;

                this.maxProgress = recipe.time();

                if (progress >= recipe.time()) {
                    inputBE.removeOne();

                    ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(recipe.output())));
                    outputBE.insert(output);

                    System.out.println("✅ Crafting completato: " + output);
                    progress = 0;
                } else {
                    progress++;
                    if (progress % 20 == 0) {
                        System.out.println("⏳ Crafting... [" + progress + "/" + recipe.time() + "]");
                    }
                }

                break; // una sola ricetta per tick
            }

            break; // solo la prima struttura valida
        }
    }
}
