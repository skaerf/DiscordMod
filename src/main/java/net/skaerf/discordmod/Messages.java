package net.skaerf.discordmod;

import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Messages {

    // parse Discord message to readable format for Minecraft chat
    public static String parseMessage(net.dv8tion.jda.api.entities.Message messageToParse) {
        String parsedMessage = "";
        String name = "";
        if (messageToParse.getMember().getNickname() == null) {
            name = messageToParse.getAuthor().getName();
        }
        else {
            name = messageToParse.getMember().getNickname()+" ("+messageToParse.getAuthor().getName()+")";
        }
        parsedMessage = "&f[&bDiscord &3| &b"+name+"&f] ";
        parsedMessage = parsedMessage + messageToParse.getContentRaw();
        parsedMessage = ChatColor.translateAlternateColorCodes('&', parsedMessage);
        return parsedMessage;
    }

    // format Minecraft message for Discord chat
    public static String formatMessage(String unformattedMessage) {
        String formattedMsg = "";
        return formattedMsg;
    }

    public static void executeConsoleCommand(String cmd) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }
}
