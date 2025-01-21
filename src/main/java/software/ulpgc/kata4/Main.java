package software.ulpgc.kata4;

import software.ulpgc.kata4.control.*;
import software.ulpgc.kata4.model.Histogram;
import software.ulpgc.kata4.view.MainFrame;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("content.tsv");
        File database = new File("database.db");
        TitleLoader loader = new TitleLoader(file, database);
        loader.loadTitles();

        TitleReader reader = new SQLTitleReader(database);
        Histogram histogram = new TitleTypeHistogram(reader.readTitles());
        MainFrame mainFrame = new MainFrame();
        mainFrame.showHistogramDisplay(histogram);
        mainFrame.setVisible(true);


    }
}
