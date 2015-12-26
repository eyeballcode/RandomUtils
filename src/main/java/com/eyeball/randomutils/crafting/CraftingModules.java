package com.eyeball.randomutils.crafting;

import com.eyeball.randomutils.Registry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingModules {

    private static boolean hasInit = false;

    public static void init() {
        if (hasInit) return;
        hasInit = true;
        Registry.addShapelessRecipe(new ItemStack(Items.experience_bottle), new ItemStack(Items.glass_bottle));
    }
}