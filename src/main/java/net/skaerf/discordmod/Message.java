package net.skaerf.discordmod;

public class Message {

    // parse Discord message to readable format for Minecraft chat
    public static String parseMessage(String messageToParse) {
        System.out.println(messageToParse);
        String[] split1 = messageToParse.split(":");
        String username = split1[2];
        String messageWithID = split1[2];
        String[] split2 = messageWithID.split("\\(");
        String rawMessage = split2[0];
        String parsedMessage = username+rawMessage;
        return parsedMessage;
    }

    // format Minecraft message for Discord chat
    public static String formatMessage(String unformattedMessage) {
        return unformattedMessage;
    }
}
