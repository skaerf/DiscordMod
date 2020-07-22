package net.skaerf.discordmod;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public File bot;
    public FileConfiguration botcfg;

    public void createBotFile() {
        bot = new File(DiscordMod.getPlugin(DiscordMod.class).getDataFolder(), "bot.yml");
        if (!bot.exists()) {
            try {
                bot.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                DiscordMod.console.info("Could not create the bot.yml file.");
            }
        }
    }
}
