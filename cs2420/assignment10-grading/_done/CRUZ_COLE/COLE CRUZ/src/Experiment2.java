//Cole Cruz
package assignment10;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Experiment for hash tables.
 * 
 * @author Cole Cruz
 *
 */
public class Experiment2 {

	private static QuadProbeHashTable testQPHash;
	private static ChainingHashTable testCHash;
	private static GoodHashFunctor ghf = new GoodHashFunctor();
	private static ArrayList<String> listToAdd;
	private static XYSeriesCollection data;
	private static XYSeries qpDataPoints;
	private static XYSeries cDataPoints;

	/**
	 * Does experiment stuff.
	 * 
	 * @param args
	 *            -- arguments.
	 */
	public static void main(String[] args) {
		data = new XYSeriesCollection();
		qpDataPoints = new XYSeries("QuadProbeHashTable");
		cDataPoints = new XYSeries("ChainingHashTable");
		for (int size = 20; size <= 200; size += 5) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testQPHash = new QuadProbeHashTable(size, ghf);
			testQPHash.addAll(listToAdd);
			qpDataPoints.add(new XYDataItem(testQPHash.storage.length, testQPHash.collisions));
		}
		data.addSeries(qpDataPoints);
		for (int size = 20; size <= 200; size += 5) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testCHash = new ChainingHashTable(size, ghf);
			testCHash.addAll(listToAdd);
			cDataPoints.add(new XYDataItem(testCHash.storage.length, testCHash.collisions));
		}
		data.addSeries(cDataPoints);
		JFreeChart lineChart = ChartFactory.createXYLineChart("# of Collisions Per Hash Table Type", "Hash Table Size",
				"# of Collisions", data);
		File file = new File("experiment2part1.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(file, lineChart, 1000, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// finished first times
		data = new XYSeriesCollection();
		qpDataPoints = new XYSeries("QuadProbeHashTable");
		cDataPoints = new XYSeries("ChainingHashTable");
		for (int size = 20; size <= 200; size += 10) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testQPHash = new QuadProbeHashTable(size, ghf);
			long start, stop, total = 0;
			start = System.nanoTime();
			testQPHash.addAll(listToAdd);
			stop = System.nanoTime();
			total = stop - start;
			qpDataPoints.add(new XYDataItem(testQPHash.storage.length, total / (long) size));
		}
		data.addSeries(qpDataPoints);
		for (int size = 20; size <= 200; size += 10) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testCHash = new ChainingHashTable(size, ghf);
			long start, stop, total = 0;
			start = System.nanoTime();
			testCHash.addAll(listToAdd);
			stop = System.nanoTime();
			total = stop - start;
			cDataPoints.add(new XYDataItem(testCHash.storage.length, total / (long) size));
		}
		data.addSeries(cDataPoints);
		lineChart = ChartFactory.createXYLineChart("Hash Function Performance for Hash Table Type", "Hash Table Size",
				"Time(ns)", data);
		file = new File("experiment2part2.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(file, lineChart, 1000, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
