import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldEditor {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } catch (Exception e) {
        }

        FieldEditorWindow window = new FieldEditorWindow();
        window.drawTextFields(false);

    }
}

class FieldEditorWindow extends JFrame {

    public FieldEditorWindow() {
        setTitle("FieldEditor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public void drawTextFields(Boolean isEditMode, String... fields) {
        if(isEditMode){
            setSize(430, 310);
        }else{
            setSize(530, 410);
        }

        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        add(p1);
        add(p2);
        add(p3);

        p2.setLayout(new GridLayout(1, 3));
        JPanel p21 = new JPanel();
        JPanel p23 = new JPanel();
        JPanel logicPanel = new JPanel();

        p2.add(p21);
        p2.add(logicPanel);
        p2.add(p23);

        logicPanel.setLayout(new GridLayout(4, 1));

        int sizeTextField = 25;
        JTextField lastNameTF = new JTextField("", sizeTextField);
        JTextField firstNameTF = new JTextField( "",sizeTextField);
        JTextField patronymicTF = new JTextField("", sizeTextField);
        try{
            lastNameTF.setText(fields[0]);
            firstNameTF.setText(fields[1]);
            patronymicTF.setText(fields[2]);
        }catch(ArrayIndexOutOfBoundsException e){
            lastNameTF.setText("Фамилия");
            firstNameTF.setText("Имя");
            patronymicTF.setText("Отчество");
        }

        if(!isEditMode){
            lastNameTF.setEnabled(false);
            firstNameTF.setEnabled(false);
            patronymicTF.setEnabled(false);
        }

        logicPanel.add(lastNameTF);
        logicPanel.add(firstNameTF);
        logicPanel.add(patronymicTF);

        if(!isEditMode){
            JButton btnEdit = new JButton("Редактировать");
            logicPanel.add(btnEdit);

            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FieldEditorWindow window = new FieldEditorWindow();
                    window.drawTextFields(true,lastNameTF.getText(),firstNameTF.getText(),patronymicTF.getText());
                    setVisible(false);

                }
            });
        }else{
            JButton btnEdit = new JButton("Ок");
            logicPanel.add(btnEdit);

            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FieldEditorWindow window = new FieldEditorWindow();
                    window.drawTextFields(false,lastNameTF.getText(),firstNameTF.getText(),patronymicTF.getText());

                    setVisible(false);
                }
            });
        }

        repaint();
        revalidate();
    }
}