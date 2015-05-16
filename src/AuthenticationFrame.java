import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Authentication frame
 */
public class AuthenticationFrame extends JFrame implements ActionListener {
    private JPanel contentPanel;
    private JTextField numberField;
    private JLabel label;
    private JLabel errorLabel;
    private JButton signInButton;
    private JButton cancelButton;
    private String number;
    private ServiceManager serviceManager;
    public AuthenticationFrame( String serverHost, int portNumber ) throws IOException
    {
        serviceManager = new ServiceManager(serverHost, portNumber);
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

            try
            {
                if (serviceManager.authenticate(numberField.getText())) {
                    new MainFrame(serviceManager);
                    setVisible(false);
                    dispose();
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
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try
        {
            new AuthenticationFrame( args.length == 0 ? "localhost" : args[0], SERVER_PORT );
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
