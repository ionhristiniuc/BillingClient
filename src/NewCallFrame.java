import jdk.nashorn.internal.codegen.CompilerConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Created by Mihai on 5/16/2015.
 */
public class NewCallFrame extends JFrame {
    private CallFrame.CallType type;
    private final ServiceManager serviceManager;
    private String number;
    private JPanel contentPanel;
    private JLabel label;
    private JTextField numberField;
    private JButton callButton;
    private JButton cancelButton;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    public CallFrame cf;

    NewCallFrame(CallFrame.CallType t, ServiceManager serviceManager) {
        type = t;
        this.serviceManager = serviceManager;
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        label = new JLabel("Enter phone number you want to call:");
        contentPanel.add(label);
        label.setBounds(40, 40, 120, 20);
        numberField = new JTextField();
        contentPanel.add(numberField);
        numberField.setBounds(20, 80, 160, 40);
        callButton = new JButton("Call");
        contentPanel.add(callButton);
        callButton.setBounds(20, 140, 60, 40);
        callButton.addActionListener(e -> {
            number = numberField.getText();
            if ("".equals(number))
                return;
            else
            {
                cf = new CallFrame(type, number, CallFrame.CallDirection.FromMe, serviceManager);
                executorService.execute(() -> {
                    serviceManager.sendMessage(CALL + SEPARATOR + number);
                });
            }
        });

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                setVisible(false);
                dispose();
            });
        contentPanel.add(cancelButton);
            cancelButton.setBounds(120, 140, 60, 40);
            setSize(300, 550);
            setLocation(533, 95);
            setResizable(false);
            setTitle("Enter called number");
            setVisible(true);
        }

        public void startTimer()
        {
            cf.startTimer();
        }
}
