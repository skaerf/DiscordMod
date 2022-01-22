package net.skaerf.discordmod;

import net.skaerf.discordmod.cmds.DiscordCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DiscordMod extends JavaPlugin {

    public static Logger console = Bukkit.getLogger();
    ConfigManager CFGm = new ConfigManager();

    @Override
    public void onEnable() {
        console.info("[DiscordMod] Starting");
        console.info("[DiscordMod] Loading Discord bot..");
        getCommand("discord").setExecutor(new DiscordCommand());
        getServer().getPluginManager().registerEvents(new Events(), this);
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        CFGm.createBotFile();
        CFGm.createDataFile();
        CFGm.reloadBotFile();
        Bot.token = CFGm.getBotFile().getString("token");
        Bot.setDefaultChannel(CFGm.getBotFile().getString("default-channel"));
        Bot.status = CFGm.getBotFile().getString("bot-status");
        Bot.load();
    }

    public void onDisable() {
        //Bot.sendMessageToDefault("**Server has stopped**"); FIX THIS. ON BOT INIT, BUKKIT RUNNABLE REPEATING SEND UNTIL DISCORD CONFIRMS IT WORKED
    }

}
