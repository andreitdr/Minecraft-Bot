package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {

    private Main plugin;
    public Fly(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("fly").setExecutor(this);
    }



    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!plugin.getConfig().getBoolean("commands.fly.enabled")) return false;
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player)commandSender;

        if(!player.hasPermission(plugin.getConfig().getString("commands.fly.permission_node"))) {
            Utils.SendDM(player, "&4You do not have permission to use this command !!!");
            return false;
        }

        player.setAllowFlight(!player.getAllowFlight());
        if(player.getAllowFlight()){
            Utils.SendDM(player, "&3You are now able to fly !");
            return true;
        }
        Utils.SendDM(player, "&6Fly mode is OFF");

        return true;
    }
}
