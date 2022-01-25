package net.skaerf.discordmod;

import net.skaerf.discordmod.cmds.DiscordCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DiscordMod extends JavaPlugin {

    public static final Logger console = Bukkit.getLogger();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onEnable() {
        console.info("[DiscordMod] Starting");
        console.info("[DiscordMod] Loading Discord bot..");
        getCommand("discord").setExecutor(new DiscordCommand());
        getServer().getPluginManager().registerEvents(new Events(), this);
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        ConfigManager.createBotFile();
        ConfigManager.createDataFile();
        ConfigManager.reloadBotFile();
        Bot.token = ConfigManager.getBotFile().getString("token");
        Bot.setDefaultChannel(ConfigManager.getBotFile().getString("default-channel"));
        Bot.status = ConfigManager.getBotFile().getString("bot-status");
        Bot.load();
    }

    public void onDisable() {
        //Bot.sendMessageToDefault("**Server has stopped**"); TODO FIX THIS. ON BOT INIT, BUKKIT RUNNABLE REPEATING SEND UNTIL DISCORD CONFIRMS IT WORKED
    }

}
