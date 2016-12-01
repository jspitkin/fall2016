package assignment10;

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

public class HashTableCharter {

	public void createChart(String dataFile, String filename) {
		String chartName;
		String chartType;
		if(dataFile.equals("tableCollisions.tsv")){
			chartName = "Hash Table Collisions";
			chartType = "Number of Collisions";
		}
		else{
			chartName = "Hash Functor Timing";
			chartType = "Time (ns)";
		}
		JFreeChart chart = ChartFactory.createXYLineChart(chartName, "Size (N)", chartType, createDataSet(dataFile));
//		chart.getXYPlot().setDomainAxis(new LogarithmicAxis("Size (N"));
		try {
			ChartUtilities.saveChartAsPNG(new File(filename), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showChart(chart);
	}

	private void showChart(JFreeChart chart) {
		JFrame frame = new JFrame();
	    frame.setTitle("Hash Table Experiment");
	    ChartPanel chartPanel = new ChartPanel(chart);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(400, 200));
	    frame.add(chartPanel);
	    frame.pack();
	    frame.setVisible(true);
	}

	private XYDataset createDataSet(String dataFile) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		try(FileReader reader = new FileReader(dataFile);
				BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series1 = new XYSeries("QuadProbe Table");
			while((line = br.readLine()) != null) {
				if (line.equals("newData")) {
					break;
				}
				String[] split = line.split("\t");
				series1.add(new XYDataItem(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
			}
			dataset.addSeries(series1);
			
			XYSeries series2 = new XYSeries("SeparateChaining Table");
			while((line = br.readLine()) != null) {
				if (line.equals("newData")) {
					break;
				}
				String[] split = line.split("\t");
				series2.add(new XYDataItem(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
			}
			dataset.addSeries(series2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataset;
	}
}
