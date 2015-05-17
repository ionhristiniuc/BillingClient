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
    public  String smsNumberToSend;
    public  String smsToSend;
    public  String numberToCall;
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
    private CallFrame callFrame;
    private CallFrame.CallType callType;
    private CallFrame.CallDirection callDirection;

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
            new NewCallFrame(this);
            callType = CallFrame.CallType.VoiceCall;
            callDirection = CallFrame.CallDirection.FromMe;
        });
        videoCallBtn = new JButton("Video Call");
        videoCallBtn.addActionListener(e -> {
            new NewCallFrame(this);
            callType = CallFrame.CallType.VideoCall;
            callDirection = CallFrame.CallDirection.FromMe;
        });
        sendSMSBtn = new JButton("Send SMS");
        sendSMSBtn.addActionListener(e -> {
            new NewMessageFrame(this);
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
        numberLabel.setBounds(20, 40, 260, 40);
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

    public void startNewCall() {
        callFrame = new CallFrame(callType, numberToCall, CallFrame.CallDirection.FromMe, CallFrame.CallStatus.Connecting, serviceManager);
        serviceManager.sendMessage(CALL + SEPARATOR + numberToCall);
    }

    public void sendNewSMS () {
       //serviceManager.sendMessage();
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
                    JOptionPane.showMessageDialog(this, "Balance: " + data[1], "Balance", JOptionPane.INFORMATION_MESSAGE);
                });
                break;
            case CALL:
                int result = JOptionPane.showConfirmDialog(this, "Incoming call from " + data[1] + "\nRespond?", "Incoming Call", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION)
                {
                    serviceManager.sendMessage(ANSWERED + SEPARATOR + data[1]);
                    if (callFrame != null)
                        callFrame.setStatus(CallFrame.CallStatus.Connected);
                    else {
                        callFrame = new CallFrame(callType, data[1], CallFrame.CallDirection.ToMe, CallFrame.CallStatus.Connecting, serviceManager);
                        callFrame.setStatus(CallFrame.CallStatus.Connected);
                    }
                }
                else
                {
                    serviceManager.sendMessage(STOP + SEPARATOR + data[1]);
                }
                break;
            case ANSWERED:
                callFrame.setStatus(CallFrame.CallStatus.Connected);
                break;
            case NO_RESOURCES:
                JOptionPane.showMessageDialog( this, "You don't have enough credit" );
                break;
            /*case INVALID_PHONE_NUMBER:

                break;*/
            case INVALID_PHONE_NUMBER_CALL:
                JOptionPane.showMessageDialog(this, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case STOP:
                if (callFrame != null) {
                    callFrame.setVisible(false);
                    callFrame.dispose();
                }
                if (data[2] != null) {
                    String duration = data[2];
                    JOptionPane.showMessageDialog(this, "Call duration: " + duration, "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case OFFLINE:
                JOptionPane.showMessageDialog(this, "This person is not connected", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            case WAIT_RESPONSE:
                //JOptionPane.showMessageDialog(this, "We're waiting for " + data[1] + " to respond", "Wait", JOptionPane.INFORMATION_MESSAGE);
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
