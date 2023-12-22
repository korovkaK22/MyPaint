package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListViewMemento {
    private ListView<String> layersListView;
    private ObservableList<String> list;
}
