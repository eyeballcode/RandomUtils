package com.eyeball.randomutils;

import com.eyeball.randomutils.event.ChatMessageListener;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.command.CommandHelp;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.util.Arrays;
import java.util.Map;

@Mod(modid = "RandomUtil", dependencies = "after:*;", name = "Random Utils", version = "1.0.0", acceptedMinecraftVersions = "1.7.10")
public class RandomUtil {


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StaticConstants.LOGGER = event.getModLog();
        StaticConstants.LOGGER.info("RandomUtil started!");
        StaticConstants.CONFIG = new Configuration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        StaticConstants.enableRemoveCommands = StaticConstants.CONFIG.get("Remove commands", "enabled", true, "Enable removing of commands?").getBoolean();
        if (StaticConstants.enableRemoveCommands) {
            StaticConstants.removeCommands.addAll(Arrays.asList(StaticConstants.CONFIG.get("Remove Commands", "Commands To Remove", new String[]{}, "List the commands you want to remove here.").getStringList()));
        }
        StaticConstants.noVulgarities = StaticConstants.CONFIG.get("Tweaks", "Filter out vulgarities from chat", true, "Replace vulgarities with * from chat.").getBoolean();
        if (StaticConstants.noVulgarities) {
            StaticConstants.vulgarities.addAll(Arrays.asList(StaticConstants.CONFIG.get("Tweaks", "Vulgarity list", new String[]{}, "List vulgarities here ;)").getStringList()));
            StaticConstants.replaceVulgarityWith = StaticConstants.CONFIG.get("Tweaks", "Replace vulgarity with", "*", "Replace vulgarities with this character").getString();
            MinecraftForge.EVENT_BUS.register(new ChatMessageListener());
        }

        StaticConstants.dingOnLC = StaticConstants.CONFIG.get("Misc", "Ding on load complete", true, "Play a sound when Minecraft finishes loading.").getBoolean();
        StaticConstants.dingOnLCSound = StaticConstants.CONFIG.get("Misc", "Sound to play on Load Complete", "minecraft:random.click", "What sound to play when Minecraft finishes loading").getString();
        if (StaticConstants.CONFIG.hasChanged())
            StaticConstants.CONFIG.save();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        if (StaticConstants.dingOnLC) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147673_a(new ResourceLocation(StaticConstants.dingOnLCSound)));
        }
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        if (StaticConstants.enableRemoveCommands) {
            removeCommands(event.getServer().getCommandManager().getCommands());
            event.registerServerCommand(new CommandHelp());
        }
    }

    private void removeCommands(Map commands) {
        commands.remove("help");
        for (String command : StaticConstants.removeCommands) {
            if (commands.get(command) == null) {
                StaticConstants.LOGGER.warn("Attempted to remove nonexistent command " + command + ", skipping");
            } else {
                commands.remove(command);
                StaticConstants.LOGGER.info("Successfully removed command " + command + "!");
            }
        }
    }

}
