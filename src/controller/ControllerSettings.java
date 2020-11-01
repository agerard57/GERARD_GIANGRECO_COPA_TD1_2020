package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import dao.factory.DAOFactory;
import enumz.ModeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerSettings {

	private static DAOFactory daof;
	
    @FXML
    private VBox vbSettings;

    @FXML
    private TabPane tabSettings;

    @FXML
    private Tab tabMode;

    @FXML
    private AnchorPane apMode;

    @FXML
    private RadioButton rbLM;

    @FXML
    private ToggleGroup tgMode;

    @FXML
    private RadioButton rbBDD;

    @FXML
    private Tab tabTheme;

    @FXML
    private AnchorPane apTheme;

    @FXML
    private RadioButton rbNoir;

    @FXML
    private ToggleGroup tgTheme;

    @FXML
    private RadioButton rbBlanc;

    @FXML
    private Tab tabAbout;

    @FXML
    private AnchorPane apAbout;

    @FXML
    private ImageView btnGit;

    @FXML
    private ImageView btnTrello;

    @FXML
    private Label lblGit;

    @FXML
    private Label lblTrello;

    @FXML
    private Label lblAbout;

    @FXML
    private AnchorPane apBtn;

    @FXML
    private Button btnFermer;

    
    @FXML
    void fermerPage(ActionEvent event) {
		Stage stage = (Stage) btnFermer.getScene().getWindow();
		stage.close();
    }
    

 //--------------------------MODE-------------------------------
    
    @FXML
    void modeBaseDeDonnees(ActionEvent event) {
    	setDaof(DAOFactory.getDAOFactory(ModeDAO.MYSQL));
    }

    @FXML
    void modeListeMemoire(ActionEvent event) {
    	setDaof(DAOFactory.getDAOFactory(ModeDAO.LISTE_MEMOIRE)); 
    }
    
    //--------------------------THEME------------------------------
    
    //--------------------------ABOUT------------------------------   
    @FXML
    void pageGit(MouseEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://github.com/agerard57/GERARD_GIANGRECO_CPOA_TD1_2020").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}

    }

    @FXML
    void pageTrello(MouseEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://trello.com/b/DcAjprl2/cpoa2020gerardgiangreco").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}

    }


	public static DAOFactory getDaof() {
		return daof;
	}


	public static void setDaof(DAOFactory daof) {
		ControllerSettings.daof = daof;
	}

}
