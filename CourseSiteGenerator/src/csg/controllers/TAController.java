package csg.controllers;

import static djf.settings.AppPropertyType.INVALID_EMAIL;
import djf.ui.AppGUI;
import static csg.TaProp.*;
import djf.ui.AppMessageDialogSingleton;
import java.util.Collections;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import jtps.jTPS;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;
import csg.CSGApp;
import csg.data.CSGData;
import csg.data.EmailValidator;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import static csg.style.TAStyle.CLASS_HIGHLIGHTED_GRID_CELL;
import static csg.style.TAStyle.CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN;
import static csg.style.TAStyle.CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE;
import csg.workspace.TaTab;
import csg.workspace.Workspace;

/**
 * This class provides responses to all workspace interactions, meaning
 * interactions with the application controls not including the file
 * toolbar.
 * 
 * @author Richard McKenna
 * @version 1.0
 */
public class TAController {
    // THE APP PROVIDES ACCESS TO OTHER COMPONENTS AS NEEDED
    CSGApp app;
String oldName="";
String oldEmail="";
public static jTPS TaJ=new jTPS();
    /**
     * Constructor, note that the app must already be constructed.
     */
    public TAController(CSGApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
    }
    
    /**
     * This helper method should be called every time an edit happens.
     */    
 public   void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    /**
     * This method responds to when the user requests to add
     * a new TA via the UI. Note that it must first do some
     * validation to make sure a unique name and email address
     * has been provided.
     */
    public void handleAddTA() {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        
        Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab taTab = (TaTab)taworkspace.getTaTab();
        TextField nameTextField = taTab.getNameTextField();
        TextField emailTextField = taTab.getEmailTextField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData csgData =  (CSGData) app.getDataComponent();
        TAData data = csgData.getTaData();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
       if(taTab.getAddButton().getText().equals("Update TA")){
             TableView taTable = taTab.getTATable();
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
          TeachingAssistant ta = (TeachingAssistant)selectedItem;
           if(!(name.equals(oldName)&&email.equals(oldEmail))){
         nameTextField.setText("");
               emailTextField.setText("");
               taTab.getAddButton().setText("Add TA");
           jTPS_Transaction change=new change(oldName,oldEmail,name,email,data);
           TaJ.addTransaction(change );
           }
               else;
       }
       else{ 
             EmailValidator a=new EmailValidator();
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
        }
        // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));                        
        }
        // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.containsTA(name, email)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
        // EVERYTHING IS FINE, ADD A NEW TA
        else if(a.validate(email)){
            // ADD THE NEW TA TO THE DATA
        
            jTPS_Transaction tt=new addTATr(name,email,data);
            TaJ.addTransaction(tt);
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
        else{
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(INVALID_EMAIL), props.getProperty(INVALID_EMAIL)); 
        }
       }
       Collections.sort(data.getTeachingAssistants());
    }

    /**
     * This function provides a response for when the user presses a
     * keyboard key. Note that we're only responding to Delete, to remove
     * a TA.
     * 
     * @param code The keyboard code pressed.
     */
    public void checkselected(){
       
        Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();
            TableView taTable = workspace.getTATable();
         Object selectedItem = taTable.getSelectionModel().getSelectedItem(); 
            if (selectedItem != null) {
                 TeachingAssistant ta = (TeachingAssistant)selectedItem;
               
                workspace.getNameTextField().setText(ta.getName());
                oldName=ta.getName();
               workspace.getEmailTextField().setText(ta.getEmail());
            oldEmail=ta.getEmail();
                workspace.getAddButton().setText("Update TA");
            }
   
    }
    public void handleKeyPress(KeyEvent codee,KeyCode code) {
        // DID THE USER PRESS THE DELETE KEY?
        if ( code==KeyCode.DELETE) {
           Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();
            TableView taTable = workspace.getTATable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                jTPS_Transaction delete=new deleteTA(app,this,selectedItem, workspace);
                TaJ.addTransaction(delete);
            }
        }else if (code==KeyCode.Z&&codee.isControlDown()){
            TaJ.undoTransaction();
        }
        else if(code==KeyCode.Y&&codee.isControlDown()){
            TaJ.doTransaction();
        }
     
    }
    
public jTPS getJ(){
    return TaJ;
}
    /**
     * This function provides a response for when the user clicks
     * on the office hours grid to add or remove a TA to a time slot.
     * 
     * @param pane The pane that was toggled.
     */
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
         Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();
        TableView taTable = workspace.getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            CSGData csgData =  (CSGData) app.getDataComponent();
            TAData data = csgData.getTaData();
            String cellKey = pane.getId();
            
            // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
             jTPS_Transaction tt=new addtoGrid(cellKey,taName,data);
            TaJ.addTransaction(tt);
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
    
    public void handleGridCellMouseExited(Pane pane) {
        String cellKey = pane.getId();
        CSGData csgData =  (CSGData) app.getDataComponent();
        TAData data = csgData.getTaData();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();

        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);

        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }
    }

    public void handleGridCellMouseEntered(Pane pane) {
        String cellKey = pane.getId();
        CSGData csgData =  (CSGData) app.getDataComponent();
        TAData data = csgData.getTaData();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();
        
        // THE MOUSED OVER PANE
        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_CELL);
        
        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }
    }

    public void handleDelete() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Workspace taworkspace = (Workspace) app.getWorkspaceComponent();
        TaTab workspace = (TaTab)taworkspace.getTaTab();
            TableView taTable = workspace.getTATable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                jTPS_Transaction delete=new deleteTA(app,this,selectedItem, workspace);
                TaJ.addTransaction(delete);
            }
        
    
    }
}