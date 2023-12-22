package com.example.mypaint.controllers;

import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.tools.Pencil;
import com.example.mypaint.tools.ToolParams;
import com.example.mypaint.utils.factory.CanvasFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

        @FXML
        private ScrollPane canvasScrollPanel;

        @FXML
        private ColorPicker colorPicker;

        @FXML
        private MenuItem editRedo;

        @FXML
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
        private ChoiceBox<Integer> sizeChooser;

        @FXML
        private MenuButton toolsButton;

        @FXML
        private LayersController layersFXMLController;

        @FXML
        private CanvasController canvasesFXMLController;

        @Getter
        private ListViewManager listViewManager;
        private CanvasManager canvasManager;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                listViewManager = new ListViewManager(layersFXMLController.getLayersListView());
                canvasManager = new CanvasManager(canvasesFXMLController.getMainStackPane().getChildren(), new Pencil()); //==============

                layersFXMLController.setCanvasManager(canvasManager);
                canvasesFXMLController.setCanvasManager(canvasManager);
                layersFXMLController.setListViewManager(listViewManager);
                canvasesFXMLController.setListViewManager(listViewManager);

                initSizeChooser();
                initializeFirstLayer();
        }



        //========= Класи ініціалізації ========
        private void initializeFirstLayer(){
                listViewManager.addNewLayerOnTop("Шар 0");
                listViewManager.chooseLayer(0);
                canvasManager.addNewCanvasOnTop(CanvasFactory.createFilledCanvas(canvasManager.getWidth(), canvasManager.getHeight(), Color.WHITE));
                canvasManager.setSelectedCanvas(0);
        }



        private void initSizeChooser(){
                ObservableList<Integer> items = sizeChooser.getItems();
                for (int i = 1; i <= 10; i++) {
                        items.add(i);
                }
                sizeChooser.setValue(1);
        }

}

