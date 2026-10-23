package com.trendy.bta.block;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.material.Material;
import net.neoforged.neoforge.common.ToolAction;
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/UltraFurnaceBlock.java`

**код файла:** (начало)
```java
package com.trendy.bta.block;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/LogisticsPipeBlock.java`

**код файла:** (начало)
```java
package com.trendy.bta.block;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/MeControllerBlock.java`

**код файла:** (начало)
```java
package com.trendy.bta.block;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/WirelessTerminalBlock.java`

**код файла:** (начало)
```java
package com.trendy.bta.block;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
...
____________________

---

## **3. BLOCK ENTITIES** (15 files)
**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/entity/QuarryBlockEntity.java`

**код файла:** (начало)
```java
package com.trendy.bta.block.entity;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.me.IMENode;
import com.trendy.bta.me.MENetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/entity/MineTerminalBlockEntity.java`

**код файла:** (начало)
```java
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/entity/StorageCellBlockEntity.java`

**код файла:** (начало)
```java
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/entity/MeControllerBlockEntity.java`

**код файла:** (начало)
```java
...
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/block/entity/WirelessTerminalBlockEntity.java`

**код файла:** (начало)
```java
...
____________________

---

## **4. ITEMS** (16 files)
**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/item/MeDiskItem.java`

**код файла:**
```java
package com.trendy.bta.item;
import com.trendy.bta.TrendyBTA;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class MeDiskItem extends Item {
    public MeDiskItem() { super(new Item.Properties().stacksTo(64).tab(TrendyBTA.tab)); }
}
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/item/MeCellItem.java`

**код файла:**
```java
...