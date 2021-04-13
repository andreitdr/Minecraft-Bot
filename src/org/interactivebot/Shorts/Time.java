package org.interactivebot.Shorts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;

public class Time implements CommandExecutor {

    public Main plugin;
    public Time(Main p){
        plugin=p;
        plugin.getCommand("day").setExecutor(this);
        plugin.getCommand("night").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player)
        {
            if(((Player)sender).hasPermission(plugin.getConfig().getString("shorts.day.permission_node")) && plugin.getConfig().getBoolean("shorts.day.enabled")){
                if(command.getName().equals("day"))
                    plugin.getServer().dispatchCommand(plugin.getServer().getPlayer(((Player)sender).getUniqueId()),"time set day");
            }
            if(((Player)sender).hasPermission(plugin.getConfig().getString("shorts.night.permission_node"))&& plugin.getConfig().getBoolean("shorts.night.enabled")){
                if(command.getName().equals("night"))
                    plugin.getServer().dispatchCommand(plugin.getServer().getPlayer(((Player)sender).getUniqueId()),"time set night");
            }
            return true;

        }
        else{
            Utils.SendDM(sender, "You are not allowed to use this command from the Console !");
        }
        return false;
    }
}
