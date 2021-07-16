package be.kauzas.kernel.utils;

import java.util.Arrays;
import java.util.List;

public class HoldingString {

    private String[] strings;

    public HoldingString(String string) {
        this.strings = new String[]{string};
    }

    public HoldingString(String... strings) {
        this.strings = strings;
    }

    public HoldingString replace(String holder, Object value) {
        for (int i = 0; i < strings.length; i++) {
            String actualHolder = "%" + holder + "%";
            if (strings[i].contains(actualHolder)) {
                strings[i] = strings[i].replace(actualHolder, value.toString());
                return this;
            }
        }
        return this;
    }

    public HoldingString replaceAll(String holder, Object value) {
        for (int i = 0; i < strings.length; i++) {
            String actualHolder = "%" + holder + "%";
            strings[i] = strings[i].replaceAll(actualHolder, value.toString());
        }
        return this;
    }

    public String get() {
        if (strings.length == 0) return "";
        return strings[0];
    }

    public String[] asArray() {
        return strings;
    }

    public List<String> asList() {
        return Arrays.asList(strings);
    }

}
