package com.eyeball.randomutils.crafting;

import com.eyeball.randomutils.Registry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CraftingModules {

    private static boolean hasInit = false;

    public static void init() {
        if (hasInit) return;
        hasInit = true;
        Registry.addShapelessRecipe(new ItemStack(Items.experience_bottle), new ItemStack(Items.glass_bottle));
    }
}
