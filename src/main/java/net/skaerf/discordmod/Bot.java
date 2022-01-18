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
    public Messages msgClass;
    static String defaultChannel;


    public static void sendMessage(String channelID, String message) {
        TextChannel channel = jda.getTextChannelById(channelID);
        channel.sendMessage(message).queue();
    }

    public static void sendMessageToDefault(String message) {
        TextChannel channel = jda.getTextChannelById(defaultChannel);
        channel.sendMessage(message).queue();
    }

    public static void setDefaultChannel(String channelID) {
        defaultChannel = channelID;
    }

    public static void load() {
        try {
            startBot("OTMzMDc3NjgyMTAxNTc5Nzk2.YecSTw.LKe68iBHLW1aDVqJKjjo5WQm9TI");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void startBot(String token) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token);
        jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("SSMC | v0.0.7"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
        //Bot.sendMessageToDefault("**Server has started**");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (channel.getType().equals(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
            channel.sendMessage("lol i got a dm").queue();
        }
        if (channel.getId().equalsIgnoreCase("933078390259482644") && !event.getAuthor().isBot()) {
            Bukkit.broadcastMessage(msgClass.parseMessage(msg));
        }
        if (channel.getId().equalsIgnoreCase("734735980568772649") && !event.getAuthor().isBot()) {
            channel.sendMessage("Sending command to server...").queue();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), msg.getContentRaw());
        }
    }
}
