import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;
import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Created by Mihai on 5/16/2015.
 */
public class CallFrame extends JFrame implements ActionListener {
    public enum CallType {
        VoiceCall,
        VideoCall
    }
    public enum CallDirection {
        FromMe,
        ToMe
    }
    private CallType type;
    private final ServiceManager serviceManager;
    private CallDirection direction;
    private String callingNumber;
    private JLabel topLabel;
    private JLabel numberLabel;
    private JLabel durationLabel;
    private JLabel statusLabel;
    private JButton endCallButton;
    private JButton pauseContinueButton;
    private JPanel contentPanel;
    private Timer timer = new Timer();
    public CallFrame( CallType t, String callingNumber, CallDirection d, ServiceManager serviceManager) {
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
        contentPanel.add(topLabel);
        topLabel.setBounds(80, 20, 140, 40);
        durationLabel = new JLabel("0");
        contentPanel.add(durationLabel);
        durationLabel.setBounds(0, 0, 10, 10);
        numberLabel = new JLabel(callingNumber);
        contentPanel.add(numberLabel);
        numberLabel.setBounds(40, 80, 220, 40);
        endCallButton = new JButton("End Call");
        endCallButton.addActionListener(this);
        contentPanel.add(endCallButton);
        endCallButton.setBounds(100, 160, 100, 40);
        pauseContinueButton = new JButton("Pause");
        pauseContinueButton.addActionListener(this);
        contentPanel.add(pauseContinueButton);
        pauseContinueButton.setEnabled(false);
        pauseContinueButton.setBounds(100, 240, 100, 40);
        if (direction == CallDirection.FromMe)
            statusLabel = new JLabel("Connecting...");
        else
            statusLabel = new JLabel("Talk");
        contentPanel.add(statusLabel);
        statusLabel.setBounds(40, 320, 220, 20);

        setSize(300, 400);
        setLocation(533, 160);
        setResizable(false);
        if (direction == CallDirection.FromMe)
            setTitle("Calling...");
        else
            setTitle("Incoming call...");
        setVisible(true);
        // send message to server to call
    }
    public void startTimer() {
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                SwingUtilities.invokeLater(() -> {
                    durationLabel.setText(String.valueOf(Integer.parseInt(durationLabel.getText()) + 1));
                });
            }
        }, 0, 1000);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(endCallButton)) {
            serviceManager.sendMessage( STOP );
        } else {
            if (((JButton)e.getSource()).getText().equals("Pause")) {
                // send message to pause call
                ((JButton)e.getSource()).setText("Continue");
            } else {
                // send message to continue call
                ((JButton)e.getSource()).setText("Pause");
            }
        }
    }
}
