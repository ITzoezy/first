package DAO;

import JDBCUtil.JDBCBean;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class baseDAO {


    //通用的增删改  --增加了事务
    public int update(Connection connection,String sql,Object...args) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCBean.closeConnection(null, ps);
        }
        return 0;
    }

    /**
      * 针对于不同表通用的查询操作，返回表中的多条记录
      */
     public <T> List<T> getForList(Connection connection,Class<T> clazz , String sql, Object...args) {
         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             ps = connection.prepareStatement(sql);
             //填充占位符
             for (int i = 0; i < args.length; i++) {
                 ps.setObject(i+1,args[i]);

             }
             rs = ps.executeQuery();
             //获取结果集的元数据
             ResultSetMetaData rsmd = rs.getMetaData();
             //通过ResultSetMetaData获取列数
             int columnCount = rsmd.getColumnCount();
             //创建集合对象
             ArrayList<T> list = new ArrayList<>();
             while(rs.next()){
                 T t = clazz.newInstance();
                 //处理结果集每一行数据中的每一列:给t对象指定的属性赋值
                 for (int i = 0; i < columnCount; i++) {
                     //获取列值
                     Object columnValue = rs.getObject(i + 1);
                     //获取列名
                     String columnLable= rsmd.getColumnLabel(i + 1);

                     //给customers对象指定的columnName属性，赋值为colunmValue：通过反射
                     Field field =clazz.getDeclaredField(columnLable);
                     field.setAccessible(true);
                     field.set(t,columnValue);

                 }
                 list.add(t);
             }
             return list;
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             JDBCBean.closeConnection(null,ps,rs);
         }
         return null;
     }

     /**
      * 针对于不同表通用的查询操作，返回表中的一条记录
      * @param clazz
      * @param sql
      * @param args
      * @param <T>
      * @return
      */
     public <T> T QueryForCustomers(Connection connection,Class<T> clazz ,String sql,Object...args) {
         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             ps = connection.prepareStatement(sql);
             //填充占位符
             for (int i = 0; i < args.length; i++) {
                 ps.setObject(i+1,args[i]);

             }
             rs = ps.executeQuery();
             //获取结果集的元数据
             ResultSetMetaData rsmd = rs.getMetaData();
             //通过ResultSetMetaData获取列数
             int columnCount = rsmd.getColumnCount();
             if(rs.next()){
                 T t = clazz.newInstance();
                 //处理结果集每一行数据中的每一列
                 for (int i = 0; i < columnCount; i++) {
                     //获取列值
                     Object columnValue = rs.getObject(i + 1);
                     //获取列名
                     String columnLable= rsmd.getColumnLabel(i + 1);

                     //给customers对象指定的columnName属性，赋值为colunmValue：通过反射
                     Field field =clazz.getDeclaredField(columnLable);
                     field.setAccessible(true);
                     field.set(t,columnValue);

                 }
                 return t;

             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             JDBCBean.closeConnection(null,ps,rs);
         }
         return null;
     }

     //用于查询特殊值的通用方法
     public <E> E getValues(Connection connection,String sql,Object...args){

         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             ps = connection.prepareStatement(sql);
             for (int i = 0; i < args.length; i++) {
                 ps.setObject(i+1,args[i]);
             }
             rs = ps.executeQuery();
             if(rs.next()){
                 return (E) rs.getObject(1);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         } finally {
             JDBCBean.closeConnection(null,ps,rs);
         }
         return null;

     }

}
