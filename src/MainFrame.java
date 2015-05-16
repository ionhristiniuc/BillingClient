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
    private IncomingCallFrame icf;
    private NewCallFrame ncf;

    public MainFrame(ServiceManager serviceManager, String number) {
        this.serviceManager = serviceManager;
        this.number = number;
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
            new NewCallFrame(CallFrame.CallType.VoiceCall, serviceManager);
        });
        videoCallBtn = new JButton("Video Call");
        videoCallBtn.addActionListener(e -> {
            new NewCallFrame(CallFrame.CallType.VideoCall, serviceManager);
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
            //JOptionPane.showMessageDialog(null, message);
            processMessage(message);

        } while (!message.equals(DISCONNECT));

    }

    private void processMessage( String message )
    {
        String[] data = message.split(Pattern.quote(SEPARATOR));
        displayMessage(message);

        switch (data[0])
        {
            case BALANCE:
                SwingUtilities.invokeLater(() -> {
                    //new BalanceFrame(data[1]);
                    JOptionPane.showMessageDialog(this, "Balance: " + data[1], "Balance", JOptionPane.INFORMATION_MESSAGE);
                });
                break;
            case CALL:
                // suna altul sau raspunzi sau inchizi      inchizi - STOP + SEPARATOR +
                //icf = new IncomingCallFrame(data[1], serviceManager);
                //icf.startCallFrameTimer();
                //JOptionPane.showInputDialog(this,"Incoming call from " + data[1], "Incoming Call", JOptionPane.QUESTION_MESSAGE);
                int result = JOptionPane.showConfirmDialog(this, "Incoming call from " + data[1], "Incoming Call", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION)
                {
                    serviceManager.sendMessage(ANSWERED + SEPARATOR + data[1]);
                    // pornim ceasul
                }
                else
                {
                    serviceManager.sendMessage(STOP + SEPARATOR + data[1]);
                }
                break;
            case ANSWERED:
                // pornim ceasornicul
                break;
            case NO_RESOURCES:  // no money
                JOptionPane.showMessageDialog( this, "You don't have enough credit" );
                break;
            /*case INVALID_PHONE_NUMBER:

                break;*/
            case INVALID_PHONE_NUMBER_CALL:
//                if ( ncf.cf != null )
//                {
//                    ncf.cf.setVisible(false);
//                    ncf.cf.dispose();
//                    ncf.setVisible(false);
//                    ncf.dispose();
//                }
                JOptionPane.showMessageDialog(this, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case STOP:
                // oprim ceasornicul
                break;
            case OFFLINE:
                JOptionPane.showMessageDialog(this, "This person is not connected", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            case WAIT_RESPONSE:
                JOptionPane.showMessageDialog(this, "We're waiting for " + data[1] + " to respond", "Wait", JOptionPane.INFORMATION_MESSAGE);
                break;
            case BUSY:
                JOptionPane.showMessageDialog(this, data[1] + " is busy now", "Busy", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                displayMessage("Unhandled message: " + message);
        }
    }

    private void displayMessage(String message )
    {
        System.out.println(message);
    }
}
