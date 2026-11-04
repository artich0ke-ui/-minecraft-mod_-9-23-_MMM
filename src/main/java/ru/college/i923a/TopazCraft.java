package ru.college.i923a;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopazCraft implements ModInitializer {

    // Должно полностью совпадать с "id" в fabric.mod.json
    public static final String MOD_ID = "topazcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // ЛР-7: регистрация блока и предмета
        ModBlocks.initialize();

        // ЛР-9: блок появляется во вкладке «Строительные блоки» творческого инвентаря
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS)
                .register(entries -> entries.accept(ModBlocks.TOPAZ_BLOCK.asItem()));

        // ЛР-10: дополнительное поведение — ПКМ пустой рукой по топазовому блоку
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockState state = level.getBlockState(hitResult.getBlockPos());

            // Условие, отличающее наш блок от остальных, + пустая основная рука
            if (state.is(ModBlocks.TOPAZ_BLOCK)
                    && hand == InteractionHand.MAIN_HAND
                    && player.getItemInHand(hand).isEmpty()
                    && !player.isSpectator()) {

                // Сообщение только на сервере, иначе оно появилось бы дважды
                if (!level.isClientSide()) {
                    player.displayClientMessage(Component.translatable("message.topazcraft.topaz_used"), false);
                }
                return InteractionResult.SUCCESS;
            }

            // Все остальные блоки и ситуации обрабатываются игрой как обычно
            return InteractionResult.PASS;
        });

        LOGGER.info("TopazCraft загружен");
    }
}