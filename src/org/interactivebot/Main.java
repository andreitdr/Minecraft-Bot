package org.interactivebot;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.interactivebot.Shorts.Time;
import org.interactivebot.Shorts.gm;
import org.interactivebot.commands.*;
import org.interactivebot.events.UI.DailyEventInventoryListener;
import org.interactivebot.events.player.PlayerJoin;
import org.interactivebot.events.player.PlayerQuit;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;

public class Main extends JavaPlugin {

    public String BotPrefix;

    private Server server;
    private PluginManager pluginManager;

    private void Start() {
        this.server = getServer();
        this.pluginManager = server.getPluginManager();
        BotPrefix = getConfig().getString("general.bot.prefix");
        Utils.BotPrefix = this.BotPrefix;
        saveDefaultConfig();

        if(!getConfig().getBoolean("general.enabled"))
        {
            Utils.WriteLog("The bot failed to start because of: PLUGIN_DISABLED 0x001",1);
            return;
        }
        Console.WriteLine(
                "\n\n***************\n" +
                        "Minecrfat Bot has been activated !\n" +
                        "Developer: Wizzy\n" +
                        "***************"
        );

    }


    @Override
    public void onEnable() {
        Start();
        Utils.WriteLog("Loading Plugin ...",0);
        EnableCommands();
        EnableListeners();



        Utils.WriteLog("The Plugin has been loaded Successfully !",0);

    }

    @Override
    public void onDisable() {
        Utils.WriteLog("Bot has been disabled",0);
    }

    public void EnableCommands(){
        new HelloCommand(this,getConfig());
        new Reload(this);
        new gm(this);
        new Time(this);
        new CurrentTime(this);
        new DailyEvent(this);
        new EntityKill(this);
        new Fly(this);
        new Nick(this);
        new Vanish(this);

    }

    public void EnableListeners(){
        pluginManager.registerEvents(new PlayerJoin(),this);
        pluginManager.registerEvents(new PlayerQuit(), this);

        pluginManager.registerEvents(new DailyEventInventoryListener(), this);
    }
}
