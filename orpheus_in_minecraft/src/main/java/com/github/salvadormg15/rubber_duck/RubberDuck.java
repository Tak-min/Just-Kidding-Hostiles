package com.github.tak-min.orpheus;

import com.github.tak-min.orpheus.client.render.curio.CurioRenderers;
import com.github.tak-min.orpheus.config.CommonConfigs;
import com.github.tak-min.orpheus.constants.CommonConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.tak-min.orpheus.core.ForgeEventHandler;
import com.github.tak-min.orpheus.core.Registries;
// The value here should match an entry in the META-INF/mods.toml file
@Mod("orpheus")
public class orpheus
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "orpheus";

    public orpheus() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	bus.addListener(this::setup);
    	bus.addListener(this::enqueue);
    	bus.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC, MODID+"-common.toml");

    	Registries.BLOCKS.register(bus);
    	Registries.ITEMS.register(bus);
    	Registries.SOUND_EVENTS.register(bus);
        bus.addListener(Registries::buildContents);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler.class);
    }

    private void setup(final FMLCommonSetupEvent event){
        CommonConstants.initConstants();
    }
    
    private void clientSetup(final FMLClientSetupEvent event){
    	if(FMLEnvironment.dist == Dist.CLIENT) {
            CurioRenderers.register();
    	}
    }
    
    private void enqueue(InterModEnqueueEvent event) {
        if (!ModList.get().isLoaded("curios")) {
            LOGGER.error("Cannot find Curios in modloading");
            return;
        }
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().cosmetic().build());
    }
}

   