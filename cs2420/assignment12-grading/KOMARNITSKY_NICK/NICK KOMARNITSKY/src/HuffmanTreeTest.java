package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import Testing.DataForm;
import Testing.ProgressForm;
import Testing.Testing;
import Testing.xyData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HuffmanTreeTest {
	Testing test = new Testing();
	private final int ITERATION_COUNT;
	private static int weight;
	private ObservableList<Button> elements = FXCollections.observableArrayList();
	private ObservableList<xyData> series1 = FXCollections.observableArrayList();
	private ObservableList<xyData> series2 = FXCollections.observableArrayList();
	private Label label = new Label("Assignment12 Tests");
	private Button button1 = new Button("Compressed vs Uncompressed");
	private Button button2 = new Button("Frequency");
	ProgressForm pForm = new ProgressForm();

	public HuffmanTreeTest(int ITERATION_COUNT) {
		this.ITERATION_COUNT = ITERATION_COUNT;
		elements.addAll(button1, button2);
		button1.setOnAction(e -> {
			DataForm dForm = new DataForm();
			pForm.activateProgressBar(task1);
			task1.setOnSucceeded(event -> {
				pForm.getDialogStage().close();
				dForm.getData().add(series1);
				dForm.SetupDataForm("Assignment12 Test", "Unique Characters", "Original size/Compression size",
						"Compression data", "ratio");
				dForm.getDataStage().show();
				button1.setDisable(false);
			});

			button1.setDisable(true);
			pForm.getDialogStage().show();

			Thread thread = new Thread(task1);
			thread.start();
		});
		button2.setOnAction(e -> {
			DataForm dForm = new DataForm();
			pForm.activateProgressBar(task2);
			task1.setOnSucceeded(event -> {
				pForm.getDialogStage().close();
				dForm.getData().add(series1);
				dForm.SetupDataForm("Assignment12 Test", "Frequency", "Original size/Compression size",
						"Compression data", "ratio");
				dForm.getDataStage().show();
				button2.setDisable(false);
			});

			button2.setDisable(true);
			pForm.getDialogStage().show();

			Thread thread = new Thread(task1);
			thread.start();
		});
	}

	private Task<Void> task1 = new Task<Void>() {
		@Override
		public Void call() throws InterruptedException, IOException {
			int progress = 0;
			int totalCount = ITERATION_COUNT + ITERATION_COUNT;
			writeFilesUC();
			for (int k = 0; k <= ITERATION_COUNT; k++) {
				updateProgress(progress, totalCount);
				compressFile(k + ".txt", k + "compressed.txt");
				decompressFile(k + "compressed.txt", k + "decompressed.txt");
				File original = new File(k + ".txt");
				File compressed = new File(k + "compressed.txt");
				xyData d = new xyData("" + weight, original.length() / compressed.length());
				series1.add(d);
				progress++;
			}
			for (int i = 0; i < ITERATION_COUNT; i++) {
				updateProgress(progress, totalCount);
				deleteFile(i + ".txt");
				deleteFile(i + "compressed.txt");
				deleteFile(i + "decompressed.txt");
				progress++;
			}
			updateProgress(totalCount, totalCount);
			return null;
		}
	};
	private Task<Void> task2 = new Task<Void>() {
		@Override
		public Void call() throws InterruptedException, IOException {
			int progress = 0;
			int totalCount = ITERATION_COUNT + ITERATION_COUNT;
			for (int k = 0; k <= ITERATION_COUNT; k++) {
				writeFilesF(k);
				updateProgress(progress, totalCount);
				compressFile(k + ".txt", k + "compressed.txt");
				decompressFile(k + "compressed.txt", k + "decompressed.txt");
				File original = new File(k + ".txt");
				File compressed = new File(k + "compressed.txt");
				xyData d = new xyData("" + k, original.length() / compressed.length());
				series1.add(d);
				progress++;
			}
			for (int i = 0; i < ITERATION_COUNT; i++) {
				updateProgress(progress, totalCount);
				deleteFile(i + ".txt");
				deleteFile(i + "compressed.txt");
				deleteFile(i + "decompressed.txt");
				progress++;
			}
			updateProgress(totalCount, totalCount);
			return null;
		}
	};

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File(infile), new File(outfile));
		weight = t.getWeightTotal();
	}

	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();
		t.decompressFile(new File(infile), new File(outfile));
	}

	private static boolean writeFilesUC() throws IOException {
		try {
			for (int i = 32; i < 126; i++) {
				FileWriter fw = new FileWriter(i - 32 + ".txt");
				PrintWriter pr = new PrintWriter(fw);
				for (int k = 32; k < i; k++) {
					pr.write((char) i);
				}
				pr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean writeFilesF(int f) throws IOException {
		try {
			for (int i = 32; i < 126; i++) {
				FileWriter fw = new FileWriter(i - 32 + ".txt");
				PrintWriter pr = new PrintWriter(fw);
				for (int k = 32; k < i; k++) {
					for (int x = 0; x < f; x++) {
						pr.write((char) k);
					}
				}
				pr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean deleteFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.delete()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Testing getTest() {
		return test;
	}

	public void setTest(Testing test) {
		this.test = test;
	}

	public ObservableList<Button> getElements() {
		return elements;
	}

	public void setElements(ObservableList<Button> elements) {
		this.elements = elements;
	}

	public ObservableList<xyData> getSeries1() {
		return series1;
	}

	public void setSeries1(ObservableList<xyData> series1) {
		this.series1 = series1;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Button getButton1() {
		return button1;
	}

	public void setButton1(Button button1) {
		this.button1 = button1;
	}

	public ProgressForm getpForm() {
		return pForm;
	}

	public void setpForm(ProgressForm pForm) {
		this.pForm = pForm;
	}

	public Task<Void> getTask1() {
		return task1;
	}

	public void setTask1(Task<Void> task1) {
		this.task1 = task1;
	}

	public int getITERATION_COUNT() {
		return ITERATION_COUNT;
	}

}
