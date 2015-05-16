import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class AuthenticationFrame extends JFrame implements ActionListener {
    private JPanel contentPanel;
    private JTextField numberField;
    private JLabel label;
    private JLabel errorLabel;
    private JButton signInButton;
    private JButton cancelButton;
    private String number;
    public AuthenticationFrame() {

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
        numberField = new JTextField();
        contentPanel.add(numberField);
        label = new JLabel("Enter your phone number:");
        contentPanel.add(label);
        signInButton = new JButton("SIGN IN");
        signInButton.addActionListener(this);
        contentPanel.add(signInButton);
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        contentPanel.add(cancelButton);
        label.setBounds(100, 40, 200, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Courier", Font.PLAIN, 14));
        numberField.setBounds(60, 120, 280, 40);
        signInButton.setBounds(60, 220, 100, 40);
        cancelButton.setBounds(240, 220, 100, 40);
        contentPanel.setBackground(Color.LIGHT_GRAY);
        setSize(400, 400);
        setLocation(483, 160);
        setResizable(false);
        setTitle("Authentication");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancelButton)) {
            System.exit(0);
        } else {
            if (errorLabel != null )
                contentPanel.remove(errorLabel);
            if (ServiceManager.getManager().Authenticate(numberField.getText()) == true) {
                new MainFrame(numberField.getText());
                setVisible(false);
            } else {
                errorLabel = new JLabel("Authentication Failed...");
                errorLabel.setBounds(100, 280, 200, 20);
                errorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                errorLabel.setFont(new Font("Courier", Font.PLAIN, 12));
                errorLabel.setForeground(Color.RED);
                contentPanel.add(errorLabel);
                numberField.setText("");
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        new AuthenticationFrame();
    }
}
