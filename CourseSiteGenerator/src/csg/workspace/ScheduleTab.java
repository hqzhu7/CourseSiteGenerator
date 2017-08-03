/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.ScheduleProp;
import csg.controllers.ScheController;
import csg.data.CSGData;
import csg.data.Schedule;
import csg.data.ScheData;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import static csg.controllers.ScheController.scheJ;
import djf.ui.AppGUI;
/**
 *
 * @author Hengqi Zhu
 */
public class ScheduleTab extends Tab{
    CSGApp app;
    
        //top
        Label scheduleTitle;
        Label calendarBound;
        Label start;
        Label end;
        DatePicker startDate;
        DatePicker endDate;
        VBox top;
        VBox datePane;
        HBox titleDel;
        Button confirm;
        
        //middle
        Label scheItem;
        Button del;
        TableView scheTable;
        TableColumn typeCol;
        TableColumn dateCol;
        TableColumn titleCol;
        TableColumn topicCol;
        VBox middle;
        
        //bottom
        Label addEdit;
        Label type,date,time,title,topic,link,criteria;
        Button addButton,clear;
        TextField timeText,titleText,topicText,linkText,criteriaText;
        ComboBox typeBox;
        DatePicker datePicker;   
        GridPane bottom;
        
        ObservableList<Schedule> scheList;
         ObservableList<String> typeList;
        
        GridPane schedulePane;
        ScheData data;
        ScheController controller;
    
