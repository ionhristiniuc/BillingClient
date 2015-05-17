import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private CallDirection direction;
    private String callingNumber;
    private JLabel topLabel;
    private JLabel numberLabel;
    private JLabel durationLabel;
    private JLabel statusLabel;
    private JButton endCallButton;
    private JButton pauseContinueButton;
    private JPanel contentPanel;
    public CallFrame( CallType t, String callingNumber, CallDirection d) {
        this.callingNumber = callingNumber;
        this.direction = d;
        this.type = t;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        if (type == CallType.VideoCall)
            topLabel = new JLabel("Video Call");
        else
            topLabel = new JLabel("Voice Call");
        contentPanel.add(topLabel);
        topLabel.setBounds(80, 20, 140, 40);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(endCallButton)) {
            // send message to server to end call
            // display call duration
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
