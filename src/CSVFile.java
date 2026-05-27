import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Simple CSV-backed implementation of TimeTable.
 * Each line is a row, comma separated.
 */
class CSVFile implements TimeTable {

    File file;
    ArrayList<ArrayList<String>> content;

    CSVFile(String fileName) {

        try {

            file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            content = new ArrayList<>();

            loadContentFromFile();

        } catch (IOException e) {

            e.printStackTrace();
            content = new ArrayList<>();
        }
    }

    // Method to load content from the file into the content ArrayList
    private void loadContentFromFile() {

        content.clear();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                ArrayList<String> row =
                        new ArrayList<>(Arrays.asList(line.split(",")));

                content.add(row);
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    String[] find(String searchEntry) {

        if (searchEntry == null)
            return null;

        for (ArrayList<String> row : content) {

            if (!row.isEmpty() && row.get(0).equals(searchEntry)) {

                return row.toArray(new String[0]);
            }
        }

        return null;
    }

    @Override
    public void add(ArrayList<String> entry) {

        content.add(entry);

        saveContentToFile();
    }

    @Override
    public int size() {

        return content.size();
    }

    @Override
    public ArrayList<String> get(int row) {

        return content.get(row);
    }

    @Override
    public void remove(int row) {

        content.remove(row);

        saveContentToFile();
    }

    @Override
    public void set(int row, ArrayList<String> newEntry) {

        content.set(row, newEntry);

        saveContentToFile();
    }

    // Method to save content ArrayList back to the file
    private void saveContentToFile() {

        try (PrintWriter writer = new PrintWriter(file)) {

            for (ArrayList<String> row : content) {

                writer.println(String.join(",", row));
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}