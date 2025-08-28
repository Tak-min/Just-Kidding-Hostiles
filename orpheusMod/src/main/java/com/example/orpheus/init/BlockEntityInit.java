package com.example.orpheus.init;

import com.example.orpheus.orpheus;
import com.example.orpheus.blockentity.WallpaperBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, orpheus.MOD_ID);

    public static final RegistryObject<BlockEntityType<WallpaperBlockEntity>> WALLPAPER_BE = BLOCK_ENTITIES.register("wallpaper_be",
            () -> BlockEntityType.Builder.of(WallpaperBlockEntity::new, BlockInit.WALLPAPER_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}