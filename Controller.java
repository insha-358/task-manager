package task_manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {
     int count =0;

    @FXML
    public TextField task;
    @FXML
    public TextField priority;
    @FXML
    public DatePicker duedate;
    @FXML
    public Button add;
    public Button delete;
    @FXML
    public TableView<Taskmanage> tasktable;
    @FXML
    private TableColumn<Taskmanage, String> taskCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskCol.setCellValueFactory(new PropertyValueFactory<Taskmanage, String>("Str"));
       // tasktable.setItems(getTask());
        try {
            add.setOnAction(event -> {
                file_entry();
            });
            delete.setOnAction(event -> {
                removing_task();
            });
        } catch (Exception e) {
            warnUser();
        }
    }

    private void file_entry() {
        try {
            FileOutputStream file = new FileOutputStream("C:\\Users\\User\\Desktop\\task_manager\\task.text", true);
            PrintStream print = new PrintStream(file);
            String entered_task = task.getText();
            String prior = priority.getText();
            LocalDate Date = duedate.getValue();
            print.print(prior);
            print.print(' ');
            print.print(entered_task);
            print.print(' ');
            print.print("DUE DATE : ");
            print.print(Date);
            if(count==0)
            setTaskTable();
            else
            {
                Taskmanage t = new Taskmanage(prior+" "+entered_task+" DUE DATE : "+Date);
                tasktable.getItems().add(t);
            }
            print.println();
            print.close();
            file.close();
            data_inserted();
        } catch (Exception e) {
            warnUser();
        }
    }

    private void warnUser() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Some error has occurred");
        alert.setContentText("Try Again");
        alert.show();
    }

    private void data_inserted() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Data has been entered successfully ");
        alert.show();
    }
    private void setTaskTable()
    {
        try{
           // DefaultTableModel table = (DefaultTableModel)tasktable.getColumns();
            FileInputStream file = new FileInputStream("C:\\Users\\User\\Desktop\\task_manager\\task.text");
            Scanner input = new Scanner(file);
             taskCol.setSortable(true);
            while(true)
                try{
                String obj = new String();
                obj = input.nextLine();
                Taskmanage st = new Taskmanage(obj);
                st.setStr(obj);
                tasktable.getItems().add(st);
                count++;
                }
                catch(Exception e)
                { break;}
        }
        catch(Exception e)
        {
            System.out.println(e);
            warnUser();
        }
    }
private void removing_task()
{
    Taskmanage s = tasktable.getSelectionModel().getSelectedItem();
    String t = s.getStr();
    if(s==null)
            warnUser();
    else {
        try {
            FileOutputStream f = new FileOutputStream("temp.txt");
            PrintStream p = new PrintStream(f);
            FileInputStream ff = new FileInputStream("C:\\Users\\User\\Desktop\\task_manager\\task.text");
            Scanner input = new Scanner(ff);
            while (true)
                try { String del =input.nextLine();
                    if(!t.equalsIgnoreCase(del))
                   p.println(del);

                } catch (Exception e) {
                    break;
                } p.close();
                input.close();
                f.close();
                ff.close();
            File file = new File("C:\\Users\\User\\Desktop\\task_manager\\task.text");
            file.delete();
            File tempfile = new File("temp.txt");
            File newfile = new File("task.txt");
            tempfile.renameTo(newfile);
        }
        catch (Exception e) {
                }

        }
    }
}


