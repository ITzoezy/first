package DAO;

import GeneralClass.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomersDAO {
    /**
     * 将Customers对象添加到数据库中
     * @param connection
     * @param customers
     */
    void insert(Connection connection, Customers customers);

    /**
     * 针对指定的id。删除表中一条记录
     * @param connection
     */
    void deleteById(Connection connection,int id);

    /**
     * 针对内存中的customers对象，去修改数据表中指定的记录
     * @param connection
     * @param customers
     */
    void update(Connection connection,Customers customers);

    /**
     * 针对指定id查询得到对应的customers对象
     * @param connection
     * @param id
     */
    Customers getCustomerById(Connection connection,int id);

    /**
     * 查询表中得到所以记录的集合
     * @param connection
     * @return
     */
    List<Customers> getAll(Connection connection);

    /**
     * 返回数据表中的数据条目数
     * @param connection
     * @return
     */
    long getCount(Connection connection);

    /**
     * 返回数据表中最大的生日
     * @param connection
     * @return
     */
    Date getMaxBirth(Connection connection);

}
