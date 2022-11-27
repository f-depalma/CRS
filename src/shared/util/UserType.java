package shared.util;

import java.util.HashMap;
import java.util.Map;

public class UserType {
    public enum TYPE {
        STUDENT,
        TEACHER,
        ADMIN
    }

    private static Map<Object, Object> values = new HashMap<>();

    static {
        values.put("S", TYPE.STUDENT);
        values.put("A", TYPE.ADMIN);
        values.put("T", TYPE.TEACHER);
        values.put(TYPE.STUDENT, "S");
        values.put(TYPE.ADMIN, "A");
        values.put(TYPE.TEACHER, "T");
    }

    public static TYPE getEnum(String str) {
        Object obj;
        try {
            obj = values.get(str);
        } catch (NullPointerException e) {
            return null;
        }
        return (TYPE) obj;
    }

    public static String getValue(TYPE type) {
        return (String) values.get(type);
    }
}
