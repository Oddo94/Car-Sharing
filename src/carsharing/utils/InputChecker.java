package carsharing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    public static boolean isDigit(String input) {
        if(input == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^\\d$");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();

    }
}
