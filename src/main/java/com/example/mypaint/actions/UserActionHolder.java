package com.example.mypaint.actions;

import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.*;


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

    public void addUserAction() {
        if (queue.size() == MAX_SIZE) {
            queue.removeFirst();
        }

        UserAction action = new UserAction(canvasManager.getMemento(), listViewManager.getMemento());
        System.out.println("Нова дія: " + action); //==============
        undoActionQueue.clear();
        queue.addLast(action);
        updateButtonAvailability();
        silentMode = false;
    }

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
            System.out.println("Ундо до дії: "+prewAction); //==============
        }
    }

    public void redoUserAction() {
        if (undoActionQueue.size() != 0) {
            UserAction action = undoActionQueue.removeLast();
            queue.addLast(action);
            setToManagersLastMemento(action);
            updateButtonAvailability();
            System.out.println("Редо до дії: "+action); //==============
        }
    }

    private void setToManagersLastMemento(UserAction userAction) {
        canvasManager.setMemento(userAction.getCanvasMemento());
        listViewManager.setMemento(userAction.getListViewMemento());
    }


    private void updateButtonAvailability() {
        undoAvailable.set(queue.size() > 1);
        redoAvailable.set(!undoActionQueue.isEmpty());
    }

    public BooleanProperty undoAvailableProperty() {
        return undoAvailable;
    }

    public BooleanProperty redoAvailableProperty() {
        return redoAvailable;
    }

}
