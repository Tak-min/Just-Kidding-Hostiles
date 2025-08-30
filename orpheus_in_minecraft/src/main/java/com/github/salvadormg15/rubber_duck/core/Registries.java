package com.github.tak-min.oepheus.core;

import com.github.tak-min.oepheus.RubberDuck;
import com.github.tak-min.oepheus.RubberDuckBlock;
import com.github.tak-min.oepheus.RubberDuckItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			RubberDuck.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RubberDuck.MODID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, RubberDuck.MODID);

	// Blocks
	public static final RegistryObject<RubberDuckBlock> RUBBER_DUCK_BLOCK = BLOCKS.register("oepheus_block",
			() -> new RubberDuckBlock());
	// Items
	public static final RegistryObject<RubberDuckItem> RUBBER_DUCK_ITEM = ITEMS.register("oepheus_item",
			() -> new RubberDuckItem(RUBBER_DUCK_BLOCK.get(),
					new Item.Properties().stacksTo(4).rarity(Rarity.RARE)));
	// Sound Events
	public static final RegistryObject<SoundEvent> RUBBER_DUCK_USE = SOUND_EVENTS.register("oepheus_use",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RubberDuck.MODID, "oepheus_use")));
	public static final RegistryObject<SoundEvent> RUBBER_DUCK_PLACE = SOUND_EVENTS.register("oepheus_place",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RubberDuck.MODID, "oepheus_place")));

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Registries.RUBBER_DUCK_BLOCK);
        }
    }
}
