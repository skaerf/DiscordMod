package net.skaerf.discordmod;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static File bot;
    public static File data;
    public static FileConfiguration datacfg;
    public static FileConfiguration botcfg;

    public static void createBotFile() {
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

    public static FileConfiguration getBotFile() {
        return botcfg;
    }

    public static void reloadBotFile() {
        botcfg = YamlConfiguration.loadConfiguration(bot);
    }

    public static void saveBotFile() {
        try {
            botcfg.save(bot);
        }
        catch (IOException e) {
            System.out.println("Could not save bot.yml file");
        }
    }

    public static void createDataFile() {
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
