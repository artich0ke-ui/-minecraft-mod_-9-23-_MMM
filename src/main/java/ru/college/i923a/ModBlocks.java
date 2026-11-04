package ru.college.i923a;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Класс регистрации (ЛР-7). Здесь создаётся блок и его предмет.
 * Идентификатор блока: topazcraft:topaz_block
 *
 * Проект использует официальные названия классов Mojang, поэтому:
 * Identifier -> ResourceLocation, AbstractBlock.Settings -> BlockBehaviour.Properties,
 * Registries -> BuiltInRegistries, Item.Settings -> Item.Properties.
 */
public class ModBlocks {

    // Блок «Топазовый блок». Настройки скопированы от железного блока.
    public static final Block TOPAZ_BLOCK = register(
            "topaz_block",
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(3.0f, 6.0f)            // прочность 3, сопротивление взрыву 6
                    .lightLevel(state -> 8)          // светится уровнем 8 из 15
                    .requiresCorrectToolForDrops())  // без кирки предмет не выпадает
    );

    /**
     * Регистрирует блок и BlockItem под одним идентификатором.
     * BlockItem нужен, чтобы блок можно было держать в руке и ставить.
     */
    private static Block register(String name, Block block) {
        ResourceLocation id = new ResourceLocation(TopazCraft.MOD_ID, name);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    // Пустой метод: его вызов в точке входа заставляет JVM загрузить класс
    // и выполнить регистрацию статических полей.
    public static void initialize() {
    }
}
