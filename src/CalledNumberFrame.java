import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class CalledNumberFrame extends JFrame implements ActionListener {
    private CallFrame.CallType type;
    private String number;
    private JPanel contentPanel;
    private JLabel label;
    private JTextField numberField;
    private JButton callButton;
    private JButton cancelButton;

    CalledNumberFrame(CallFrame.CallType t) {
        type = t;
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
        cancelButton = new JButton("Cancel");
        contentPanel.add(cancelButton);
        cancelButton.setBounds(120, 140, 60, 40);
        setSize(300, 550);
        setLocation(533, 95);
        setResizable(false);
        setTitle("Enter called number");
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(callButton)) {
            number = numberField.getText();
            if ("".equals(number))
                return;
            else {
                new CallFrame(type, number, CallFrame.CallDirection.FromMe);
            }
        } else {
            setVisible(false);
            dispose();
        }
    }
}
