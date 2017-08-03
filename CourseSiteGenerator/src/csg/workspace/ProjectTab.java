/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import properties_manager.PropertiesManager;
import csg.ProjectProp;
import csg.controllers.ProjectController;
import csg.data.CSGData;
import csg.data.ProjectData;
import csg.data.Student;
import csg.data.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.*;
import javafx.stage.Screen;

/**
 *
 * @author Hengqi Zhu
 */
public class ProjectTab extends Tab{
    
    CSGApp app;
    
    //left
    GridPane leftPane;
    HBox titleDel;
    Label projectTitle,teamTitle,addEdit1,name,color,textColor, link;
    Button del1, addUpdate1, clear1;
    TableView teamTable;
    TableColumn nameCol,colorCol,textColorCol,linkCol;
    TextField nameText,linkText;
    Label c1, c2;
    StackPane circle1,circle2;
    Circle circleColor,circleTextColor;
    
    //right
    GridPane rightPane;
    HBox titleDel2;
    Label studentTitle,addEdit2,first,last,team, role;
    Button del2, addUpdate2, clear2;
    TableView studentTable;
    TableColumn firstCol,lastCol,teamCol,roleCol;
    TextField firstText,lastText,roleText;
    ComboBox teamBox;
    
    ObservableList<Team> teamList;
    ObservableList<Student> stuList;
    
    VBox projectPane;
    HBox sPane;
    ScrollPane left;
    ScrollPane right;
    
    ColorPicker cp1;
    ColorPicker cp2;
    
    ProjectData data;
    ProjectController controller;
    
