import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class MessageFrame extends JFrame implements ActionListener {
    private String number;
    private String message;
    private JPanel contentPanel;
    private JLabel toLabel;
    private JLabel msgLabel;
    private JTextField numberTextField;
    private JTextField messageTextField;
    private JButton sendButton;
    private JButton cancelButton;
    MessageFrame() {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {System.out.print("error");}

        contentPanel = new JPanel();
        contentPanel.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
