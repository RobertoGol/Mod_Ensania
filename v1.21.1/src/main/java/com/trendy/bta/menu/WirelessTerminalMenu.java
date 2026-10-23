...
____________________

---

## **6. REGISTRIES** (5 files)
**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/registry/ModBlocks.java`

**код файла:**
```java
package com.trendy.bta.registry;
import com.trendy.bta.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = net.neoforged.neoforge.registries.DeferredRegister.createBlocks(net.neoforged.neoforge.registries.Registries.BLOCKS);
    public static final DeferredBlock<Block> ME_CONTROLLER = net.neoforged.neoforge.registries.DeferredBlock.registration(Registries.BLOCKS,"me_controller",()->new MeControllerBlock(net.minecraft.world.level.block.BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.METAL)));
    // ... more blocks
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, net.neoforged.neoforge.registries.DeferredBlock.Manager<T> manager) { return manager.register(name); }
}
____________________

**путь до файла/имя файла:** `/home/user/mod_package/src/main/java/com/trendy/bta/registry/ModItems.java`

**код файла:**
```java
...