package APITestAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReader {

	private void csvReaderMethod1(String filePath) {

		try {
			Scanner scanner = new Scanner(new File(filePath));

			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				System.out.print(scanner.next() + " ");
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

    private void csvReaderMethod2(String filePath) {

		try {
			String line = "";
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				while ((line = br.readLine()) != null) {
					String[] laptop = line.split(",");
					System.out.println(laptop[0] + " " + laptop[1] + " " + laptop[2] + " " + laptop[3]);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void csvReaderMethod3(String filePath) {
		try {
			FileReader fileReader = new FileReader(filePath);
			try (CSVReader csvReader = new CSVReader(fileReader)) {
				String[] line;
				while ((line = csvReader.readNext()) != null) {
					for (String data : line) {
						System.out.print(data + " ");
					}
					System.out.println();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		String filePath = new File("").getAbsolutePath() + File.separator
				+ "/src/test/resources/SchemaValidation/csvdataReading.csv";
		System.out.println(filePath);
		CsvReader csvObj = new CsvReader();
		System.out.println("********Reading data through Scanner Class********");
		csvObj.csvReaderMethod1(filePath);
        System.out.println("\n********Reading data through String.split() method********");
		csvObj.csvReaderMethod2(filePath);
		System.out.println("********Reading data through OpenCSV API********");
		csvObj.csvReaderMethod3(filePath);

	}
}