    public ScheduleTab(CSGApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData csgData =  (CSGData) app.getDataComponent();
        data = csgData.getSechData();
       
        scheduleTitle = new Label(props.getProperty(ScheduleProp.SCHEDULETITLE.toString()));
        start = new Label(props.getProperty(ScheduleProp.START.toString()));
        calendarBound = new Label(props.getProperty(ScheduleProp.CALENDARBOUN.toString()));
        end = new Label(props.getProperty(ScheduleProp.END.toString()));
       
        startDate = new DatePicker();
        endDate = new DatePicker();
        top = new VBox();
        datePane = new VBox();
        datePane.setSpacing(10);
        datePane.setMinWidth(500);
        confirm = new Button("Confirm");
        confirm.setStyle("-fx-font-size: 14pt;\n" +
                         "-fx-font-weight: bold;  \n" +
                         "-fx-border-radius: 10 10 10 10; \n"+
                          "-fx-background-radius: 10 10 10 10;");
        datePane.getChildren().addAll(start,startDate,end,endDate,confirm);
        top.getChildren().addAll(calendarBound,datePane);
        
        //middle
        titleDel = new HBox();
        scheItem = new Label(props.getProperty(ScheduleProp.SCHEDULEITEM.toString()));
        del = new Button(props.getProperty(ScheduleProp.DELBUTTON.toString()));
        titleDel.getChildren().addAll(scheItem,del);
        
        scheTable = new TableView();
        scheTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        typeCol = new TableColumn(props.getProperty(ScheduleProp.TYPECOL.toString()));
        dateCol = new TableColumn(props.getProperty(ScheduleProp.DATECOL.toString()));
        titleCol = new TableColumn(props.getProperty(ScheduleProp.TITLECOL.toString()));
        topicCol = new TableColumn(props.getProperty(ScheduleProp.TOPICCOL.toString()));
        scheTable.getColumns().addAll(typeCol,dateCol,titleCol,topicCol);
        
        typeCol.setCellValueFactory(new PropertyValueFactory<Schedule,String>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Schedule,String>("date"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Schedule,String>("title"));
        topicCol.setCellValueFactory(new PropertyValueFactory<Schedule,String>("topic"));
        topicCol.setMinWidth(100);
        
        scheList = data.getSchedules();
        scheTable.setItems(scheList);
        
        scheTable.setFixedCellSize(25);
        scheTable.prefHeightProperty().bind(scheTable.fixedCellSizeProperty().multiply(Bindings.size(scheTable.getItems()).add(2.3)));
        scheTable.setMaxWidth(750);
        
        middle = new VBox();
        middle.setSpacing(20);
        middle.getChildren().addAll(titleDel,scheTable);
        
        addEdit = new Label(props.getProperty(ScheduleProp.ADDEDIT.toString()));
        type = new Label(props.getProperty(ScheduleProp.TYPECOL.toString()));
        date = new Label(props.getProperty(ScheduleProp.DATECOL.toString()));
        time = new Label(props.getProperty(ScheduleProp.TIME.toString()));
        title = new Label(props.getProperty(ScheduleProp.TITLECOL.toString()));
        topic =  new Label(props.getProperty(ScheduleProp.TOPICCOL.toString()));
        link = new Label(props.getProperty(ScheduleProp.LINK.toString()));
        criteria = new Label(props.getProperty(ScheduleProp.CRITERIA.toString()));
        
        typeList  = FXCollections.observableArrayList("holidays","lectures","references","recitations","hws");
        typeBox = new ComboBox();
        typeBox.setItems(typeList);
        datePicker = new DatePicker();
        datePicker.setMinWidth(200);
        timeText = new TextField();
        timeText.setMinWidth(200);
        titleText = new TextField();
        titleText.setMinWidth(200);
        topicText = new TextField();
        topicText.setMinWidth(200);
        linkText = new TextField();
        linkText.setMinWidth(200);
        criteriaText = new TextField();
        criteriaText.setMinWidth(200);
        
        addButton = new Button(props.getProperty(ScheduleProp.ADDUPDATE.toString()));
        clear = new Button(props.getProperty(ScheduleProp.CLEAR.toString()));
        
        bottom = new GridPane();
        bottom.setVgap(10);
        bottom.setHgap(30);
        bottom.add(addEdit, 0, 0);
        bottom.add(type, 0, 1);bottom.add(typeBox, 1, 1);
        bottom.add(date, 0, 2);bottom.add(datePicker, 1, 2);
        bottom.add(time, 0, 3);bottom.add(timeText,1,3);
        bottom.add(title, 0, 4);bottom.add(titleText,1,4);
        bottom.add(topic, 0, 5);bottom.add(topicText, 1, 5);
        bottom.add(link, 0, 6);bottom.add(linkText, 1, 6);
        bottom.add(criteria, 0, 7);bottom.add(criteriaText,1,7);
        bottom.add(addButton, 0, 8);bottom.add(clear, 1, 8);
        
        schedulePane = new GridPane();
        schedulePane.setHgap(20);
        schedulePane.setVgap(20);

        
        //schedulePane.getChildren().addAll(scheduleTitle,top,middle,bottom);
        schedulePane.add(scheduleTitle, 0, 0);schedulePane.add(bottom, 1, 1);
        GridPane.setRowSpan(bottom, GridPane.REMAINING);
        schedulePane.add(top, 0, 1);
        schedulePane.add(middle, 0, 2);
        //schedulePane.setStyle("-fx-padding:20,20,10,10");

        this.setContent(schedulePane);
        this.setClosable(false);
        this.setText(props.getProperty(ScheduleProp.SCHEDULTAB.toString()));
        
        controller = new ScheController(app);
        
        confirm.setOnAction(e->{
            controller.handleDate();
        });
        
        this.scheTable.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.DELETE)
               controller.handleDelete();
        });
        
        this.addButton.setOnAction(e->{
            controller.handleAdd();
        });
        
        this.scheTable.setOnMouseClicked(e->{
            controller.handleclick();
        });
      
        this.del.setOnMouseClicked(e->{
            controller.handleDelete();
        });
        
        this.clear.setOnAction(e->{
            controller.handleClear();
        });
        schedulePane.setOnKeyPressed(e->{
            if(e.isControlDown()&&e.getCode()==KeyCode.Z)
              scheJ.undoTransaction();
            else if(e.isControlDown()&&e.getCode()==KeyCode.Y)
              scheJ.doTransaction();
            
            AppGUI gui = app.getGUI();
            gui.getFileController().markAsEdited(gui);
        });
        
        
    }

    public CSGApp getApp() {
        return app;
    }

    public Label getScheduleTitle() {
        return scheduleTitle;
    }

    public Label getCalendarBound() {
        return calendarBound;
    }

    public Label getStart() {
        return start;
    }

    public Label getEnd() {
        return end;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public VBox getTop() {
        return top;
    }

    public VBox getDatePane() {
        return datePane;
    }

    public HBox getTitleDel() {
        return titleDel;
    }

    public Label getScheItem() {
        return scheItem;
    }

    public Button getDel() {
        return del;
    }

    public TableView getScheTable() {
        return scheTable;
    }

    public TableColumn getTypeCol() {
        return typeCol;
    }

    public TableColumn getDateCol() {
        return dateCol;
    }

    public TableColumn getTitleCol() {
        return titleCol;
    }

    public TableColumn getTopicCol() {
        return topicCol;
    }

    public VBox getMiddle() {
        return middle;
    }
    
    public Button getConfirm() {
        return confirm;
    }

    public Label getAddEdit() {
        return addEdit;
    }

    public Label getType() {
        return type;
    }

    public Label getDate() {
        return date;
    }

    public Label getTime() {
        return time;
    }

    public Label getTitle() {
        return title;
    }

    public Label getTopic() {
        return topic;
    }

    public Label getLink() {
        return link;
    }

    public Label getCriteria() {
        return criteria;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getClear() {
        return clear;
    }

    public TextField getTimeText() {
        return timeText;
    }

    public TextField getTitleText() {
        return titleText;
    }

    public TextField getTopicText() {
        return topicText;
    }

    public TextField getLinkText() {
        return linkText;
    }

    public TextField getCriteriaText() {
        return criteriaText;
    }

    public ComboBox getTypeBox() {
        return typeBox;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public GridPane getBottom() {
        return bottom;
    }

    public ObservableList<Schedule> getScheList() {
        return scheList;
    }

    public ObservableList<String> getTypeList() {
        return typeList;
    }

    public GridPane getSchedulePane() {
        return schedulePane;
    }
    
    
    public void resetWorkspace(){
        scheList.clear();
    }
    
    
    
}
