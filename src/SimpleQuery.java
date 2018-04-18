import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

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
        buildData(rs);

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