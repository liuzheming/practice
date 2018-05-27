package form;

import java.util.HashMap;
import java.util.Map;

public class FormPattern {

    private static final String EMAIL
            = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    private static final String PHONE = "^1[0-9]{10}$";

    public static final Map<String, String> PATTERN_MAP = new HashMap<>();

    static {
        PATTERN_MAP.put("email", EMAIL);
        PATTERN_MAP.put("phone", PHONE);
    }


}
