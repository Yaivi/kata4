package software.ulpgc.kata4.view;

import software.ulpgc.kata4.model.Histogram;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private HistogramDisplay histogramDisplay;

    public MainFrame() throws HeadlessException {
        this.setTitle("Histogram");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        createHistogramDisplay();
        add((JPanel) histogramDisplay);
    }

    private Component createHistogramDisplay() {
        JFreeChartHistogramDisplay display = new JFreeChartHistogramDisplay();
        this.histogramDisplay = display;
        return display;
    }

    public void showHistogramDisplay(Histogram histogram) {
        this.histogramDisplay.show(histogram);
    }


}
