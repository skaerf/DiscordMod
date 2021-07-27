package net.skaerf.discordmod;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public File bot;
    public File data;
    public FileConfiguration datacfg;
    public FileConfiguration botcfg;

    public void createBotFile() {
        bot = new File(DiscordMod.getPlugin(DiscordMod.class).getDataFolder(), "bot.yml");
        if (!bot.exists()) {
            try {
                bot.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                DiscordMod.console.info("[DiscordMod] Could not create the bot.yml file.");
            }
        }
        botcfg = YamlConfiguration.loadConfiguration(bot);
    }

    public FileConfiguration getBotFile() {
        return botcfg;
    }

    public void reloadBotFile() {
        botcfg = YamlConfiguration.loadConfiguration(bot);
    }

    public void saveBotFile() {
        try {
            botcfg.save(bot);
        }
        catch (IOException e) {
            System.out.println("Could not save bot.yml file");
        }
    }

    public void createDataFile() {
        data = new File(DiscordMod.getPlugin(DiscordMod.class).getDataFolder(), "data.yml");
        if (!data.exists()) {
            try {
                data.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                DiscordMod.console.info("[DiscordMod] Could not create the data.yml file.");
            }
        }
    }
}
