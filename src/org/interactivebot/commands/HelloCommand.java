package org.interactivebot.commands;

import org.bukkit.configuration.file.FileConfiguration;
import org.interactivebot.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.utils.Utils;


public class HelloCommand implements CommandExecutor {
    public Main plugin;
    private FileConfiguration cfg;
    public HelloCommand(Main _plugin, FileConfiguration c)
    {
        this.plugin = _plugin;
        cfg=c;
        plugin.getCommand("hello").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!cfg.getBoolean("commands.hello.enabled")) {
            return true;
        }
        if(!(commandSender instanceof Player))
        {
            if(plugin.getConfig().getBoolean("commands.hello.respond_console"))
                Utils.SendDM(commandSender,"Hello");
            return true;
        }

        if(commandSender.hasPermission(plugin.getConfig().getString("commands.hello.permission_node")))
        {
            Utils.SendDM(commandSender,"Hello");
            return true;
        }else{
            Utils.SendDM(commandSender,"You are not able to use this command !");
        }

        return false;
    }
}
