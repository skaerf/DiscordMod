package net.skaerf.discordmod;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.DirectAudioController;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import okhttp3.OkHttpClient;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.security.auth.login.LoginException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class Bot extends ListenerAdapter {

    public static JDA jda;


    public static void sendMessage(String channelID, String message) {
        TextChannel channel = jda.getTextChannelById(channelID);
        channel.sendMessage(message).queue();
    }

    public static void load() {
        try {
            startBot("NzM0NDk0MjA2NjA0NjA3NjA5.XxTk6w.OGzsxL9EeCjpwJzSpk0icPVgsBY");
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
                    .setActivity(Activity.playing("hello"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        }
        catch (LoginException e) {

        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (channel.getType().equals(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
            channel.sendMessage("test").queue();
        }
        if (channel.getName().equalsIgnoreCase("discordmod") && !event.getAuthor().isBot()) {
            channel.sendMessage(net.skaerf.discordmod.Message.parseMessage(msg.toString())).queue();
        }
    }
}
