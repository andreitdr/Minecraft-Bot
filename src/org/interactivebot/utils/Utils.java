package org.interactivebot.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String BotPrefix;
    private static String MainFolder = "plugins\\Minecraft_Bot\\";

    public static String chat(String message) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void SendDM(CommandSender commandSender, String message){
        if(commandSender instanceof Player)
            message = chat(BotPrefix + " &f" + message);
        commandSender.sendMessage(message);


    }


    public static void WriteLog(String message,int error) {
        try {
            new File(MainFolder + "LOGS").mkdirs();
            new File(MainFolder + "LOGS\\Logs.txt").createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        File logs = new File(MainFolder + "LOGS\\Logs.txt");
        Writer writer;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer = new BufferedWriter(new FileWriter(logs, true));
            if(error == 0)
                writer.write( dtf.format(now) + " [INFO] " + message + "\n");
            else if (error == 1)
                writer.write( dtf.format(now) + " [WARN] " + message + "\n");
            else
                writer.write( dtf.format(now) + " [ERROR] " + message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
