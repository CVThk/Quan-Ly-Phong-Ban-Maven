/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author Chau Thinh
 */
public class JDBCHelper {

    private static final String Server = "CVT";
    private static final String DBName = "QLPhongBan";
    private static final String Username = "sa";
    private static final String Password = "123";
    private static Connection connect = null;

    private static JDBCHelper instance;

    private JDBCHelper() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static JDBCHelper getInstance() {
        if (instance == null) {
            instance = new JDBCHelper();
            
        }
        return instance;
    }

    private String getConnectionString() {
        return "jdbc:sqlserver://" + Server + ":1433" + ";databaseName=" + DBName + ";user=" + Username + ";password=" + Password + ";encrypt=false";
    }

    public Connection OpenConnection() {
        try {
            if (connect != null && connect.isClosed() == false) {
                return connect;
            }

            connect = DriverManager.getConnection(this.getConnectionString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connect;
    }

    public void CloseConnection() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setParams(Object[] objs, PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) {
                preparedStatement.setString(i + 1, objs[i].toString());
            }
        }
    }

    public <T> ArrayList<T> Execute(String query, Object[] params, Class<T> cl) throws Exception {
        ArrayList<T> arrayList = new ArrayList<>();
        OpenConnection();
        PreparedStatement preparedStatement = connect.prepareCall(query);
        if (params != null && params.length > 0) {
            setParams(params, preparedStatement);
        }
        ResultSet result = preparedStatement.executeQuery();
        Field[] fields = cl.getDeclaredFields();
        Method[] methods = cl.getMethods();
        while (result.next()) {
            T t = cl.getDeclaredConstructor().newInstance();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Object objValue = result.getObject(fields[i].getName());
                    Method methodt = null;
                    for (Method method : methods) {
                        if (method.getName().equals("set" + fields[i].getName())) {
                            methodt = method;
                            break;
                        }
                    }
                    if (objValue != null && methodt != null) {
                        methodt.setAccessible(true);
                        methodt.invoke(t, objValue);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
            arrayList.add(t);
        }
        CloseConnection();
        return arrayList;
    }

    public int ExecuteScalar(String query, Object[] params) throws Exception {
        OpenConnection();
        int value = 0;
        PreparedStatement preparedStatement = connect.prepareCall(query);
        if (params != null && params.length > 0) {
            setParams(params, preparedStatement);
        }
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        Object valGet = result.getObject(1);
        if (valGet != null) {
            value = (int) valGet;
        }
        CloseConnection();
        return value;
    }

    public int ExecuteNonQuery(String query, Object[] params) throws Exception {
        OpenConnection();
        int value = 0;
        PreparedStatement preparedStatement = connect.prepareCall(query);
        if (params != null && params.length > 0) {
            setParams(params, preparedStatement);
        }
        value = preparedStatement.executeUpdate();
        CloseConnection();
        return value;
    }

    public <T> Vector ExecuteGetVector(String query, Object[] params, Class<T> cl) throws Exception {
        Vector data = new Vector();
        OpenConnection();
        PreparedStatement preparedStatement = connect.prepareCall(query);
        if (params != null && params.length > 0) {
            setParams(params, preparedStatement);
        }
        ResultSet result = preparedStatement.executeQuery();
        Field[] fields = cl.getDeclaredFields();
        while (result.next()) {
            Vector<Object> vt = new Vector<>();
            for (int i = 0; i < fields.length; i++) {
                Object objValue = result.getObject(fields[i].getName());
                if (objValue != null) {
                    vt.add(objValue);
                }
            }
            data.add(vt);
        }
        CloseConnection();
        return data;
    }
}
