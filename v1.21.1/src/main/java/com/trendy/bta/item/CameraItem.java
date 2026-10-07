package com.trendy.bta.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class CameraItem extends Item {
    public CameraItem(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            // Звук щелчка камеры
            level.playSound(null, player.blockPosition(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.PLAYERS, 1.0F, 1.5F);
            player.sendSystemMessage(Component.literal("§a[Камера] §fСнимок сохранен в галерею!"));
            // TODO: В будущем можно привязать сохранение скриншота через клиентские события
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
