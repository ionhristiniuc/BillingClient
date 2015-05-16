import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Created by Mihai on 5/16/2015.
 */
public class IncomingCallFrame extends JFrame {
    private String number;
    private final ServiceManager serviceManager;
    private JPanel contentPanel;
    private JLabel label;
    private JLabel numberLabel;
    private JButton answerButton;
    private JButton rejectButton;
    private ExecutorService service = Executors.newFixedThreadPool(1);
    public CallFrame cf;
    IncomingCallFrame(String number, ServiceManager serviceManager) {
        this.number = number;
        this.serviceManager = serviceManager;
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
            service.execute(() -> {
                serviceManager.sendMessage(ANSWERED + SEPARATOR + number);
                SwingUtilities.invokeLater(() ->
                {
                    cf = new CallFrame(CallFrame.CallType.VoiceCall, number, CallFrame.CallDirection.ToMe, serviceManager);
                    setVisible(false);
                    dispose();
                });
            });
        });
        contentPanel.add(answerButton);
        answerButton.setBounds(20, 100, 60, 40);
        rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> {
            // send message to reject
            serviceManager.sendMessage(STOP + SEPARATOR + number);
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
