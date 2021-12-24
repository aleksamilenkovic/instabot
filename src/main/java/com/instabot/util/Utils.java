package com.instabot.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author lezalekss
 */
public class Utils {
    public static List<String> readJsonStringArray(String resourceName, String arrayName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(findResourceFile(resourceName)));
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray)jsonObject.get(arrayName);
            for (int i=0;i<jsonArray.size();i++)
                list.add(jsonArray.get(i).toString());
        return list;
    }

    public static String findResourceFile(String fileName) {
        String path = System.getProperty("user.dir");
        path+="//target//test-classes//";
        return path + fileName;
    }

    public static int parseInt(String likesXfollowers){
        int i=0;
        try{
            i = Integer.parseInt(likesXfollowers.trim().replaceAll(",","").replaceAll("m","000000").replaceAll("k", "000"));
        }catch (NumberFormatException nfe){
            System.out.println("Couldn't format to integer: "+likesXfollowers);
        }
        return i;
    }

    public static LocalDateTime fetchCurrentDate(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        System.out.println(LocalDateTime.now());
        return LocalDateTime.now(zoneId);
    }

    public static LocalDateTime getDateFromString(String date){
        return LocalDateTime.parse(date.substring(0, date.length()-1));
    }

    public static LocalDateTime getDateFromTimestamp(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());
    }

    public static byte[] convertImageToByte(String imgUrl)  {
        try {
            URL url = new URL(imgUrl);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = new BufferedInputStream(url.openStream());
            byte[] byteChunk = new byte[4096];
            int n;
            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
            return baos.toByteArray();
        }
        catch (IOException e) {e.printStackTrace ();}
        return null;
    }
}
