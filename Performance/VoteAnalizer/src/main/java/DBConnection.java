import java.sql.*;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "testtest";

    private static StringBuilder insertBuilder = new StringBuilder();

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/" + dbName +
                    "?user=" + dbUser + "&password=" + dbPass +
                    "&serverTimezone=Europe/Moscow";
            connection = DriverManager.getConnection(url);
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name varchar(50) NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "unique key name_date(name(50), birthDate))" +
                    "engine = memory");
        }
        return connection;
    }

    public static void executeMultiInsert() throws Exception {
        String sql = "insert into voter_count(name, birthDate, `count`) " +
                "values" + insertBuilder.toString() +
                " on duplicate key update `count` = `count` + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) throws Exception {
        birthDay = birthDay.replace('.', '-');

        insertBuilder.append((insertBuilder.length() == 0 ? "" : ",") +
                "('" + name + "', '" + birthDay + "', 1)");
        if (insertBuilder.length() > 2_400_000) {
            executeMultiInsert();
            insertBuilder.setLength(0);
        }
    }

    public static void printVoterCounts() throws Exception {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1 " +
                "order by `count`, name";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }

}
