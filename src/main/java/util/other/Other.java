package util.other;

/**
 * Created by Роман on 20.06.2017.
 */
public class Other {
    public static String stackTrace(Exception e) {
        StringBuilder stringBuffer = new StringBuilder();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            stringBuffer.append(stackTraceElements[i].toString() + System.lineSeparator());
        }
        return stringBuffer.toString();
    }

}
