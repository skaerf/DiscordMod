package net.skaerf.discordmod;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

public class DiscordAccountLink {

    static HashMap<Player, String> codes = new HashMap<Player, String>();

    static Random rand = new Random();
    static String code = "";

    public static String generateNewCode(Player player) {
        code = "";
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                if (rand.nextBoolean() == true) {
                    code = code + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()[rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray().length)];
                }
                else {
                    code = code + "abcdefghijklmnopqrstuvwxyz".toCharArray()[rand.nextInt("abcdefghijklmnopqrstuvwxyz".toCharArray().length)];
                }
            }
            else if (i == 1) {
                if (rand.nextBoolean() == true) {
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
            else if (i == 4) {
                if (rand.nextBoolean() == true) {
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

    public static void confirmAccountLink(Player player) {

    }

}
