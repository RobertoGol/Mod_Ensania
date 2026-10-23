package com.trendy.bta.me;
import net.neoforged.neoforge.items.IItemHandler;
import java.util.UUID;
public interface IMENode {
    UUID getNetworkId();
    double getPowerDraw();
    void setNetwork(MENetwork network);
    void onNetworkJoin(MENetwork network);
    void onNetworkLeave();
    default IItemHandler getInventory() { return null; }
    default boolean hasInventory() { return false; }
}
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/me/MENetwork.java`

**код файла:**
```java
package com.trendy.bta.me;
import com.trendy.bta.TrendyBTA;
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
    private final java.util.Map<ItemStack,java.util.List<ItemStack>> craftingRecipes = new java.util.HashMap<>();
    public MENetwork(UUID networkId) { this.networkId = networkId; this.storage = new NetworkStorage(this); }
    public void addNode(IMENode node) { nodes.add(node); node.setNetwork(this); }
    public void removeNode(IMENode node) { nodes.remove(node); node.setNetwork(null); }
    public void tick() {
        tickCounter++;
        if(tickCounter%20==0) { checkAndCraft(); updateStorage(); }
        double totalDraw = 0;
        for(IMENode n : nodes) if(n.getNetwork()==this) totalDraw+=n.getPowerDraw();
        if(powerBuffer<totalDraw) { powerNeededThisTick=totalDraw-powerBuffer; powerBuffer=0; }
        else { powerBuffer-=totalDraw; powerNeededThisTick=0; }
        storage.tick();
    }
    private void checkAndCraft() {
        for(var e:craftingRecipes.entrySet()) {
            ItemStack out=e.getKey(); java.util.List<ItemStack> in=e.getValue();
            if(!storage.hasItem(out,1)) {
                boolean ok=true;
                for(ItemStack i:in) if(!storage.hasItem(i,1)){ok=false;break;}
                if(ok){ for(ItemStack i:in) storage.extractItem(i,true); storage.addItem(out,1); }
            }
        }
    }
    private void updateStorage() { for(IMENode n:nodes) if(n.hasInventory()){/*pull/push*/} }
    public NetworkStorage getStorage(){return storage;}
    public double getPowerBuffer(){return powerBuffer;}
    public double getPowerCapacity(){return powerCapacity;}
    public UUID getNetworkId(){return networkId;}
    public boolean hasNode(IMENode n){return nodes.contains(n);}
    public int getNodeCount(){return nodes.size();}
    public void registerCraftingRecipe(ItemStack out, ItemStack...in){ craftingRecipes.put(out,java.util.Arrays.asList(in)); }
}
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/me/NetworkStorage.java`

**код файла:**
```java
package com.trendy.bta.me;
import net.neoforged.neoforge.items.IItemHandler;
import java.util.HashMap;
import java.util.Map;
public class NetworkStorage {
    private final MENetwork network;
    private final Map<ItemStack,Integer> itemStorage=new HashMap<>();
    private double power=0,maxPower=1000000;
    public NetworkStorage(MENetwork n){this.network=n;}
    public void addItem(ItemStack s,int c){ItemStack ss=s.copy(); itemStorage.put(ss,itemStorage.getOrDefault(ss,0)+c); }
    public boolean hasItem(ItemStack s,int c){ItemStack ss=s.copy();return itemStorage.getOrDefault(ss,0)>=c; }
    public int extractItem(ItemStack s,boolean sim){ItemStack ss=s.copy();int cur=itemStorage.getOrDefault(ss,0);if(cur>0&&!sim)itemStorage.put(ss,0);return cur; }
    public int insertItem(ItemStack s,int c,boolean sim){ItemStack ss=s.copy();int cur=itemStorage.getOrDefault(ss,0);int max=ss.getMaxStackSize();int space=Math.max(0,max-cur);int ins=Math.min(c,space);if(!sim)itemStorage.put(ss,cur+ins);return ins; }
    public void setPower(double p){this.power=Math.max(0,Math.min(maxPower,p));}
    public double getPower(){return power;}
    public void addPower(double a){this.power=Math.max(0,Math.min(maxPower,this.power+a));}
    public void tick(){/*default*/}
}
____________________