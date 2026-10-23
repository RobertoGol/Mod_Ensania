## **5. MENUS** (15 files)
**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/menu/QuarryMenu.java`

**код файла:**
```java
package com.trendy.bta.menu;
import com.trendy.bta.block.entity.QuarryBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
public class QuarryMenu extends AbstractContainerMenu {
    private final QuarryBlockEntity blockEntity;
    private final int containerId;
    public QuarryMenu(int id, Player playerInventory, QuarryBlockEntity be) {
        super(null, id, playerInventory, be);
        this.containerId = id;
        this.blockEntity = be;
        for (int i = 0; i < be.inventory.getCapacities(); i++) {
            addSlot(new Slot(be.inventory, i, 8 + (i % 4) * 18, 18 + (i / 4) * 18));
        }
    }
    @Override public boolean stillValid(Player player) { return true; }
    @Override public ItemStack quickMoveStack(Player player, int index) { return ItemStack.EMPTY; }
}
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/menu/MeControllerMenu.java`

**код файла:**
```java
...