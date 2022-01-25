package net.skaerf.discordmod.customevent;

import net.skaerf.discordmod.Bot;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

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
}
