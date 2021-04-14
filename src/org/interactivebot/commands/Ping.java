package org.interactivebot.commands;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;
import org.jetbrains.annotations.NotNull;

public class Ping implements CommandExecutor {

    private Main plugin;

    public Ping(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ping").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) return false;
        CraftPlayer player = (CraftPlayer)((Player)commandSender);
        int ping = player.getHandle().ping;
        if(ping <= 100)
            Utils.SendDM(commandSender, "Your ping is " + ChatColor.GREEN + String.valueOf(ping) + " ms");
        else if (ping > 100 && ping <= 350)
            Utils.SendDM(commandSender, "Your ping is " + ChatColor.GOLD + String.valueOf(ping) + " ms");
        else Utils.SendDM(commandSender, "Your ping is " + ChatColor.RED + String.valueOf(ping) + " ms");
        return true;
    }
}
