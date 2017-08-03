/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CourseProp;
import csg.controllers.CourseController;
import csg.data.CSGData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import csg.data.Template;
import javafx.beans.binding.Bindings;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import csg.data.CourseData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import csg.workspace.MyCheckBoxTableCell

/**
 *
 * @author Hengqi Zhu
 */

public class CourseTab extends Tab{
    
    CSGApp app;
    
    GridPane top;
    VBox middle;
    GridPane bottom;
    GridPane courseTabPane;
    
    ScrollPane sp;
     
    //top
    Label courseInfo;
    Label subject;
    Label number;
    Label semester;
    Label year;
    Label title;
    Label instrName;
    Label instrHome;
    Label dir;

    ComboBox subjectBox;
    ComboBox numberBox;
    ComboBox semesterBox;
    ComboBox yearBox;
    TextField titleText;
    TextField instrNameText;
    TextField instrHomeText;
    Label dirAddress;
    Button changeTop;
    
    ObservableList<String> subjectList;
    ObservableList<Integer> numberList;
    ObservableList<String> semesterList;
    ObservableList<Integer> yearList;
    ObservableList<String> cssList;
    ObservableList<Template> templateList;
    
    //middle
    Label siteTemp;
    Label useTip;
    Label templateLoc;
    Button select;
    Label sitePage;
    TableColumn navbarCol;
    TableColumn filenameCol;
    TableColumn scriptCol;
    TableColumn< Template, Boolean > useCol;
    
    TableView<Template> siteFiles;
    
    //bottom
    Label pageStyle;
    Label school;
    Label leftFooter;
    Label rightFooter;
    Label stylesheet;
    Label note;
    Label schoolContent;
    Label leftContent;
    Label rightContent;
    Button change1;
    Button change2;
    Button change3;
    ComboBox cssFile;
    CourseData data;
    
    CourseController controller;

