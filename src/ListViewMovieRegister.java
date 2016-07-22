/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;

public class ListViewMovieRegister
{
    ListView<String> list = new ListView<>();

    public ListViewMovieRegister(MovieRegister register)
    {
        list.getItems().addAll(register.keySet());

    }

    public ListView<String> createListViewMovieRegister(MovieRegister register)
    {
        list.setEditable(true);
        list.setCellFactory(TextFieldListCell.forListView());


        return list;
    }

    public void refreshList(MovieRegister register)
    {
        list.getItems().clear();
        list.getItems().addAll(register.keySet());
    }
}
