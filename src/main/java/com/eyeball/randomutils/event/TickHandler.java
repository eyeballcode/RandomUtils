package com.eyeball.randomutils.event;

import com.eyeball.randomutils.util.Raytracer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

public class TickHandler {

    public static Block lookingAtBlock = null;
    public static Entity lookingAtEntity = null;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        Block lookingAtBlock = Raytracer.getPlayerTargetBlock(true);
        Entity lookingAtEntity = Raytracer.getPlayerTargetEntity();
        if (lookingAtBlock == null && lookingAtEntity == null) return;
        TickHandler.lookingAtBlock = lookingAtBlock;
        TickHandler.lookingAtEntity = lookingAtEntity;
    }

}
