package backends;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {

    // 定义连接所需的字符串
    // 192.168.0.X是本机地址(要改成自己的IP地址)，1521端口号，XE是精简版Oracle的默认数据库名
    private static String USERNAMR = "scott";
    private static String PASSWORD = "tiger";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1522:orcl";

    // 创建一个数据库连接
    Connection connection = null;
    // 创建预编译语句对象，一般都是用这个而不用Statement
    PreparedStatement pstm = null;
    // 创建一个结果集对象
    ResultSet rs = null;


    /**
     * @param friend
     * 向数据库中增加记录
     */
    public void AddFriend(Friend friend) {
        connection = getConnection();
        String sqlStr = "insert into friend values(?,?,?,?,?,?)";
        int count = 0;

        try {
            // 计算数据库student表中数据总数
          // pstm = connection.prepareStatement(sql);
            //rs = pstm.executeQuery();
            //while (rs.next()) {
            //    count = rs.getInt(1) + 1;
            //    System.out.println(rs.getInt(1));
           // }
            // 执行插入数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, friend.getId());
            pstm.setString(2, friend.getName());
            pstm.setString(3, friend.getSex());
            pstm.setInt(4, friend.getAge());
            pstm.setString(5, friend.getImage());
            pstm.setString(6, friend.getPhone());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }


    /**
     * @param friend
     * 从数据库中移除记录
     */
    public void DeleteFriend(Friend friend) {
        connection = getConnection();
        String sqlStr = "delete from FRIEND where ID=?";
        try {
            // 执行删除数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, friend.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }


    /**
     * @param friend
     * 更新数据库中记录
     */
    public void UpdateData(Friend friend) {
        connection = getConnection();

        String sqlStr = "update FRIEND set ID=?,NAME=?,SEX=?,AGE=?,PHONE=?,IMAGE=? where ID=?";
        int count = 0;

        try {
            // 计算数据库student表中数据总数

            // 执行插入数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, friend.getId());
            pstm.setString(2, friend.getName());
            pstm.setString(3, friend.getSex());
            pstm.setInt(4, friend.getAge());
            pstm.setString(5, friend.getPhone());
            pstm.setString(6,friend.getImage());
            pstm.setString(7,friend.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 向数据库中查询数据
     */
    public ArrayList<Friend> ListFriends() {
        ArrayList<Friend> friends=new ArrayList<>();
        connection = getConnection();
        String sql = "select * from FRIEND";
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Friend friend=new Friend();
                friend.setId(rs.getString("id"));
                friend.setName(rs.getString("name"));
                friend.setSex(rs.getString("sex"));
                friend.setAge(Integer.parseInt(rs.getString("age")));
                friend.setPhone(rs.getString("phone"));
                friend.setImage(rs.getString("image"));
                System.out.println(friend.getName() + "\t" + friend.getSex()+ "\t" + friend.getAge() + "\t"
                        + friend.getPhone() + "\t" + friend.getImage());
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return friends;
    }

    /**
     * 使用ResultSetMetaData计算列数
     */
    public void SelectData2() {
        connection = getConnection();
        String sql = "select * from employees where 1 = 1";
        int count = 0;

        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count++;
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols_len = rsmd.getColumnCount();

            System.out.println("count=" + count + "\tcols_len=" + cols_len);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 获取Connection对象
     *
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * 释放资源
     */
    public void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Friend friend=new Friend();
        friend.setAge(22);
        friend.setName("Tom");
        friend.setPhone("15996356501");
        friend.setId("161530313");
        friend.setSex("女");
        friend.setImage("D:\\java图标\\unamed.jpg");
        Dao dao =new Dao();
        dao.AddFriend(friend);
        dao.ListFriends();
        //dao.UpdateData(friend);
        //dao.DeleteFriend(friend);
        //dao.ListFriends();


    }
}
