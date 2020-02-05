package io.github.MontoyaR;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
// You will definitely need AT LEAST ONE MORE IMPORT

class Main {
    public static void main(String[] args) {
        // Reads a csv file and outputs the results.
        try {
            Scanner scanner = new Scanner(new File("src/Data/SEOExample.csv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.equals("")){
                    System.out.println(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The repair file.
        Path repairsFile = Paths.get("src/Data/SEOExample.csv");

        // ADD CODE HERE
        boolean pathExists = Files.exists(repairsFile);
    }
}
