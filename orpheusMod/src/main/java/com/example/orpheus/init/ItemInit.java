package com.example.orpheus.init;

import com.example.orpheus.orpheus;
import com.example.orpheus.item.WallpaperBrushItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObgect;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, orpheus.MOD_ID);

    public static final RegistryObject<Item> WALLPAPER_BRUSH = ITEMS.register("wallpaper_brush",
            () -> new WallpaperBrushItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}