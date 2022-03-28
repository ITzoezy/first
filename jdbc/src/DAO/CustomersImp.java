package DAO;

import GeneralClass.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomersImp extends baseDAO implements CustomersDAO{
    @Override
    public void insert(Connection connection, Customers customers) {
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        update(connection,sql,customers.getName(),customers.getEmail(),customers.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        update(connection,sql,id);

    }

    @Override
    public void update(Connection connection, Customers customers) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(connection,sql,customers.getName(),customers.getEmail(),customers.getBirth(),customers.getId());
    }

    @Override
    public Customers getCustomerById(Connection connection, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customers customers = QueryForCustomers(connection, Customers.class, sql, id);
        return  customers;
    }

    @Override
    public List<Customers> getAll(Connection connection) {
        String sql = "select id,name,email,birth from customers ";

        List<Customers> list = getForList(connection, Customers.class, sql);
        return list;
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from customers";
         return getValues(connection, sql);

    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select MAX(birth) from customers";
        return getValues(connection, sql);
    }
}
