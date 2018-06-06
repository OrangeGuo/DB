package ui;

import javax.swing.*;

public class FriendShow extends JPanel {
    JLabel name,sex,phone,age;
    public FriendShow(){
        this.setLayout(null);

        name = new JLabel("年龄");
        name.setBounds(14, 26, 72, 18);
        this.add(name);

        sex = new JLabel("性别");
        sex.setBounds(14, 66, 72, 18);
        this.add(sex);

        age = new JLabel("年龄");
        age.setBounds(14, 111, 72, 18);
        this.add(age);

        phone = new JLabel("手机");
        phone.setBounds(14, 149, 72, 18);
        this.add(phone);

        Photo photo=new Photo();
        photo.setBounds(230, 36, 99, 99);
        this.add(photo);
    }
}
