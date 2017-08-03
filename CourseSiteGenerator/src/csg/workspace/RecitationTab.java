/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import javafx.scene.control.Tab;
import csg.CSGApp;
import csg.RecitationProp;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import csg.data.Recitation;
import properties_manager.PropertiesManager;
import csg.RecitationProp;
import csg.controllers.ReciController;
import csg.data.CSGData;
import csg.data.ReciData;
import csg.data.TAData;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static csg.controllers.ReciController.reciJ;

/**
 *
 * @author Hengqi Zhu
 */
public class RecitationTab extends Tab{
    
    CSGApp app;
    
    Label reciTitle;
    TableView reciTable;
    VBox reciPane;
    VBox box123;
    GridPane bottom;
    Label addEdit;
    Label section;
    Label instructor;
    Label dayTime;
    Label location;
    Label ta1;
    Label ta2;
    Button addButton;
    Button clearButton;
    
    TableColumn sectionCol;
    TableColumn instCol;
    TableColumn dayTimeCol;
    TableColumn locCol; 
    TableColumn taCol1;
    TableColumn taCol2;
    
    ObservableList<Recitation> reciList;
    
    TextField sectionText;
    TextField instorText;
    TextField dayTimeText;
    TextField locationText;
    HBox titleBox;
    Button del;
    ComboBox taBox1;
    ComboBox taBox2;
    
