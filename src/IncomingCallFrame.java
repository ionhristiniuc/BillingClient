import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class IncomingCallFrame extends JFrame {
    private String number;
    private JPanel contentPanel;
    private JLabel label;
    private JLabel numberLabel;
    private JButton answerButton;
    private JButton rejectButton;

    IncomingCallFrame(String number) {
        this.number = number;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        label = new JLabel("Incoming call from:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(20, 20, 160, 20);
        contentPanel.add(label);
        numberLabel = new JLabel(number);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setBounds(40, 60, 120, 20);
        contentPanel.add(numberLabel);
        answerButton = new JButton("Answer");
        answerButton.addActionListener(e -> {
            // send message to answer
        });
        contentPanel.add(answerButton);
        answerButton.setBounds(20, 100, 60, 40);
        rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> {
            // send message to reject
            setVisible(false);
            dispose();
        });
        contentPanel.add(rejectButton);
        rejectButton.setBounds(120, 100, 60, 40);
        setSize(200, 200);
        setLocation(583, 260);
        setResizable(false);
        setTitle("Incoming Call");
        setVisible(true);
    }
}
