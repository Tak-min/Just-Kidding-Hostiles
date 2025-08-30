package com.github.tak-min.oepheus.core;

import com.github.tak-min.oepheus.orpheus;
import com.github.tak-min.oepheus.orpheusBlock;
import com.github.tak-min.oepheus.orpheusItem;

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
			orpheus.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, orpheus.MODID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, orpheus.MODID);

	// Blocks
	public static final RegistryObject<orpheusBlock> RUBBER_DUCK_BLOCK = BLOCKS.register("oepheus_block",
			() -> new orpheusBlock());
	// Items
	public static final RegistryObject<orpheusItem> RUBBER_DUCK_ITEM = ITEMS.register("oepheus_item",
			() -> new orpheusItem(RUBBER_DUCK_BLOCK.get(),
					new Item.Properties().stacksTo(4).rarity(Rarity.RARE)));
	// Sound Events
	public static final RegistryObject<SoundEvent> RUBBER_DUCK_USE = SOUND_EVENTS.register("oepheus_use",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(orpheus.MODID, "oepheus_use")));
	public static final RegistryObject<SoundEvent> RUBBER_DUCK_PLACE = SOUND_EVENTS.register("oepheus_place",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(orpheus.MODID, "oepheus_place")));

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Registries.RUBBER_DUCK_BLOCK);
        }
    }
}
