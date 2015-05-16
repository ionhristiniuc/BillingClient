import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Created by Mihai on 5/16/2015.
 */
public class MainFrame extends JFrame {
    private String number;
    private JLabel numberLabel;
    private JButton voiceCallBtn;
    private JButton videoCallBtn;
    private JButton sendSMSBtn;
    private JButton balanceBtn;
    private JButton callHistoryBtn;
    private JPanel contentPanel;
    private ServiceManager serviceManager;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    public MainFrame(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
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
        numberLabel = new JLabel(number);
        voiceCallBtn = new JButton("Voice Call");
        voiceCallBtn.addActionListener(e -> {

        });
        videoCallBtn = new JButton("Video Call");
        videoCallBtn.addActionListener(e -> {

        });
        sendSMSBtn = new JButton("Send SMS");
        sendSMSBtn.addActionListener(e -> {

        });
        balanceBtn = new JButton("View Balance");
        balanceBtn.addActionListener(e -> {
            serviceManager.sendMessage(BALANCE);
        });
        callHistoryBtn = new JButton("View Call History");
        callHistoryBtn.addActionListener(e -> {

        });
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

        executorService.execute(this::listenServer);
    }

    public void listenServer()
    {
        String message = null;
        do
        {
            message = serviceManager.receiveMessage();
            String[] data = message.split(Pattern.quote(SEPARATOR));
            //JOptionPane.showMessageDialog(null, message);

            switch (data[0])
            {
                case BALANCE:
                    SwingUtilities.invokeLater(() -> {
                        new BalanceFrame(data[1]);
                    });
                    break;
            }
        } while (!message.equals(DISCONNECT));

    }
}
