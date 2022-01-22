package net.skaerf.discordmod;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
                //noinspection ResultOfMethodCallIgnored
                bot.createNewFile();
                botcfg = YamlConfiguration.loadConfiguration(bot);
                getBotFile().addDefault("token", "TOKEN");
                getBotFile().addDefault("default-channel", "DEFAULT CHANNEL ID");
                getBotFile().addDefault("bot-status", "DiscordMod");
                getBotFile().addDefault("discord-link-msg", "DISCORD LINK");
                getBotFile().options().copyDefaults(true);
                saveBotFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                DiscordMod.console.info("[DiscordMod] Could not create the bot.yml file.");
            }
        }
        else {
            botcfg = YamlConfiguration.loadConfiguration(bot);
        }
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
                //noinspection ResultOfMethodCallIgnored
                data.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                DiscordMod.console.info("[DiscordMod] Could not create the data.yml file.");
            }
        }
    }
}
