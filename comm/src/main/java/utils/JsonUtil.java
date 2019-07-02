package utils;

import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static Gson gson;

    public static Map json2map(String json) {
        return getGson().fromJson(json,Map.class);
    }

    public static String map2json(Map<String, Object> map){
        return getGson().toJson(map);
    }

    public static List json2list(String json){
        return getGson().fromJson(json,List.class);
    }

    public static String list2json(List list){
        return getGson().toJson(list);
    }

    public static <T> T getValue(String jsonSrc, String key, Class<T> type){
        return (T)json2map(jsonSrc).get(key);
    }

    private static Gson getGson(){
        if(gson == null){
            gson = new GsonBuilder()
                    .setDateFormat(Constant.TIME_FORMAT)
                    .registerTypeAdapter(LocalDateTime.class,
                            (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                    new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(Constant.TIME_FORMAT))))
                    .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(Constant.DATE_FORMAT))))
                    .create();
        }
         return gson;
    }
}
