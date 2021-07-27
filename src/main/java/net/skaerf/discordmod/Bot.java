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
            startBot("NzM0NDk0MjA2NjA0NjA3NjA5.Xxhv4Q.PoU05Cb5EAdSbKhJaB4kYbZh9bA");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void startBot(String token) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        try {
            jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                    .addEventListeners(new Bot())
                    .setActivity(Activity.playing("skaerfMC | v0.0.7"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        }
        catch (LoginException e) {

        }
        Bot.sendMessageToDefault("**Server has started**");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (channel.getType().equals(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
            channel.sendMessage("lol i got a dm").queue();
        }
        if (channel.getId().equalsIgnoreCase("734692426798858290") && !event.getAuthor().isBot()) {
            Bukkit.broadcastMessage(msgClass.parseMessage(msg));
        }
        if (channel.getId().equalsIgnoreCase("734735980568772649") && !event.getAuthor().isBot()) {
            channel.sendMessage("Sending command to server...").queue();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), msg.getContentRaw());
        }
    }
}
