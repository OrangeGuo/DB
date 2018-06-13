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
    DefaultListModel defaultListModel;
    JList jList;
    JScrollPane jScrollPane;
    String[] operations;
    JTextField searchInput;
    JButton add,reset,pre,next,search,edit,delete,cancle,ok;
    JPanel addPanel,showPanel,searchPanel,resultPanel,editPanel,mainPanel;
    FriendShow friendShow;
    AddFriend addFriend;
    Dao dao;

    ArrayList<Friend> friends,target,pointer;
    boolean isModified=true;

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
        defaultListModel=new DefaultListModel();
        jList=new JList(defaultListModel);
        jScrollPane=new JScrollPane(jList);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dao=new Dao();
	    operations=new String[]{"退出程序","添加好友","好友管理","好友展示"};
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
        edit=new JButton("编辑");
        edit.addActionListener(this);
        delete=new JButton("删除");
        delete.addActionListener(this);
        cancle=new JButton("取消");
        cancle.addActionListener(this);
        ok=new JButton("保存");
        ok.addActionListener(this);
        searchInput=new JTextField(10);
        addPanel=new JPanel();
        addPanel.add(add);
        addPanel.add(reset);
        showPanel=new JPanel();

        searchPanel=new JPanel();
        searchPanel.add(searchInput);
        searchPanel.add(search);
        resultPanel=new JPanel();

        resultPanel.add(delete);
        resultPanel.add(edit);
        editPanel=new JPanel();
        editPanel.add(cancle);
        editPanel.add(ok);
        mainPanel=new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);
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
        isModified=false;
        if(friends.size()>0){
            mainPanel.add(friendShow);
            mainPanel.add(showPanel,BorderLayout.SOUTH);
            showPanel.add(pre);
            showPanel.add(next);
            friendShow.display(friends.get(0),0);
        }
        else{
            result.setText("空空如也，请添加好友");
            mainPanel.add(result);
        }
	    pointer=friends;
    }

    /**
     * get into the windows for adding friend
     */
    public void addFriend(){
	    mainPanel.add(addFriend);
	    mainPanel.add(addPanel,BorderLayout.SOUTH);

    }

    /**
     * search friends
     */
    public void searchFriend(){
        mainPanel.add(searchPanel,BorderLayout.NORTH);
        searchInput.setToolTipText("输入姓名或者手机号");
        if(isModified||friends.size()==0)
            friends=dao.ListFriends();
        isModified=false;
        defaultListModel.removeAllElements();
        for (int i = 0; i < friends.size(); i++) {
            defaultListModel.addElement(friends.get(i).getName());
        }
        mainPanel.add(jScrollPane);

    }

    /**
     * @return if friend is already in your database
     */
    public boolean isExist(Friend friend){
        if(isModified||friends.size()==0)
            friends=dao.ListFriends();
        for (int i = 0; i < friends.size(); i++) {
            Friend friendToCompare=friends.get(i);
            if(friend.getName().equals(friendToCompare.getName())&&friend.getPhone().equals(friendToCompare.getPhone()))
                return true;
        }
        return false;
    }

    /**
     * @param command
     */
    public void actionHandler(int command){
        mainPanel.removeAll();
            if(command==1){
                addFriend();
            }else if(command==2)
                searchFriend();
            else if(command==3){
                listFriends();
            }
        mainPanel.updateUI();
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
                if(isModified||friends.size()==0)
                    friends=dao.ListFriends();
                Friend friend=addFriend.getInput();
                if(!isExist(friend)) {
                    int id=0;
                    for (int i = 0; i < friends.size(); i++) {
                        int temp=Integer.parseInt(friends.get(i).getId().trim());
                        if(temp>id)
                            id=temp;
                    }
                    id++;
                    friend.setId(String.valueOf(id));
                    dao.AddFriend(friend);
                }
                else
                    JOptionPane.showMessageDialog(null, "好友已存在！", "警告", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "输入非法！", "警告", JOptionPane.ERROR_MESSAGE);
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
                    defaultListModel.removeAllElements();
                    for (int i = 0; i < target.size(); i++) {
                        defaultListModel.addElement(target.get(i).getName());
                    }
                    mainPanel.removeAll();
                    mainPanel.add(searchPanel,BorderLayout.NORTH);
                    mainPanel.add(jScrollPane);
                    mainPanel.add(resultPanel,BorderLayout.SOUTH);

                }else
                    result.setText("没有相关结果");
                    result.setHorizontalTextPosition(JLabel.CENTER);
            }
            mainPanel.updateUI();
        }
        else if(e.getSource().equals(next)){
            int index=(friendShow.getPosition()+1)%pointer.size();
            friendShow.display(pointer.get(index),index);
        }
        else if(e.getSource().equals(pre)){
            int index=(friendShow.getPosition()+pointer.size()-1)%pointer.size();
            friendShow.display(pointer.get(index),index);
        }
        else if(e.getSource().equals(delete)){
            isModified=true;
            dao.DeleteFriend(target.get(friendShow.getPosition()));
            target.remove(friendShow.getPosition());
            if(target.size()>0){
                int index=friendShow.getPosition()%target.size();
                friendShow.display(target.get(index),index);
            }
            else if(target.size()==0){
                result.setText("空空如也，请添加好友");
                mainPanel.removeAll();
                mainPanel.add(result);
                mainPanel.updateUI();
            }
        }
        else if(e.getSource().equals(edit)){
            mainPanel.removeAll();
            mainPanel.add(addFriend);
            addFriend.edit(target.get(friendShow.getPosition()));
            mainPanel.add(editPanel,BorderLayout.SOUTH);
            mainPanel.updateUI();
        }
        else if(e.getSource().equals(ok)){
            if(addFriend.checkInput()){
                isModified=true;
                if(!isExist(addFriend.getInput()))
                    actionHandler(3);
                else
                    JOptionPane.showMessageDialog(null, "好友已存在！", "警告", JOptionPane.ERROR_MESSAGE);

            }
            else{
                JOptionPane.showMessageDialog(null, "输入非法！", "警告", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource().equals(cancle)){
            actionHandler(3);
        }
    }
}
