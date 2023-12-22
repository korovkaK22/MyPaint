package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;


public class ListViewManager {
    private final ListView<String> layersListView;
    private ObservableList<String> list;



    public ListViewManager(ListView<String> layersListView) {
        this.layersListView = layersListView;
        this.list = layersListView.getItems();
    }

    /**
     * Створює новий шар поверх вибраного
     */
    public void addNewLayerOnTop(String layerName) {
        int position = getSelectedItemPosition();
        if (position == -1) {
            list.add(layerName);
        } else {
            list.add(position, layerName);
        }
    }

    /**
     * Додати новий шар в список
     *
     * @param position  позиція шару
     * @param layerName назва шару
     */
    public void addNewLayer(int position, String layerName) {
        list.add(position, layerName);
    }


    /**
     * Зробити конкрений шар вибраним
     *
     * @param index індекс вибраного елементу
     */
    public void chooseLayer(int index) {
        layersListView.getSelectionModel().select(index);
    }



    /**
     * видалити вибраний шар
     */
    public void removeSelectedLayer() {
        int index = getSelectedItemPosition();
        list.remove(index);
        if (list.isEmpty()) {
            chooseLayer(-1);
        } else if (index < list.size()) {
            chooseLayer(index);
        } else {
            chooseLayer(index - 1);
        }
    }

    /**
     * Отримати позицію шару, який зараз вибрано;
     * Позиціонування йде з originalList
     * @return позиція, якщо return -1, то позиція не вибрана
     */
    public int getSelectedItemPosition() {
        return  layersListView.getSelectionModel().getSelectedIndex();
    }

    public int getSelectedItemPositionReverse() {
        return list.size()-1- layersListView.getSelectionModel().getSelectedIndex();
    }


    public int getSize(){
        return list.size();
    }

}
