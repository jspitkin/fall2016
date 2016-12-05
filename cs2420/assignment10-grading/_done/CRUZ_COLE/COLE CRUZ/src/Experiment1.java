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
public class Experiment1 {

	private static QuadProbeHashTable testHash;
	private static BadHashFunctor bhf = new BadHashFunctor();
	private static MediocreHashFunctor mhf = new MediocreHashFunctor();
	private static GoodHashFunctor ghf = new GoodHashFunctor();
	private static ArrayList<String> listToAdd;
	private static XYSeriesCollection data;
	private static XYSeries bhfDataPoints;
	private static XYSeries mhfDataPoints;
	private static XYSeries ghfDataPoints;

	/**
	 * Does experiment stuff.
	 * 
	 * @param args
	 *            -- arguments.
	 */
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;
		data = new XYSeriesCollection();
		bhfDataPoints = new XYSeries("BadHashFunctor");
		mhfDataPoints = new XYSeries("MediocreHashFunctor");
		ghfDataPoints = new XYSeries("GoodHashFunctor");
		for (int size = 20; size <= 200; size += 5) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, bhf);
			testHash.addAll(listToAdd);
			bhfDataPoints.add(new XYDataItem(testHash.storage.length, testHash.collisions));
		}
		data.addSeries(bhfDataPoints);
		for (int size = 20; size <= 200; size += 5) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, mhf);
			testHash.addAll(listToAdd);
			mhfDataPoints.add(new XYDataItem(testHash.storage.length, testHash.collisions));
		}
		data.addSeries(mhfDataPoints);
		for (int size = 20; size <= 200; size += 5) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, ghf);
			testHash.addAll(listToAdd);
			ghfDataPoints.add(new XYDataItem(testHash.storage.length, testHash.collisions));
		}
		data.addSeries(ghfDataPoints);
		JFreeChart lineChart = ChartFactory.createXYLineChart("# of Collisions Per Hash Table Size", "Hash Table Size",
				"# of Collisions", data);
		File file = new File("experiment1part1.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(file, lineChart, 1000, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// finished first times
		data = new XYSeriesCollection();
		bhfDataPoints = new XYSeries("BadHashFunctor");
		mhfDataPoints = new XYSeries("MediocreHashFunctor");
		ghfDataPoints = new XYSeries("GoodHashFunctor");
		for (int size = 20; size <= 200; size += 10) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, bhf);
			long start, stop, total = 0;
			for (String item : listToAdd) {
				start = System.nanoTime();
				testHash.add(item);
				stop = System.nanoTime();
				total += stop - start;
			}
			bhfDataPoints.add(new XYDataItem(testHash.storage.length, total / (long) size));
		}
		data.addSeries(bhfDataPoints);
		for (int size = 20; size <= 200; size += 10) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, mhf);
			long start, stop, total = 0;
			for (String item : listToAdd) {
				start = System.nanoTime();
				testHash.add(item);
				stop = System.nanoTime();
				total += stop - start;
			}
			mhfDataPoints.add(new XYDataItem(testHash.storage.length, total / (long) size));
		}
		data.addSeries(mhfDataPoints);
		for (int size = 20; size <= 200; size += 10) {
			listToAdd = new ArrayList<String>();
			for (int index = 0; index < size; index++) {
				listToAdd.add("" + index);
			}
			testHash = new QuadProbeHashTable(size, ghf);
			long start, stop, total = 0;
			for (String item : listToAdd) {
				start = System.nanoTime();
				testHash.add(item);
				stop = System.nanoTime();
				total += stop - start;
			}
			ghfDataPoints.add(new XYDataItem(testHash.storage.length, total / (long) size));
		}
		data.addSeries(ghfDataPoints);
		lineChart = ChartFactory.createXYLineChart("Hash Function Performance for Hash Table Size", "Hash Table Size",
				"Time(ns)", data);
		file = new File("experiment1part2.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(file, lineChart, 1000, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
