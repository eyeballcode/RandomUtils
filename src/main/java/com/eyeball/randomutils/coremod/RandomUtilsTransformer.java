package com.eyeball.randomutils.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class RandomUtilsTransformer implements IClassTransformer {

    static {
        File myConfig = new File(new File(System.getProperty("user.dir"), "config"), "randomutil.cfg");
        Configuration cfg = new Configuration(myConfig);
        shouldAllowEPInCreative = cfg.get("Tweaks", "ender pearl in creative", true, "Allow using of ender pearls in creative mode.").getBoolean();
        shouldTweakEPDamageInSurvival = cfg.get("Tweaks", "enable tweaking ender pearl damage", true, "Enable tweaking of the damage recieved when teleporting using ender pearls.").getBoolean();
        enableRemoveCommands = cfg.get("Remove commands", "enabled", true, "Enable removing of commands?").getBoolean();
        enderPearlDamage = (float) cfg.get("Tweaks", "ender pearl damage", 1.0, "The amount of damage recieved when teleporting using ender pearls.").getDouble();

        if (cfg.hasChanged())
            cfg.save();
    }

    static boolean shouldAllowEPInCreative,
            shouldTweakEPDamageInSurvival,
            enableRemoveCommands;
    static float enderPearlDamage;


    @Override
    public byte[] transform(String className, String newName, byte[] classBytes) {
        if (className.equals("net.minecraft.item.ItemEnderPearl") && shouldAllowEPInCreative)
            return Transformer.transformEnderPearl(classBytes);
        if (className.equals("net.minecraft.entity.item.EntityEnderPearl") && shouldTweakEPDamageInSurvival)
            return Transformer.transformEnderPearlEntity(classBytes, enderPearlDamage);
        return classBytes;
    }
}