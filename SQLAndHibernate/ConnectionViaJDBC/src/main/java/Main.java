import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static String sqlQuery =
            "       select course_name, " +
                    "   count(*) / ( " +
                    "       select max(month(subscription_date)) - " +
                    "           min(month(subscription_date)) + 1 " +
                    "       from purchaselist " +
                    "       where course_name = pl.course_name and " +
                    "           year(subscription_date) = 2018 " +
                    "       ) ratio " +
                    "from purchaselist pl " +
                    "where year(subscription_date) = 2018 " +
                    "group by course_name ";

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String course_name = resultSet.getString("course_name");
                String ratio = resultSet.getString("ratio");
                System.out.printf("%-36s%s\n", course_name, ratio);
            }
        }
    }
}
