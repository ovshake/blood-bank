import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateDatabase {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CreateDatabase() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            statement = connection.createStatement();


        }
        catch (Exception e){
            System.out.println("Exception ENCOUNTERED "+e);
        }
    }

    public ResultSet getDataFromDB(String query){
        try {

            resultSet = statement.executeQuery(query);
            return resultSet;

        }
        catch (Exception e){
            System.out.println("EXCEPTION IS "+e);
            return null;
        }
    }


}
