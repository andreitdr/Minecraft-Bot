package org.interactivebot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.interactivebot.Main;
import org.interactivebot.events.UI.DailyEventInventoryListener;
import org.interactivebot.utils.PlayerFiles.DailyEventFileManager;
import org.interactivebot.utils.Utils;
import org.interactivebot.utils.items.ItemManager;

public class DailyEvent implements CommandExecutor, Listener {

    private Main plugin;

    public DailyEvent(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("daily").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!plugin.getConfig().getBoolean("commands.daily.enabled")) return false;

        if(!(commandSender instanceof Player)) {
            Utils.SendDM(commandSender, "&4You are allowed to use this command once every 24h");
            return false;
        }
        if(!commandSender.hasPermission(plugin.getConfig().getString("commands.daily.permission_node")))
             return false;

        if(!DailyEventFileManager.CheckifDaily((Player)commandSender)) {
            Inventory i = Bukkit.createInventory((Player)commandSender, 9, ChatColor.BLUE + "Daily Event");
            i.addItem(ItemManager.CreateItem("Daily Item 1", Material.DIAMOND, 1));
            i.addItem(ItemManager.CreateItem("Daily Item 2", Material.DIAMOND, 1));
            i.addItem(ItemManager.CreateItem("Daily Item 3", Material.STONE_SWORD, 1, "Calos"));
            ((Player)commandSender).openInventory(i);
            DailyEventInventoryListener.SetInventory(i);
            return true;
        }

        return true;
    }



}
