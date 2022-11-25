package shared.util;

import java.util.List;

public class Utils {
    private static final List<String> TYPES = List.of("A", "S", "T");

    public static String checkProfileType(String type) throws Exception {
        if (type.length() == 1)
            if (TYPES.contains(type))
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
