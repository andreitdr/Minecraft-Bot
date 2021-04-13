package org.interactivebot.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player =  e.getPlayer();
        e.setQuitMessage(Utils.chat("&5" + player.getName() + "&f has left the server !"));
        Console.WriteLine(player.getName() + " left");
    }

}
