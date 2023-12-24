package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, який керує списком шарів, які виводяться на екран зліва, та синхронізує шари з канвасами.
 */
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
     * @param position  позиція шару
     * @param layerName назва шару
     */
    public void addNewLayer(int position, String layerName) {
        list.add(position, layerName);
    }


    /**
     * Зробити конкрений шар вибраним
     * @param index індекс вибраного елементу
     */
    public void setSelectedPosition(int index) {
        layersListView.getSelectionModel().select(index);
    }



    /**
     * видалити вибраний шар
     */
    public void removeSelectedLayer() {
        int index = getSelectedItemPosition();
        list.remove(index);
        if (list.isEmpty()) {
            setSelectedPosition(-1);
        } else if (index < list.size()) {
            setSelectedPosition(index);
        } else {
            setSelectedPosition(index - 1);
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

    /**
     * Отримати реверс позицію шару, який вибраний
     * @return реверс позиція, якщо return -1, то позиція не вибрана
     */
    public int getSelectedItemPositionReverse() {
        return list.size()-1- layersListView.getSelectionModel().getSelectedIndex();
    }

    /**
     * Отримати знімок менеджера, який потім можна буде загрузити для зміни всіх даних
     * @return знімок
     */
    public ListViewMemento getMemento(){
        List<String> listElements = new ArrayList<>(list);
        return new ListViewMemento(listElements, getSelectedItemPosition());
    }

    /**
     * встановити стан менеджера згідно знімка. Всі дані будуть перезаписані на дані зі знімку.
     * @param memento знімок
     */
    public void setMemento(ListViewMemento memento){
        list.clear();
        list.addAll(memento.getListElements());
        setSelectedPosition(memento.getSelectedPosition());
    }

    /**
     * Отримати кількість шарів
     * @return кількість шарів
     */
    public int getSize(){
        return list.size();
    }

}
