package net.skaerf.discordmod.cmds;

import net.skaerf.discordmod.Bot;
import net.skaerf.discordmod.ConfigManager;
import net.skaerf.discordmod.DiscordAccountLink;
import net.skaerf.discordmod.DiscordMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            DiscordMod.console.info("[DiscordMod] These commands are only for players!");
        }
        else {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getBotFile().getString("discord-link-msg")));
            }
            else {
                if (args[0].equalsIgnoreCase("link")) {
                    String code = DiscordAccountLink.generateNewCode(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlease DM DiscordMod through Discord with the code "+code+". This will link your Discord account to your Minecraft account."));
                }
                if (args[0].equalsIgnoreCase("reinit")) {
                    if (!sender.hasPermission("discordmod.reinit")) {
                        sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");
                    }
                    else {
                        ConfigManager.reloadBotFile();
                        Bot.token = ConfigManager.getBotFile().getString("token");
                        Bot.setDefaultChannel(ConfigManager.getBotFile().getString("default-channel"));
                        Bot.consoleChannel = ConfigManager.getBotFile().getString("console-channel");
                        Bot.status = ConfigManager.getBotFile().getString("bot-status");
                        sender.sendMessage(ChatColor.GREEN+"Config reloaded. If the bot token or status was changed, you will still have to restart the server.");
                    }
                }
            }
        }
        return true;
    }
}
