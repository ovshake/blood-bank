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

public class ComplexQuery {

    public ComboBox avgPriceBGroup;
    public ComboBox centreComboBox;
    public ComboBox BloodGroupCBox;
    public ComboBox avgAgeRecipientCBox;
    public Label outputBox;
    public TableView resultTable;
    public TextField BloodGroupCBox1;
    CreateDatabase db = new CreateDatabase();

    public void onClickDonorGo(ActionEvent actionEvent) throws SQLException {
        String query = "SELECT Donor.Name as DonorName, SUM(Quantity) from Donor, Blood where Donor.DonorID = Blood.DonorID GROUP BY Blood.DonorID ORDER BY SUM(Quantity) DESC;";
        finalFun(query);
    }

    public void onClickGo1(ActionEvent actionEvent) throws SQLException {
        if(avgPriceBGroup.getValue() != null)
        {
            String query = "SELECT BloodGroup, AVG(Price) from Blood where BloodGroup = '" + avgPriceBGroup.getValue().toString() + "' GROUP BY BloodGroup;";
            finalFun(query);
        }
    }

    public void onClickGo2(ActionEvent actionEvent) throws SQLException {
        if(centreComboBox.getValue() != null && BloodGroupCBox1.getText().compareTo("")!=0)
        {
            String query = "SELECT B.BloodGroup, B.Quantity, B.Price from Blood B INNER JOIN Donor on B.DonorID = Donor.DonorID where Donor.Centres = '" + centreComboBox.getValue().toString() + "' and B.Price <= " + BloodGroupCBox1.getText() + ";";
            System.out.println(query);
            finalFun(query);
        }
    }

    public void onClickGo3(ActionEvent actionEvent) throws SQLException {
        if(avgAgeRecipientCBox.getValue() != null)
        {
            String query = "SELECT AVG(Recipient.Age) from Recipient, Hospital where Hospital.Name = '" + avgAgeRecipientCBox.getValue().toString() + "' and Hospital.HospitalID = Recipient.HospitalID;";
            finalFun(query);
        }
    }

    public void onClickCountBloodBank(ActionEvent actionEvent) throws SQLException {
        String query = "SELECT COUNT(1) as NumberOfBloodSamples from Blood;";
        finalFun(query);
    }


    public void intialise_combo_box(){
        String[] listAll = {null,"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {null,"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        String[] hospitalList = {null,"Saroj Hospital","Max Hospital"};
        String[] centres = {null,"A","B"};
        for(String a : centres)
            centreComboBox.getItems().add(a);
        for(String a : avalaibleB){
            avgPriceBGroup.getItems().add(a);
        }

        for(String a : hospitalList)
            avgAgeRecipientCBox.getItems().add(a);
    }

    public void finalFun(String query) throws SQLException {
        // do your sorcery here

        ResultSet rs = db.getDataFromDB(query);

        JTable table = new JTable(SimpleQuery.buildTableModel(rs));

        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));
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