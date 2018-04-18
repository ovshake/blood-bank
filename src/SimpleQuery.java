import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class SimpleQuery {
    public TextField dontionYear;
    public ComboBox listOfAll;
    public ComboBox avalaibleBlood;
    public ComboBox hospital;
    public Label result;
    public CreateDatabase db = new CreateDatabase();


    public void onClickSubmit(ActionEvent actionEvent) throws Exception{
        String query = "";
        if(!(dontionYear.getText().compareTo("")==0))
        {
            String year = dontionYear.getText();
            query = "SELECT * FROM Blood where YEAR(DonationDate) = " + year + ";";
        }

        else if(listOfAll.getValue() != null)
        {
            String tablename = listOfAll.getValue().toString();
            query = "SELECT Name FROM " + tablename + ";";
        }

        else if(avalaibleBlood.getValue() != null)
        {
            String bld = avalaibleBlood.getValue().toString();
            query = "SELECT BloodGroup, Quantity, Price from Blood where RecievingDate IS null and BloodGroup = '" + bld + "';";
        }

        else if(hospital.getValue() != null)
        {
            String hptl = hospital.getValue().toString();
            query = "SELECT * from Hospital where Name = '" + hptl + "';";
        }


        //do whatever you want with this query string
        ResultSet rs = db.getDataFromDB(query);
        String s = printTable(rs);
        result.setText(s);
    }

    public void intialise_combo_box(){
        String[] listAll = {"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        String[] hospitalList = {"Hospital A","Hospital B","Hospital C"};
        for(String a : listAll)
            listOfAll.getItems().add(a);
        for(String a : avalaibleB)
            avalaibleBlood.getItems().add(a);
        for(String a : hospitalList)
            hospital.getItems().add(a);


    }


    public String printTable(ResultSet rs) throws Exception{
        ResultSet resultSet = rs;
        ResultSetMetaData rsmd = resultSet.getMetaData();
        String ans = "";
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print("|  ");
                    ans += "| ";
                }
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                ans += columnValue + " " + rsmd.getColumnName(i);
            }
            ans += '\n';
            System.out.println("");
        }
        return ans;
    }
}