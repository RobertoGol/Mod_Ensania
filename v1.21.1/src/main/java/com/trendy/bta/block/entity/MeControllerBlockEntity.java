package com.trendy.bta.block.entity;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
import com.trendy.bta.me.MENetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class MeControllerBlockEntity extends BlockEntity implements IMENode {
    public final ItemStackHandler inventory = new ItemStackHandler(16) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    
    private MENetwork network;
    private final UUID networkId = UUID.randomUUID();
    
    public MeControllerBlockEntity(BlockPos pos, BlockState state) {
        super(/* ModBlockEntities.ME_CONTROLLER_BE.get() */ null, pos, state);
    }
    
    @Override
    public UUID getNetworkId() {
        return networkId;
    }
    
    @Override
    public double getPowerDraw() {
        return 5.0;
    }
    
    @Override
    public void setNetwork(MENetwork network) {
        this.network = network;
        if (network != null) {
            network.addNode(this);
        }
    }
    
    @Override
    public MENetwork getNetwork() {
        return network;
    }
    
    @Override
    public boolean hasInventory() {
        return true;
    }
    
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.trendybta.me_controller");
    }
    
    @Override
    public AbstractContainerMenu createMenu(int id, Player.playerInventory inventory, Player player) {
        return new MeControllerMenu(id, inventory, this);
    }
}