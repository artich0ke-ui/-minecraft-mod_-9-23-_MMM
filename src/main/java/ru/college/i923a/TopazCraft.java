package ru.college.i923a;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TopazCraft implements ModInitializer {

	public static final Block TOPAZ_BLOCK = new Block(AbstractBlock.Settings.create().strength(3.0f).luminance(state -> 15));
	public static final Item TOPAZ_ITEM = new BlockItem(TOPAZ_BLOCK, new Item.Settings());

	@Override
	public void onInitialize() {
		Registry.register(Registries.BLOCK, Identifier.of("topazcraft", "topaz_block"), TOPAZ_BLOCK);
		Registry.register(Registries.ITEM, Identifier.of("topazcraft", "topaz_block"), TOPAZ_ITEM);
	}
}