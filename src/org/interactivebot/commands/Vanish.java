package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.interactivebot.Main;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Vanish implements CommandExecutor {

    private Main plugin;
    public Vanish(Main plugin){
        this.plugin=plugin;

        plugin.getCommand("vanish").setExecutor(this);
        plugin.getCommand("vanish").setTabCompleter(this::onTabCommand);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!plugin.getConfig().getBoolean("commands.vanish.enabled")) return false;
        if(!(commandSender instanceof Player)){
            Console.WriteLine("You are not allowed to use this command from the console");
            return false;
        }

        Player player = (Player)commandSender;
        if(!player.hasPermission(plugin.getConfig().getString("commands.vanish.permission_node"))){
            Utils.SendDM(commandSender, "&cYou can not use this command");
            return false;
        }

        player.setInvisible(!player.isInvisible());

        if(player.isInvisible()){
            Utils.SendDM(player, "You are now &aINVISIBLE");
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
            return true;
        }

        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        Utils.SendDM(player, "You are now &aVISIBLE");
        return true;
    }


    @Nullable
    public List<String> onTabCommand(CommandSender sender, Command c, String s, String[] str){
        return null;
    }

}
