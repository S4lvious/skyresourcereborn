package com.blake.skyresourcereborn.blockentity;

import com.blake.skyresourcereborn.multiblock.MultiblockManager;
import com.blake.skyresourcereborn.multiblock.MultiblockStructure;
import com.blake.skyresourcereborn.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class MultiblockCoreBE extends BlockEntity {

    private int progress = 0;

    public MultiblockCoreBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MULTIBLOCK_CORE_BE.get(), pos, state);
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
            BlockPos inputPos = structure.structure().stream()
                    .filter(b -> b.blockId().getPath().equals("item_input"))
                    .findFirst().map(b -> this.getBlockPos().offset(b.offset())).orElse(null);

            BlockPos outputPos = structure.structure().stream()
                    .filter(b -> b.blockId().getPath().equals("item_output"))
                    .findFirst().map(b -> this.getBlockPos().offset(b.offset())).orElse(null);

            if (inputPos == null || outputPos == null) return;
            var inputBE = level.getBlockEntity(inputPos);
            var outputBE = level.getBlockEntity(outputPos);

            if (!(inputBE instanceof ItemInputBE input) || !(outputBE instanceof ItemOutputBE output)) return;
            ItemStack current = input.getStack();

            for (MultiblockStructure.Recipe recipe : structure.recipes()) {
                var expectedItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(recipe.input()));
                if (expectedItem == null || !current.is(expectedItem)) continue;

                if (progress >= recipe.time()) {
                    input.removeOne();
                    var outputItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(recipe.output()));
                    if (outputItem == null) return;

                    ItemStack outStack = new ItemStack(outputItem);
                    output.insert(outStack);

                    System.out.println("Crafting completato: " + outStack);
                    progress = 0;
                } else {
                    progress++;
                    if (progress % 20 == 0) {
                        System.out.println("Crafting... [" + progress + "/" + recipe.time() + "]");
                    }
                }
                break; // solo una ricetta per tick
            }
            break;
        }
    }
}
