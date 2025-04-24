package com.blake.skyresourcereborn.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemOutputBE extends BlockEntity {

    private final ItemStackHandler inventory = new ItemStackHandler(1);
    private final LazyOptional<IItemHandler> lazyInventory = LazyOptional.of(() -> inventory);

    public ItemOutputBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ITEM_OUTPUT_BE.get(), pos, state);
    }

    public void insert(ItemStack stack) {
        inventory.insertItem(0, stack, false);
    }

    public ItemStack getStack() {
        return inventory.getStackInSlot(0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return lazyInventory.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyInventory.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
    }
}
