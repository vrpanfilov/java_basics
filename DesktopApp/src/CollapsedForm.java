import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class CollapsedForm {
    private JPanel mainPanel;
    private JButton expandButton;
    private JTextField fullNameTextField;
    private JTextField textField2;
    private JTextField textField3;

    public CollapsedForm() {
        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regex = "^[А-ЯЁ][а-яё]{2,}([-][А-ЯЁ][а-яё]{2,})?\\s[А-ЯЁ][а-яё]{2,}(\\s[А-ЯЁ][а-яё]{2,})*$";
                Pattern pattern = Pattern.compile(regex);
                String fullname = fullNameTextField.getText().strip();
                fullname = fullname.replaceAll("( )+", " ");
                if (!pattern.matcher(fullname).find()) {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Неправильно введено ФИО!",
                            "Ошибка ввода",
                            JOptionPane.PLAIN_MESSAGE
                    );
                } else {
                    Main.switchToAnother(fullname);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void acceptFullname(String fullname) {
        fullNameTextField.setText(fullname);
        fullNameTextField.requestFocusInWindow();
        fullNameTextField.setCaretPosition(0);
    }
}
