import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class SimpleQuery {
    public TextField dontionYear;
    public ComboBox listOfAll;
    public ComboBox avalaibleBlood;
    public ComboBox hospital;
    public Label result;
    public CreateDatabase db = new CreateDatabase();
    public TableView resultTable;


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


        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));

        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));


    }

    public void intialise_combo_box(){
        String[] listAll = {null,"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {null,"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        String[] hospitalList = {null,"Saroj Hospital","Max Hospital"};
        for(String a : listAll)
            listOfAll.getItems().add(a);
        for(String a : avalaibleB)
            avalaibleBlood.getItems().add(a);
        for(String a : hospitalList)
            hospital.getItems().add(a);


    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

}