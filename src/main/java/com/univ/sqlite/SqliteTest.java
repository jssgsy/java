package com.univ.sqlite;

import java.sql.*;

/**
 * 测试java如何使用sqlite
 * @author univ
 * date 2024/8/19
 */
public class SqliteTest {

    public static void main( String args[] ) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data/sqlite/java.db");// 这里不用传入username、password
            // mysql连接示例：DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
            statement.execute("select * from demo");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("id: " + id + " name: " + name);
            }
        } catch (Exception e) {
        } finally {
            // 资源关闭
        }
    }
}
