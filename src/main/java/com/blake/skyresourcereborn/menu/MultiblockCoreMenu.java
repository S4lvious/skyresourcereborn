package com.blake.skyresourcereborn.menu;

import com.blake.skyresourcereborn.blockentity.ItemInputBE;
import com.blake.skyresourcereborn.blockentity.ItemOutputBE;
import com.blake.skyresourcereborn.blockentity.MultiblockCoreBE;
import com.blake.skyresourcereborn.multiblock.MultiblockManager;
import com.blake.skyresourcereborn.multiblock.MultiblockStructure;
import com.blake.skyresourcereborn.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class MultiblockCoreMenu extends AbstractContainerMenu {

    public final MultiblockCoreBE blockEntity;
    private final ContainerData data;

    public MultiblockCoreMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(id, playerInventory, playerInventory.player.level(), extraData.readBlockPos());
    }

    public MultiblockCoreMenu(int id, Inventory playerInventory, Level level, BlockPos pos) {
        super(ModMenus.MULTIBLOCK_CORE_MENU.get(), id);
        this.blockEntity = (MultiblockCoreBE) level.getBlockEntity(pos);
        this.data = blockEntity.getDataAccess();
        this.addDataSlots(data);

        // Trova input e output block entities dai dati multiblock
        for (MultiblockStructure structure : MultiblockManager.STRUCTURES.values()) {
            boolean valid = structure.structure().stream().allMatch(offset -> {
                BlockPos checkPos = pos.offset(offset.offset());
                var state = level.getBlockState(checkPos);
                return state != null && state.getBlock().builtInRegistryHolder().key().location().equals(offset.blockId());
            });

            if (!valid) continue;

            BlockPos inputPos = structure.structure().stream()
                    .filter(b -> b.blockId().getPath().equals("item_input"))
                    .findFirst().map(b -> pos.offset(b.offset())).orElse(null);

            BlockPos outputPos = structure.structure().stream()
                    .filter(b -> b.blockId().getPath().equals("item_output"))
                    .findFirst().map(b -> pos.offset(b.offset())).orElse(null);

            if (inputPos != null) {
                BlockEntity inputBE = level.getBlockEntity(inputPos);
                if (inputBE instanceof ItemInputBE input) {
                    input.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 0, 40, 28)); // input
                    });
                }
            }

            if (outputPos != null) {
                BlockEntity outputBE = level.getBlockEntity(outputPos);
                if (outputBE instanceof ItemOutputBE output) {
                    output.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 0, 120, 28)); // output
                    });
                }
            }

            break; // usa solo la prima struttura valida
        }

        // Inventario giocatore
        int startX = 8;
        int startY = 84;
        int slotSize = 18;

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        startX + col * slotSize,
                        startY + row * slotSize));
            }
        }

        int hotbarY = startY + slotSize * 3 + 2; // = 76 + 54 + 2 = 132
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory,
                    i,
                    startX + i * slotSize,
                    hotbarY));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    public int getProgress() {
        return data.get(0);
    }

    public int getMaxProgress() {
        return data.get(1);
    }
}
