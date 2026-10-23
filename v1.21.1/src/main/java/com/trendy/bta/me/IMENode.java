package com.trendy.bta.me;

import net.neoforged.neoforge.items.IItemHandler;
import java.util.UUID;

public interface IMENode {
    UUID getNetworkId();
    
    double getPowerDraw();
    
    void setNetwork(MENetwork network);
    
    void onNetworkJoin(MENetwork network);
    
    void onNetworkLeave();
    
    // Method to get inventory if the node has one
    default IItemHandler getInventory() {
        return null;
    }
    
    // Method to check if node has inventory
    default boolean hasInventory() {
        return false;
    }
}