package dbutils;

import GeneralClass.Customers;
import JDBCUtil.JDBCBean;
import JDBCUtil.JDBCutils;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryRunner {
    @Test
    public void testQueryRunner() {
        Connection connection = null;
        try {
            org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
            connection = JDBCutils.getconnection();
            String sql = "insert into customers(name,email,birth) values(?,?,?)";
            runner.update(connection,sql,"蔡徐坤","caixukun@gmail.com","1999-09-09");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }
    }

    /**
     * BeanHandler:是ResultSetHandler接口的实现类，用于封装表的一条记录
         */
        @Test
        public void testQueryRunner1() {
            Connection connection = null;
            try {
                org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
                connection = JDBCutils.getconnection();
                String sql = "select id,name,email,birth from customers where id = ?";
                BeanHandler<Customers> handler = new BeanHandler<>(Customers.class);
                Customers customers = runner.query(connection, sql, handler, 23);
                System.out.println(customers);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCBean.closeConnection(connection,null);
            }

        }
    /**
     * BeanHandler:是ResultSetHandler接口的实现类，用于封装表的多条记录
         */
        @Test
        public void testQueryRunner2() {
            Connection connection = null;
            try {
                org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
                connection = JDBCutils.getconnection();
                String sql = "select id,name,email,birth from customers where id < ?";
                BeanListHandler<Customers> listHandler = new BeanListHandler<>(Customers.class);
                List<Customers> list = runner.query(connection, sql, listHandler, 23);
                list.forEach(System.out::println);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCBean.closeConnection(connection,null);
            }

        }

    /**
     * MapHandler:是ResultSetHandler接口的实现类，对应表的一条记录
     * 将字段及相应字段的值作为map中的key和value，将这些map添加到list中
         */
        @Test
        public void testQueryRunner3() {
            Connection connection = null;
            try {
                org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
                connection = JDBCutils.getconnection();
                String sql = "select id,name,email,birth from customers where id = ?";
                MapHandler handler = new MapHandler();
                Map<String, Object> query = runner.query(connection, sql, handler, 23);
                System.out.println(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCBean.closeConnection(connection,null);
            }

        }

    /**
     * MapHandler:是ResultSetHandler接口的实现类，对应表的多条记录
     * 将字段及相应字段的值作为map中的key和value
         */
        @Test
        public void testQueryRunner4() {
            Connection connection = null;
            try {
                org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
                connection = JDBCutils.getconnection();
                String sql = "select id,name,email,birth from customers where id < ?";
                MapListHandler handler = new MapListHandler();
                List<Map<String, Object>> list = runner.query(connection, sql, handler, 23);
                list.forEach(System.out::println);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCBean.closeConnection(connection,null);
            }

        }

    /**
     * ScalarHandler:用于查询特殊值
     */
    @Test
    public void testQueryRunner5() {
        Connection connection = null;
        try {
            org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
            connection = JDBCutils.getconnection();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            long count = (long) runner.query(connection, sql, handler);
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }
    @Test
    public void testQueryRunner6() {
        Connection connection = null;
        try {
            org.apache.commons.dbutils.QueryRunner runner = new org.apache.commons.dbutils.QueryRunner();
            connection = JDBCutils.getconnection();
            String sql = "select max(birth) from customers";
            ScalarHandler handler = new ScalarHandler();
            Date maxdate = (Date) runner.query(connection, sql, handler);
            System.out.println(maxdate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }

}
