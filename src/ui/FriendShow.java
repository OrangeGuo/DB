package ui;

import backends.Friend;

import javax.swing.*;

public class FriendShow extends JPanel {
    JLabel name,sex,phone,age;
    public FriendShow(){
        this.setLayout(null);


    }

    public void display(Friend friend){
        name = new JLabel("年龄"+friend.getName());
        name.setBounds(14, 26, 72, 18);
        this.add(name);

        sex = new JLabel("性别"+friend.getSex());
        sex.setBounds(14, 66, 72, 18);
        this.add(sex);

        age = new JLabel("年龄"+friend.getAge());
        age.setBounds(14, 111, 72, 18);
        this.add(age);

        phone = new JLabel("手机"+friend.getPhone());
        phone.setBounds(14, 149, 72, 18);
        this.add(phone);

        Photo photo=new Photo();
        photo.setImage(friend.getImage());
        photo.setBounds(230, 36, 99, 99);
        this.add(photo);
    }
}
