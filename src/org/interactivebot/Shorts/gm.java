package org.interactivebot.Shorts;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;

public class gm implements CommandExecutor {

    public Main plugin;

    public gm(Main p) {
        plugin = p;
        p.getCommand("gm").setExecutor(this);
    }

    private boolean CheckStringInArray(String[] array, String searchedString) {
        for (String value : array)
            if (value.equals(searchedString))
                return true;
        return false;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!plugin.getConfig().getBoolean("shorts.gm.enabled")){
            return false;
        }

        String OriginalCommand = plugin.getConfig().getString("shorts.gm.original");

        try {
            if (commandSender instanceof Player) {

                if (((Player) commandSender).hasPermission(plugin.getConfig().getString("shorts.gm.permission_node"))) {
                    String arguments = plugin.getConfig().getString("shorts.gm.arguments");
                    String[] args = arguments.split(",");
                    if (!CheckStringInArray(args, strings[0])) {
                        commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Invalid argument " + strings[0]);
                        commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Arguments: " + arguments);
                        return true;
                    }

                    if(strings[0].equals(args[0])){
                        //plugin.getServer().dispatchCommand(plugin.getServer().getPlayer(((Player) commandSender).getUniqueId()), OriginalCommand + " survival " + commandSender.getName());
                        plugin.getServer().getPlayer(commandSender.getName()).setGameMode(GameMode.SURVIVAL);
                        ((Player) commandSender).getPlayer().sendMessage(Utils.chat(plugin.BotPrefix+ "Your gamemode has been setted to : &aSURVIVAL"));
                    }
                    else if (strings[0].equals(args[1])){
                        plugin.getServer().getPlayer(commandSender.getName()).setGameMode(GameMode.CREATIVE);
                        ((Player) commandSender).getPlayer().sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &eCREATIVE"));
                    }
                    else if(strings[0].equals(args[2])){
                        plugin.getServer().getPlayer(commandSender.getName()).setGameMode(GameMode.ADVENTURE);
                        ((Player) commandSender).getPlayer().sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &cADVENTURE"));
                    }
                    else if(strings[0].equals(args[3])){
                        plugin.getServer().getPlayer(commandSender.getName()).setGameMode(GameMode.SPECTATOR);
                        ((Player) commandSender).getPlayer().sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &bSPECTATOR"));
                    }
                }
            }
            else {
                //Console
                String arguments = plugin.getConfig().getString("shorts.gm.arguments");
                String[] args = arguments.split(",");
                if (strings[0] == "") {
                    commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Invalid argument " + strings[0]);
                    commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Invalid player name !");
                    return true;
                }
                if (!CheckStringInArray(arguments.split(","), strings[1])) {
                    commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Invalid argument " + strings[1]);
                    commandSender.sendMessage(Utils.chat(plugin.BotPrefix) + "Arguments: " + arguments);
                    return true;
                }
                if(strings[1].equals(args[0])){
                    plugin.getServer().getPlayer(strings[0]).setGameMode(GameMode.SURVIVAL);
                    plugin.getServer().getPlayer(strings[0]).sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &aSURVIVAL"));
                }
                else if (strings[1].equals(args[1])){
                    plugin.getServer().getPlayer(strings[0]).setGameMode(GameMode.CREATIVE);
                    plugin.getServer().getPlayer(strings[0]).sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &eCREATIVE"));
                }
                else if(strings[1].equals(args[2])){
                    plugin.getServer().getPlayer(strings[0]).setGameMode(GameMode.ADVENTURE);
                    plugin.getServer().getPlayer(strings[0]).sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &cADVENTURE"));
                }
                else if(strings[1].equals(args[3])){
                    plugin.getServer().getPlayer(strings[0]).setGameMode(GameMode.SPECTATOR);
                    plugin.getServer().getPlayer(strings[0]).sendMessage(Utils.chat(plugin.BotPrefix + "Your gamemode has been setted to : &bSPECTATOR"));
                }
                return true;
            }
        } catch (Exception ex) {
            commandSender.sendMessage(ex.getMessage());
        }
        return false;
    }
}
