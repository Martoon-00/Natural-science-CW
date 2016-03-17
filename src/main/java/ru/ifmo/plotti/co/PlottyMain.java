package ru.ifmo.plotti.co;

import ru.ifmo.plotti.co.send.PlotColor;
import ru.ifmo.plotti.co.send.PlotValueBuilder;
import ru.ifmo.plotti.co.send.PattiVisualizer;

public class PlottyMain {
    public static void main(String[] args) {
        System.out.println("Visit http://plotti.co/NS-CourceWork-year2012/plot.svg");
        PattiVisualizer viz = new PattiVisualizer("NS-CourceWork-year2012");
        viz.reset();

        for (int i = 0; i < 5; i++) {
            viz.plot(new PlotValueBuilder()
                    .add(PlotColor.BLUE, 2)
                    .add(PlotColor.RED, 5));
        }
    }
}
