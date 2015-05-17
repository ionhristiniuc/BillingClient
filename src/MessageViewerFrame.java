import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class MessageViewerFrame extends JFrame implements ActionListener {
    private String time, number, message;
    private JPanel contentPanel;
    private JLabel receivedLabel;
    private JLabel fromLabel;
    private JLabel timeLabel;
    private JLabel numberLabel;
    private JTextArea messageArea;
    private JButton OKButton;

    MessageViewerFrame(String time, String number, String message) {
        this.time = time;
        this.number = number;
        this.message = message;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        receivedLabel = new JLabel("Recived:");
        contentPanel.add(receivedLabel);
        receivedLabel.setBounds(20, 20, 60, 20);
        fromLabel = new JLabel("From:");
        contentPanel.add(fromLabel);
        fromLabel.setBounds(20, 60, 60, 20);
        timeLabel = new JLabel(time);
        contentPanel.add(timeLabel);
        timeLabel.setBounds(100, 20, 80, 20);
        numberLabel = new JLabel(number);
        contentPanel.add(numberLabel);
        numberLabel.setBounds(100, 60, 80, 20);
        messageArea = new JTextArea(message);
        messageArea.setColumns(30);
        JScrollPane sp = new JScrollPane();
        contentPanel.add(sp);
        sp.setBounds(20, 100, 160, 120);
        OKButton = new JButton("OK");
        OKButton.addActionListener(this);
        contentPanel.add(OKButton);
        OKButton.setBounds(60, 240, 80, 40);

        setSize(200, 400);
        setLocation(583, 200);
        setResizable(false);
        setTitle("Message");
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
