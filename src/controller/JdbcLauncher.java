package controller;

import model.Klant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    Onderstaand script gebruiken in MySQL Workbench
    CREATE USER 'userBeheer'@'localhost' IDENTIFIED BY 'wachtwoord123';
    GRANT ALL PRIVILEGES ON ProjectBeheer.* TO 'userBeheer'@'localhost';
*/
public class JdbcLauncher {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PREFIX_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String CONNECTION_SETTINGS = "?useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";

    public static void main(String[] args) {

        final String databaseName = "projectbeheer";
        final String mainUser = "userBeheer";
        final String mainUserPassword = "wachtwoord123";

        String connectionURL = PREFIX_CONNECTION_URL + databaseName + CONNECTION_SETTINGS;
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(connectionURL, mainUser, mainUserPassword);
        } catch (ClassNotFoundException error) { // catch voor het niet vinden van de driver
            System.out.println("MySQL JDBC driver niet gevonden.");
        } catch (SQLException sqlFout) { // catch voor connectie errors met de database
            System.out.println("SQL Exception: " + sqlFout.getMessage());
        }

        Klant nieuweKlant = new Klant(8, "Axion", "Bussum");

        String sql = "INSERT INTO Klant VALUES (?, ?, ?);";
        if (connection != null) {
            try {
                PreparedStatement insertStatement = connection.prepareStatement(sql);
                insertStatement.setInt(1, nieuweKlant.getKlantNummer());
                insertStatement.setString(2, nieuweKlant.getKlantNaam());
                insertStatement.setString(3, nieuweKlant.getLocatie());
                insertStatement.executeUpdate();
            } catch ( SQLException sqlError) {
                System.out.println("SQL Exception: " + sqlError.getMessage());
            }
        }

        List<Klant> klantList = new ArrayList<>();
        if (connection != null) {
            String selectSql = "SELECT * FROM Klant;";
            try {
                PreparedStatement selectStatement = connection.prepareStatement(selectSql);
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    int nummer = resultSet.getInt("klantNummer");
                    String naam = resultSet.getString("klantNaam");
                    String locatie = resultSet.getString("locatie");
                    klantList.add(new Klant(nummer, naam, locatie));
                }
            } catch ( SQLException sqlError) {
                System.out.println("SQL Exception: " + sqlError.getMessage());
            }
        }

        for (Klant klant : klantList) {
            System.out.println(klant);
        }

    }
}
