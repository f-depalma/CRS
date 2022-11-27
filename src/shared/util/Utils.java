package shared.util;

public class Utils {

    public static final String DATE_FORMAT = "MM/dd/yyyy";
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
}
