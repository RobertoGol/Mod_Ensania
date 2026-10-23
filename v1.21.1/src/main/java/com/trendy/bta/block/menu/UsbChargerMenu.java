package com.trendy.bta.block.menu;
import com.trendy.bta.block.entity.UsbChargerBlockEntity;
import com.trendy.bta.registry.ModBlocks;
import com.trendy.bta.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
public class UsbChargerMenu extends AbstractContainerMenu {
    public final UsbChargerBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    public UsbChargerMenu(int id, Inventory playerInv, FriendlyByteBuf extraData) { this(id, playerInv, playerInv.player.level().getBlockEntity(extraData.readBlockPos())); }
    public UsbChargerMenu(int id, Inventory playerInv, BlockEntity entity) {
        super(ModMenuTypes.USB_CHARGER_MENU.get(), id);
        this.blockEntity = (UsbChargerBlockEntity) entity;
        this.levelAccess = ContainerLevelAccess.create(entity.getLevel(), entity.getBlockPos());
        int slotIndex = 0;
        for (int i = 0; i < 4; ++i) this.addSlot(new SlotItemHandler(blockEntity.inventory, slotIndex++, 12, 17 + (i * 18)));
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 11; ++col) this.addSlot(new SlotItemHandler(blockEntity.inventory, slotIndex++, 44 + (col * 18), 17 + (row * 18)));
        }
        this.addSlot(new SlotItemHandler(blockEntity.inventory, slotIndex, 220, 17)); // 37-й слот (Крипер)
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) this.addSlot(new Slot(playerInv, l + i * 9 + 9, 44 + l * 18, 140 + i * 18));
        }
        for (int i = 0; i < 9; ++i) this.addSlot(new Slot(playerInv, i, 44 + i * 18, 198));
    }
    @Override public ItemStack quickMoveStack(Player player, int index) { return ItemStack.EMPTY; }
    @Override public boolean stillValid(Player player) { return stillValid(this.levelAccess, player, ModBlocks.USB_CHARGER.get()); }
}
