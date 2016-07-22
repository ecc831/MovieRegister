/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class TableViewMovieRegister
{
    TableView<Movie> table= new TableView<>();
    ControlMovieRegister control;

    TableColumn<Movie, String> titlecol;
    TableColumn<Movie, String> germantitlecol;
    TableColumn<Movie, Genre> genrecol;
    TableColumn<Movie, String> directorcol ;
    TableColumn<Movie, Integer> yearcol ;
    TableColumn<Movie, Integer> lengthcol;
    TableColumn<Movie, Integer> fskcol ;
    TableColumn<Movie, Boolean> hdcol;

    public TableViewMovieRegister(MovieRegister register, ControlMovieRegister control)
    {
        this.control=control;
        table.getItems().addAll(register.movieList());
    }

    public TableView createTableViewMovieRegister (MovieRegister register)
    {

    /** TableView **/
    table.setEditable(true);

    /** Spalten erzeugen **/
  titlecol = new TableColumn<>("Originaltitel");
       germantitlecol = new TableColumn<>("deutscher Titel");
      genrecol = new TableColumn<>("Genre");
      directorcol = new TableColumn<>("Regisseur");
         yearcol  = new TableColumn<>("Erscheinungsjahr");
        lengthcol = new TableColumn<>("Spiellänge");
      fskcol = new TableColumn<>("FSK");
        hdcol = new TableColumn<>("in HD");

    /** erlaubt das Sortieren nach Spalte **/
    table.getColumns().addAll(titlecol,germantitlecol,genrecol,directorcol,yearcol,lengthcol,fskcol,hdcol);
    for (TableColumn col : table.getColumns())
        col.setSortable(true);

    /** Werte in die TableView einfügen **/
    titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
    germantitlecol.setCellValueFactory(new PropertyValueFactory<>("germanTitle"));
    genrecol.setCellValueFactory(new PropertyValueFactory<>("genre"));
    directorcol.setCellValueFactory(new PropertyValueFactory<>("director"));
    yearcol.setCellValueFactory(new PropertyValueFactory<>("year"));
    lengthcol.setCellValueFactory(new PropertyValueFactory<>("length"));
    fskcol.setCellValueFactory(new PropertyValueFactory<>("fsk"));
    hdcol.setCellValueFactory(e -> e.getValue().inHDProberty());

    titlecol.setCellFactory(TextFieldTableCell.forTableColumn());
    germantitlecol.setCellFactory(TextFieldTableCell.forTableColumn());
    genrecol.setCellFactory(c -> new ComboBoxTableCell<>(Genre.values()));
    directorcol.setCellFactory(TextFieldTableCell.forTableColumn());
    yearcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    lengthcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    fskcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    hdcol.setCellFactory(CheckBoxTableCell.forTableColumn(hdcol));

        return table;
    }


    public void refreshTable(MovieRegister register)
    {
        table.getItems().clear();
        table.getItems().addAll(register.movieList());
    }
}
