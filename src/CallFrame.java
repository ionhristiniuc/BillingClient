import javax.swing.*;
import java.awt.*;
import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Created by Mihai on 5/16/2015.
 */
public class CallFrame extends JFrame {
    public enum CallType {
        VoiceCall,
        VideoCall
    }
    public enum CallDirection {
        FromMe,
        ToMe
    }
    public enum CallStatus {
        Connecting,
        WaitingForResponse,
        Busy,
        Rejected,
        Stop,
        Connected,
        Paused
    }

    private final ServiceManager serviceManager;
    private CallDirection direction;
    private CallType type;
    private CallStatus status;
    private String callingNumber;
    private JLabel topLabel;
    private JLabel numberLabel;
    private JLabel durationLabel;
    private JLabel statusLabel;
    private JButton endCallButton;
    private JButton pauseContinueButton;
    private JPanel contentPanel;
    public CallFrame( CallType t, String callingNumber, CallDirection d, CallStatus st, ServiceManager serviceManager) {
        this.status = st;
        this.callingNumber = callingNumber;
        this.direction = d;
        this.type = t;
        this.serviceManager = serviceManager;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        if (type == CallType.VideoCall)
            topLabel = new JLabel("Video Call");
        else
            topLabel = new JLabel("Voice Call");
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(topLabel);
        topLabel.setBounds(20, 20, 210, 20);
        numberLabel = new JLabel(callingNumber);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(numberLabel);
        numberLabel.setBounds(20, 60, 210, 20);
        durationLabel = new JLabel("0");
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(durationLabel);
        durationLabel.setBounds(20, 100, 210, 20);
        statusLabel = new JLabel();
        contentPanel.add(statusLabel);
        statusLabel.setBounds(20, 140, 210, 20);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        setStatus(status);
        endCallButton = new JButton("End Call");
        endCallButton.addActionListener(e -> {
            serviceManager.sendMessage(STOP + SEPARATOR + callingNumber);
            serviceManager.sendMessage(BALANCE);
            setVisible(false);
            dispose();
        });
        contentPanel.add(endCallButton);
        endCallButton.setBounds(20, 180, 95, 40);
        pauseContinueButton = new JButton("Pause");
        pauseContinueButton.addActionListener(e -> {
            if (((JButton)e.getSource()).getText().equals("Pause")) {
                //serviceManager.sendMessage(PAUSE);
                ((JButton)e.getSource()).setText("Continue");
            } else {
                //serviceManager.sendMessage(RELOAD);
                ((JButton)e.getSource()).setText("Pause");
            }
        });
        contentPanel.add(pauseContinueButton);
        pauseContinueButton.setEnabled(false);
        pauseContinueButton.setBounds(120, 180, 95, 40);

        setSize(250, 270);
        setLocation(533, 160);
        setResizable(false);
        setTitle("Call");
        setVisible(true);

        if (status == CallStatus.Connecting) {

        }
    }

    public void setStatus(CallStatus st) {
        status = st;
        switch(status) {
            case Connecting:
                statusLabel.setText("Connecting...");
                statusLabel.setForeground(Color.GREEN);
                break;
            case WaitingForResponse:
                statusLabel.setText("Waiting For Response...");
                break;
            case Busy:
                statusLabel.setText("Number Is Busy.");
                statusLabel.setForeground(Color.RED);
                break;
            case Rejected:
                statusLabel.setText("Rejected.");
                statusLabel.setForeground(Color.RED);
                break;
            case Stop:
                statusLabel.setText("Call interrupted.");
                statusLabel.setForeground(Color.RED);
                break;
            case Connected:
                statusLabel.setText("Connected.");
                statusLabel.setForeground(Color.GREEN);
                break;
            case Paused:
                statusLabel.setText("Paused.");
                statusLabel.setForeground(Color.ORANGE);
                break;
        }
    }
}
