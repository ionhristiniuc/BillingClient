import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class InboxMessagaFrame extends JFrame {
    private JPanel contentPanel;
    private JLabel label;
    private JButton yesButton;
    private JButton noButton;
    private String time, number, message;

    InboxMessagaFrame(String time, String number, String message) {
        this.time = time;
        this.number = number;
        this.message = message;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        label = new JLabel("An message has arrived.\nRead now?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(20, 20, 160, 40);
        yesButton = new JButton("Yes");
        yesButton.addActionListener(e -> {
            new MessageViewerFrame(time, number, message);
        });
        contentPanel.add(yesButton);
        yesButton.setBounds(20, 100, 60, 40);
        noButton = new JButton("No");
        noButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        contentPanel.add(noButton);
        noButton.setBounds(120, 100, 60, 40);

        setSize(200, 200);
        setLocation(583, 260);
        setResizable(false);
        setTitle("Message");
        setVisible(true);
    }
}
