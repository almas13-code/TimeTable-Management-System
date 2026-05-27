import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AdminGUI extends TimeTableGUI {

    public AdminGUI(JFrame mainFrame) {

        super(mainFrame);

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem =
                new JMenuItem("Open CSV");

        JMenuItem exportPDF =
                new JMenuItem("Export PDF");

        openItem.addActionListener(
                e -> openFile()
        );

        exportPDF.addActionListener(
                e -> exportPDFAdmin()
        );

        fileMenu.add(openItem);

        fileMenu.add(exportPDF);

        JMenu adminMenu = new JMenu("Edit");

        JMenuItem addButton =
                new JMenuItem("Add");

        JMenuItem editButton =
                new JMenuItem("Edit");

        JMenuItem deleteButton =
                new JMenuItem("Delete");

        adminMenu.add(addButton);

        adminMenu.add(editButton);

        adminMenu.add(deleteButton);

        menuBar.add(fileMenu);

        menuBar.add(adminMenu);

        frame.setJMenuBar(menuBar);

        // ADD
        addButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        if (timeTable == null) {

                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Open CSV first."
                            );

                            return;
                        }

                        JFrame addFrame =
                                new JFrame("Add Entry");

                        addFrame.setSize(400, 400);

                        JPanel panel =
                                new JPanel();

                        addFrame.add(panel);

                        panel.setLayout(
                                new BoxLayout(
                                        panel,
                                        BoxLayout.Y_AXIS
                                )
                        );

                        panel.add(new JLabel("Day"));
                        JTextField dayField =
                                new JTextField();

                        panel.add(dayField);

                        panel.add(new JLabel("Hour"));
                        JTextField hourField =
                                new JTextField();

                        panel.add(hourField);

                        panel.add(new JLabel("Course Title"));
                        JTextField titleField =
                                new JTextField();

                        panel.add(titleField);

                        panel.add(new JLabel("Course ID"));
                        JTextField idField =
                                new JTextField();

                        panel.add(idField);

                        panel.add(new JLabel("Instructor"));
                        JTextField instructorField =
                                new JTextField();

                        panel.add(instructorField);

                        panel.add(new JLabel("Section"));
                        JTextField sectionField =
                                new JTextField();

                        panel.add(sectionField);

                        JButton saveButton =
                                new JButton("Save");

                        panel.add(saveButton);

                        saveButton.addActionListener(
                                new ActionListener() {

                                    @Override
                                    public void actionPerformed(
                                            ActionEvent e
                                    ) {

                                        ArrayList<String> newEntry =
                                                new ArrayList<String>();

                                        newEntry.add(
                                                dayField.getText()
                                        );

                                        newEntry.add(
                                                hourField.getText()
                                        );

                                        newEntry.add(
                                                titleField.getText()
                                        );

                                        newEntry.add(
                                                idField.getText()
                                        );

                                        newEntry.add(
                                                instructorField.getText()
                                        );

                                        newEntry.add(
                                                sectionField.getText()
                                        );

                                        timeTable.add(
                                                newEntry
                                        );

                                        addFrame.dispose();

                                        updateTable();
                                    }
                                }
                        );

                        addFrame.setVisible(true);
                    }
                }
        );

        // EDIT
        editButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        if (timeTable == null
                                || timeTable.size() == 0) {

                            JOptionPane.showMessageDialog(
                                    frame,
                                    "No entries found."
                            );

                            return;
                        }

                        JFrame editFrame =
                                new JFrame("Edit Entry");

                        editFrame.setSize(400, 500);

                        JPanel panel =
                                new JPanel();

                        editFrame.add(panel);

                        panel.setLayout(
                                new BoxLayout(
                                        panel,
                                        BoxLayout.Y_AXIS
                                )
                        );

                        String[] entries =
                                new String[timeTable.size()];

                        for (int i = 0;
                             i < entries.length;
                             i++) {

                            entries[i] =
                                    "Entry " + (i + 1);
                        }

                        JComboBox<String> entrySelector =
                                new JComboBox<>(entries);

                        panel.add(entrySelector);

                        panel.add(new JLabel("Day"));
                        JTextField updatedDayField =
                                new JTextField();

                        panel.add(updatedDayField);

                        panel.add(new JLabel("Hour"));
                        JTextField updatedHourField =
                                new JTextField();

                        panel.add(updatedHourField);

                        panel.add(new JLabel("Course Title"));
                        JTextField updatedTitleField =
                                new JTextField();

                        panel.add(updatedTitleField);

                        panel.add(new JLabel("Course ID"));
                        JTextField updatedIdField =
                                new JTextField();

                        panel.add(updatedIdField);

                        panel.add(new JLabel("Instructor"));
                        JTextField updatedInstructorField =
                                new JTextField();

                        panel.add(updatedInstructorField);

                        panel.add(new JLabel("Section"));
                        JTextField updatedSectionField =
                                new JTextField();

                        panel.add(updatedSectionField);

                        JButton updateButton =
                                new JButton("Update");

                        panel.add(updateButton);

                        updateButton.addActionListener(
                                new ActionListener() {

                                    @Override
                                    public void actionPerformed(
                                            ActionEvent e
                                    ) {

                                        int idx =
                                                entrySelector
                                                        .getSelectedIndex();

                                        ArrayList<String> newEntry =
                                                new ArrayList<String>();

                                        newEntry.add(
                                                updatedDayField.getText()
                                        );

                                        newEntry.add(
                                                updatedHourField.getText()
                                        );

                                        newEntry.add(
                                                updatedTitleField.getText()
                                        );

                                        newEntry.add(
                                                updatedIdField.getText()
                                        );

                                        newEntry.add(
                                                updatedInstructorField.getText()
                                        );

                                        newEntry.add(
                                                updatedSectionField.getText()
                                        );

                                        timeTable.set(
                                                idx,
                                                newEntry
                                        );

                                        editFrame.dispose();

                                        updateTable();
                                    }
                                }
                        );

                        editFrame.setVisible(true);
                    }
                }
        );

        // DELETE
        deleteButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        if (timeTable == null
                                || timeTable.size() == 0) {

                            JOptionPane.showMessageDialog(
                                    frame,
                                    "No entries found."
                            );

                            return;
                        }

                        JFrame deleteFrame =
                                new JFrame("Delete Entry");

                        deleteFrame.setSize(300, 200);

                        JPanel panel =
                                new JPanel();

                        deleteFrame.add(panel);

                        panel.setLayout(
                                new BoxLayout(
                                        panel,
                                        BoxLayout.Y_AXIS
                                )
                        );

                        String[] entries =
                                new String[timeTable.size()];

                        for (int i = 0;
                             i < entries.length;
                             i++) {

                            entries[i] =
                                    "Entry " + (i + 1);
                        }

                        JComboBox<String> entrySelector =
                                new JComboBox<>(entries);

                        JButton deleteBtn =
                                new JButton("Delete");

                        panel.add(entrySelector);

                        panel.add(deleteBtn);

                        deleteBtn.addActionListener(
                                new ActionListener() {

                                    @Override
                                    public void actionPerformed(
                                            ActionEvent e
                                    ) {

                                        int idx =
                                                entrySelector
                                                        .getSelectedIndex();

                                        timeTable.remove(idx);

                                        deleteFrame.dispose();

                                        updateTable();
                                    }
                                }
                        );

                        deleteFrame.setVisible(true);
                    }
                }
        );
    }

    // EXPORT PDF
    private void exportPDFAdmin() {

        try {

            JFileChooser fileChooser =
                    new JFileChooser();

            fileChooser.setDialogTitle(
                    "Save PDF File"
            );

            int userSelection =
                    fileChooser.showSaveDialog(frame);

            if (userSelection ==
                    JFileChooser.APPROVE_OPTION) {

                java.io.File fileToSave =
                        fileChooser.getSelectedFile();

                String filePath =
                        fileToSave.getAbsolutePath();

                if (!filePath.endsWith(".pdf")) {

                    filePath += ".pdf";
                }

                Document document =
                        new Document();

                PdfWriter.getInstance(
                        document,
                        new java.io.FileOutputStream(filePath)
                );

                document.open();

                Font titleFont =
                        new Font(
                                Font.FontFamily.HELVETICA,
                                18,
                                Font.BOLD
                        );

                Paragraph title =
                        new Paragraph(
                                "TIME TABLE\n\n",
                                titleFont
                        );

                title.setAlignment(
                        Element.ALIGN_CENTER
                );

                document.add(title);

                PdfPTable pdfTable =
                        new PdfPTable(
                                model.getColumnCount()
                        );

                pdfTable.setWidthPercentage(100);

                Font headerFont =
                        new Font(
                                Font.FontFamily.HELVETICA,
                                12,
                                Font.BOLD
                        );

                for (int i = 0;
                     i < model.getColumnCount();
                     i++) {

                    Paragraph header =
                            new Paragraph(
                                    model.getColumnName(i),
                                    headerFont
                            );

                    pdfTable.addCell(header);
                }

                for (int rows = 0;
                     rows < model.getRowCount();
                     rows++) {

                    for (int cols = 0;
                         cols < model.getColumnCount();
                         cols++) {

                        pdfTable.addCell(
                                model.getValueAt(
                                        rows,
                                        cols
                                ).toString()
                        );
                    }
                }

                document.add(pdfTable);

                document.close();

                JOptionPane.showMessageDialog(
                        frame,
                        "PDF Saved Successfully!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Error exporting PDF"
            );

            e.printStackTrace();
        }
    }
}