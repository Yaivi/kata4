package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class TitleLoader {
    private final File file;
    private final File dbFile;

    public TitleLoader(File file, File database) {
        this.file = file;
        this.dbFile = database;
    }

    public void loadTitles() throws IOException {
        TitleReader reader = new TsvFileTitleReader(this.file);
        SQLiteTitleWriter dbWriter = new SQLiteTitleWriter(this.dbFile);
        Iterator<Title> titles=reader.readTitles();
        titles.next();
        while(titles.hasNext()){
            dbWriter.write(titles.next());
        }

        dbWriter.closeConnection();
    }
}