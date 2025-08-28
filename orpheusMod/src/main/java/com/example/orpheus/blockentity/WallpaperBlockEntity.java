package com.example.orpheus.blockentity;

import com.example.orpheus.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WallpaperBlockEntity extends BlockEntity {
    private String texturePath = "orpheus:textures/wallpaper/image1.png"; // MOD IDを合わせる
    private Direction face = Direction.NORTH;

    public WallpaperBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.WALLPAPER_BE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putString("TexturePath", this.texturePath);
        nbt.putInt("Face", this.face.get3DDataValue());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.texturePath = nbt.getString("TexturePath");
        this.face = Direction.from3DDataValue(nbt.getInt("Face"));
    }

    public ResourceLocation getTexture() { return new ResourceLocation(texturePath); }
    public void setTexturePath(String path) {
        this.texturePath = path;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }
    public Direction getFace() { return face; }
    public void setFace(Direction face) {
        this.face = face;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    // --- 同期処理 ---
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}