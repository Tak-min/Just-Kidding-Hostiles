package com.example.orpheus.block;

import com.example.orpheus.blockentity.WallpaperBlockEntity;
import com.mojang.serialization.MapCodec; // これをインポート
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WallpaperBlock extends BaseEntityBlock {

    // ▼▼▼ この行を追加 ▼▼▼
    public static final MapCodec<WallpaperBlock> CODEC = simpleCodec(WallpaperBlock::new);

    public WallpaperBlock(Properties properties) {
        super(properties);
    }

    // ▼▼▼ このメソッドを追加 ▼▼▼
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WallpaperBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}