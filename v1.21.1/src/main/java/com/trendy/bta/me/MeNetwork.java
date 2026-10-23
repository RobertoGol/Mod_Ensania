package com.trendy.bta.me;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.entity.*;
import net.neoforged.neoforge.items.IItemHandler;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

public class MENetwork {
    private final UUID networkId;
    private final Set<IMENode> nodes = new HashSet<>();
    private final NetworkStorage storage;
    private double powerBuffer = 0;
    private double powerCapacity = 1000000;
    private double powerNeededThisTick = 0;
    private int tickCounter = 0;
    
    // Simple autocrafting - recipes stored as output -> input items
    private final Map<ItemStack, List<ItemStack>> craftingRecipes = new HashMap<>();

    public MENetwork(UUID networkId) {
        this.networkId = networkId;
        this.storage = new NetworkStorage(this);
        // Register some default recipes (can be overridden)
        registerDefaultRecipes();
    }
    
    private void registerDefaultRecipes() {
        // Example: crafting a basic item from others
        // In practice, these would be loaded from config or defined by mods
    }

    public void addNode(IMENode node) {
        nodes.add(node);
        node.setNetwork(this);
    }

    public void removeNode(IMENode node) {
        nodes.remove(node);
        node.setNetwork(null);
    }

    public void tick() {
        tickCounter++;
        
        // Каждые 20 тиков (1 секунда) выполняем проверки
        if (tickCounter % 20 == 0) {
            checkAndCraft();
            updateStorage();
        }
        
        // Считаем энергопотребление
        double totalPowerDraw = 0;
        for (IMENode node : nodes) {
            if (node.getNetwork() == this) {
                totalPowerDraw += node.getPowerDraw();
            }
        }

        // Если энергии не хватает, некоторые блоки выключаются
        if (powerBuffer < totalPowerDraw) {
            powerNeededThisTick = totalPowerDraw - powerBuffer;
            powerBuffer = 0;
        } else {
            powerBuffer -= totalPowerDraw;
            powerNeededThisTick = 0;
        }

        // Обновляем хранилище
        storage.tick();
    }
    
    /** 
     * Check crafting recipes and attempt to craft items 
     */
    private void checkAndCraft() {
        // For each crafting recipe, check if we have the inputs and need the output
        for (var entry : craftingRecipes.entrySet()) {
            ItemStack output = entry.getKey();
            List<ItemStack> inputs = entry.getValue();
            
            // Check if we have space for the output
            if (!storage.hasItem(output, 1)) {
                // Check if we have all inputs
                boolean hasAllInputs = true;
                for (ItemStack input : inputs) {
                    if (!storage.hasItem(input, 1)) {
                        hasAllInputs = false;
                        break;
                    }
                }
                
                // If we have all inputs, craft the output
                if (hasAllInputs) {
                    // Remove inputs from storage
                    for (ItemStack input : inputs) {
                        storage.extractItem(input, true); // simulate first
                    }
                    // Add output to storage
                    storage.addItem(output, 1);
                    
                    // Notify nodes that crafting happened
                    for (IMENode node : nodes) {
                        node.onNetworkJoin(network); // simplified
                    }
                }
            }
        }
    }
    
    private void updateStorage() {
        // Collect items from all nodes with inventories
        for (IMENode node : nodes) {
            if (node.hasInventory()) {
                // Can add logic here to pull/push items between network and node inventories
            }
        }
    }

    public NetworkStorage getStorage() {
        return storage;
    }

    public double getPowerBuffer() {
        return powerBuffer;
    }

    public double getPowerCapacity() {
        return powerCapacity;
    }

    public UUID getNetworkId() {
        return networkId;
    }

    public boolean hasNode(IMENode node) {
        return nodes.contains(node);
    }

    public int getNodeCount() {
        return nodes.size();
    }
    
    /** Register a crafting recipe for the ME network */
    public void registerCraftingRecipe(ItemStack output, ItemStack... inputs) {
        craftingRecipes.put(output, Arrays.asList(inputs));
    }
}