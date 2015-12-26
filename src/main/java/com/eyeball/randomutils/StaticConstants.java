package com.eyeball.randomutils;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class StaticConstants {

    public static Configuration CONFIG;
    public static Logger LOGGER;

    public static float enderTeleportDmg = 0f;
    public static boolean enableTweakEnderTPDmg = true;

    public static boolean enableEnderPearlInCreative = true;

    public static ArrayList<String> removeCommands = new ArrayList<String>();
    public static boolean enableRemoveCommands;

    public static ArrayList<String> vulgarities = new ArrayList<String>();
    public static boolean noVulgarities = true;
    public static String replaceVulgarityWith = "*";

    public static boolean dingOnLC = true;
    public static String dingOnLCSound = "minecraft:random.click";
}
