package org.interactivebot.events.UI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.interactivebot.utils.PlayerFiles.DailyEventFileManager;

public class DailyEventInventoryListener implements Listener {

    private static Inventory i;

    public static void SetInventory(Inventory inv)
    {
        i = inv;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getClickedInventory() == i){

            ItemStack stack = e.getCurrentItem();
            if(stack==null || stack.getType() == Material.AIR)
                return;


            DailyEventFileManager.UpdatePlayerDaily((Player)e.getWhoClicked());
        }
    }

}
