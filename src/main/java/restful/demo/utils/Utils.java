package restful.demo.utils;

import java.util.UUID;

public class Utils {
    public String generateUserId(){
       return UUID.randomUUID().toString();
    }
}
