package com.example.orpheus;

import com.example.orpheus.client.ClientSetup;
import com.example.orpheus.init.BlockEntityInit;
import com.example.orpheus.init.BlockInit;
import com.example.orpheus.init.ItemInit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(orpheus.MOD_ID)
public class orpheus {
    public static final String MOD_ID = "orpheus";

    public orpheus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 各要素を登録
        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        BlockEntityInit.register(modEventBus);

        // クライアント側の設定を登録
        modEventBus.addListener(ClientSetup::init);
        // クリエイティブタブ登録イベントをリッスン
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    // クリエイティブタブにアイテムを追加するメソッド
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // 壁紙ブラシを「道具」タブに追加
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ItemInit.WALLPAPER_BRUSH);
        }
        // 壁紙ブロックを「建材ブロック」タブに追加
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BlockInit.WALLPAPER_BLOCK);
        }
    }
}