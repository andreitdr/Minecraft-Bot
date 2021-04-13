package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime implements CommandExecutor {

    private Main plugin;

    public CurrentTime(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("ct").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            if (plugin.getConfig().getBoolean("commands.ct.respond_player") && ((Player) commandSender).hasPermission(plugin.getConfig().getString("commands.ct.permission_node"))) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);

                Utils.SendDM(commandSender,"Current time is : " + date);
            }
            else
                Utils.SendDM(commandSender,"You are not allowed to use this command !");
        }
        else{
            if(plugin.getConfig().getBoolean("commands.ct.respond_console")){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);

                Utils.SendDM(commandSender, "Current time is : " + date);
            }
        }


        return false;
    }
}
