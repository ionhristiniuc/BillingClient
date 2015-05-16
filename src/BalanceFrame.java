import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mihai on 5/16/2015.
 */
public class BalanceFrame extends JFrame {
    String balance;
    private JButton OKBtn;
    private JLabel label;
    private JLabel balanceLabel;
    private JPanel contentPanel;

    BalanceFrame( String balance ) {
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
        OKBtn = new JButton("OK");
        OKBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
                });
        label = new JLabel("Your balance:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel = new JLabel(balance);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setText(balance);
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        add(contentPanel);
        contentPanel.add(label);
        contentPanel.add(OKBtn);
        contentPanel.add(balanceLabel);
        label.setBounds(40, 40, 120, 20);
        balanceLabel.setBounds(60, 80, 80, 20);
        OKBtn.setBounds(60, 120, 80, 40);
        setSize(200, 220);
        setLocation(583, 250);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
