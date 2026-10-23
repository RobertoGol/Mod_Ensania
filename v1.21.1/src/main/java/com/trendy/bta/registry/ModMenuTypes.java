package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.menu.UsbChargerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, TrendyBTA.MOD_ID);

    public static final Supplier<MenuType<UsbChargerMenu>> USB_CHARGER_MENU = 
            MENUS.register("usb_charger_menu", () -> IMenuTypeExtension.create(UsbChargerMenu::new));
}
