package com.example.mypaint.actions;

import com.example.mypaint.managers.CanvasMemento;
import com.example.mypaint.managers.ListViewMemento;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Клас, для збереження стану системи при дії користувача
 */
@Getter
public class UserAction {
    static int idC =0 ;
    int id;
    CanvasMemento canvasMemento;
    ListViewMemento listViewMemento;

    public UserAction(CanvasMemento canvasMemento, ListViewMemento listViewMemento) {
        this.canvasMemento = canvasMemento;
        this.listViewMemento = listViewMemento;
        this.id = UserAction.idC++;
    }

    @Override
    public String toString() {
        return "UserAction{" +
                "id=" + id +
                '}';
    }
}
