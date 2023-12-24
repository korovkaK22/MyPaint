package com.example.mypaint.controllers;

import com.example.mypaint.actions.UserActionHolder;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.CanvasMemento;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.managers.ListViewMemento;
import com.example.mypaint.utils.CanvasFactory;
import com.example.mypaint.utils.CanvasUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LayersController implements Initializable {
    @Getter @Setter
    private ListViewManager listViewManager;
    @Getter @Setter
    private CanvasManager canvasManager;
    @Getter @Setter
    private UserActionHolder userActionHolder;
    @Getter @Setter
    private Stage stage;
    private int layerId = 1;


    @FXML
    @Getter
    private ListView<String> layersListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layersListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && (int) newValue >= 0) {
                canvasManager.setSelectedCanvas(listViewManager.getSize() - 1 - (int) newValue);
            }
        });
    }


    /**
     * Метод викликається кнопкою, створення нового шару поверх попереднього
     */
    public void addNewLayerAction() {
        userActionHolder.addUserAction();
        int position = Math.max(0, listViewManager.getSelectedItemPosition());
        Canvas canvas = CanvasFactory.createTransparentCanvas(canvasManager.getWidth(), canvasManager.getHeight());
        addNewLayer(canvas);
        listViewManager.setSelectedPosition(position);
    }

    /**
     * Додавання шару поверх вибраного
     * @param canvas Шар
     */
    public void addNewLayer(Canvas canvas) {
        listViewManager.addNewLayerOnTop("Layer " + layerId++);
        canvasManager.addNewCanvasOnTop(canvas, listViewManager.getSelectedItemPositionReverse());
    }


    /**
     * Метод викликається кнопкою, видалення вибраного шару
     */
    public void removeLayer() {
        userActionHolder.addUserAction();
        canvasManager.removeSelectedCanvas();
        listViewManager.removeSelectedLayer();
    }


    /**
     * Метод викликається кнопкою, клонування вибраного шару
     */
    public void duplicateSelectedLayer() {
        userActionHolder.addUserAction();
        if (listViewManager.getSelectedItemPosition() == -1) {
            return;
        }
        Canvas selectedCanvas = canvasManager.getSelectedCanvas();
        Canvas newCanvas = CanvasUtil.getCanvasCopyWithoutEvents(selectedCanvas);
        addNewLayer(newCanvas);
    }



}
