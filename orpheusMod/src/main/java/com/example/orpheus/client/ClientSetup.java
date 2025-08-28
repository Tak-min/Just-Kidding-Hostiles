package com.example.orpheus.client;

import com.example.orpheus.client.renderer.WallpaperBlockEntityRenderer;
import com.example.orpheus.init.BlockEntityInit;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(BlockEntityInit.WALLPAPER_BE.get(), WallpaperBlockEntityRenderer::new);
        });
    }
}