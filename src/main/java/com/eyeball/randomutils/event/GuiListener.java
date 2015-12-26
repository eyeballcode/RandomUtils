package com.eyeball.randomutils.event;

import com.eyeball.randomutils.StaticConstants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiListener {

    boolean seen = false;

    @SubscribeEvent
    public void onGui(GuiOpenEvent event) {
        if (seen) return;
        seen = true;
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147673_a(new ResourceLocation(StaticConstants.dingOnLCSound)));
    }

}
