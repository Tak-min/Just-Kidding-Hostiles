package com.example.orpheus.init;

import com.example.orpheus.item.WallpaperBrushItem;
import com.example.orpheus.orpheus; // orpheusクラスをインポート
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, orpheus.MOD_ID);

    public static final RegistryObject<Item> WALLPAPER_BRUSH = ITEMS.register("wallpaper_brush",
            () -> new WallpaperBrushItem(new Item.Properties())); // .tab() を削除

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}