package JDBCUtil;

import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCBean {
    public static Connection Connection() throws Exception{
//        1.读取配置文件中的4个信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(is);

        String driverClass = prop.getProperty("driverClass");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");

//        2.加载驱动器
        Class.forName(driverClass);
//        3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    //关闭连接和statement的操作
    public static void closeConnection(Connection connection, PreparedStatement ps){
        try {
            if (ps != null)
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //关闭连接和statement的操作
    public static void closeConnection(Connection connection, PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (rs != null)
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     * @param connection
     * @param ps
     * @param rs
     */
    public static void closeConnection1(Connection connection, PreparedStatement ps,ResultSet rs){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);


    }
}
