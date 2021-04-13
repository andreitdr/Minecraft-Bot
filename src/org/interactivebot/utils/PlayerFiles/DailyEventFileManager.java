package org.interactivebot.utils.PlayerFiles;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.interactivebot.utils.Console;
import org.interactivebot.utils.Utils;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class DailyEventFileManager {


    private static final String DailyEventFileLocation = "plugins\\Minecraft_Bot\\DailyEvent\\";
    public static boolean CheckifDaily(Player player) {
        UUID id = player.getUniqueId();
        try{
            FileReader reader = new FileReader(DailyEventFileLocation + id + ".dat");
            Scanner scanner = new Scanner(reader);
            String fileContent = scanner.nextLine();
            scanner.close();
            reader.close();
            if(fileContent.isEmpty()) return false;
            String ldt = LocalDateTime.now().toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE);
            if(fileContent.equalsIgnoreCase(ldt))
                return true;
            return false;

        }catch(FileNotFoundException exception){
            Console.WriteLine("UUID " + id + " could not be found. It is its first daily event participation !");
            return false;
        } catch (IOException e) {
            Console.WriteLine("Error occured while closing the file reader stream: \n" + e.getMessage());
            Utils.SendDM(player, "Failed to load data. Please try again alter !");
            return true;
        }

    }

    public static void UpdatePlayerDaily(Player player){
        UUID id = player.getUniqueId();
        new File(DailyEventFileLocation).mkdirs();
        try{
            FileWriter fw = new FileWriter(DailyEventFileLocation + id + ".dat");
            fw.write(LocalDateTime.now().toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE));
            fw.close();
        } catch(IOException exception) {
            Console.WriteLine(exception.getMessage());
        }
    }

}
