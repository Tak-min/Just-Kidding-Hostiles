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
import org.joml.Matrix4f;

public class WallpaperBlockEntityRenderer implements BlockEntityRenderer<WallpaperBlockEntity> {
    public WallpaperBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(WallpaperBlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction face = be.getFace();
        if (face == null) return;

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entitySolid(be.getTexture()));
        poseStack.pushPose();

        Matrix4f matrix = poseStack.last().pose();

        // Z fighting(描画のちらつき)を避けるための微小なオフセット
        float offset = 0.0005f;

        // 全ての座標値をfloat型で定義
        float x0 = 0.0f;
        float x1 = 1.0f;
        float y0 = 0.0f;
        float y1 = 1.0f;
        float z0 = 0.0f;
        float z1 = 1.0f;


        // 頂点を定義して面を描画
        switch (face) {
            case DOWN:
                consumer.addVertex(matrix, x0, y0 + offset, z0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0);
                consumer.addVertex(matrix, x0, y0 + offset, z1).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0);
                consumer.addVertex(matrix, x1, y0 + offset, z1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0);
                consumer.addVertex(matrix, x1, y0 + offset, z0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, -1, 0);
                break;
            case UP:
                consumer.addVertex(matrix, x0, y1 - offset, z1).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0);
                consumer.addVertex(matrix, x0, y1 - offset, z0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0);
                consumer.addVertex(matrix, x1, y1 - offset, z0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0);
                consumer.addVertex(matrix, x1, y1 - offset, z1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 1, 0);
                break;
            case NORTH:
                consumer.addVertex(matrix, x1, y1, z0 + offset).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1);
                consumer.addVertex(matrix, x1, y0, z0 + offset).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1);
                consumer.addVertex(matrix, x0, y0, z0 + offset).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1);
                consumer.addVertex(matrix, x0, y1, z0 + offset).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, -1);
                break;
            case SOUTH:
                consumer.addVertex(matrix, x0, y1, z1 - offset).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1);
                consumer.addVertex(matrix, x0, y0, z1 - offset).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1);
                consumer.addVertex(matrix, x1, y0, z1 - offset).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1);
                consumer.addVertex(matrix, x1, y1, z1 - offset).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0, 0, 1);
                break;
            case WEST:
                consumer.addVertex(matrix, x0 + offset, y1, z0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(-1, 0, 0);
                consumer.addVertex(matrix, x0 + offset, y0, z0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(-1, 0, 0);
                consumer.addVertex(matrix, x0 + offset, y0, z1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(-1, 0, 0);
                consumer.addVertex(matrix, x0 + offset, y1, z1).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(-1, 0, 0);
                break;
            case EAST:
                consumer.addVertex(matrix, x1 - offset, y1, z1).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0);
                consumer.addVertex(matrix, x1 - offset, y0, z1).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0);
                consumer.addVertex(matrix, x1 - offset, y0, z0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0);
                consumer.addVertex(matrix, x1 - offset, y1, z0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0);
                break;
        }

        poseStack.popPose();
    }
}