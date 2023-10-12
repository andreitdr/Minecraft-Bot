package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class EntityKill implements CommandExecutor {

    private Main plugin;
    public EntityKill (Main plugin){
        this.plugin = plugin;
        plugin.getCommand("killentity").setExecutor(this);
        plugin.getCommand("killentity").setTabCompleter(
                this::onTabComplete
        );
    }

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {
        if(!plugin.getConfig().getBoolean("commands.killentity.enabled")){
            Utils.SendDM(commandSender, "Command is not enabled on server !!!");
            return false;
        }

        if(!(commandSender instanceof Player)){
            Console.WriteLine("This command can not be used from console !!");
            return false;
        }

        if(!(((Player)commandSender).hasPermission(plugin.getConfig().getString("commands.killentity.permission_node")))){
            Utils.SendDM(commandSender, "You are now allowed to use this command");
            return false;
        }

        try{
            String entity = strings[0];
            ((Player) commandSender).performCommand("kill @e[type=" + entity + "]");
        }
        catch(Exception ex){
            Utils.SendDM(commandSender, "Invalid arguments given.\nUsage: /killentity [MonsterName]");
            return false;
        }


        return false;
    }

    public List<String> onTabComplete( CommandSender commandSender,  Command command,  String s,  String[] strings) {
        if(strings.length <= 1)
            return Arrays.asList(
                    "minecraft:ender_dragon",
                    "minecraft:wither",
                    "minecraft:zombie",
                    "minecraft:creeper",
                    "minecraft:skeleton",
                    "minecraft:enderman"
            );
        return Arrays.asList(" ");
    }


}
