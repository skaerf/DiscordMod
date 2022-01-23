package net.skaerf.discordmod;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    public static JDA jda;
    public static String defaultChannel;
    public static String consoleChannel;
    public static String token;
    public static String status;


    public static void sendMessageToConsoleChannel(String message) { // MAKE LOG4J CATCHER
        TextChannel channel = jda.getTextChannelById(consoleChannel);
        if (channel != null) {
            channel.sendMessage(message).queue();
        }
    }

    public static void sendMessageToDefault(String message) {
        TextChannel channel = jda.getTextChannelById(defaultChannel);
        if (channel != null) {
            channel.sendMessage(message).queue();
        }
    }

    public static void setDefaultChannel(String channelID) {
        defaultChannel = channelID;
    }

    public static void load() {
        try {
            startBot(token);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void startBot(String token) throws LoginException {
        jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing(status+" | v"+DiscordMod.getPlugin(DiscordMod.class).getDescription().getVersion()))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
        //Bot.sendMessageToDefault("**Server has started**");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (channel.getType().equals(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
            channel.sendMessage("lol i got a dm").queue(); // put custom event here for API to link to other things
        }
        if (channel.getId().equalsIgnoreCase(defaultChannel) && !event.getAuthor().isBot()) {
            Bukkit.broadcastMessage(Messages.parseMessage(msg));
        }
        if (channel.getId().equalsIgnoreCase(consoleChannel) && !event.getAuthor().isBot()) {
            channel.sendMessage("Sending command to server...").queue();
            Messages.executeConsoleCommand(msg.getContentRaw());
        }
    }
}
