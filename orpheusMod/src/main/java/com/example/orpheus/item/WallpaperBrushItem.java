package com.example.orpheus.item;

import com.example.orpheus.blockentity.WallpaperBlockEntity;
import com.example.orpheus.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WallpaperBrushItem extends Item {
    public WallpaperBrushItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            BlockPos clickedPos = context.getClickedPos();
            Direction face = context.getClickedFace();
            BlockPos placePos = clickedPos.relative(face);

            if (level.getBlockState(placePos).canBeReplaced()) {
                level.setBlock(placePos, BlockInit.WALLPAPER_BLOCK.get().defaultBlockState(), 3);
                BlockEntity be = level.getBlockEntity(placePos);

                if (be instanceof WallpaperBlockEntity wallpaperBE) {
                    CompoundTag nbt = context.getItemInHand().getOrCreateTag();
                    String texturePath = "orpheus:textures/wallpaper/image1.png"; // デフォルト画像

                    if (nbt.contains("texture")) {
                        texturePath = nbt.getString("texture");
                    }
                    
                    wallpaperBE.setFace(face);
                    wallpaperBE.setTexturePath(texturePath);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}