package org.interactivebot.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.interactivebot.utils.BotFiles.PlayerStats;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;

import java.io.FileNotFoundException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws FileNotFoundException {
        Player player =  e.getPlayer();
        e.setJoinMessage(Utils.chat("Welcome &5" + player.getName() + "&f to the server"));
        Console.WriteLine(player.getName() + " joined");

        PlayerStats.LoadData(player);
    }

}
