package assignment05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * JAVA FX chart generator method Nickolas Komarnitsky u0717854 , Shahid Bilal
 * Razzaq u0996062
 *
 */
public class SortUtilTImingJavaFX extends Application {
	// Instance Variables
	private static final int ITERATION_COUNT = 500;
	private static Random rand;
	private static final int BILLION = 1000_000_000;

	/**
	 * Main Method
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method generates random string
	 *
	 * @return - random String
	 */
	public static String randomString(int length) {

		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder stringbuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			stringbuilder.append(c);
		}
		return stringbuilder.toString();
	}

	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("SortUtil Timing");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		final CategoryAxis xAxis2 = new CategoryAxis();
		final NumberAxis yAxis2 = new NumberAxis();

		final CategoryAxis xAxis3 = new CategoryAxis();
		final NumberAxis yAxis3 = new NumberAxis();

		final CategoryAxis xAxis4 = new CategoryAxis();
		final NumberAxis yAxis4 = new NumberAxis();

		final CategoryAxis xAxis5 = new CategoryAxis();
		final NumberAxis yAxis5 = new NumberAxis();

		xAxis.setLabel("Test number");
		yAxis.setLabel("Time (in ms)");

		xAxis2.setLabel("Test number");
		yAxis2.setLabel("Time (in ms)");

		xAxis3.setLabel("Test number");
		yAxis3.setLabel("Time (in ms)");

		xAxis4.setLabel("Test number");
		yAxis4.setLabel("Time (in ms)");

		xAxis4.setLabel("Test number");
		yAxis4.setLabel("Time (in ms)");
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		final LineChart<String, Number> lineChart2 = new LineChart<String, Number>(xAxis2, yAxis2);
		final LineChart<String, Number> lineChart3 = new LineChart<String, Number>(xAxis3, yAxis3);
		final LineChart<String, Number> lineChart4 = new LineChart<String, Number>(xAxis3, yAxis3);
		final LineChart<String, Number> lineChart5 = new LineChart<String, Number>(xAxis3, yAxis3);
		lineChart.setTitle("SortUtil timing");
		lineChart2.setTitle("SortUtil timming");
		lineChart3.setTitle("Quicksort vs mergesort");

		XYChart.Series series1 = new XYChart.Series();

		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		XYChart.Series series4 = new XYChart.Series();

		XYChart.Series series5 = new XYChart.Series();
		XYChart.Series series6 = new XYChart.Series();

		XYChart.Series series7 = new XYChart.Series();
		XYChart.Series series8 = new XYChart.Series();

		XYChart.Series series9 = new XYChart.Series();
		XYChart.Series series10 = new XYChart.Series();
		series1.setName("mergesort timing data");

		series2.setName("quicksort median of three timing data");
		series3.setName("quicksort random timing data");
		series4.setName("quicksort middle timing data");

		series5.setName("quicksort bestCase timing data");
		series6.setName("mergesort bestCase timing data");

		series7.setName("quicksort worstCase timing data");
		series8.setName("mergesort worstCase timing data");

		series9.setName("quicksort averageCase timing data");
		series10.setName("mergesort averageCase timing data");
		Comparator<Integer> comp = new CustomComparator();
		int sizeoflist = 1000;
		for (int i = 0; i < 5; i += 1) {
			long totalTime = 0;
			// ArrayList<String> arr = new
			// ArrayList<String>(Arrays.asList(readFile("moderate_word_list.txt")));
			ArrayList<Integer> arr = SortUtil.generateAverageCase(sizeoflist);
			int threshold = i * 100;
			XYChart.Series s = new XYChart.Series();
			s.setName("" + threshold);
			for (int k = 0; k < 500; k++) {
				long start = System.nanoTime(); // start timer
				SortUtil.mergesort(arr, comp, threshold);
				long stop = System.nanoTime(); // end timer
				totalTime += stop - start;
				double averageTime = totalTime / (double) BILLION;
				s.getData().add(new XYChart.Data("" + sizeoflist, averageTime));
			}
			lineChart.getData().add(s);
			sizeoflist += sizeoflist;
			// System.out.println(s.getData().toString());
		}
		long totalTime = 0;
		int threshold = 0;
		sizeoflist = 1000;
		Comparator<Integer> intComp = new CustomComparator();
		ArrayList bestCase;
		ArrayList worstCase;
		ArrayList averageCase;
		for (int k = 0; k < 4; k++) {
			bestCase = SortUtil.generateBestCase(sizeoflist);
			worstCase = SortUtil.generateWorstCase(sizeoflist);
			averageCase = SortUtil.generateAverageCase(sizeoflist);
			totalTime = 0;
			// Time bestCase
			long start = System.nanoTime(); // start timer
			SortUtil.mergesort(bestCase, intComp, threshold);
			long stop = System.nanoTime(); // end timer
			totalTime += stop - start;
			double averageTime = totalTime / (double) BILLION;
			series6.getData().add(new XYChart.Data("" + sizeoflist, averageTime));

			totalTime = 0;
			long start4 = System.nanoTime(); // start timer
			SortUtil.quicksort(bestCase, intComp);
			long stop4 = System.nanoTime(); // end timer
			totalTime += stop4 - start4;
			double averageTime4 = totalTime / (double) BILLION;
			series5.getData().add(new XYChart.Data("" + sizeoflist, averageTime4));

			totalTime = 0;
			// Time worstCase
			long start2 = System.nanoTime(); // start timer
			SortUtil.mergesort(worstCase, intComp, threshold);
			long stop2 = System.nanoTime(); // end timer
			totalTime += stop2 - start2;
			double averageTime2 = totalTime / (double) BILLION;
			series8.getData().add(new XYChart.Data("" + sizeoflist, averageTime2));

			totalTime = 0;
			long start5 = System.nanoTime(); // start timer
			SortUtil.quicksort(worstCase, intComp);
			long stop5 = System.nanoTime(); // end timer
			totalTime += stop5 - start5;
			double averageTime5 = totalTime / (double) BILLION;
			series7.getData().add(new XYChart.Data("" + sizeoflist, averageTime5));

			totalTime = 0;
			// Time averageCase
			long start6 = System.nanoTime(); // start timer
			SortUtil.mergesort(averageCase, intComp, threshold);
			long stop6 = System.nanoTime(); // end timer
			totalTime += stop6 - start6;
			double averageTime6 = totalTime / (double) BILLION;
			series10.getData().add(new XYChart.Data("" + sizeoflist, averageTime6));

			totalTime = 0;
			long start3 = System.nanoTime(); // start timer
			SortUtil.quicksort(averageCase, intComp);
			long stop3 = System.nanoTime(); // end timer
			totalTime += stop3 - start3;
			double averageTime3 = totalTime / (double) BILLION;
			series9.getData().add(new XYChart.Data("" + sizeoflist, averageTime3));
			sizeoflist += sizeoflist;
		}
		String[] sizes = { "1000", "2000", "4000", "8000", "16000", "32000", "64000", "128000", "256000" };
		double[] times = { 10410.29, 18146.11, 4812.7, 7453.36, 16326.48, 35872.59, 76436.83, 186981.09, 452752.46, 28951.27, 1875.62, 4468.76, 7518.4, 16956.56, 35334.4, 77460.03, 186333.74, 407243.47, 3666.33, 8286.93, 3990.05, 7447.56, 16338.85,
				35413.21, 77294.03, 196104.51, 413946.84 };
		int j = 0;
		for (int i = 0; i < 9; i++) {
			series2.getData().add(new XYChart.Data(sizes[i], times[j]));
			j++;
		}
		for (int i = 0; i < 9; i++) {
			series3.getData().add(new XYChart.Data(sizes[i], times[j]));
			j++;
		}
		for (int i = 0; i < 9; i++) {
			series4.getData().add(new XYChart.Data(sizes[i], times[j]));
			j++;
		}

		lineChart2.getData().add(series2);
		lineChart2.getData().add(series3);
		lineChart2.getData().add(series4);

		lineChart3.getData().add(series5);
		lineChart3.getData().add(series6);

		lineChart4.getData().add(series7);
		lineChart4.getData().add(series8);

		lineChart5.getData().add(series9);
		lineChart5.getData().add(series10);

		Scene scene1 = new Scene(lineChart, 800, 600);
		Scene scene2 = new Scene(lineChart2, 800, 600);
		Scene scene3 = new Scene(lineChart3, 800, 600);
		Scene scene4 = new Scene(lineChart4, 800, 600);
		Scene scene5 = new Scene(lineChart5, 800, 600);

		Stage stage2 = new Stage();
		Stage stage3 = new Stage();
		Stage stage4 = new Stage();
		Stage stage5 = new Stage();

		lineChart.setCreateSymbols(false);
		lineChart2.setCreateSymbols(false);
		lineChart3.setCreateSymbols(false);
		lineChart4.setCreateSymbols(false);
		lineChart5.setCreateSymbols(false);

		stage.setScene(scene1);
		stage2.setScene(scene2);
		stage3.setScene(scene3);
		stage4.setScene(scene4);
		stage5.setScene(scene5);
		stage.show();
		stage2.show();
		stage3.show();
		stage4.show();
		stage5.show();
	}
}
