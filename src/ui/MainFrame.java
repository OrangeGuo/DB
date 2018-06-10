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
    JLabel result;
    String[] operations;
    JTextField searchInput;
    JButton add,reset,pre,next,search;
    JPanel addPanel,showPanel,searchPanel;
    FriendShow friendShow;
    AddFriend addFriend;
    Dao dao;
    Photo mainPanel;
    ArrayList<Friend> friends,target;
    boolean isModified=true;
    int flag=0;
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
	    friends=new ArrayList<>();
	    target=new ArrayList<>();
        addFriend=new AddFriend();
        friendShow=new FriendShow();
        dao=new Dao();
	    operations=new String[]{"退出程序","添加好友","查找好友","好友展示"};
	    jMenuItems=new JMenuItem[operations.length];
	    option=new JMenu("选项");
        for (int i = 0; i < jMenuItems.length; i++) {
            jMenuItems[i]=new JMenuItem(operations[i]);
            jMenuItems[i].addActionListener(this);
            option.add(jMenuItems[i]);
        }
        result=new JLabel("",JLabel.CENTER);
	    jMenuBar=new JMenuBar();
	    jMenuBar.add(option);
	    this.setJMenuBar(jMenuBar);
        add=new JButton("添加");
        add.addActionListener(this);
        reset=new JButton("重置");
        reset.addActionListener(this);
        pre=new JButton("上一页");
        pre.addActionListener(this);
        next=new JButton("下一页");
        next.addActionListener(this);
        search=new JButton("搜索");
        search.addActionListener(this);
        searchInput=new JTextField(10);
        addPanel=new JPanel();
        addPanel.add(add);
        addPanel.add(reset);
        showPanel=new JPanel();
        showPanel.add(pre);
        showPanel.add(next);
        searchPanel=new JPanel();
        searchPanel.add(searchInput);
        searchPanel.add(search);
        //this.add(mainPanel);
		this.setBounds(100, 100, 450, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


    /**
     * display all friends
     */
    public void listFriends(){
        if(isModified||friends.size()==0)
            friends=dao.ListFriends();
        this.add(friendShow);
	    this.add(showPanel,BorderLayout.SOUTH);
	    friendShow.display(friends.get(0),0);
	    friendShow.updateUI();
    }

    /**
     * get into the windows for adding friend
     */
    public void addFriend(){
	    this.add(addFriend);
	    this.add(addPanel,BorderLayout.SOUTH);
	    addFriend.updateUI();

    }

    /**
     * search friends
     */
    public void searchFriend(){
        this.add(searchPanel,BorderLayout.NORTH);
        searchInput.setToolTipText("输入姓名或者手机号");
        searchPanel.updateUI();
        this.add(result);
        result.updateUI();
    }
    public void actionHandler(int command){
        if(flag==command)return;
        if(flag==0){
            if(command==1){
                addFriend();
            }else if(command==2)
                searchFriend();
            else if(command==3){
                listFriends();
            }
        }
        else if(flag==1){
            this.remove(addFriend);
            this.remove(addPanel);
            if(command==3){
                listFriends();
            }
            else if(command==2)
                searchFriend();
        }
        else if(flag==2){
            this.remove(searchPanel);
            this.remove(result);
            if(command==1)
                 addFriend();
            else if(command==3)
                listFriends();
        }
        else if(flag==3){
            this.remove(friendShow);
            this.remove(showPanel);
            if(command==1)
                addFriend();
            else if(command==2)
                searchFriend();
        }
        flag=command;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < jMenuItems.length; i++) {
            if(e.getSource().equals(jMenuItems[i])){
                if(i==0)
                    System.exit(0);
                else
                    actionHandler(i);
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
        else if(e.getSource().equals(reset)){
            addFriend.reset();
        }
        else if(e.getSource().equals(search)){
            String keyword=searchInput.getText();
            if(keyword!=null&&keyword.trim().length()>0){
                keyword=keyword.trim();
                if(isModified) friends=dao.ListFriends();
                isModified=false;
                target.clear();
                for (int i = 0; i < friends.size(); i++) {
                    Friend friend=friends.get(i);
                    if(friend.getName().contains(keyword)||friend.getPhone().contains(keyword)){
                        target.add(friend);
                    }
                }
                if(target.size()>0){
                    System.out.printf("888");
                }else
                    result.setText("没有相关结果");
                    result.setHorizontalTextPosition(JLabel.CENTER);
            }
        }
        else if(e.getSource().equals(next)){
            int index=(friendShow.getPosition()+1)%friends.size();
            friendShow.display(friends.get(index),index);
        }
        else if(e.getSource().equals(pre)){
            int index=(friendShow.getPosition()+friends.size()-1)%friends.size();
            friendShow.display(friends.get(index),index);
        }
    }
}
