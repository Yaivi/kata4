package software.ulpgc.kata4.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.control.TitleTypeHistogram;
import software.ulpgc.kata4.model.Histogram;
import software.ulpgc.kata4.model.Title;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class JFreeChartHistogramDisplay extends JPanel implements HistogramDisplay {
    @Override
    public void show(Histogram provider) {
        JFreeChart histogram = ChartFactory.createBarChart(
                "Histogram",
                "Interval",
                "Frequency",
                datasetWith(provider),
                PlotOrientation.VERTICAL,
                true,
                false,
            false
        );

        add(new ChartPanel(histogram));

    }

    private DefaultCategoryDataset datasetWith(Histogram provider) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HashMap<Title.TitleType, Integer> intervalData = ((TitleTypeHistogram) provider).getHistogram();
        for (Map.Entry<Title.TitleType, Integer> entry : intervalData.entrySet()) {
            String interval = String.valueOf(entry.getKey());
            Integer frequency = entry.getValue();
            dataset.addValue(frequency, "Frequency", interval);
        }
        return dataset;
    }
}
