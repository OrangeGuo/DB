package ui;

import backends.Friend;

import javax.swing.*;

public class FriendShow extends JPanel {
    JLabel name,sex,phone,age;
    Photo photo;
    int position;
    public FriendShow(){
        this.setLayout(null);
        name = new JLabel();
        name.setBounds(14, 26, 72, 18);
        this.add(name);
        sex = new JLabel();
        sex.setBounds(14, 66, 72, 18);
        this.add(sex);
        age = new JLabel();
        age.setBounds(14, 111, 72, 18);
        this.add(age);
        phone = new JLabel();
        phone.setBounds(14, 149, 72, 18);
        this.add(phone);
        photo=new Photo(99,99);
        photo.setBounds(230, 36, 99, 99);
        this.add(photo);
    }

    public void display(Friend friend,int position){
        name.setText("年龄:"+friend.getName());
        sex.setText("性别:"+friend.getSex());
        age.setText("年龄:"+friend.getAge());
        phone.setText("手机:"+friend.getPhone());
        photo.setImage(friend.getImage());
        this.position=position;
    }

    public int getPosition(){
        return position;
    }
}
