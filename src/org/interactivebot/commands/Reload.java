package org.interactivebot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.interactivebot.Main;
import org.interactivebot.utils.Utils;

public class Reload implements CommandExecutor {

    public Main plugin;

    public Reload(Main p) {
        this.plugin = p;

        plugin.getCommand("botreload").setExecutor(this);
    }

    private void ReloadConfiguration()
    {
        plugin.reloadConfig();
        plugin.BotPrefix = plugin.getConfig().getString("general.bot.prefix");
        Utils.WriteLog("Config reloaded !", 0);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (plugin.getConfig().getBoolean("commands.reload.respond_player") && ((Player) commandSender).hasPermission(plugin.getConfig().getString("commands.reload.permission_node"))) {

                ReloadConfiguration();
                Utils.SendDM(commandSender, "All settings were reloaded");
                return true;
            }

        }
        else {
            if (!plugin.getConfig().getBoolean("commands.reload.respond_console"))
                return true;
            ReloadConfiguration();

            Utils.SendDM(commandSender, "All Settings Were restored !");
            return true;
        }


        return false;
    }
}
