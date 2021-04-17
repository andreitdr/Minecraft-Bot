package org.interactivebot.utils.BotFiles;

import org.bukkit.entity.Player;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class PlayerStats {

    private static final String pathToData = "plugins\\Minecraft_Bot\\PlayerData\\";

    public static void SaveData(Player player) throws IOException {
        new File(pathToData).mkdirs();

        String playerID = player.getUniqueId().toString();
        String fileLocation = pathToData + playerID + ".wzy";

        FileWriter writer = new FileWriter(fileLocation);

        String text = "Vanished=" + player.isInvisible() + "\n" +
                "CustomName=" + player.getCustomName() + "\n" +
                "DisplayName=" + player.getDisplayName() + "\n" +
                "FlySpeed=" + player.getFlySpeed()+"\n" +
                "WalkSpeed=" + player.getWalkSpeed() + "\n";

        writer.write(text);
        writer.close();
    }

    public static String GetDataFromFile(String file, String KeyCode) throws FileNotFoundException {
        if(new File(file).exists()) {
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNext()){
                String s = scanner.nextLine();
                if(s.startsWith(KeyCode))
                    return s.split("=")[1];
            }
        }
        return null;
    }

    public static void LoadData(Player player) throws FileNotFoundException {
        String fileLocation = pathToData + player.getUniqueId().toString() + ".wzy";

        if (!new File(fileLocation).exists()) return;

        player.setInvisible(Boolean.parseBoolean(GetDataFromFile(fileLocation, "Vanished")));
        player.setCustomName(GetDataFromFile(fileLocation, "CustomName"));
        player.setDisplayName(GetDataFromFile(fileLocation, "DisplayName"));
        player.setFlySpeed(Float.parseFloat(Objects.requireNonNull(GetDataFromFile(fileLocation, "FlySpeed"))));
        player.setWalkSpeed(Float.parseFloat(Objects.requireNonNull(GetDataFromFile(fileLocation, "WalkSpeed"))));

    }

}
