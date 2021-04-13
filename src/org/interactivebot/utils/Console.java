package org.interactivebot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void WriteLine(String message){
        System.out.println("******** Minecraft Bot by Wizzy ********");
        System.out.println(message);
    }


    public static String ReadLine() throws IOException {
        return reader.readLine();
    }

    public static int Read() throws IOException {
        return  reader.read();
    }


}
