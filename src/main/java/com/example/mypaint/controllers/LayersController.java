package com.example.mypaint.controllers;

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
    @Getter
    @Setter
    private ListViewManager listViewManager;
    @Getter
    @Setter
    private CanvasManager canvasManager;
    @Getter
    @Setter
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
        Canvas canvas = CanvasFactory.createTransparentCanvas(canvasManager.getWidth(), canvasManager.getHeight());
        addNewLayerAction(canvas);
    }

    public void addNewLayerAction(Canvas canvas) {
        int position = Math.max(0, listViewManager.getSelectedItemPosition());
        listViewManager.addNewLayerOnTop("Layer " + layerId++);
        canvasManager.addNewCanvasOnTop(canvas, listViewManager.getSelectedItemPositionReverse());
        listViewManager.setSelectedPosition(position);
    }


    /**
     * Метод викликається кнопкою, видалення вибраного шару
     */
    public void removeLayer() {
        canvasManager.removeSelectedCanvas();
        listViewManager.removeSelectedLayer();
    }


    /**
     * Метод викликається кнопкою, клонування вибраного шару
     */
    public void duplicateSelectedLayer() {
        if (listViewManager.getSelectedItemPosition() == -1) {
            return;
        }
        Canvas selectedCanvas = canvasManager.getSelectedCanvas();
        Canvas newCanvas = CanvasUtil.getCanvasCopyWithoutEvents(selectedCanvas);
        addNewLayerAction(newCanvas);
    }

    CanvasMemento canvasMemento;
    ListViewMemento listViewMemento;

    public void connectLayers() {
        if (canvasMemento != null) {
            canvasManager.setMemento(canvasMemento);
            listViewManager.setMemento(listViewMemento);
            canvasMemento = null;
            listViewMemento = null;
            System.out.println("сет мементо");
        } else {
            canvasMemento = canvasManager.getMemento();
            listViewMemento = listViewManager.getMemento();
            System.out.println("гет мементо");
        }


        Canvas selectedCanvas = canvasManager.getSelectedCanvas();
        GraphicsContext gc = selectedCanvas.getGraphicsContext2D();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webm")
        );

        // Відкриття діалогового вікна для вибору файлу
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                // Завантаження та малювання вибраного зображення на канвасі
                Image image = new Image(file.toURI().toString());
                gc.drawImage(image, 0, 0, selectedCanvas.getWidth(), selectedCanvas.getHeight());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Помилка при завантаженні зображення: " + e.getMessage());
            }
        }
    }


}
