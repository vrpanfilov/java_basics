import javax.swing.*;

public class Main {
    private static ExpandedForm expandedForm;
    private static CollapsedForm collapsedForm;
    private static JFrame frame;
    private static boolean expandedIsActive = true;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(360, 240);

        expandedForm = new ExpandedForm();
        collapsedForm = new CollapsedForm();

        frame.add(expandedForm.getMainPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void switchToAnother(String fullname) {
        if (expandedIsActive) {
            frame.remove(expandedForm.getMainPanel());
            frame.add(collapsedForm.getMainPanel());
            collapsedForm.acceptFullname(fullname);
        } else {
            frame.remove(collapsedForm.getMainPanel());
            frame.add(expandedForm.getMainPanel());
            expandedForm.acceptFullname(fullname);
        }
        expandedIsActive = !expandedIsActive;

        frame.setVisible(true);
        frame.repaint();
    }
}
