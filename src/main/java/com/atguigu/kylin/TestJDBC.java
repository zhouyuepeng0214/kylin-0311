package com.atguigu.kylin;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Kylin_JDBC 驱动
        String KYLIN_DRIVER = "org.apache.kylin.jdbc.Driver";

        //Kylin_URL
        String KYLIN_URL = "jdbc:kylin://hadoop102:7070/gmall";

        //Kylin的用户名
        String KYLIN_USER = "admin";

        //Kylin的密码
        String KYLIN_PASSWD = "KYLIN";

        //添加驱动信息
        Class.forName(KYLIN_DRIVER);

        //获取连接
        Connection connection = DriverManager.getConnection(KYLIN_URL, KYLIN_USER, KYLIN_PASSWD);

        String sql = "select u.gender,sum(pay.total_amount) from dwd_payment_info pay join dwd_user_view u on pay.user_id = u.id group by u.gender";

        PreparedStatement ps = connection.prepareStatement(sql);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getDouble(2));
        }

    }
}
