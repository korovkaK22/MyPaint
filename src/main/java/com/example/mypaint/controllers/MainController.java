package com.example.mypaint.controllers;

import com.example.mypaint.actions.UserActionHolder;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.tools.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private ScrollPane canvasScrollPanel;

    @FXML
    @Getter
    private ColorPicker colorPicker;

    @FXML
    @Getter
    private MenuItem editRedo;

    @FXML
    @Getter
    private MenuItem editUndo;

    @FXML
    private MenuItem effectDarkness;

    @FXML
    private MenuItem effectRotate;

    @FXML
    private MenuItem effectSize;

    @FXML
    private MenuItem fileOpen;

    @FXML
    private MenuItem fileSave;

    @FXML
    private MenuItem fileSaveAs;

    @FXML
    private MenuBar menuBar;

    @FXML
    @Getter
    private ChoiceBox<Integer> sizeChooser;

    @FXML
    @Getter
    private MenuButton toolsButton;

    @FXML
    private LayersController layersFXMLController;


    @FXML
    @Getter
    private StackPane mainStackPane;
    @Getter
    private ListViewManager listViewManager;
    @Getter
    private CanvasManager canvasManager;
    @Getter
    UserActionHolder userActionHolder;
    @Getter
    @Setter
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewManager = new ListViewManager(layersFXMLController.getLayersListView());
        canvasManager = new CanvasManager(mainStackPane.getChildren());
        userActionHolder = new UserActionHolder(canvasManager, listViewManager);

        layersFXMLController.setCanvasManager(canvasManager);
        layersFXMLController.setListViewManager(listViewManager);
        layersFXMLController.setStage(stage);
        layersFXMLController.setUserActionHolder(userActionHolder);

        new MainControllerInitializer(this, userActionHolder);
    }

    public void editUndo() {
        userActionHolder.undoLastUserAction();
    }

    public void editRedo() {
        userActionHolder.redoUserAction();
    }


}

