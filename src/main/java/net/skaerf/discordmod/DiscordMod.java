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
        Bot.setDefaultChannel("933078390259482644");
        //getCommand("discord").setExecutor(new DiscordCommand()); BLANKED BECAUSE ALREADY HAVE A DISCORD COMMAND - THIS IS NOT NEEDED
        getServer().getPluginManager().registerEvents(new Events(), this);
        Bot.load();
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        CFGm.createBotFile();
        CFGm.createDataFile();
        CFGm.reloadBotFile();
        //this.saveConfig();
    }

    public void onDisable() {
        //Bot.sendMessageToDefault("**Server has stopped**");
    }

}
