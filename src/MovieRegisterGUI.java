/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MovieRegisterGUI extends Application {

    Movie m;
    Movie oldVersion;
    Movie newVersion;
    MovieRegister register = new MovieRegister();
    CSMovieIO datei;

    private BorderPane rootlist;
    private BorderPane roottable;
    private TextField texttitle;
    private TextField textgermantitle;
    private ComboBox<Genre> genrebox;
    private TextField textdirector;
    private TextField textyear;
    private TextField textlength;
    private TextField textfsk;
    private TextField texttitle2;
    private TextField textgermantitle2;
    private ComboBox<Genre> genrebox2;
    private TextField textdirector2;
    private TextField textyear2;
    private TextField textlength2;
    private TextField textfsk2;
    private Label errorlabelListView;
    private Label errorlabelTableView;
    private Label labelgetMovie;
    private Button buttonSaveAdd;
    private Button buttonSaveUpdate;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonUpdate;
    private Button buttonQuit;
    private VBox boxrightlabel;
    private VBox boxrighttextfieldadd;
    private VBox boxrighttextfieldupdate;
    private HBox boxbottom;
    private HBox boxleft;
    private Group group;
    private TabPane tabPane;
    private ListViewMovieRegister list;
    private TableViewMovieRegister table;
    private BooleanProperty inHD;
    private ControlMovieRegister control;

    @Override
    public void start(Stage primaryStage) throws ExceptionNullObject, ExceptionNoKey, ExceptionIsExist {


        rootlist = new BorderPane();
        roottable = new BorderPane();
        group = new Group();
        boxleft = new HBox();
        boxrightlabel = new VBox();
        boxrighttextfieldadd = new VBox();
        boxrighttextfieldupdate = new VBox();
        boxbottom = new HBox();
        errorlabelListView = new Label();
        errorlabelTableView = new Label();
        labelgetMovie = new Label();
        texttitle = new TextField();
        textgermantitle = new TextField();
        textdirector = new TextField();
        textyear = new TextField();
        textlength = new TextField();
        textfsk = new TextField();
        genrebox = new ComboBox<>();
        texttitle2 = new TextField();
        textgermantitle2 = new TextField();
        textdirector2 = new TextField();
        textyear2 = new TextField();
        textlength2 = new TextField();
        textfsk2 = new TextField();
        genrebox2 = new ComboBox<>();
        buttonAdd = new Button("Film hinzufügen");
        buttonRemove = new Button("Film entfernen");
        buttonUpdate = new Button("Film bearbeiten");
        buttonQuit = new Button("Abbrechen");
        buttonSaveAdd = new Button("Speichern und Film hinzufügen");
        buttonSaveUpdate = new Button("Update Speichern");
        tabPane = new TabPane();


        control = new ControlMovieRegister(register);
        table = new TableViewMovieRegister(register,control);
        list = new ListViewMovieRegister(register);
        inHD = new SimpleBooleanProperty();
        datei=new CSMovieIO();


        /** TabPane **/
        Tab listview_tab = new Tab("ListView");
        listview_tab.setClosable(false);
        Tab tableview_tab = new Tab("TableView");
        tableview_tab.setClosable(false);
        tabPane.getTabs().addAll(listview_tab,tableview_tab);
        listview_tab.setContent(rootlist);
        tableview_tab.setContent(roottable);

        /** Fenster + Eigenschaften **/
        Scene scene = new Scene(tabPane,1000,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Register");

        /** BorderPanes **/
        rootlist.setLeft(boxleft);
        rootlist.setRight(group);
        rootlist.setBottom(boxbottom);
        roottable.setCenter(table.createTableViewMovieRegister(register));
        roottable.setBottom(errorlabelTableView);
        roottable.setAlignment(errorlabelTableView, Pos.TOP_CENTER);

        /** TextField **/
        texttitle.setPromptText("Originaltitel");
        textgermantitle.setPromptText("deutscher Titel");
        textdirector.setPromptText("Regisseur");
        textyear.setPromptText("Jahr");
        textlength.setPromptText("Länge");
        textfsk.setPromptText("FSK");

        texttitle2.setPromptText("Originaltitel");
        textgermantitle2.setPromptText("deutscher Titel");
        textdirector2.setPromptText("Regisseur");
        textyear2.setPromptText("Jahr");
        textlength2.setPromptText("Länge");
        textfsk2.setPromptText("FSK");

        /** ComboBox **/
        genrebox.getItems().addAll(Genre.values());

        /** HBox und VBox **/
        boxrighttextfieldadd.getChildren().addAll(texttitle, textgermantitle, genrebox, textdirector,textyear,
                                                  textlength,textfsk, buttonSaveAdd);
        boxrighttextfieldupdate.getChildren().addAll(texttitle2, textgermantitle2, genrebox2, textdirector2,textyear2,
                                                     textlength2,textfsk2, buttonSaveUpdate);
        boxrightlabel.getChildren().addAll(labelgetMovie);
        boxleft.getChildren().addAll(list.createListViewMovieRegister(register));
        boxbottom.setAlignment(Pos.BOTTOM_CENTER);
        boxbottom.getChildren().addAll(buttonAdd, buttonRemove, buttonUpdate, buttonQuit, errorlabelListView);

        group.getChildren().addAll(boxrightlabel);


        /** CSV Daten einlesen **/
        try
        {
            register.fillFromFile(datei.readMovieFile("movie",":"));

            list.refreshList(register);
            table.refreshTable(register);

        } catch (IOException e)
        {
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Neustart des Movieregisters");
                alert.setHeaderText(null);
                alert.setContentText("Fülle das Register mit Filmen.");

                alert.showAndWait();
            }
        }

        /** Events für ListView **/
        buttonAdd.setOnAction(e -> addMovieButton());
        buttonSaveAdd.setOnAction(e -> saveMovie());
        buttonSaveUpdate.setOnAction(e -> saveUpdate());
        buttonUpdate.setOnAction(e -> updateMovieButton());
        buttonRemove.setOnAction(e -> removeMovieButton());
        buttonQuit.setOnAction(e -> quit());
        list.createListViewMovieRegister(register).setOnEditCommit(e -> saveCell(e));
        list.createListViewMovieRegister(register).getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                labelgetMovie.setText(register.getMovie(newValue).toString());
            } catch (ExceptionNotExist exceptionNotExist) {
            } catch (ExceptionNoKey exceptionNoKey) {
            }
        });

        /** Events für TableView **/
        table.titlecol.setOnEditCommit(e -> saveTitle(e));
        table.germantitlecol.setOnEditCommit(e -> saveGmTitle(e));
        table.genrecol.setOnEditCommit(e -> control.saveGenre(e));
        table.directorcol.setOnEditCommit(e -> control.saveDirector(e));
        table.yearcol.setOnEditCommit(e -> control.saveYear(e));
        table.lengthcol.setOnEditCommit(e -> control.saveLength(e));
        table.fskcol.setOnEditCommit(e -> control.saveFSK(e));
        table.hdcol.setOnEditCommit(e -> control.saveInHD(e));

        /** Event für CSV Datei **/
        scene.setOnMouseExited(e -> writeCSV(e));

        primaryStage.show();
    }


    private void saveTitle(TableColumn.CellEditEvent<Movie, String> e) {

        errorlabelTableView.setText("");

        try {
            control.saveTitle(e);
        } catch (ExceptionNotExist exceptionNotExist) {

            errorlabelTableView.setText("Film ist nicht vorhanden");
            errorlabelTableView.setTextFill(Color.RED);
        } catch (ExceptionIsExist exceptionIsExist) {
            errorlabelTableView.setText("Film bereits vorhanden");
            errorlabelTableView.setTextFill(Color.RED);
        } catch (ExceptionNoKey exceptionNoKey) {
            errorlabelTableView.setText("Falsche bzw. leere Eingabe");
            errorlabelTableView.setTextFill(Color.RED);
        }

        list.refreshList(register);
        table.refreshTable(register);

    }

    private void saveGmTitle(TableColumn.CellEditEvent<Movie, String> e) {

        errorlabelTableView.setText("");

        try {

            control.saveGmTitle(e);
        } catch (ExceptionNotExist exceptionNotExist) {
            errorlabelTableView.setText("Film ist nicht vorhanden");
            errorlabelTableView.setTextFill(Color.RED);
        } catch (ExceptionIsExist exceptionIsExist) {
            errorlabelTableView.setText("Film bereits vorhanden");
            errorlabelTableView.setTextFill(Color.RED);
        } catch (ExceptionNoKey exceptionNoKey) {
            errorlabelTableView.setText("Falsche bzw. leere Eingabe");
            errorlabelTableView.setTextFill(Color.RED);
        }

        list.refreshList(register);
        table.refreshTable(register);

    }



    /** Event für das klicken des UpdateButton **/
    private void updateMovieButton() {

        /** Textfelder für die Titel werden auf nicht editierbar gesetzt **/
        texttitle2.setEditable(false);
        textgermantitle2.setEditable(false);

        group.getChildren().clear();
        group.getChildren().addAll(boxrighttextfieldupdate);

        boxrighttextfieldupdate.getChildren().removeAll(buttonSaveAdd, buttonSaveUpdate);
        boxrighttextfieldupdate.getChildren().add(buttonSaveUpdate);

        genrebox2.getItems().addAll(Genre.values());


        /** die Daten vom selektierten FIlm aus der ListView werden in die Textfelder geladen **/
        list.createListViewMovieRegister(register).getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                texttitle2.setText(register.getMovie(newValue).getTitle());
                textgermantitle2.setText(register.getMovie(newValue).getGermanTitle());
                genrebox2.setValue(register.getMovie(newValue).getGenre());
                textdirector2.setText(register.getMovie(newValue).getDirector());
                textyear2.setText(String.valueOf(register.getMovie(newValue).getYear()));
                textlength2.setText(String.valueOf(register.getMovie(newValue).getLength()));
                textfsk2.setText(String.valueOf(register.getMovie(newValue).getFsk()));
            } catch (ExceptionNotExist exceptionNotExist) {
            } catch (ExceptionNoKey exceptionNoKey) {
            }
        });
    }

    /** Updated den ausgewählten Film aus der ListView **/
    private void saveUpdate() {

        /** Holt sich die Daten aus den TextFields und speichert sie in ein neues Movie Objekt(oldVersion **/
        String title = texttitle2.getText();
        String germantitle = textgermantitle2.getText();
        Genre genre = genrebox2.getSelectionModel().getSelectedItem();
        String director = textdirector2.getText();
        int year;
        int length;
        int fsk;

        if(textlength2.getText().equals("")) {
            length = 0;
        }else {
            length = Integer.parseInt(textlength2.getText());
        }

        if(textfsk2.getText().equals("")){
            fsk = -100;
        }else {
            fsk  = Integer.parseInt(textfsk2.getText());
        }

        if(textyear2.getText().equals("")) {
            year = 0;
        }else{
            year = Integer.parseInt(textyear2.getText());
        }

        oldVersion = new Movie(title,germantitle,genre,director,year,length,fsk);

        /** holt sich die neuen Daten erneut aus den TextFields und speichert sie in das Movie Objekt newVersion **/
        Genre genreupdate = genrebox2.getSelectionModel().getSelectedItem();
        String directorupdate = textdirector2.getText();
        int yearupdate;
        int lengthupdate;
        int fskupdate;

        if(textlength2.getText().equals("")) {
            lengthupdate = 0;
        }else {
            lengthupdate = Integer.parseInt(textlength2.getText());
        }

        if(textfsk2.getText().equals("")){
            fskupdate = -100;
        }else {
            fskupdate  = Integer.parseInt(textfsk2.getText());
        }

        if(textyear2.getText().equals("")) {
            yearupdate = 0;
        }else{
            yearupdate = Integer.parseInt(textyear2.getText());
        }

        newVersion = new Movie(texttitle2.getText(),textgermantitle2.getText(),genreupdate,directorupdate,yearupdate,lengthupdate,fskupdate);

        /** Der Film wird aktualisiert, oldVersion wird aus der Hashmap gelöscht und newVerion wird hinzugefügt
         * ListView wird dann wieder aktualisiert **/
        control.saveUpdate(oldVersion, newVersion);

        list.refreshList(register);
        table.refreshTable(register);
    }


    /** Event für das bearbeiten des Titels direkt in der ListView **/
    private void saveCell(ListView.EditEvent<String> e) {
        int index = e.getIndex();

        String title = list.createListViewMovieRegister(register).getItems().get(index);

        try {
            control.saveCell(title, e.getNewValue());
        } catch (ExceptionNotExist exceptionNotExist) {
            errorlabelListView.setText("Film ist nicht vorhanden");
            errorlabelListView.setTextFill(Color.RED);
        } catch (ExceptionIsExist exceptionIsExist) {
            errorlabelListView.setText("Film bereits vorhanden");
            errorlabelListView.setTextFill(Color.RED);
        } catch (ExceptionNoKey exceptionNoKey) {
            errorlabelListView.setText("Falsche bzw. leere Eingabe");
            errorlabelListView.setTextFill(Color.RED);
        }

        list.refreshList(register);
        table.refreshTable(register);
    }

    /** Abbrechen Button, Details zum selektierten Film werden wieder angezeigt **/
    private void quit() {

        group.getChildren().clear();
        group.getChildren().addAll(boxrightlabel);


    }

    /** Event für den Film hinzufügen Button **/
    private void addMovieButton() {

        group.getChildren().clear();
        group.getChildren().addAll(boxrighttextfieldadd);

        boxrighttextfieldadd.getChildren().removeAll(buttonSaveAdd, buttonSaveUpdate);
        boxrighttextfieldadd.getChildren().add(buttonSaveAdd);

        texttitle.setEditable(true);
        textgermantitle.setEditable(true);

        list.refreshList(register);
        table.refreshTable(register);
    }

    /** Speichern Button um ein Film hinzuzufügen **/
    private void saveMovie() {

        errorlabelListView.setText("");

        /** die Daten aus den Texfeldern werden in das Movieobjekt m gespeichert **/
        String title = texttitle.getText();
        String germantitle = textgermantitle.getText();
        Genre genre = genrebox.getSelectionModel().getSelectedItem();
        String director = textdirector.getText();
        int year;
        int length;
        int fsk;

        if(textlength.getText().equals("")) {
            length = 0;
        }else {
            length = Integer.parseInt(textlength.getText());
        }

        if(textfsk.getText().equals("")){
            fsk = -100;
        }else {
            fsk  = Integer.parseInt(textfsk.getText());
        }

        if(textyear.getText().equals("")) {
            year = 0;
        }else{
            year = Integer.parseInt(textyear.getText());
        }

        m = new Movie(title, germantitle, genre, director,year,length,fsk);

        try {
            control.saveMovie(m);
        } catch (ExceptionNullObject exceptionNullObject) {
            errorlabelListView.setText("Kein Filmobjekt vorhanden");
            errorlabelListView.setTextFill(Color.RED);
        } catch (ExceptionIsExist exceptionIsExist) {
            errorlabelListView.setText("Film bereits vorhanden");
            errorlabelListView.setTextFill(Color.RED);
        } catch (ExceptionNoKey exceptionNoKey) {
            errorlabelListView.setText("Falsche bzw. leere Eingabe");
            errorlabelListView.setTextFill(Color.RED);
        }


        group.getChildren().remove(boxrighttextfieldadd);
        group.getChildren().addAll(boxrightlabel);


        list.refreshList(register);
        table.refreshTable(register);
    }

    /** ausgewählter Film in der ListView wird gelöscht (auch aus der Hashmap) **/
    private void removeMovieButton() {

        errorlabelListView.setText("");

        String delete = list.createListViewMovieRegister(register).getSelectionModel().selectedItemProperty().getValue();

        try {
            control.removeMovie(delete);
        } catch (ExceptionNoKey exceptionNoKey) {
            errorlabelListView.setText("Falsche bzw. leere Eingabe");
            errorlabelListView.setTextFill(Color.RED);
        } catch (ExceptionNotExist exceptionNotExist) {
            errorlabelListView.setText("Film ist nicht vorhanden");
            errorlabelListView.setTextFill(Color.RED);
        }

        group.getChildren().remove(boxrightlabel);

        list.refreshList(register);
        table.refreshTable(register);
    }



    private void writeCSV(MouseEvent e)
    {
        try
        {
            datei.writeMovieFile(register.movieList(),"movie",":");
        } catch (IOException exc)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warnung");
            alert.setHeaderText("Datenbankfehler");
            alert.setContentText("Achtung, deine Filmdaten werden nicht gespeichert!");

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {launch(args);}
}




