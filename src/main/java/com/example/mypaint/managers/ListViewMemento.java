package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListViewMemento {
    private List<String> listElements;
    private int selectedPosition;
}
