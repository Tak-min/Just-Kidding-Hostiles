package com.example.orpheus.client.renderer;

import com.example.orpheus.blockentity.WallpaperBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;

public class WallpaperBlockEntityRenderer implements BlockEntityRenderer<WallpaperBlockEntity> {
    public WallpaperBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(WallpaperBlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose(); // 現在の行列を保存

        Direction face = be.getFace();

        // 描画が裏返らないように、面の向きに応じて少しだけ内側にずらす
        float offset = 0.001f;
        poseStack.translate(face.getStepX() * offset, face.getStepY() * offset, face.getStepZ() * offset);
        
        // 面の向きに合わせて画像を回転させる
        poseStack.translate(0.5, 0.5, 0.5); // 中心に移動
        poseStack.mulPose(face.getRotation());       // 面の向きに回転
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180)); // 面の内側を向くように調整
        poseStack.translate(-0.5, -0.5, -0.5); // 元の位置に戻す

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.solid());

        // 四角形を描画
        // 引数: (Pose, x, y, z, r, g, b, a, u, v, overlay, light, normalX, normalY, normalZ)
        float x0 = 0, x1 = 1;
        float y0 = 0, y1 = 1;
        float z = 0.5f; // Z fighting を避けるため、ブロックの面にぴったり付けない
        
        // 頂点1 (左下)
        consumer.vertex(poseStack.last().pose(), x0, y0, z).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1).endVertex();
        // 頂点2 (右下)
        consumer.vertex(poseStack.last().pose(), x1, y0, z).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1).endVertex();
        // 頂点3 (右上)
        consumer.vertex(poseStack.last().pose(), x1, y1, z).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1).endVertex();
        // 頂点4 (左上)
        consumer.vertex(poseStack.last().pose(), x0, y1, z).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1).endVertex();
        
        poseStack.popPose(); // 保存した行列を復元
    }
}