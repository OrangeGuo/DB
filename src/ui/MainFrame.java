package ui;

import backends.Dao;
import backends.Friend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

    JMenuBar jMenuBar;
    JMenu option;
    JMenuItem[] jMenuItems ;
    String[] operations;
    JButton add,next;

    FriendShow friendShow;
    AddFriend addFriend;
    Dao dao;
    Photo mainPanel;
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

        dao=new Dao();
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

        next=new JButton("下一个");
        next.addActionListener(this);
        mainPanel=new Photo(450,300);

        mainPanel.setImage("D:\\java图标\\background.jpg");

        this.add(mainPanel);
		this.setBounds(100, 100, 450, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

    /**
     * @param friends
     * display the information of all friends
     */
    public void listFriends(ArrayList<Friend> friends){
	    Friend friend=friends.get(0);
        FriendShow friendShow=new FriendShow();
        friendShow.display(friend);

        this.add(friendShow);
	    this.add(next,BorderLayout.SOUTH);
	    friendShow.updateUI();
        System.out.println(888);
    }

    /**
     * get into the windows for adding friend
     */
    public void addFriend(){

	    //this.removeAll();
	    addFriend=new AddFriend();

	    this.add(addFriend);
	    add=new JButton("添加");
	    add.addActionListener(this);
	    this.add(add,BorderLayout.SOUTH);
	    addFriend.updateUI();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < jMenuItems.length; i++) {
            if(e.getSource().equals(jMenuItems[i])){
                switch (i) {
                    case 3:System.exit(0);break;
                    case 2:listFriends(dao.ListFriends());break;
					case 0: addFriend();break;
                }
                break;
            }
        }
        if(e.getSource().equals(add)){
            if(addFriend.checkInput()){
                int id=dao.ListFriends().size();
                Friend friend=addFriend.getInput();
                friend.setId(String.valueOf(id+1));
                dao.AddFriend(friend);
            }
        }
    }
}
