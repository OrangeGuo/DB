package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

    JMenuBar jMenuBar;
    JMenu option;
    JMenuItem[] jMenuItems ;
    String[] operations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainFrame mainFrame=new MainFrame();
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    operations=new String[]{"添加好友","查找好友","好友展示","退出程序"};
	    jMenuItems=new JMenuItem[operations.length];
	    option=new JMenu("选项");
        for (int i = 0; i < jMenuItems.length; i++) {
            jMenuItems[i]=new JMenuItem(operations[i]);
            jMenuItems[i].addActionListener(this);
            option.add(jMenuItems[i]);
        }
	    jMenuBar=new JMenuBar();
	    jMenuBar.add(option);
	    this.setJMenuBar(jMenuBar);
		this.add(new FriendShow());

		this.setBounds(100, 100, 450, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < jMenuItems.length; i++) {
            if(e.getSource().equals(jMenuItems[i])){
                switch (i) {
                    case 3:System.exit(0);
                }
                break;
            }
        }
    }
}