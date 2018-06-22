package util;

public class LogUtil {

    public static void d(String tag,String content){
        System.out.println(tag +":"+content);
    }

    public static void e(String tag,String content){
        System.err.println(tag +":"+content);
    }

}
