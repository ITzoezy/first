package transaction;

import JDBCUtil.JDBCBean;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class transactionTest {

    @Test
    public void testTx() {
        Connection connection = null;
        try {
            connection = JDBCBean.Connection();
//        System.out.println(connection.setAutoCommit());
//        1.关闭自动提交?
            System.out.println(connection.getAutoCommit());
            connection.setAutoCommit(false);
            String sql = "update user_table set balance = balance - 100 where user = ?";
            connection.prepareStatement(sql);
            update(connection,sql,"AA");

            //模拟网络异常
            System.out.println( 10 / 0);

            String sql1 = "update user_table set balance = balance + 100 where user = ?";
            connection.prepareStatement(sql1);
            update(connection,sql1,"BB");
            System.out.println("转账成功");

//        2.提交数据
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
//        3.回滚
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }


    public int  update(Connection connection,String sql,Object...args) {
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);

                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1,args[i]);
                }

               return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCBean.closeConnection(null,ps);
            }
            return 0;


        }

}
