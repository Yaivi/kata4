package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

public interface TitleDeserializer {
    Title deserialize(String data);
}

