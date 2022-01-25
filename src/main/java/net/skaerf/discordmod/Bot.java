package net.skaerf.discordmod;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.skaerf.discordmod.customevent.DMReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Bot extends ListenerAdapter {

    public static JDA jda;
    public static String defaultChannel;
    public static String consoleChannel;
    public static String token;
    public static String status;
    public static String username;


    public static void sendMessageToConsoleChannel(String message) { // TODO MAKE LOG4J CATCHER
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

    public static void  sendMessageToUser(String userID, String message) {
        jda.retrieveUserById(userID).complete().openPrivateChannel().complete().sendMessage(message).queue();
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
        username = jda.getSelfUser().getName();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String text = msg.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (channel.getType().equals(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
            DMReceivedEvent receivedEvent = new DMReceivedEvent(msg.getAuthor().getId(), msg.getContentRaw());
            Bukkit.getPluginManager().callEvent(receivedEvent);
            if (msg.getContentRaw().length() == 5) {
                for(Map.Entry<Player, String> entry: DiscordAccountLink.codes.entrySet()) {
                    if(Objects.equals(entry.getValue(), text)) {
                        channel.sendMessage("Account found! Minecraft player `"+entry.getKey().getName()+"` is now linked to <@"+msg.getAuthor().getId()+">.").queue();
                        DiscordAccountLink.successfulLink(entry.getKey(), msg.getAuthor().getName());
                        List<String> linkedAccounts = ConfigManager.getDataFile().getStringList("linked-accounts");
                        linkedAccounts.add(entry.getKey().getUniqueId()+":"+msg.getAuthor().getId());
                        ConfigManager.getDataFile().set("linked-accounts", linkedAccounts);
                        ConfigManager.saveDataFile();
                        DiscordAccountLink.codes.remove(entry.getKey());
                        break;
                    }
                }
            }
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
