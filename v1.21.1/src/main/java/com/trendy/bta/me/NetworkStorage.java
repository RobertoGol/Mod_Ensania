package com.trendy.bta.me;

import com.trendy.bta.TrendyBTA;
import net.neoforged.neoforge.items.IItemHandler;
import java.util.HashMap;
import java.util.Map;

public class NetworkStorage {
    private final MENetwork network;
    
    // Хранилище предметов
    private final Map<ItemStack, Integer> itemStorage = new HashMap<>();
    private final Map<ItemStack, Integer> itemRequests = new HashMap<>();
    
    // Энергия
    private double power = 0;
    private double maxPower = 1000000;
    
    public NetworkStorage(MENetwork network) {
        this.network = network;
    }
    
    public void addItem(ItemStack stack, int count) {
        // Добавляем предметы в хранилище сети
        int currentCount = itemStorage.getOrDefault(stack, 0) + count;
        itemStorage.put(stack, currentCount);
    }
    
    public boolean hasItem(ItemStack stack, int count) {
        return itemStorage.getOrDefault(stack, 0) >= count;
    }
    
    public int extractItem(ItemStack stack, boolean simulate) {
        int count = itemStorage.getOrDefault(stack, 0);
        if (count > 0) {
            if (simulate) return count;
            itemStorage.put(stack, 0);
            return count;
        }
        return 0;
    }
    
    public int insertItem(ItemStack stack, int count, boolean simulate) {
        int current = itemStorage.getOrDefault(stack, 0);
        int maxStack = stack.getMaxStackSize();
        int availableSpace = Math.max(0, maxStack - current);
        
        int inserted = Math.min(count, availableSpace);
        
        if (!simulate) {
            itemStorage.put(stack, current + inserted);
        }
        
        return inserted;
    }
    
    public void setPower(double power) {
        this.power = Math.max(0, Math.min(maxPower, power));
    }
    
    public double getPower() {
        return power;
    }
    
    public void addPower(double amount) {
        this.power = Math.max(0, Math.min(maxPower, this.power + amount));
    }
    
    public void tick() {
        // Тick-обновление хранилища
        // Можно добавить потерь энергии, регенерацию и т.д.
    }
}