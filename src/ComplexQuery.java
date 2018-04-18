import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;

public class ComplexQuery {

    public ComboBox avgPriceBGroup;
    public ComboBox centreComboBox;
    public ComboBox BloodGroupCBox;
    public ComboBox avgAgeRecipientCBox;
    public Label outputBox;
    public TableView resultTable;
    CreateDatabase db = new CreateDatabase();

    public void onClickDonorGo(ActionEvent actionEvent) {
        String query = "SELECT Donor.Name as DonorName, SUM(Quantity) from Donor, Blood where Donor.DonorID = Blood.DonorID GROUP BY Blood.DonorID ORDER BY SUM(Quantity) DESC;";
        finalFun(query);
    }

    public void onClickGo1(ActionEvent actionEvent) {
        if(avgPriceBGroup.getValue() != null)
        {
            String query = "SELECT BloodGroup, AVG(Price) from Blood where BloodGroup = '" + avgPriceBGroup.getValue().toString() + "' GROUP BY BloodGroup;";
            finalFun(query);
        }
    }

    public void onClickGo2(ActionEvent actionEvent) {
        if(centreComboBox.getValue() != null && BloodGroupCBox.getValue() != null)
        {
            String query = "SELECT B.BloodGroup, B.Quantity, B.Price from Blood B INNER JOIN Donor on B.DonorID = Donor.DonorID where Donor.Centres = '" + centreComboBox.getValue().toString() + "' and B.Price <= " + BloodGroupCBox.getValue().toString() + ";";
            finalFun(query);
        }
    }

    public void onClickGo3(ActionEvent actionEvent) {
        if(avgAgeRecipientCBox.getValue() != null)
        {
            String query = "SELECT AVG(Recipient.Age) from Recipient, Hospital where Hospital.Name = '" + avgAgeRecipientCBox.getValue().toString() + "' and Hospital.HospitalID = Recipient.HospitalID;";
            finalFun(query);
        }
    }

    public void onClickCountBloodBank(ActionEvent actionEvent) {
        String query = "SELECT COUNT(1) as NumberOfBloodSamples from Blood;";
        finalFun(query);
    }


    public void intialise_combo_box(){
        String[] listAll = {"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        String[] hospitalList = {"Hospital A","Hospital B","Hospital C"};
        String[] centres = {"Centre A","Centre B","Centre C"};
        for(String a : centres)
            centreComboBox.getItems().add(a);
        for(String a : avalaibleB){
            avgPriceBGroup.getItems().add(a);
            BloodGroupCBox.getItems().add(a);
        }

        for(String a : hospitalList)
            avgAgeRecipientCBox.getItems().add(a);
    }

    public void finalFun(String query)
    {
        // do your sorcery here
        ResultSet rs = db.getDataFromDB(query);
        buildData(rs);
    }
    public void buildData(ResultSet rs){

        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try{


            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                resultTable.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            resultTable.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}