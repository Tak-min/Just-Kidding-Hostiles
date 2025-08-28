package com.example.orpheus.blockentity;

import com.example.orpheus.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WallpaperBlockEntity extends BlockEntity {
    private String texturePath = "orpheus:textures/wallpaper/image1.png";
    private Direction face = Direction.NORTH;

    public WallpaperBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.WALLPAPER_BE.get(), pos, state);
    }

    // 読み込み処理 (load)
    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.loadAdditional(nbt, provider);
        this.texturePath = nbt.getString("TexturePath");
        this.face = Direction.from3DDataValue(nbt.getInt("Face"));
    }

    // 保存処理 (save)
    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);
        nbt.putString("TexturePath", this.texturePath);
        nbt.putInt("Face", this.face.get3DDataValue());
    }

    // --- ゲッターとセッター ---
    public ResourceLocation getTexture() {
        return ResourceLocation.tryParse(texturePath);
    }
    public void setTexturePath(String path) {
        this.texturePath = path;
        setChanged(); // 変更をワールドに通知
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // クライアントに更新パケットを送信
    }
    public Direction getFace() { return face; }
    public void setFace(Direction face) {
        this.face = face;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    // --- 同期処理 ---
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return saveWithFullMetadata(provider);
    }
}