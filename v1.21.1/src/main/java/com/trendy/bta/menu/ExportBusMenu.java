package com.trendy.bta.menu;

import com.trendy.bta.block.entity.ExportBusBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ExportBusMenu extends AbstractContainerMenu {
    private final ExportBusBlockEntity blockEntity;
    private final int containerId;

    public ExportBusMenu(int id, Player.playerInventory inventory, ExportBusBlockEntity be) {
        super(null, id, inventory, be);
        this.containerId = id;
        this.blockEntity = be;
        for (int i = 0; i < be.inventory.getCapacities(); i++) {
            addSlot(new Slot(be.inventory, i, 8 + (i % 4) * 18, 18 + (i / 4) * 18));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}