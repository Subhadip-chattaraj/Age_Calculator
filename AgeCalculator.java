import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
public class AgeCalculator
{
    public static void main(String[] args)
    {
        // Create the frame
        JFrame frame = new JFrame("Age Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());

        // Create a custom panel for background
        JPanel mainPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(new Color(240, 240, 240)); // Light gray background
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        frame.setContentPane(mainPanel);

        // Fonts
        Font headingFont = new Font("Arial", Font.BOLD, 24);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        // Heading
        JLabel headingLabel = new JLabel("Age Calculator");
        headingLabel.setFont(headingFont);
        headingLabel.setForeground(new Color(60, 100, 200));

        // Input fields
        JLabel dobLabel = new JLabel("Enter your Date of Birth:");
        dobLabel.setFont(labelFont);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        JTextField yearField = new JTextField(5);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setFont(labelFont);
        JComboBox<String> monthComboBox = new JComboBox<>(new String[] {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setFont(labelFont);
        JTextField dayField = new JTextField(5);

        // Button
        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(100, 150, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);

        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        resultLabel.setForeground(new Color(0, 100, 0));

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(headingLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(dobLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(yearLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(monthLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(monthComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(dayLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(dayField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(calculateButton, gbc);

        gbc.gridy = 6;
        mainPanel.add(resultLabel, gbc);

        // Button action
        calculateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int year = Integer.parseInt(yearField.getText());
                    int day = Integer.parseInt(dayField.getText());
                    String monthString = (String) monthComboBox.getSelectedItem();
                    int month = monthComboBox.getSelectedIndex() + 1;

                    // Validate the input date
                    LocalDate dob = LocalDate.of(year, month, day);
                    LocalDate today = LocalDate.now();

                    if (dob.isAfter(today))
                    {
                        resultLabel.setText("Date of Birth cannot be in the future!");
                    }
                    else
                    {
                        Period age = Period.between(dob, today);
                        resultLabel.setText("Age: " + age.getYears() + " Years, " +
                                age.getMonths() + " Months, " +
                                age.getDays() + " Days");
                    }
                } catch (Exception ex) {
                    resultLabel.setText("Please enter a valid date.");
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
