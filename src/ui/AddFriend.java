package ui;

import backends.Friend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddFriend extends JPanel implements ActionListener {
    private JTextField name;
    private JTextField sex;
    private JTextField age;
    private JTextField phone;
    private JButton selectIcon;
    private Photo photo;
    private String image="D:\\java图标\\unamed.jpg";
    private String id;
    public AddFriend(){

        this.setLayout(null);
        name = new JTextField();
        name.setBounds(60, 27, 86, 24);
        this.add(name);
        name.setColumns(10);

        JLabel label = new JLabel("姓名");
        label.setBounds(14, 30, 72, 18);
        this.add(label);

        JLabel label_1 = new JLabel("性别");
        label_1.setBounds(14, 64, 72, 18);
        this.add(label_1);

        sex = new JTextField();
        sex.setBounds(60, 64, 40, 24);
        this.add(sex);
        sex.setColumns(10);

        JLabel label_2 = new JLabel("年龄");
        label_2.setBounds(14, 101, 72, 18);
        this.add(label_2);

        age = new JTextField();
        age.setBounds(60, 95, 40, 24);
        this.add(age);
        age.setColumns(10);

        JLabel label_3 = new JLabel("电话");
        label_3.setBounds(14, 139, 72, 18);
        this.add(label_3);

        phone = new JTextField();
        phone.setBounds(60, 136, 86, 24);
        this.add(phone);
        phone.setColumns(10);

        selectIcon = new JButton("选择头像");
        selectIcon.setBounds(206, 30, 113, 27);
        selectIcon.addActionListener(this);
        this.add(selectIcon);

        photo=new Photo(99,99);
        photo.setBounds(216, 70, 99, 99);
        this.add(photo);
        this.setBackground(Color.white);
       // this.setOpaque(false);
    }

    public void reset(){
        this.photo.setImage("D:\\java图标\\unamed.jpg");
        this.name.setText("");
        this.sex.setText("");
        this.age.setText("");
        this.phone.setText("");
        this.image="D:\\java图标\\unamed.jpg";
        this.id="";
    }
    public void edit(Friend friend){
        this.phone.setText(friend.getPhone());
        this.name.setText(friend.getName());
        this.sex.setText(friend.getSex());
        this.age.setText(friend.getAge()+"");
        this.photo.setImage(friend.getImage());
        this.image=friend.getImage();
        this.id=friend.getId();
    }
    public boolean checkInput(){
        if(name.getText().trim().length()==0||phone.getText().trim().length()!=11)
            return false;
        if(!(sex.getText().trim().equals("男")||sex.getText().trim().equals("女")))
            return false;
        if(Integer.parseInt(age.getText().trim())<8)
            return false;
        return true;
    }

    public Friend getInput(){
        Friend friend=new Friend();
        friend.setImage(image);
        friend.setName(name.getText().trim());
        friend.setSex(sex.getText().trim());
        friend.setAge(Integer.parseInt(age.getText().trim()));
        friend.setPhone(phone.getText().trim());
        friend.setId(id);
        this.reset();
        return friend;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(selectIcon)){
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setCurrentDirectory(new File("D://java图标"));
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
            if(file!=null){
                image=file.getAbsolutePath();
                this.photo.setImage(file.getAbsolutePath());
            }

        }
    }
}
