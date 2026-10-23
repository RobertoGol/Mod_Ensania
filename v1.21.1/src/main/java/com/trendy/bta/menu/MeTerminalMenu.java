package com.trendy.bta.menu;

import com.trendy.bta.block.entity.MeTerminalBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MeTerminalMenu extends AbstractContainerMenu {
    private final MeTerminalBlockEntity blockEntity;
    private final int containerId;

    public MeTerminalMenu(int id, Player.playerInventory inventory, MeTerminalBlockEntity be) {
        super(null, id, inventory, be);
        this.containerId = id;
        this.blockEntity = be;
        // Добавляем слоты
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
