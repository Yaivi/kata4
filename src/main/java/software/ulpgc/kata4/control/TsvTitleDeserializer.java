package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.util.Arrays;

public class TsvTitleDeserializer implements TitleDeserializer {
    @Override
    public Title deserialize(String data) {
        return deserialize_array(data.split("\t"));
    }

    private Title deserialize_array(String[] split) {
        if (split.length != 9) {
            System.out.println(split[0]);
            System.err.println("Malformed line: not enough columns " + split.length);
            return null;  // O maneja el error según lo que necesites
        }
        return new Title(split[0],toTitleType(split[1]), split[2], split[3], toInteger(split[4]), toInteger(split[5]), toInteger(split[6]),  toInteger(split[7]));
    }

    private int toInteger(String s) {
        if (s.equals("\\N")) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    private Title.TitleType toTitleType(String s) {
        return Title.TitleType.valueOf(normalize(s));
    }

    private String normalize(String s) {
        String upper = s.toUpperCase();
        String temp = s.replace("-", "");
        String entrega = upper.toCharArray()[0] + temp.substring(1);
        return entrega;
    }
}
