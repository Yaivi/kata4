package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Histogram;
import software.ulpgc.kata4.model.Title;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TitleTypeHistogram implements Histogram {
    private final HashMap<Title.TitleType, Integer> histogram;

    public TitleTypeHistogram(Iterator<Title> titles) {
        histogram = createHistogram(titles);
    }

    private static HashMap<Title.TitleType, Integer> createHistogram(Iterator<Title> titles) {
        HashMap<Title.TitleType, Integer> histogram = new HashMap<>();
        for (Iterator<Title> it = titles; it.hasNext(); ) {
            Title title = it.next();
            if (title == null) {
                continue;
            }
            histogram.put(title.getTitleType(), histogram.getOrDefault(title.getTitleType(), 0)+1);
        }
        return histogram;
    }

    @Override
    public int bins() {
        return 20;
    }

    @Override
    public double[] values() {
        return histogram.values().stream().mapToDouble(Integer::doubleValue).toArray();
    }

    public HashMap<Title.TitleType, Integer> getHistogram() {
        return histogram;
    }
}
