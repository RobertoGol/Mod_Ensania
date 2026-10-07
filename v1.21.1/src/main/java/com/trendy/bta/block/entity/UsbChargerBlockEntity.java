package com.trendy.bta.block.entity;
import com.trendy.bta.block.menu.UsbChargerMenu;
import com.trendy.bta.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
public class UsbChargerBlockEntity extends BlockEntity implements MenuProvider {
    public boolean hasGivenKit = false;
    public boolean isDimensionalLinkActive = false; 
    public final ItemStackHandler inventory = new ItemStackHandler(38) {
        @Override protected void onContentsChanged(int slot) { 
            setChanged(); 
            if (slot == 37) checkDimensionalLink();
        }
    };
    public UsbChargerBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.USB_CHARGER_BE.get(), pos, state); }
    private void checkDimensionalLink() {
        boolean hasActivator = inventory.getStackInSlot(37).getItem() == Items.NETHER_STAR;
        if (hasActivator && !isDimensionalLinkActive) this.isDimensionalLinkActive = true;
        else if (!hasActivator && isDimensionalLinkActive) this.isDimensionalLinkActive = false;
    }
    @Override public Component getDisplayName() { return Component.translatable("block.trendybta.usb_charger"); }
    @Override public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) { return new UsbChargerMenu(id, playerInventory, this); }
    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putBoolean("HasGivenKit", hasGivenKit);
        tag.putBoolean("LinkActive", isDimensionalLinkActive);
    }
    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        hasGivenKit = tag.getBoolean("HasGivenKit");
        isDimensionalLinkActive = tag.getBoolean("LinkActive");
    }
}
