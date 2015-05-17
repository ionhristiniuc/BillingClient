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
    private JPanel contentPanel;
    private JLabel label;
    private JTextField numberField;
    private JButton callButton;
    private JButton cancelButton;

    NewCallFrame(MainFrame frame) {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        label = new JLabel("Enter phone number you want to call:");
        contentPanel.add(label);
        label.setBounds(40, 20, 220, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        numberField = new JTextField();
        contentPanel.add(numberField);
        numberField.setBounds(40, 80, 220, 40);
        callButton = new JButton("Call");
        contentPanel.add(callButton);
        callButton.setBounds(40, 160, 80, 40);
        callButton.addActionListener(e -> {
            String number = numberField.getText();
            frame.numberToCall = number;
            frame.startNewCall();
            setVisible(false);
            dispose();
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        contentPanel.add(cancelButton);
        cancelButton.setBounds(180, 160, 80, 40);
        setSize(300, 280);
        setLocation(533, 95);
        setResizable(false);
        setTitle("Enter called number");
        setVisible(true);
        }
}
