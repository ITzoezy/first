package transaction;

import JDBCUtil.JDBCBean;
import org.junit.Test;

import java.sql.Connection;

public class ConnectionTest {

    @Test
    public void test() throws Exception {
        Connection connection = JDBCBean.Connection();
        System.out.println(connection);
    }
}
