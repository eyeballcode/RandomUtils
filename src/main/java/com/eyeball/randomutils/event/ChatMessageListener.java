package com.eyeball.randomutils.event;

import com.eyeball.randomutils.StaticConstants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.CommandHelp;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

import java.util.regex.Pattern;

public class ChatMessageListener {

    @SubscribeEvent
    public void onMessage(ServerChatEvent event) {
        String message = event.message;
        String[] parts = message.split(" ");
        StringBuilder out = new StringBuilder();
        for (String part : parts) {
            boolean foundBad = false;
            for (String vulgarity : StaticConstants.vulgarities) {
                Pattern boundaryPattern = Pattern.compile("[^a-zA-Z]*" + vulgarity.toLowerCase() + "[^a-zA-Z]*", Pattern.CASE_INSENSITIVE);
                if (part.toLowerCase().equals(vulgarity.toLowerCase()) || boundaryPattern.matcher(part).matches()) {
                    StringBuilder x = new StringBuilder();
                    for (int i = 0; i < vulgarity.length(); i++)
                        x.append("*");
                    out.append(boundaryPattern.matcher(part).replaceAll(x.toString())).append(" ");
                    foundBad = true;
                }
            }
            if (!foundBad)
                out.append(part).append(" ");
        }
        if (!out.toString().trim().equals(message)) {
            //Changed!
            event.setCanceled(true);
            event.player.mcServer.getCommandManager().executeCommand(event.player, "/say " + out.toString().trim());
        }
    }

}
