import java.util.ArrayList;

interface TimeTable {

    void add(ArrayList<String> entry);

    int size();

    ArrayList<String> get(int row);

    void set(int row, ArrayList<String> newEntry);

    void remove(int row);
}