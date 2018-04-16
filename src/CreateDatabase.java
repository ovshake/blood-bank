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
            connection = DriverManager.getConnection("jdbc:mysql://192.168.66.182:3306/test","root","");
            statement = connection.createStatement();


        }
        catch (Exception e){
            System.out.println("Exception ENCOUNTERED "+e);
        }
    }

    public ResultSet getDataFromDB(String query){
        try {

            resultSet = statement.executeQuery(query);
            ResultSet returnResultSet = resultSet;
            System.out.println("RECORDS FROM DATABASE");
            while(resultSet.next()){
                String donorID = resultSet.getString("Donor ID");
                String bloodtype = resultSet.getString("Blood Type");
                String Centre = resultSet.getString("Centre");
                String gender = resultSet.getString("Gender");
                System.out.println(donorID + " " + Centre + " "+bloodtype + " " + gender);

            }
            return returnResultSet;

        }
        catch (Exception e){
            System.out.println("EXCEPTION IS "+e);
            return null;
        }
    }


}
