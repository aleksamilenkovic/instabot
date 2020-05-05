package com.instabot.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author lezalekss
 */
public class Utils {
    /**
     * Parsing json array
     *
     * @param resourceName as String
     * @param arrayName as String
     *
     * @return List of Strings
     */
    public static List<String> readJsonStringArray(String resourceName, String arrayName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(findFile(resourceName)));
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray)jsonObject.get(arrayName);
            for (int i=0;i<jsonArray.size();i++)
                list.add(jsonArray.get(i).toString());
        return list;
    }

    /**
     * Finding a path to file from resource
     *
     * @param fileName as String (name of the file)
     *
     * @return file pathname as String
     */
    public static String findFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        System.out.println(url.getPath());
        return url.getFile();
    }

    /**
     * Parsing integer from String
     * (for scrapping likes and followers)
     *
     * @param likesXfollowers (likes || followers to scrapp)
     *
     * @return likes || followers as int
     */
    public static int parseInt(String likesXfollowers){
        int i=0;
        try{
            i = Integer.parseInt(likesXfollowers.trim().replaceAll(",","").replaceAll("m","000000"));
        }catch (NumberFormatException nfe){
            System.out.println("Couldn't format to integer: "+likesXfollowers);
        }
        return i;
    }
    /**
     * Fetching current date
     *
     *
     * @return data and time as LocalDateTime
     */
    public static LocalDateTime fetchCurrentDate(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        return LocalDateTime.now(zoneId);
    }
    /**
     * Parsing date from scrapped text
     *
     * @param date (date as String)
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getDateFromString(String date){
        return LocalDateTime.parse(date.substring(0, date.length()-1));
    }
}
