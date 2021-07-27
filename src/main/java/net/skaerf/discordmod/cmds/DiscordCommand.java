package net.skaerf.discordmod.cmds;

import net.skaerf.discordmod.ConfigManager;
import net.skaerf.discordmod.DiscordAccountLink;
import net.skaerf.discordmod.DiscordMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    ConfigManager CFGm = new ConfigManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            DiscordMod.console.info("[DiscordMod] These commands are only for players!");
        }
        else {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CFGm.getBotFile().getString("discord-link-msg")));
            }
            else {
                if (args[0].equalsIgnoreCase("link")) {
                    String code = DiscordAccountLink.generateNewCode(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlease DM DiscordMod through Discord with the code "+code+". This will link your Discord account to your Minecraft account."));
                }
            }
        }
        return true;
    }
}
