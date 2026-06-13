package org.asif.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Windows button implementation.
 */
public class WindowsButton implements Button {
    private static final Color LABEL_BACKGROUND = new Color(235, 233, 126);
    private static final int LABEL_FONT_SIZE = 44;
    private static final int FRAME_WIDTH = 320;
    private static final int FRAME_HEIGHT = 200;

    private final JPanel panel = new JPanel();
    private final JFrame frame = new JFrame();
    private JButton button;

    public void render() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World!");
        label.setOpaque(true);
        label.setBackground(LABEL_BACKGROUND);
        label.setFont(new Font("Dialog", Font.BOLD, LABEL_FONT_SIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(panel);
        panel.add(label);
        onClick();
        panel.add(button);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        onClick();
    }

    public void onClick() {
        button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
    }
}
