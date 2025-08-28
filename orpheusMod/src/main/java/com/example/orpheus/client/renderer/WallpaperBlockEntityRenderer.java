package com.example.orpheus.client.renderer;

import com.example.orpheus.blockentity.WallpaperBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import org.joml.Quaternionf;

public class WallpaperBlockEntityRenderer implements BlockEntityRenderer<WallpaperBlockEntity> {
    public WallpaperBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(WallpaperBlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        Direction face = be.getFace();

        // 描画位置をブロックの中心に移動
        poseStack.translate(0.5, 0.5, 0.5);
        
        // ▼▼▼ 回転処理をこちらに修正 ▼▼▼
        poseStack.mulPose(face.getRotation());
        // ▲▲▲ ここまで ▲▲▲
        
        // 描画位置を戻す
        poseStack.translate(-0.5, -0.5, -0.5);

        // Z fighting を避けるために少しだけオフセット
        float offset = -0.0005f; // 少し値を調整
        poseStack.translate(face.getStepX() * offset, face.getStepY() * offset, face.getStepZ() * offset);

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entitySolid(be.getTexture()));

        // 四角形を描画
        float x0 = 0, x1 = 1;
        float y0 = 0, y1 = 1;
        float z = 0.0f; // 描画面をブロックの面に合わせる

        // 面の向きに応じて描画する頂点を変える
        if (face == Direction.UP) {
            z = 1.0f;
            consumer.vertex(poseStack.last().pose(), x0, z, x1).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, z, x1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, z, x0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x0, z, x0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0).endVertex();
        } else if (face == Direction.DOWN) {
            consumer.vertex(poseStack.last().pose(), x0, z, x0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, z, x0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, z, x1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0).endVertex();
            consumer.vertex(poseStack.last().pose(), x0, z, x1).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0).endVertex();
        } else { // NORTH, SOUTH, WEST, EAST
            consumer.vertex(poseStack.last().pose(), x0, y0, 0.5f).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, y0, 0.5f).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1).endVertex();
            consumer.vertex(poseStack.last().pose(), x1, y1, 0.5f).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1).endVertex();
            consumer.vertex(poseStack.last().pose(), x0, y1, 0.5f).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1).endVertex();
        }

        poseStack.popPose();
    }
}