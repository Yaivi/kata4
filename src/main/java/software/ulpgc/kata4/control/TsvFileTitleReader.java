package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TsvFileTitleReader implements TitleReader {
    private final File file;
    private final TsvTitleDeserializer deserializer;

    public TsvFileTitleReader(File file) {
        this.file = file;
        this.deserializer = new TsvTitleDeserializer();
    }

    @Override
    public Iterator<Title> readTitles() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return readTitlesWith(reader);
    }


    private Iterator<Title> readTitlesWith(BufferedReader reader) throws IOException {
        return new Iterator<>(){
            String line = reader.readLine();
            @Override
            public boolean hasNext(){return line!=null;}

            @Override
            public Title next(){
                try{
                    Title title = line == null ? null : titleOf(line);
                    line = reader.readLine();
                    if(line==null) reader.close();
                    return title;
                } catch (IOException e){
                    return null;
                }
            }
        };

    }
    private Title titleOf(String l){
        return deserializer.deserialize(l);
    }

}
