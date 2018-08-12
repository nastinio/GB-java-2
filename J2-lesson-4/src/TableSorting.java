import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class TableSorting {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } catch (Exception e) {
        }

        String[][] data = {{"Васечкин", "34", "50000"}, {"Петров", "29", "45000"}, {"Федяев", "47", "68000"}};
        new TableWindow(data);

    }
}

class TableWindow extends JFrame {
    TableWindow(String[][] data) {
        setLayout(new BorderLayout());
        setSize(630, 480); //шв
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel primaryPanel = new JPanel(new BorderLayout());
        setContentPane(primaryPanel);

        primaryPanel.add(createTable(data));

        repaint();
        revalidate();
    }

    public JPanel createTable(String[][] data) {
        String[] colHeads = {"Фамилия", "Возраст", "З/П"};

        JPanel jp = new JPanel(new BorderLayout());
        Font font = new Font("Verdana", Font.ROMAN_BASELINE, 13);

        JPanel lblPanel = new JPanel(new GridLayout(3, 1));
        JLabel l1 = new JLabel(" ");
        JLabel l2 = new JLabel("ТАБЛИЦА СЕКРЕТНЫХ ДАННЫХ");
        JLabel l3 = new JLabel(" ");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2.setHorizontalAlignment(JLabel.CENTER);
        lblPanel.add(l1);
        lblPanel.add(l2);
        lblPanel.add(l3);

        JTable table = new JTable(data, colHeads);
        table.setAutoCreateRowSorter(true);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setVisible(true);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        jp.add(lblPanel, BorderLayout.PAGE_START);
        jp.add(jsp, BorderLayout.CENTER);

        return jp;
    }
}