    public CourseTab(CSGApp initApp){
    
        app=(CSGApp)initApp;
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        this.setText(props.getProperty(CourseProp.COURSETAB.toString()));
        this.setClosable(false);
        CSGData csgData =  (CSGData) app.getDataComponent();
        data = csgData.getCourseData();
        
        //TOP
        String courseInofTitle = props.getProperty(CourseProp.COURSEINFO.toString());
        courseInfo = new Label(courseInofTitle);
        subject = new Label(props.getProperty(CourseProp.SUBJECT.toString()));
        number = new Label(props.getProperty(CourseProp.NUMBER.toString()));
        semester = new  Label(props.getProperty(CourseProp.SEMESTER.toString()));
        year = new  Label(props.getProperty(CourseProp.YEAR.toString()));
        title= new  Label(props.getProperty(CourseProp.TITLE.toString()));
        instrName= new  Label(props.getProperty(CourseProp.NAME.toString()));
        instrHome = new  Label(props.getProperty(CourseProp.HOME.toString()));
        dir = new  Label(props.getProperty(CourseProp.EXPORTDIR.toString()));
        changeTop = new Button(props.getProperty(CourseProp.CHANGE.toString()));
        
        subjectList = FXCollections.observableArrayList("CSE","AMS");
        numberList = FXCollections.observableArrayList(new Integer(219),new Integer(220));
        semesterList = FXCollections.observableArrayList("FALL","SPRING","SUMMER");
        yearList = FXCollections.observableArrayList(new Integer(2017),new Integer(2018));
        
        subjectBox = new ComboBox(subjectList);
        subjectBox.setMaxWidth(90);
        numberBox = new ComboBox(numberList); 
        numberBox.setMaxWidth(90);
        semesterBox = new ComboBox(semesterList);
        semesterBox.setMaxWidth(90);
        yearBox = new ComboBox(yearList);
        yearBox.setMaxWidth(90);
        titleText = new TextField();
        titleText.setPrefWidth(100);
        instrNameText = new TextField();
        instrNameText.setPrefWidth(250);
        instrHomeText = new TextField();
        instrHomeText.setPrefWidth(250);
        dirAddress = new Label();
        dirAddress.setMaxWidth(300);dirAddress.setMinWidth(300);
        

        top = new GridPane();
        top.setStyle("-fx-border: 1px");
        top.setHgap(10);
        top.setVgap(10);
        top.setPadding(new Insets(10, 10, 10, 10));
        top.add(courseInfo,0,0);
        //Pane seperate = new Pane();
        top.add(subject, 0, 1); top.add(subjectBox,1,1);top.add(number,2,1);top.add(numberBox,4,1);
        top.add(semester, 0, 2);top.add(semesterBox,1,2);top.add(year,2,2);top.add(yearBox,4,2);
        top.add(title, 0, 3);top.add(titleText, 1, 3);
        GridPane.setColumnSpan(titleText,GridPane.REMAINING);
        top.add(instrName,0,4);top.add(instrNameText,1,4);
        GridPane.setColumnSpan(instrNameText,GridPane.REMAINING);
        top.add(instrHome,0,5);top.add(instrHomeText,1,5);
        GridPane.setColumnSpan(instrHomeText,GridPane.REMAINING);
        top.add(dir,0,6);top.add(dirAddress,1,6);top.add(changeTop,2,6);
        
        //middle
        siteTemp = new Label(props.getProperty(CourseProp.SITETEMP.toString()));
        useTip = new Label(props.getProperty(CourseProp.USETIP.toString()));
        templateLoc = new Label(".\\templates\\CSE219");
        select = new Button(props.getProperty(CourseProp.SELECT.toString()));
        sitePage = new Label(props.getProperty(CourseProp.SITEPAGE.toString()));
        
        siteFiles= new TableView();
        siteFiles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        siteFiles.setEditable(true);
        
        //TableColumn useCol = new TableColumn(props.getProperty(CourseProp.USECOL.toString()));
         navbarCol = new TableColumn(props.getProperty(CourseProp.NAVBARCOL.toString()));
     
        filenameCol =  new TableColumn(props.getProperty(CourseProp.FILENAMECOL.toString()));
        scriptCol = new TableColumn(props.getProperty(CourseProp.SCRIPTCOL.toString()));
        
        
        useCol = new TableColumn<>( props.getProperty(CourseProp.USECOL.toString()) );
        
        useCol.setCellValueFactory((CellDataFeatures<Template, Boolean> param) -> param.getValue().getChecked());
        useCol.setCellFactory((TableColumn<Template,Boolean> p) -> new CheckBoxTableCell());

        siteFiles.getColumns().addAll(useCol,navbarCol,filenameCol,scriptCol);

        navbarCol.setCellValueFactory(new PropertyValueFactory<Template,String>("navbar"));
        filenameCol.setCellValueFactory(new PropertyValueFactory<Template,String>("name"));
        scriptCol.setCellValueFactory(new PropertyValueFactory<Template,String>("script"));
        templateList = FXCollections.observableArrayList(new Template("Home","index.html","HomeBuilder.js",false),
                                                        new Template("Syllabus","syllabus.html","SyllabusBuilder.js",false),
                                                        new Template("Schedule","schedule.html","Schedule.js",false),
                                                        new Template("HWs","hws.html","HWsBuilder.js",false),
                                                        new Template("Projects","projcets.html","Projectsbuilderi.js",false));

        siteFiles.setItems(templateList);
        
        siteFiles.setFixedCellSize(25);
        siteFiles.prefHeightProperty().bind(siteFiles.fixedCellSizeProperty().multiply(Bindings.size(siteFiles.getItems()).add(1.4)));
        siteFiles.setMaxWidth(550);

        
        middle = new VBox();
        middle.setStyle("-fx-padding: 10 10 10 10");
        middle.setSpacing(10);
        middle.getChildren().addAll(siteTemp,useTip,templateLoc,select,sitePage,siteFiles);
        
        //bottom
        pageStyle = new Label(props.getProperty(CourseProp.PAGESTYLE.toString()));
        school = new Label(props.getProperty(CourseProp.SCHOOL.toString()));
        leftFooter = new Label(props.getProperty(CourseProp.LEFTFOOTER.toString()));
        rightFooter = new Label(props.getProperty(CourseProp.RIGHTFOOTER.toString()));
        change1 = new Button(props.getProperty(CourseProp.CHANGE.toString()));
        change2 = new Button(props.getProperty(CourseProp.CHANGE.toString()));
        change3 = new Button(props.getProperty(CourseProp.CHANGE.toString()));
        note = new Label(props.getProperty(CourseProp.NOTEPAGESTYLE.toString()));
        note.setStyle("-fx-font-size:12pt");
        stylesheet = new Label(props.getProperty(CourseProp.STYLESHEET.toString()));
        cssList = FXCollections.observableArrayList("sea_wolf.css");
        cssFile = new ComboBox(cssList);
        schoolContent = new Label();
        schoolContent.setPrefWidth(250);
        leftContent = new Label();
        leftContent.setPrefWidth(250);
        rightContent = new Label();
        rightContent.setPrefWidth(250);
        
        bottom = new GridPane();
        bottom.setStyle("-fx-padding:10 10 10 10");
        bottom.setHgap(10);
        bottom.setVgap(10);
        bottom.setPadding(new Insets(10, 10, 10, 10));

        bottom.add(pageStyle, 0, 0);
        bottom.add(school, 0, 1);bottom.add(schoolContent, 1, 1);bottom.add(change1, 2, 1);
        bottom.add(leftFooter,0,2);bottom.add(leftContent, 1, 2);bottom.add(change2, 2, 2);
        bottom.add(rightFooter, 0, 3);bottom.add(rightContent, 1, 3);bottom.add(change3, 2, 3);
        bottom.add(stylesheet, 0, 4);bottom.add(cssFile,1,4);
        GridPane.setColumnSpan(cssFile,GridPane.REMAINING);
        bottom.add(note, 0, 5);
        GridPane.setColumnSpan(note,GridPane.REMAINING);
        
        courseTabPane = new GridPane();
        courseTabPane.setHgap(20);
        courseTabPane.setVgap(20);
        sp = new ScrollPane(courseTabPane);
        courseTabPane.add(top,0,0);
        courseTabPane.add(middle,0,1);
        courseTabPane.add(bottom, 1, 0);

        //css
        sp.setStyle("-fx-background-color:#FFECD7;");
        //courseTabPane.getStyleClass().setAll("-fx-padding:10 10 10 10","-fx-background-color:#FFECD7");
        
        controller = new CourseController(app);
        this.change1.setOnAction(e->{
            try {
                controller.handleChange1();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.change2.setOnAction(e->{
            try {
                controller.handleChange2();
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
        });
        this.change3.setOnAction(e->{
            try {
                controller.handleChange3();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        this.changeTop.setOnAction(e->{
            controller.handleChangeEx();
        });
        
        
        
      
        this.setContent(sp);

    }
    
        public GridPane getTop() {
        return top;
    }

    public VBox getMiddle() {
        return middle;
    }

    public GridPane getBottom() {
        return bottom;
    }

    public GridPane getCourseTabPane() {
        return courseTabPane;
    }

    public ScrollPane getSp() {
        return sp;
    }

    public Label getCourseInfo() {
        return courseInfo;
    }

    public Label getSubject() {
        return subject;
    }

    public Label getNumber() {
        return number;
    }

    public Label getSemester() {
        return semester;
    }

    public Label getYear() {
        return year;
    }

    public Label getTitle() {
        return title;
    }

    public Label getInstrName() {
        return instrName;
    }

    public Label getInstrHome() {
        return instrHome;
    }

    public Label getDir() {
        return dir;
    }

    public ComboBox getSubjectBox() {
        return subjectBox;
    }

    public ComboBox getNumberBox() {
        return numberBox;
    }

    public ComboBox getSemesterBox() {
        return semesterBox;
    }

    public ComboBox getYearBox() {
        return yearBox;
    }

    public TextField getTitleText() {
        return titleText;
    }

    public TextField getInstrNameText() {
        return instrNameText;
    }

    public TextField getInstrHomeText() {
        return instrHomeText;
    }

    public Label getDirAddress() {
        return dirAddress;
    }

    public Button getChangeTop() {
        return changeTop;
    }

    public ObservableList<String> getSubjectList() {
        return subjectList;
    }

    public ObservableList<Integer> getNumberList() {
        return numberList;
    }

    public ObservableList<String> getSemesterList() {
        return semesterList;
    }

    public ObservableList<Integer> getYearList() {
        return yearList;
    }

    public ObservableList<String> getCssList() {
        return cssList;
    }

    public ObservableList<Template> getTemplateList() {
        return templateList;
    }

    public Label getSiteTemp() {
        return siteTemp;
    }

    public Label getUseTip() {
        return useTip;
    }

    public Label getTemplateLoc() {
        return templateLoc;
    }

    public Button getSelect() {
        return select;
    }

    public Label getSitePage() {
        return sitePage;
    }

    public TableView<Template> getSiteFiles() {
        return siteFiles;
    }

    public Label getPageStyle() {
        return pageStyle;
    }

    public Label getSchool() {
        return school;
    }

    public Label getLeftFooter() {
        return leftFooter;
    }

    public Label getRightFooter() {
        return rightFooter;
    }

    public Label getStylesheet() {
        return stylesheet;
    }

    public Label getNote() {
        return note;
    }

    public Label getSchoolContent() {
        return schoolContent;
    }

    public Label getLeftContent() {
        return leftContent;
    }

    public Label getRightContent() {
        return rightContent;
    }

    public Button getChange1() {
        return change1;
    }

    public Button getChange2() {
        return change2;
    }

    public Button getChange3() {
        return change3;
    }

    public ComboBox getCssFile() {
        return cssFile;
    }

    public TableColumn getNavbarCol() {
        return navbarCol;
    }

    public TableColumn getFilenameCol() {
        return filenameCol;
    }

    public TableColumn getScriptCol() {
        return scriptCol;
    }

    public TableColumn<Template, Boolean> getUseCol() {
        return useCol;
    }
    
    public void resetWorkspace(){
     
    
    }
    
    public void reloadWorkspace(){
        if(data.getCourse()!=null){
            subjectBox.setPromptText(data.getCourse().getSubject());
            numberBox.setPromptText(data.getCourse().getNumber());
            semesterBox.setPromptText(data.getCourse().getSemester());
            yearBox.setPromptText(data.getCourse().getYear());
            titleText.setText(data.getCourse().getTitle());
            instrNameText.setText(data.getCourse().getName());
            instrHomeText.setText(data.getCourse().getHome());
            
        }else
        {
            System.out.println("fsdfsdfasdfsafdsfdsaf");
        }
    }
    
}
