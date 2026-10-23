package com.trendy.bta.menu;

import com.trendy.bta.block.entity.MeControllerBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MeControllerMenu extends AbstractContainerMenu {
    private final MeControllerBlockEntity blockEntity;
    private final int containerId;

    public MeControllerMenu(int id, Player.playerInventory inventory, MeControllerBlockEntity be) {
        super(null, id, inventory, be);
        this.containerId = id;
        this.blockEntity = be;
        // Добавляем слоты из inventory
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