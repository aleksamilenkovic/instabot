package com.instabot.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lezalekss
 */
public class Utils {
    public static List<String> readJsonStringArray(String resourceName, String arrayName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(findFile(resourceName)));
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray)jsonObject.get(arrayName);
            for (int i=0;i<jsonArray.size();i++)
                list.add(jsonArray.get(i).toString());
        return list;
    }

    public static String findFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        System.out.println(url.getPath());
        return url.getFile();
    }

    public static int parseInt(String likesXfollowers){
        int i=0;
        try{
            i = Integer.parseInt(likesXfollowers.trim().replaceAll(",","").replaceAll("m","000000"));
        }catch (NumberFormatException nfe){
            System.out.println("Couldn't format to integer: "+likesXfollowers);
        }
        return i;
    }
}
