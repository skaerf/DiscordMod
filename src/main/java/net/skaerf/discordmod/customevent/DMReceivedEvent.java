package net.skaerf.discordmod.customevent;

import net.skaerf.discordmod.Bot;
import net.skaerf.discordmod.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DMReceivedEvent extends Event {

    private String message;
    private String userID;

    public DMReceivedEvent(String userID, String message) {
        this.message = message;
        this.userID = userID;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public String getMessage() {
        return this.message;
    }

    public String getUserID() {
        return this.userID;
    }

    public void respond(String response) {
        Bot.sendMessageToUser(userID, response);
    }

    public UUID getMinecraftUUID() {
        UUID uuid = null;
        List<String> linkedAccounts = ConfigManager.getDataFile().getStringList("linked-accounts");
        for (String linkedAccount : linkedAccounts) {
            String[] uuidAndId = linkedAccount.split(":");
            if (Objects.equals(uuidAndId[1], this.userID)) {
                uuid = UUID.fromString(uuidAndId[1]);
                break;
            }
        }
        return uuid;
    }

}
