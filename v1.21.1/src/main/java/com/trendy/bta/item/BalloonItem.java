package com.trendy.bta.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BalloonItem extends Item {
    public BalloonItem(Properties properties) { super(properties); }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level().isClientSide) {
            // Моб улетает вверх на 10 секунд
            target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 1));
            stack.shrink(1); // Тратим шарик
        }
        return InteractionResult.SUCCESS;
    }
}
