package com.example.mypaint.controllers;

import com.example.mypaint.PaintApplication;
import com.example.mypaint.actions.UserActionHolder;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.utils.CanvasFactory;
import com.example.mypaint.utils.CanvasUtil;
import com.example.mypaint.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


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

    public void fileOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                userActionHolder.addUserAction();
                Image image = new Image(file.toURI().toString());

                //Якщо пустий канвас або 1 шар
                if (canvasManager.canvasNumber() <= 1) {
                    Canvas c = CanvasFactory.createCanvasWithImage(image);
                    if (canvasManager.canvasNumber() == 1) {
                        canvasManager.removeSelectedCanvas();
                        canvasManager.addNewCanvasOnTop(c, 0);
                        canvasManager.setSelectedCanvas(c);
                    } else if (canvasManager.canvasNumber() == 0) {
                        layersFXMLController.addNewLayer(c);
                        listViewManager.setSelectedPosition(0);
                    }
                    canvasManager.verifySizes(c, true);
                } else {
                    //2 і більше шарів
                    Canvas selectedCanvas = canvasManager.getSelectedCanvas();

                    selectedCanvas.setWidth(Math.max(image.getWidth(), selectedCanvas.getWidth()));
                    selectedCanvas.setHeight((Math.max(image.getHeight(), selectedCanvas.getHeight())));
                    GraphicsContext gc = selectedCanvas.getGraphicsContext2D();
                    gc.drawImage(image, 0, 0);

                    canvasManager.verifySizes(selectedCanvas);

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception when loading image: " + e.getMessage());
            }
        }
    }


    public void fileSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FileUtils.savePictureOnFile(file, CanvasUtil.getFinaleImage(canvasManager.getCanvases()));
        }
    }

    public void effectRotate() {
        userActionHolder.addUserAction();
        Canvas selectedCanvas = canvasManager.getSelectedCanvas();
        WritableImage result = PaintApplication.getWebCanvasEffects().rotate(CanvasUtil.getImage(selectedCanvas));
        addImageToCanvasAndVerify(result, selectedCanvas);
    }


    /**
     * Додає канвас до всіх шарів картинку, і якщо вона одна, то підганяє розміри
     * @param image картинка
     * @param canvas канвас
     */
    private void addImageToCanvasAndVerify(WritableImage image, Canvas canvas){
        CanvasUtil.writeImageOnCanvas(image, canvas);
        if (canvasManager.canvasNumber() == 1) {
            canvas.setHeight(image.getHeight());
            canvas.setWidth(image.getWidth());
            canvasManager.verifySizes(canvas, true);
        } else {
            canvasManager.verifySizes(canvas);
        }
    }
}

