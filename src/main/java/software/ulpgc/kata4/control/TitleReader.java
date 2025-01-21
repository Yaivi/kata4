package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface TitleReader {
    Iterator<Title> readTitles() throws IOException;
}
