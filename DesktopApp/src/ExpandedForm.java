import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ExpandedForm {
    private JButton collapseButton;
    private JTextField surnameTextField;
    private JTextField firstnameTextField;
    private JPanel mainPanel;
    private JTextField patronymicTextField;

    public ExpandedForm() {
        collapseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surnameRegex = "[А-ЯЁ][а-яё]{2,}([-][А-ЯЁ][а-яё]{2,})?";
                String nameRegex = "[А-ЯЁ][а-яё]{2,}";
                try {
                    Pattern pattern = Pattern.compile(surnameRegex);
                    String surname = surnameTextField.getText().strip();
                    if (!pattern.matcher(surname).find()) {
                        throw new Exception("Неправильно задана фамилия!");
                    }
                    pattern = Pattern.compile(nameRegex);
                    String firstname = firstnameTextField.getText().strip();
                    if (!pattern.matcher(firstname).find()) {
                        throw new Exception("Неправильно задано имя!");
                    }
                    String patronymic = patronymicTextField.getText().strip();
                    if (!patronymic.isEmpty()) {
                        pattern = Pattern.compile(nameRegex);
                        if (!pattern.matcher(patronymic).find()) {
                            throw new Exception("Неправильно задано отчество!");
                        }
                    }

                    Main.switchToAnother(surname + " " + firstname +
                            (patronymic.isEmpty() ? "" : " " + patronymic));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            ex.getMessage(),
                            "Ошибка ввода",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void acceptFullname(String fullname) {
        String[] parts = fullname.split(" ");
        surnameTextField.setText(parts[0]);
        firstnameTextField.setText(parts[1]);
        if (parts.length == 3) {
            patronymicTextField.setText(parts[2]);
        } else {
            patronymicTextField.setText("");
        }
        surnameTextField.requestFocusInWindow();
        surnameTextField.setCaretPosition(0);
    }
}
