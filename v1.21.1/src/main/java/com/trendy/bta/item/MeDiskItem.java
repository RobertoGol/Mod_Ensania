package com.trendy.bta.item;

import com.trendy.bta.TrendyBTA;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MeDiskItem extends Item {
    public MeDiskItem() {
        super(new Item.Properties().stacksTo(64).tab(TrendyBTA.tab));
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level level, net.minecraft.world.entity.Entity entity) {
        // ME Disk - можно использовать для сохранения данных сети
        return stack;
    }
}