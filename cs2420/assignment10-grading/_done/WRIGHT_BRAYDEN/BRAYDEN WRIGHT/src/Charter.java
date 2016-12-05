package assignment10;

/**
 * File created (by a TA?) for Lab 2
 *
 * This file seems pretty useful for creating relatively quick plots.
 * Since we've been told we can utilize any of the code used in labs,
 * I'm gonna use this to create this assignment's plots.
 *
 * @author Modified by Brayden Wright
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Charter {

    public void createChart(String dataFile, String filename, String title, String xLabel, String operation) {
        JFreeChart chart = ChartFactory.createXYLineChart(title, xLabel, "Time (ms)", createDataSet(dataFile, operation));
//		chart.getXYPlot().setDomainAxis(new LogarithmicAxis("Size (N"));
        try {
            ChartUtilities.saveChartAsPNG(new File(filename), chart, 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
//		showChart(chart);
        System.out.println("Plot saved to: " + filename);
    }

    private void showChart(JFreeChart chart) {
        JFrame frame = new JFrame();
        frame.setTitle("Remove Timing Experiement");
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private XYDataset createDataSet(String dataFile, String operation) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        try (FileReader reader = new FileReader(dataFile);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            XYSeries series = new XYSeries(operation);
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
            }
            dataset.addSeries(series);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}
