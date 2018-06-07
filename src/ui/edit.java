package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class edit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit frame = new edit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public edit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(60, 27, 86, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(14, 30, 72, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B");
		label_1.setBounds(14, 64, 72, 18);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 64, 40, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84");
		label_2.setBounds(14, 101, 72, 18);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(60, 95, 40, 24);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7535\u8BDD");
		label_3.setBounds(14, 139, 72, 18);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(60, 136, 86, 24);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("\u9009\u62E9\u5934\u50CF");
		button.setBounds(206, 30, 113, 27);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(216, 70, 99, 98);
		panel.add(panel_1);
	}
}
