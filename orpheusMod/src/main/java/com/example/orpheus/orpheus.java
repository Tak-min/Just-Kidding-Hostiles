package com.example.orpheus;

import com.example.orpheus.client.clientSetup;
import com.example.orpheus.init.BlockEntityInit;
import com.example.orpheus.init.BlockInit;
import com.example.orpheus.init.ItemInit;
import net.minecraftforge.common.Minecraftforge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(orpheus.orpheus)
public class orpheus{
    public static final String MOD_ID = "orpheus";

    public orpheus(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        BlockEntityInit.register(modEventBus);

        modEventBus.addListener(ClientSetup::init);

        MinecraftForge.EVENT_BUS.register(this);
    }
} 