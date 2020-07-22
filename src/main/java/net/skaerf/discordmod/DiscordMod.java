package net.skaerf.discordmod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DiscordMod extends JavaPlugin {

    static Logger console = Bukkit.getLogger();

    @Override
    public void onEnable() {
        console.info("Starting");
        console.info("Loading Discord bot..");
        getServer().getPluginManager().registerEvents(new Events(), this);
        Bot.load();
    }

}
