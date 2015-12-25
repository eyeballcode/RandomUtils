package com.eyeball.randomutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Mod(modid = "RandomUtil", dependencies = "after:*;", name = "Random Utils", version = "1.0.0", acceptedMinecraftVersions = "1.7.10")
public class RandomUtil {

    ArrayList<String> removeCommands = new ArrayList<String>();
    boolean enableRemoveCommands;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StaticConstants.LOGGER = event.getModLog();
        StaticConstants.LOGGER.info("RandomUtil started!");
        System.out.println(event.getSuggestedConfigurationFile());
        StaticConstants.CONFIG = new Configuration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        enableRemoveCommands = StaticConstants.CONFIG.get("Remove commands", "enabled", true, "Enable removing of commands?").getBoolean();
        if (enableRemoveCommands) {
            removeCommands.addAll(Arrays.asList(StaticConstants.CONFIG.get("Remove Commands", "Commands To Remove", new String[]{}, "List the commands you want to remove here.").getStringList()));
        }
        StaticConstants.enableTweakEnderTPDmg = StaticConstants.CONFIG.get("Tweaks", "enable tweaking ender pearl damage", true, "Enable tweaking of the damage recieved when teleporting using ender pearls.").getBoolean();
        StaticConstants.enderTeleportDmg = (float) StaticConstants.CONFIG.get("Tweaks", "ender pearl damage", 1.0, "The amount of damage recieved when teleporting using ender pearls.").getDouble();

        StaticConstants.enableEnderPearlInCreative = StaticConstants.CONFIG.get("Tweaks", "ender pearl in creative", true, "Allow using of ender pearls in creative mode.").getBoolean();
        if (StaticConstants.CONFIG.hasChanged())
            StaticConstants.CONFIG.save();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        if (enableRemoveCommands) {
            Map commands = event.getServer().getCommandManager().getCommands();
            for (String command : removeCommands) {
                if (commands.get(command) == null) {
                    StaticConstants.LOGGER.warn("Attempted to remove nonexistent command " + command + ", skipping");
                } else {
                    commands.remove(command);
                    StaticConstants.LOGGER.info("Successfully removed command " + command + "!");
                }
            }
        }
    }

}
