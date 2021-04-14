package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;
import org.jetbrains.annotations.NotNull;

public class ChangeSpeed implements CommandExecutor {

    private Main plugin;

    public ChangeSpeed(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("changespeed").setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!plugin.getConfig().getBoolean("commands.changespeed.enabled")) return false;

        if (!(commandSender instanceof Player)) {
            Console.WriteLine("You are not allowed to use this command from console !!");
            return false;
        }


        if (!((Player) commandSender).hasPermission(plugin.getConfig().getString("commands.changespeed.permission_node"))) {
            Utils.SendDM(commandSender, "&cYou are not allowed to use this command !");
            return false;
        }

        if(strings.length != 1) {

            Utils.SendDM(commandSender, "&cYou have to specify a value from 1 to 10");
            return false;
        }

        try {

            float speed = Float.parseFloat(strings[0]);
            speed /= 10;
            Player player = (Player) commandSender;

            if (player.isFlying()) {
                float oldSpeed = player.getFlySpeed()*10;
                player.setFlySpeed(speed);
                Utils.SendDM(commandSender, "Successfully changed your &3fly&f speed from &c" + oldSpeed + "&f to &6" + player.getFlySpeed()*10);
            } else {
                float oldSpeed = player.getWalkSpeed()*10;
                player.setWalkSpeed(speed);

                Utils.SendDM(commandSender, "Successfully changed your &3walk&f speed from &c" + oldSpeed + "&f to &6" + player.getWalkSpeed()*10);
            }


            return true;
        } catch (Exception ex) {
            Console.WriteLine(ex.getMessage());
            Utils.SendDM(commandSender, "&cInvalid number : " + strings[0] + ". The number should be between 1 and 10");
            return false;
        }
    }
}
