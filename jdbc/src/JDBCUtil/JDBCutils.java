package JDBCUtil;

import DAO.CustomersImp;
import GeneralClass.Customers;
import JDBCUtil.JDBCBean;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCutils {

    /**
     * 使用Druid数据库连接池技术
     */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getconnection() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }
}
