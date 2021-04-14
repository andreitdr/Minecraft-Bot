package org.interactivebot.commands;

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
        Player player = (Player) commandSender;
        int p = ((CraftPlayer) player).getHandle().ping;

        Utils.SendDM(player, "Your ping is &c" + String.valueOf(p) + " ms");
        return true;
    }
}
