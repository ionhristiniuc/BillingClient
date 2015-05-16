import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class MainFrame extends JFrame implements ActionListener {
    private String number;
    private JLabel numberLabel;
    private JButton voiceCallBtn;
    private JButton videoCallBtn;
    private JButton sendSMSBtn;
    private JButton balanceBtn;
    private JButton callHistoryBtn;
    private JPanel contentPanel;

    public MainFrame(String _number) {
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

        number = _number;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        numberLabel = new JLabel(number);
        voiceCallBtn = new JButton("Voice Call");
        voiceCallBtn.addActionListener(this);
        videoCallBtn = new JButton("Video Call");
        videoCallBtn.addActionListener(this);
        sendSMSBtn = new JButton("Send SMS");
        sendSMSBtn.addActionListener(this);
        balanceBtn = new JButton("View Balance");
        balanceBtn.addActionListener(this);
        callHistoryBtn = new JButton("View Call History");
        callHistoryBtn.addActionListener(this);
        contentPanel.add(numberLabel);
        contentPanel.add(voiceCallBtn);
        contentPanel.add(videoCallBtn);
        contentPanel.add(sendSMSBtn);
        contentPanel.add(balanceBtn);
        contentPanel.add(callHistoryBtn);
        numberLabel.setBounds(20, 40, 220, 40);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        voiceCallBtn.setBounds(60, 120, 180, 40);
        videoCallBtn.setBounds(60, 200, 180, 40);
        sendSMSBtn.setBounds(60, 280, 180, 40);
        balanceBtn.setBounds(60, 360, 180, 40);
        callHistoryBtn.setBounds(60, 440, 180, 40);
        contentPanel.setBackground(Color.LIGHT_GRAY);
        setSize(300, 550);
        setLocation(533, 95);
        setResizable(false);
        setTitle("Main");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(voiceCallBtn)) {

        } else if (e.getSource().equals(videoCallBtn)) {

        } else if (e.getSource().equals(sendSMSBtn)) {

        } else if (e.getSource().equals(balanceBtn)) {
            new BalanceFrame(number);
        } else {

        }
    }
}
