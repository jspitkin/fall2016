//Cole Cruz
package assignment07;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Creates a chart based off of a data file.
 * 
 * @author unknown
 *
 */
public class Charter {
	
	public void createChart(String windowTitle, String graphName, String xAxisLabel, String yAxisLabel,
			String lineLabel, String dataFile, String lineLabel2, String dataFile2, String lineLabel3, String dataFile3, String filename) {
		JFreeChart chart = ChartFactory.createXYLineChart(graphName, xAxisLabel, yAxisLabel,
				createDataSet(dataFile, lineLabel, dataFile2, lineLabel2, dataFile3, lineLabel3));
		try {
			ChartUtilities.saveChartAsPNG(new File(filename), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showChart(chart, windowTitle);
	}

	private void showChart(JFreeChart chart, String windowTitle) {
		JFrame frame = new JFrame();
		frame.setTitle(windowTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 200));
		frame.add(chartPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private XYDataset createDataSet(String dataFile, String lineLabel, String dataFile2, String lineLabel2, String dataFile3, String lineLabel3) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		// add more series to collection for multiple lines
		try (FileReader reader = new FileReader(dataFile); BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series = new XYSeries(lineLabel);
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\t");
				series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
			}
			dataset.addSeries(series);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (FileReader reader = new FileReader(dataFile2); BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series = new XYSeries(lineLabel2);
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\t");
				series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
			}
			dataset.addSeries(series);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (FileReader reader = new FileReader(dataFile3); BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series = new XYSeries(lineLabel3);
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
