package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.io.IOException;

public interface TitleWriter {
    void write(Title title) throws IOException;
}