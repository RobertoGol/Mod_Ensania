package com.trendy.bta.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class ArrowBagItem extends Item {
    public ArrowBagItem(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            // TODO: Открытие кастомного инвентаря на 3 стака стрел
            player.sendSystemMessage(Component.literal("§e[Сумка для стрел] §fИнтерфейс сумки скоро будет добавлен."));
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