    public ProjectTab(CSGApp initApp){
        
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData csgData =  (CSGData) app.getDataComponent();
        data = csgData.getProData();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        projectTitle = new Label(props.getProperty(ProjectProp.PROJECTTITLE.toString()));
  
        teamTitle = new Label(props.getProperty(ProjectProp.TEAMTITLE.toString()));
        del1 = new Button(props.getProperty(ProjectProp.DELBUTTON.toString()));
        titleDel = new HBox();
        titleDel.getChildren().addAll(teamTitle,del1);
        
        teamTable = new TableView();
        teamTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        nameCol = new TableColumn(props.getProperty(ProjectProp.NAMECOL.toString()));
        colorCol = new TableColumn(props.getProperty(ProjectProp.COLORCOL.toString()));
        textColorCol = new TableColumn(props.getProperty(ProjectProp.TEXTCOLORCOL.toString()));
        linkCol = new TableColumn(props.getProperty(ProjectProp.LINKCOL.toString()));
        teamTable.getColumns().addAll(nameCol,colorCol,textColorCol,linkCol);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));
        colorCol.setCellValueFactory(new PropertyValueFactory<Team,String>("color"));
        textColorCol.setCellValueFactory(new PropertyValueFactory<Team,String>("textColor"));
        linkCol.setCellValueFactory(new PropertyValueFactory<Team,String>("link"));
        
      
        teamList= data.getTeams();
        teamTable.setItems(teamList);
        teamTable.setMaxHeight(250);
        
        addEdit1 = new Label(props.getProperty(ProjectProp.ADDEDIT.toString()));
        name = new Label(props.getProperty(ProjectProp.NAMECOL.toString()));
        nameText = new TextField();
        nameText.setMinWidth(150);
        color = new Label(props.getProperty(ProjectProp.COLORCOL.toString()));
        textColor = new Label(props.getProperty(ProjectProp.TEXTCOLORCOL.toString()));
        link = new Label(props.getProperty(ProjectProp.LINKCOL.toString()));
        linkText = new TextField();
        nameText.setMinWidth(150);
        circleColor = new Circle(40);
        circleColor.setFill(BLUE);
        circleTextColor = new Circle(40);
        circleTextColor.setFill(WHITE);
        circle1 = new StackPane();
        circle2 = new StackPane();
        cp1= new ColorPicker();
        cp2 = new ColorPicker();
        HBox textCircle = new HBox();
        
        circle1.getChildren().addAll(cp1);
        circle2.getChildren().addAll(cp2);
        textCircle.getChildren().addAll(color,circle1);
        addUpdate1 = new Button(props.getProperty(ProjectProp.ADDUPDATE.toString()));
        clear1 = new Button(props.getProperty(ProjectProp.CLEAR.toString()));
        
        leftPane = new GridPane();
        leftPane.setVgap(10);
        leftPane.setHgap(10);
        leftPane.add(titleDel,0,0);
        leftPane.add(teamTable, 0, 1);
        GridPane.setColumnSpan(teamTable, GridPane.REMAINING);
        leftPane.add(addEdit1, 0, 2);
        leftPane.add(name, 0, 3);leftPane.add(nameText, 1, 3);
        leftPane.add(color, 0, 4);leftPane.add(textCircle, 1, 4);leftPane.add(textColor, 2, 4);leftPane.add(circle2, 3, 4);
        leftPane.add(link, 0, 5);leftPane.add(linkText, 1, 5);
        leftPane.add(addUpdate1, 0, 6);leftPane.add(clear1, 1, 6);
        
        titleDel2 = new HBox();
        studentTitle = new Label(props.getProperty(ProjectProp.STUTITLE.toString()));
        del2 = new Button(props.getProperty(ProjectProp.DELBUTTON.toString()));
        titleDel2.getChildren().addAll(studentTitle,del2);
        
        studentTable = new TableView();
        firstCol = new TableColumn(props.getProperty(ProjectProp.FIRSTCOL.toString()));
        lastCol = new TableColumn(props.getProperty(ProjectProp.LASTCOL.toString()));
        teamCol = new TableColumn(props.getProperty(ProjectProp.TEAMCOL.toString()));
        roleCol = new TableColumn(props.getProperty(ProjectProp.ROLECOL.toString()));
        studentTable.getColumns().addAll(firstCol,lastCol,teamCol,roleCol);
        
        firstCol.setCellValueFactory(new PropertyValueFactory<Student,String>("first"));
        lastCol.setCellValueFactory(new PropertyValueFactory<Student,String>("last"));
        teamCol.setCellValueFactory(new PropertyValueFactory<Student,String>("team"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Student,String>("role"));
        
        stuList = data.getStudents();

        studentTable.setItems(stuList);
        studentTable.setMinWidth(500);
        addEdit2= new Label(props.getProperty(ProjectProp.ADDEDIT.toString()));
        first = new Label(props.getProperty(ProjectProp.FIRSTCOL.toString()));
        last = new Label(props.getProperty(ProjectProp.LASTCOL.toString()));
        team = new Label(props.getProperty(ProjectProp.TEAMCOL.toString()));
        role = new Label(props.getProperty(ProjectProp.ROLECOL.toString()));
        
        firstText = new TextField();
        firstText.setMaxWidth(150);
        lastText = new TextField();
        lastText.setMaxWidth(150);
        roleText = new TextField();
        roleText.setMaxWidth(150);
        teamBox = new ComboBox();
        teamBox.setItems(teamList);
        teamBox.setMinWidth(150);
        
        addUpdate2 = new Button(props.getProperty(ProjectProp.ADDUPDATE.toString()));
        clear2 = new Button(props.getProperty(ProjectProp.CLEAR.toString()));
        
        rightPane = new GridPane();
        rightPane.setHgap(10);
        rightPane.setVgap(10);
        rightPane.add(titleDel2, 0, 0);
        rightPane.add(studentTable, 0, 1);
        GridPane.setColumnSpan(studentTable, GridPane.REMAINING);
        rightPane.add(first, 0, 2);rightPane.add(firstText, 1, 2);
        rightPane.add(last, 0, 3);rightPane.add(lastText, 1, 3);
        rightPane.add(team, 0, 4);rightPane.add(teamBox, 1, 4);
        rightPane.add(role, 0, 5);rightPane.add(roleText, 1, 5);
        rightPane.add(addUpdate2, 0, 6);rightPane.add(clear2, 1, 6);

        projectPane = new VBox();
        //left = new ScrollPane(leftPane);
        //right = new ScrollPane(rightPane);
        sPane = new HBox(leftPane,rightPane);
        sPane.setSpacing(100);
 
        projectPane.getChildren().addAll(projectTitle,sPane);

        this.setClosable(false);
        this.setContent(projectPane);
        this.setText(props.getProperty(ProjectProp.PROJECTDATA.toString()));
        
        controller = new ProjectController(app);
        this.addUpdate1.setOnAction(e->{
            controller.handleAdd();
        });
        
        this.clear1.setOnAction(e->{
            controller.handleClear1();
        });
        
        this.del1.setOnAction(e->{
            controller.handleDelete();
        });
        
        this.teamTable.setOnMouseClicked(e->{
            controller.handleClick1();
        });
        
        this.addUpdate2.setOnAction(e->{
            controller.handleAdd1();
        });
        this.del2.setOnAction(e->{
            controller.handleDelete1();
        });
        
        this.studentTable.setOnMouseClicked(e->{
            controller.handleClick2();
        });
        this.clear2.setOnAction(e->{
            controller.handleClear2();
        });
        this.teamTable.setOnKeyPressed(e->{
             if(e.getCode()==KeyCode.DELETE)
               controller.handleDelete();
        });
        this.studentTable.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.DELETE)
               controller.handleDelete1();
        });
        
    }

    public ColorPicker getCp1() {
        return cp1;
    }

    public ColorPicker getCp2() {
        return cp2;
    }

    
    public CSGApp getApp() {
        return app;
    }

    public GridPane getLeftPane() {
        return leftPane;
    }

    public HBox getTitleDel() {
        return titleDel;
    }

    public Label getProjectTitle() {
        return projectTitle;
    }

    public Label getTeamTitle() {
        return teamTitle;
    }

    public Label getAddEdit1() {
        return addEdit1;
    }

    public Label getName() {
        return name;
    }

    public Label getColor() {
        return color;
    }

    public Label getTextColor() {
        return textColor;
    }

    public Label getLink() {
        return link;
    }

    public Button getDel1() {
        return del1;
    }

    public Button getAddUpdate1() {
        return addUpdate1;
    }

    public Button getClear1() {
        return clear1;
    }

    public TableView getTeamTable() {
        return teamTable;
    }

    public TableColumn getNameCol() {
        return nameCol;
    }

    public TableColumn getColorCol() {
        return colorCol;
    }

    public TableColumn getTextColorCol() {
        return textColorCol;
    }

    public TableColumn getLinkCol() {
        return linkCol;
    }

    public TextField getNameText() {
        return nameText;
    }

    public TextField getLinkText() {
        return linkText;
    }

    public Label getC1() {
        return c1;
    }

    public Label getC2() {
        return c2;
    }

    public StackPane getCircle1() {
        return circle1;
    }

    public StackPane getCircle2() {
        return circle2;
    }

    public Circle getCircleColor() {
        return circleColor;
    }

    public Circle getCircleTextColor() {
        return circleTextColor;
    }

    public GridPane getRightPane() {
        return rightPane;
    }

    public HBox getTitleDel2() {
        return titleDel2;
    }

    public Label getStudentTitle() {
        return studentTitle;
    }

    public Label getAddEdit2() {
        return addEdit2;
    }

    public Label getFirst() {
        return first;
    }

    public Label getLast() {
        return last;
    }

    public Label getTeam() {
        return team;
    }

    public Label getRole() {
        return role;
    }

    public Button getDel2() {
        return del2;
    }

    public Button getAddUpdate2() {
        return addUpdate2;
    }

    public Button getClear2() {
        return clear2;
    }

    public TableView getStudentTable() {
        return studentTable;
    }

    public TableColumn getFirstCol() {
        return firstCol;
    }

    public TableColumn getLastCol() {
        return lastCol;
    }

    public TableColumn getTeamCol() {
        return teamCol;
    }

    public TableColumn getRoleCol() {
        return roleCol;
    }

    public TextField getFirstText() {
        return firstText;
    }

    public TextField getLastText() {
        return lastText;
    }

    public TextField getRoleText() {
        return roleText;
    }

    public ComboBox getTeamBox() {
        return teamBox;
    }

    public ObservableList<Team> getTeamList() {
        return teamList;
    }

    public ObservableList<Student> getStuList() {
        return stuList;
    }

    public VBox getProjectPane() {
        return projectPane;
    }

    public HBox getsPane() {
        return sPane;
    }

    public ScrollPane getLeft() {
        return left;
    }

    public ScrollPane getRight() {
        return right;
    }
    
    public void resetWorkspace(){
        teamList.clear();
         stuList.clear();
    }
}
