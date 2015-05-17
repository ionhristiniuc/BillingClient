import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class CreateMessageFrame extends JFrame {
    private String number;
    private String message;
    private JPanel contentPanel;
    private JLabel toLabel;
    private JLabel msgLabel;
    private JTextField numberTextField;
    private JTextArea messageTextArea;
    private JButton sendButton;
    private JButton cancelButton;
    CreateMessageFrame() {
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
        add(contentPanel);
        toLabel = new JLabel("TO:");
        contentPanel.add(toLabel);
        toLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toLabel.setBounds(120, 10, 60, 10);
        msgLabel = new JLabel("Message:");
        contentPanel.add(msgLabel);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        msgLabel.setBounds(80, 60, 140, 10);
        numberTextField = new JTextField();
        contentPanel.add(numberTextField);
        numberTextField.setBounds(40, 30, 220, 20);
        messageTextArea = new JTextArea();
        //messageTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane sp = new JScrollPane(messageTextArea);
        sp.setBounds(40, 80, 220, 85);
        contentPanel.add(sp);
        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {

        });
        sendButton.setBounds(40, 175, 80, 30);
        contentPanel.add(sendButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        cancelButton.setBounds(180, 175, 80, 30);
        contentPanel.add(cancelButton);

        setTitle("Send Message");
        setSize(300, 255);
        setResizable(false);
        setLocation(533, 210);
        setVisible(true);
    }
}
