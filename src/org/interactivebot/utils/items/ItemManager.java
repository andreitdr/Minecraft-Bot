package org.interactivebot.utils.items;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ItemManager {

    public static ItemStack CreateItem(String itemName, Material itemMaterial){
        ItemStack itemStack = new ItemStack(itemMaterial, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack CreateItem(String itemName, Material itemMaterial, int numberOfItems){
        ItemStack itemStack = new ItemStack(itemMaterial, numberOfItems);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack CreateItem(String itemName, Material itemMaterial, String... itemLore){
        ItemStack itemStack = new ItemStack(itemMaterial, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(Arrays.asList(itemLore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack CreateItem(String itemName, Material itemMaterial, int numberOfItems, String... itemLore){
        ItemStack itemStack = new ItemStack(itemMaterial, numberOfItems);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(Arrays.asList(itemLore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
