package shared.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    private static final String _DATE_FORMAT = "MM/dd/yyyy";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(_DATE_FORMAT);

    public static String checkProfileType(String type) throws Exception {
        if (type.length() == 1)
            if (UserType.getEnum(type) != null)
                return type;
        throw new Exception("Profile type doesn't exist");
    }

    public static String checkEmail(String email) throws Exception {
        if (email.contains("@")) {
            String[] split = email.split("@");
            if (split.length == 2)
                if (split[1].contains("."))
                    return email;
        }
        throw new Exception("Profile email format is incorrect");
    }

    public static Date stringToDate(String date) {
        java.util.Date parsed;
        Date sqlDate = null;
        try {
            parsed = DATE_FORMAT.parse(date);
            sqlDate = new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
}