    ReciData data;
    TAData taData;
    ReciController controller;
    
    
    public RecitationTab(CSGApp initApp){
    
        app = initApp;
        CSGData csgData =  (CSGData) app.getDataComponent();
        data = csgData.getRecitationData();
        taData = csgData.getTaData();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        reciTitle = new Label(props.getProperty(RecitationProp.RECITITLE.toString()));
        del = new Button("DEL");
        titleBox = new HBox();
        titleBox.setSpacing(10);
        titleBox.getChildren().addAll(reciTitle,del);
        
        reciTable = new TableView();
        reciTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        
        sectionCol = new TableColumn(props.getProperty(RecitationProp.SECTIONCOL.toString()));
        instCol =  new TableColumn(props.getProperty(RecitationProp.INSTCOL.toString()));
        dayTimeCol = new TableColumn(props.getProperty(RecitationProp.DAYTIMECOL.toString()));
        locCol = new TableColumn(props.getProperty(RecitationProp.LOCCOL.toString()));
        taCol1 = new TableColumn(props.getProperty(RecitationProp.TACOL12.toString()));
        taCol2 = new TableColumn(props.getProperty(RecitationProp.TACOL12.toString()));
        reciTable.getColumns().addAll(sectionCol,instCol,dayTimeCol,locCol,taCol1,taCol2);
        
        sectionCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("section"));
        sectionCol.setMinWidth(100);
        instCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("instructor"));
        instCol.setMinWidth(120);
        dayTimeCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("dayTime"));
        locCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("location"));
        taCol1.setCellValueFactory(new PropertyValueFactory<Recitation,String>("ta1"));
        taCol2.setCellValueFactory(new PropertyValueFactory<Recitation,String>("ta2"));
      
        reciList = data.getRecitations();
        reciTable.setItems(reciList);
        
        reciTable.setFixedCellSize(25);
        reciTable.prefHeightProperty().bind(reciTable.fixedCellSizeProperty().multiply(Bindings.size(reciTable.getItems()).add(2.3)));
        reciTable.setMaxWidth(880);
        
        bottom = new GridPane();
        section = new Label(props.getProperty(RecitationProp.SECTIONCOL.toString()));
        instructor = new Label(props.getProperty(RecitationProp.INSTCOL.toString()));
        dayTime = new Label(props.getProperty(RecitationProp.DAYTIMECOL.toString()));
        location = new Label(props.getProperty(RecitationProp.LOCCOL.toString()));
        ta1 = new Label(props.getProperty(RecitationProp.TA12.toString()));
        ta2 = new Label(props.getProperty(RecitationProp.TA12.toString()));
        
        sectionText = new TextField();
        sectionText.setMinWidth(250);
        instorText =  new TextField();
        instorText.setMinWidth(250);
        dayTimeText = new TextField();
        dayTimeText.setMinWidth(250);
        locationText = new TextField();
        locationText.setMinWidth(250);
        taBox1= new ComboBox();
        taBox1.setItems(taData.getTeachingAssistants());
        taBox1.setMaxWidth(150);
        taBox2 = new ComboBox();
        taBox2.setItems(taData.getTeachingAssistants());
        taBox2.setMaxWidth(150);
        addEdit = new Label(props.getProperty(RecitationProp.ADDEDIT.toString()));
        addButton = new Button(props.getProperty(RecitationProp.ADDUPDATE.toString()));
        clearButton = new Button(props.getProperty(RecitationProp.CLEAR.toString()));;
        
        bottom.add(addEdit,0,0);
        bottom.setVgap(10);
        bottom.setHgap(20);
        bottom.add(section,0,1);bottom.add(sectionText,1,1);
        bottom.add(instructor,0,2);bottom.add(instorText,1,2);
        bottom.add(dayTime,0,3);bottom.add(dayTimeText,1,3);
        bottom.add(location,0,4);bottom.add(locationText,1,4);
        bottom.add(ta1, 0, 5);bottom.add(taBox1,1,5);
        bottom.add(ta2,0,6);bottom.add(taBox2, 1, 6);
        bottom.add(addButton, 0, 7);bottom.add(clearButton, 1, 7);
        bottom.setMaxWidth(880);
        reciPane = new VBox();
        reciPane.setSpacing(30);
        box123 = new VBox(titleBox,reciTable);
        box123.setSpacing(30);
        reciPane.getChildren().addAll(box123,bottom);
       
        
        this.setContent(reciPane);
        this.setClosable(false);
        this.setText(props.getProperty(RecitationProp.RECITATIONTAB.toString()));
        
        controller = new ReciController(app);
        addButton.setOnAction(e->{
            controller.handleAddRecitation();
        });
        
        reciTable.setOnMouseClicked(e->{
            controller.handleClick();
        });
        
        reciTable.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.DELETE)
               controller.handleDelete();
        });
        
        
        clearButton.setOnAction(e->{
            controller.handleClear();
        });
        
        del.setOnAction(e->{
            controller.handleDelete();
        });
        
        
        reciPane.setOnKeyPressed(e->{
            if(e.isControlDown()&&e.getCode()==KeyCode.Z)
              reciJ.undoTransaction();
            else if(e.isControlDown()&&e.getCode()==KeyCode.Y)
              reciJ.doTransaction();
        });
        
        sectionText.setOnKeyPressed(e->{
            if(e.isControlDown()&&e.getCode()==KeyCode.Z)
              reciJ.undoTransaction();
            else if(e.isControlDown()&&e.getCode()==KeyCode.Y)
              reciJ.doTransaction();
        });
        
    

    } 
    public VBox getBox123(){return box123;}
    
    public CSGApp getApp() {
        return app;
    }

    public Label getReciTitle() {
        return reciTitle;
    }

    public TableView getReciTable() {
        return reciTable;
    }

    public VBox getReciPane() {
        return reciPane;
    }

    public GridPane getBottom() {
        return bottom;
    }

    public Label getAddEdit() {
        return addEdit;
    }

    public Label getSection() {
        return section;
    }

    public Label getInstructor() {
        return instructor;
    }

    public Label getDayTime() {
        return dayTime;
    }

    public Label getLocation() {
        return location;
    }

    public Label getTa1() {
        return ta1;
    }

    public Label getTa2() {
        return ta2;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public TableColumn getSectionCol() {
        return sectionCol;
    }

    public TableColumn getInstCol() {
        return instCol;
    }

    public TableColumn getDayTimeCol() {
        return dayTimeCol;
    }

    public TableColumn getLocCol() {
        return locCol;
    }

    public TableColumn getTaCol1() {
        return taCol1;
    }

    public TableColumn getTaCol2() {
        return taCol2;
    }

    public ObservableList<Recitation> getReciList() {
        return reciList;
    }

    public TextField getSectionText() {
        return sectionText;
    }

    public TextField getInstorText() {
        return instorText;
    }

    public TextField getDayTimeText() {
        return dayTimeText;
    }

    public TextField getLocationText() {
        return locationText;
    }

    public HBox getTitleBox() {
        return titleBox;
    }

    public Button getDel() {
        return del;
    }

    public ComboBox getTaBox1() {
        return taBox1;
    }

    public ComboBox getTaBox2() {
        return taBox2;
    }
     
}
