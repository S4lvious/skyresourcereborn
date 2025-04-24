package com.blake.skyresourcereborn.block;

import com.blake.skyresourcereborn.blockentity.ItemOutputBE;
import com.blake.skyresourcereborn.menu.ItemOutputMenu;
import com.blake.skyresourcereborn.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ItemOutputBlock extends Block implements EntityBlock {

    public ItemOutputBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.0f));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            var be = level.getBlockEntity(pos);
            if (be instanceof ItemOutputBE) {
                MenuProvider container = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Item Output");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory inv, Player player) {
                        return new ItemOutputMenu(id, inv, level, pos);
                    }
                };
                NetworkHooks.openScreen(serverPlayer, container, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public ItemOutputBE newBlockEntity(BlockPos pos, BlockState state) {
        return new ItemOutputBE(pos, state);
    }
}
