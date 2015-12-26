package com.eyeball.randomutils.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Raytracer {

    public static MovingObjectPosition getPlayerTarget(boolean liquids) {
        EntityLivingBase player = Minecraft.getMinecraft().renderViewEntity;
        double reachDistance = Minecraft.getMinecraft().playerController.getBlockReachDistance();

        Vec3 playerPos = player.getPosition(0);
        Vec3 playerLook = player.getLook(0);
        Vec3 target = playerPos.addVector(playerLook.xCoord * reachDistance, playerLook.yCoord * reachDistance, playerLook.zCoord * reachDistance);
        return player.worldObj.rayTraceBlocks(playerPos, target, liquids);
    }

    public static boolean isEntity(MovingObjectPosition movingObjectPosition) {
        return movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY;
    }

    public static boolean isBlock(MovingObjectPosition movingObjectPosition) {
        return movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK;
    }

    public static Block getBlock(MovingObjectPosition movingObjectPosition, World world) {
        if (!isBlock(movingObjectPosition)) return null;
        int x = movingObjectPosition.blockX,
                y = movingObjectPosition.blockY,
                z = movingObjectPosition.blockZ;
        return world.getBlock(x, y, z);
    }

    public static Block getPlayerTargetBlock(boolean liquids) {
        MovingObjectPosition lookingAt = getPlayerTarget(true);
        if (lookingAt == null || Minecraft.getMinecraft().theWorld == null || !isBlock(lookingAt))
            return null;
        return getBlock(lookingAt, Minecraft.getMinecraft().theWorld);
    }


    public static Entity getPlayerTargetEntity() {
        MovingObjectPosition lookingAt = Minecraft.getMinecraft().objectMouseOver;
        if (lookingAt == null || Minecraft.getMinecraft().theWorld == null || !isEntity(lookingAt))
            return null;
        return lookingAt.entityHit;
    }

}
