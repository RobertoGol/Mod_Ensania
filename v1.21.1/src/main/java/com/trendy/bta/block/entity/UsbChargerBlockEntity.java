package com.trendy.bta.block.entity;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
import com.trendy.bta.me.MENetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class UsbChargerBlockEntity extends BlockEntity implements MenuProvider, IMENode {
    public boolean hasGivenKit = false;
    public boolean isDimensionalLinkActive = false;
    
    // ME Network integration
    private MENetwork network;
    
    public final ItemStackHandler inventory = new ItemStackHandler(38) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            // Когда инвентарь меняется, обновляем сеть
            if (slot == 37) {
                checkDimensionalLink();
                if (network != null) {
                    network.addNode(UsbChargerBlockEntity.this);
                }
            }
        }
    };
    
    public UsbChargerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.USB_CHARGER_BE.get(), pos, state);
    }
    
    public void setNetwork(MENetwork network) {
        this.network = network;
        if (network != null) {
            network.addNode(this);
        }
    }
    
    public MENetwork getNetwork() {
        return network;
    }
    
    public void removeNetwork() {
        this.network = null;
    }
    
    private void checkDimensionalLink() {
        boolean hasActivator = inventory.getStackInSlot(37).getItem() == Items.NETHER_STAR;
        if (hasActivator && !isDimensionalLinkActive) this.isDimensionalLinkActive = true;
        else if (!hasActivator && isDimensionalLinkActive) this.isDimensionalLinkActive = false;
    }
    
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.trendybta.usb_charger");
    }
    
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new UsbChargerMenu(id, playerInventory, this);
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", inventory.serializeNBT());
        tag.putBoolean("HasGivenKit", hasGivenKit);
        tag.putBoolean("LinkActive", isDimensionalLinkActive);
        
        // Сохраняем ME network ID если есть
        if (network != null) {
            tag.putString("MENetworkId", network.getNetworkId().toString());
        }
    }
    
    @Override
    protected void loadAdditional(CompoundTag tag) {
        super.loadAdditional(tag);
        inventory.deserializeNBT(tag.getCompound("inventory"));
        hasGivenKit = tag.getBoolean("HasGivenKit");
        isDimensionalLinkActive = tag.getBoolean("LinkActive");
        
        // Восстанавливаем ME сеть
        if (tag.contains("MENetworkId")) {
            // network = MENetworkManager.getNetwork(UUID.fromString(tag.getString("MENetworkId")));
        }
    }
    
    // IMENode implementation
    @Override
    public UUID getNetworkId() {
        return network != null ? network.getNetworkId() : UUID.randomUUID();
    }
    
    @Override
    public double getPowerDraw() {
        // USB Charger consumes power when active
        return isDimensionalLinkActive ? 5.0 : 0.0;
    }
}

