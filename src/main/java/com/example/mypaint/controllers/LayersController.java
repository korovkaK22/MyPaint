package com.example.mypaint.controllers;

import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.utils.factory.CanvasFactory;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class LayersController implements Initializable {
    @Getter
    @Setter
    private ListViewManager listViewManager;
    @Getter
    @Setter
    private CanvasManager canvasManager;

    private int layerId = 1;


    @FXML
    @Getter
    private ListView<String> layersListView;

    @FXML
    private TitledPane layersWindow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        layersListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && (int) newValue >= 0) {
                int index = layersListView.getItems().size() - (int) newValue - 1;
                System.out.println("Тепер вибрано елемент з індексом: " + index);
                changedSelectedLayer(index);
            }
        });
    }


    /**
     * Метод викликається кнопкою, створення нового шару поверх попереднього
     */
    public void addNewLayerAction() {
        int position = Math.max(0, listViewManager.getSelectedItemPosition());
        listViewManager.addNewLayerOnTop("Шар " + layerId++);
        canvasManager.addNewCanvas(CanvasFactory.createTransparentCanvas(canvasManager.getWidth(), canvasManager.getHeight()), position);
        listViewManager.chooseLayer(position);
        System.out.println("Новий вибраний шар: " + position);
    }


    /**
     * Метод викликається кнопкою, видалення вибраного шару
     */
    public void removeLayer() {
        int position = listViewManager.getSelectedItemPosition();
        System.out.println("Удаляю шар: " + position);
        listViewManager.removeSelectedLayer();
        canvasManager.removeSelectedCanvas();
    }

    public void changedSelectedLayer(int newValue) {
        canvasManager.setSelectedCanvas(newValue);
    }

    //todo коли вибираєтсья інший шар, то переключаються шари

}