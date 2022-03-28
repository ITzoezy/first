package DAO.test;

import DAO.CustomersImp;
import GeneralClass.Customers;
import JDBCUtil.JDBCBean;
import JDBCUtil.JDBCutils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;

public class TestCustomersImp {

    CustomersImp custImp = new CustomersImp();
    @Test
    public void testinsert(){
        Connection connection = null;
        try {
            connection = JDBCBean.Connection();
            Customers cust = new Customers(1, "周钰", "zhouyu@gmail.com", new Date(234238L));
            custImp.insert(connection,cust);
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }

    @Test
    public void testdeleteById(){
        Connection connection = null;
        try {
            connection = JDBCBean.Connection();
            custImp.deleteById(connection,19);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }


    }

    @Test
    public void testupdate(){
        Connection connection = null;
        try {
            connection = JDBCBean.Connection();
            Customers cust = new Customers(18, "贝多芬", "beiduofeng@gmail.com", new Date(2125342611L));
            custImp.update(connection,cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }

    @Test
    public void testgetCustomerById(){
        Connection connection = null;
        try {
            connection = JDBCutils.getconnection();
            Customers customer = custImp.getCustomerById(connection, 25);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCBean.closeConnection(connection,null);
        }

    }

}
