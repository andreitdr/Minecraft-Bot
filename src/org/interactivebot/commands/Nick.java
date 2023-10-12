package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;

public class Nick implements CommandExecutor{

    private Main plugin;
    public Nick(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("nick").setExecutor(this);
    }

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {

        if(!plugin.getConfig().getBoolean("commands.nick.enabled")) return false;
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player)commandSender;
        if(!player.hasPermission(plugin.getConfig().getString("commands.nick.permission_node"))) {
            Utils.SendDM(player, "You are not allowed to use this command !!");
            return false;
        }
        player.setCustomName(Utils.chat(strings[0] + "&f"));
        player.setDisplayName(Utils.chat(strings[0] + "&f"));
        Utils.SendDM(player, "Changed your name to " + strings[0]);
        return true;
    }
}
