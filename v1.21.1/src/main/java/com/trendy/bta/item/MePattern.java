package com.trendy.bta.item;

import com.trendy.bta.TrendyBTA;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MePattern extends Item {
    public MePattern() {
        super(new Item.Properties().stacksTo(64).tab(TrendyBTA.tab));
    }
}