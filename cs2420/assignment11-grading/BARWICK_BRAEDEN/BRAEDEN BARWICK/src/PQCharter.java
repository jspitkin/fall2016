package assignment11;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Modified Charter.java class to work with multiple datasets
 * @author Braeden Barwick: u0959391
 *
 */
public class PQCharter {
	
	/**
	 * creates a chart from the specified dataFile
	 * @param dataFile the name of the output file
	 * @param filename name of the file containing the data being used to graph
	 */
	public void createChart(String dataFile, String filename) {
		JFreeChart chart = ChartFactory.createXYLineChart("Priority Queue Timing", "List Size (N)", "Time (ns)", createDataSet(dataFile));

		try {
			ChartUtilities.saveChartAsPNG(new File(filename), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showChart(chart);
	}
	
	/**
	 * Shows the chart in a window
	 * @param chart -- chart to be displayed
	 */
	private void showChart(JFreeChart chart) {
		JFrame frame = new JFrame();
	    frame.setTitle("Priority Queue Timing");
	    ChartPanel chartPanel = new ChartPanel(chart);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(400, 200));
	    frame.add(chartPanel);
	    frame.pack();
	    frame.setVisible(true);
	}

	/**
	 * Modified to be able to create multiple datasets from one file using the designator string "newData"
	 * @param dataFile
	 * @return
	 */
	private XYDataset createDataSet(String dataFile) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		try (FileReader reader = new FileReader(dataFile); BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series = new XYSeries("findMin()");
			while ((line = br.readLine()) != null) {
				if (line.equals("newData")) {
					break;
				}
				String[] split = line.split("\t");
				series.add(new XYDataItem(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
			}
			dataset.addSeries(series);
			
			XYSeries series2 = new XYSeries("deleteMin()");
			while ((line = br.readLine()) != null) {
				if (line.equals("newData")) {
					break;
				}
				String[] split = line.split("\t");
				series2.add(new XYDataItem(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
			}
			dataset.addSeries(series2);
			
			XYSeries series3 = new XYSeries("add()");
			while ((line = br.readLine()) != null) {
				if (line.equals("newData")) {
					break;
				}
				String[] split = line.split("\t");
				series3.add(new XYDataItem(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
			}
			dataset.addSeries(series3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataset;
	}
}
