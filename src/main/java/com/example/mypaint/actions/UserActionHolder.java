package com.example.mypaint.actions;

import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.*;

/**
 * Клас, в якому зберігаються всі дії користувача та реалізована логіка
 * з кнопками "назад" та "вперед"
 */
public class UserActionHolder {
    private final BooleanProperty undoAvailable = new SimpleBooleanProperty();
    private final BooleanProperty redoAvailable = new SimpleBooleanProperty();
    private boolean silentMode = false ;
    CanvasManager canvasManager;
    ListViewManager listViewManager;

    public UserActionHolder(CanvasManager canvasManager, ListViewManager listViewManager) {
        this.canvasManager = canvasManager;
        this.listViewManager = listViewManager;
    }

    Deque<UserAction> queue = new ArrayDeque<>();
    Deque<UserAction> undoActionQueue = new ArrayDeque<>();
    public static int MAX_SIZE = 10;


    /**
     * Додати нову дію юзера. При виклиці цієї дії канваси та шари
     * автоматично збережуть свій стан, та загрузять його в систему
     */
    public void addUserAction() {
        if (queue.size() == MAX_SIZE) {
            queue.removeFirst();
        }
        UserAction action = new UserAction(canvasManager.getMemento(), listViewManager.getMemento());
        undoActionQueue.clear();
        queue.addLast(action);
        updateButtonAvailability();
        silentMode = false;
    }

    /**
     * Відкотити стан шарів та канвасів на 1 дію назад
     */
    public void undoLastUserAction() {
        if (queue.size() != 0) {
            if (undoActionQueue.isEmpty() && !silentMode){
                addUserAction();
                silentMode = true;
            }
            UserAction action = queue.removeLast();
            UserAction prewAction = queue.getLast();
            undoActionQueue.addLast(action);
            setToManagersLastMemento(prewAction);
            updateButtonAvailability();
        }
    }

    /**
     * Відкотити назад стан шарів та канвасів на 1 дію вперед
     */
    public void redoUserAction() {
        if (undoActionQueue.size() != 0) {
            UserAction action = undoActionQueue.removeLast();
            queue.addLast(action);
            setToManagersLastMemento(action);
            updateButtonAvailability();
        }
    }

    /**
     * Відкатити систему до конкретного стану
     * @param userAction стан
     */
    private void setToManagersLastMemento(UserAction userAction) {
        canvasManager.setMemento(userAction.getCanvasMemento());
        listViewManager.setMemento(userAction.getListViewMemento());
    }


    /**
     * Обновляє стан клікабельності кнопок
     */
    private void updateButtonAvailability() {
        undoAvailable.set(queue.size() > 0);
        redoAvailable.set(!undoActionQueue.isEmpty());
    }


    public BooleanProperty undoAvailableProperty() {
        return undoAvailable;
    }

    public BooleanProperty redoAvailableProperty() {
        return redoAvailable;
    }

}
