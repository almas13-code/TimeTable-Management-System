import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimeTableGUI {

    protected JFrame frame;
    protected JTable table;
    protected DefaultTableModel model;
    protected JMenuBar menuBar;
    protected TimeTable timeTable;

    public TimeTableGUI(JFrame mainFrame) {

        this.frame = mainFrame;

        frame.setTitle("TimeTable Viewer");
        frame.setSize(900, 600);

        menuBar = new JMenuBar();

        // DIRECT BUTTONS
        JMenuItem openItem = new JMenuItem("Open CSV");

        JMenuItem exportPDF = new JMenuItem("Export PDF");

        openItem.addActionListener(e -> openFile());

        exportPDF.addActionListener(e -> exportPDF());

        menuBar.add(openItem);

        menuBar.add(exportPDF);

        frame.setJMenuBar(menuBar);

        String[] columns = {
                "Day",
                "Hour",
                "Course Title",
                "Course ID",
                "Instructor",
                "Section"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.setContentPane(scrollPane);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    // helper to convert a row
    protected String[] toArray(ArrayList<String> row) {

        return row.toArray(new String[0]);
    }

    // OPEN CSV
    protected void openFile() {

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {

            try {

                String path =
                        fileChooser.getSelectedFile().getAbsolutePath();

                timeTable = new CSVFile(path);

                System.out.println(
                        "Loaded " + timeTable.size() + " rows from CSV"
                );

                updateTable();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Error reading file: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                ex.printStackTrace();
            }
        }
    }

    // UPDATE TABLE
    protected void updateTable() {

        model.setRowCount(0);

        if (timeTable != null) {

            for (int i = 0; i < timeTable.size(); i++) {

                model.addRow(toArray(timeTable.get(i)));
            }
        }
    }

    // EXPORT PDF
    private void exportPDF() {

        try {

            FileWriter writer = new FileWriter("timetable.pdf");

            writer.write("TIME TABLE\n\n");

            for (int i = 0; i < model.getRowCount(); i++) {

                for (int j = 0; j < model.getColumnCount(); j++) {

                    writer.write(
                            model.getValueAt(i, j).toString() + "    "
                    );
                }

                writer.write("\n");
            }

            writer.close();

            JOptionPane.showMessageDialog(
                    frame,
                    "PDF Exported Successfully!"
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Error exporting PDF"
            );
        }
    }
}