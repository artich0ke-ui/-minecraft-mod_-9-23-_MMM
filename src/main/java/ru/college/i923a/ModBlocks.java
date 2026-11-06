package ru.college.i923a;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ModBlocks {

    public static final Block TOPAZ_BLOCK = register(
            "topaz_block",
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(3.0f, 6.0f)
                    .lightLevel(state -> 8)
                    .requiresCorrectToolForDrops())
    );

    // Создаем блок с кастомным тултипом
    public static final Block GLOWING_CRYSTAL = register(
            "glowing_crystal",
            new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                    .strength(1.5f, 3.0f)
                    .lightLevel(state -> 15)
                    .requiresCorrectToolForDrops()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable net.minecraft.world.level.BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.literal("Светящийся кристалл тихо мерцает.").withStyle(net.minecraft.ChatFormatting.AQUA));
                    super.appendHoverText(stack, level, tooltip, flag);
                }
            }
    );

    private static Block register(String name, Block block) {
        ResourceLocation id = new ResourceLocation(TopazCraft.MOD_ID, name);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {}
}