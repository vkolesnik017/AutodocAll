package ATD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String url = "jdbc:mysql://10.10.34.37:3306/autodoc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "alexeym";
    private String password = "24201901";


    private Connection coonectionDB(String nameDB) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to " + nameDB + " DB succesfull!");
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
        return conn;
    }

    // Return list from route Main By Shops getRouteListForMain("AT,DE,CH")
    List<String> getRouteListForMain(String shop) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("routes_atd");
        ArrayList<String> route = new ArrayList<>();
        String query = "SELECT " + shop + " FROM autodoc.routes_atd where id = 1";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    route.add(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
        if (conn != null) conn.close();
    }
        return route;
    }

    // Return list routes By Shops and route getRouteListByRouteName("AT,DE,CH", "lkw_main")
    List<String> getRouteListByRouteName(String shop, String routeName) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("routes_atd");
        ArrayList<String> route = new ArrayList<>();
        String query = "SELECT " + shop + " FROM autodoc.routes_atd where route_name = '" + routeName + "'";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    route.add(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        return route;
    }

    // Return String route By Shop and route getRouteByRouteName("AT", "lkw_main")
    public String getRouteByRouteName(String shop, String routeName) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("routes_atd");
        ArrayList<String> route = new ArrayList<>();
        String query = "SELECT " + shop + " FROM autodoc.routes_atd where route_name = '" + routeName + "'";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    route.add(resultSet.getString(i));
                }
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        return route.get(0);
    }

    // Return String Currency By Shop getCurrency("AT")
    public String getCurrency(String shop) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("currency");
        String curency = null;
        String query = "SELECT " + shop + " FROM autodoc.currency where id = 1";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                curency = resultSet.getString(1);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        return curency;
    }

    // Return String KBA By Shop getKba("AT")
    public String getKba(String shop) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("currency");
        String kba = null;
        String query = "SELECT " + shop + " FROM autodoc.kba_ATD where id = 1";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                kba = resultSet.getString(1);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        return kba;
    }

    public String getRetoureCauseTranslate(String shop, String value) throws SQLException {
        Statement statement = null;
        Connection conn = coonectionDB("currency");
        String translation = null;
        String query = "SELECT " .concat(shop) + " FROM autodoc.retoure_translate where valueCause="+"\"".concat(value)+"\"";
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                translation = resultSet.getString(1);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        return translation;
    }
}
