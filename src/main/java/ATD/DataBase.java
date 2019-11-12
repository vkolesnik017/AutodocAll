package ATD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private String url = "jdbc:mysql://10.10.31.145:3306/autodoc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "alexeym";
    private String password = "24201901";


    private Connection coonectionDB(String nameDB) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to "+nameDB+" DB succesfull!");
        } catch (Exception ex) {
            System.out.println("Connection failed...");
        }
        return conn;
    }

    List<String> getRouteForMain(String shop) throws SQLException {
        Connection conn = coonectionDB("routes_atd");
        ArrayList<String> route = new ArrayList<>();
        String query = "SELECT " + shop + " FROM autodoc.routes_atd where id = 1";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int columns = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                route.add(resultSet.getString(i));
            }
        }
        statement.close();
        conn.close();
        return route;
    }

    public String getCurrency(String shop) throws SQLException {
        Connection conn = coonectionDB("currency");
        String curency = null;
        String query = "SELECT " + shop + " FROM autodoc.currency where id = 1";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            curency = resultSet.getString(1);
        }
        statement.close();
        conn.close();
        return curency;
    }
}
