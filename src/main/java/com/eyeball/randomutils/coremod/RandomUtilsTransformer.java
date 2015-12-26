package com.eyeball.randomutils.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class RandomUtilsTransformer implements IClassTransformer {

    static {
        File myConfig = new File(new File(System.getProperty("user.dir"), "config"), "randomutil.cfg");
        Configuration cfg = new Configuration(myConfig);
        shouldAllowEPInCreative = cfg.get("Tweaks", "Ender Pearl in Creative Mode", true, "Allow using of Ender Pearls in Creative Mode.").getBoolean();
        shouldTweakEPDamageInSurvival = cfg.get("Tweaks", "Enable tweaking Ender Pearl damage", true, "Enable tweaking of the damage received when teleporting using Ender Pearls.").getBoolean();
        enableRemoveCommands = cfg.get("Remove commands", "Enabled", true, "Enable removing of commands?").getBoolean();
        enderPearlDamage = (float) cfg.get("Tweaks", "Ender Pearl Damage", 1.0, "The amount of damage received when teleporting using Ender Pearls.").getDouble();

        if (cfg.hasChanged())
            cfg.save();
    }

    static boolean shouldAllowEPInCreative,
            shouldTweakEPDamageInSurvival,
            enableRemoveCommands;
    static float enderPearlDamage;


    @Override
    public byte[] transform(String className, String newName, byte[] classBytes) {
        if (className.equals("net.minecraft.item.ItemEnderPearl") && shouldAllowEPInCreative) {
            return Transformer.transformEnderPearl(classBytes);
        }
        if (className.equals("net.minecraft.entity.item.EntityEnderPearl") && shouldTweakEPDamageInSurvival) {
            return Transformer.transformEnderPearlEntity(classBytes, enderPearlDamage);
        }
        if (className.equals("net.minecraft.inventory.SlotCrafting")) {
            return Transformer.transformSlotCrafting(classBytes);
        }
        return classBytes;
    }
}