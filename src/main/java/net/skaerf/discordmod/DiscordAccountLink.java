package net.skaerf.discordmod;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

public class DiscordAccountLink {

    public static HashMap<Player, String> codes = new HashMap<>();

    static Random rand = new Random();
    static String code = "";

    @SuppressWarnings("StringConcatenationInLoop")
    public static String generateNewCode(Player player) {
        code = "";
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                if (rand.nextBoolean()) {
                    code = code + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()[rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray().length)];
                }
                else {
                    code = code + "abcdefghijklmnopqrstuvwxyz".toCharArray()[rand.nextInt("abcdefghijklmnopqrstuvwxyz".toCharArray().length)];
                }
            }
            else if (i == 1) {
                if (rand.nextBoolean()) {
                    code = code + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()[rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray().length)];
                }
                else {
                    code = code + "abcdefghijklmnopqrstuvwxyz".toCharArray()[rand.nextInt("abcdefghijklmnopqrstuvwxyz".toCharArray().length)];
                }
            }
            else if (i == 2) {
                code = code + "1234567890".toCharArray()[rand.nextInt("1234567890".toCharArray().length)];
            }
            else if (i == 3) {
                code = code + "1234567890".toCharArray()[rand.nextInt("1234567890".toCharArray().length)];
            }
            else {
                if (rand.nextBoolean()) {
                    code = code + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()[rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray().length)];
                }
                else {
                    code = code + "abcdefghijklmnopqrstuvwxyz".toCharArray()[rand.nextInt("abcdefghijklmnopqrstuvwxyz".toCharArray().length)];
                }
            }
        }
        codes.put(player, code);

        return code;
    }

    public static void successfulLink(Player player, String discordUsername) {
        if (player != null) {
            //noinspection deprecation
            player.sendTitle(ChatColor.AQUA+"Thank you!", ChatColor.WHITE+"Your Minecraft account is now linked to "+discordUsername+".");
        }
    }

}
