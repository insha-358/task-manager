package task_manager;

import javafx.beans.property.SimpleStringProperty;

public class Taskmanage {
    private SimpleStringProperty str;
    Taskmanage(String str)
    {
        this.str = new SimpleStringProperty(str);
    }

    public String getStr() {
        return str.get();
    }

    public void setStr(String str) {
        this.str = new SimpleStringProperty(str);
    }
}
