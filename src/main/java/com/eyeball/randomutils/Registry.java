package com.eyeball.randomutils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Registry {

    public static void addShapelessRecipe(ItemStack out, Object... arrangements) {
        GameRegistry.addShapelessRecipe(out, arrangements);
    }

    public static void addShapedRecipe(ItemStack out, Object... arrangements) {
        GameRegistry.addRecipe(out, arrangements);
    }

    public static void addSmelting(ItemStack in, ItemStack out, float xp) {
        GameRegistry.addSmelting(in, out, xp);
    }

}
