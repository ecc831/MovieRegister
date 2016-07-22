/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import javafx.event.Event;
import javafx.scene.control.TableColumn;

public class ControlMovieRegister
{

    MovieRegister register;

    public ControlMovieRegister(MovieRegister register)
    {
        this.register = register;
    }

    public void saveMovie(Movie m) throws ExceptionNullObject, ExceptionNoKey, ExceptionIsExist
    {
            register.addMovie(m);
    }

    public void removeMovie(String delete) throws ExceptionNotExist, ExceptionNoKey
    {
        register.removeMovie(delete);
    }

    public void saveCell (String oldVal, String newVal) throws ExceptionIsExist, ExceptionNoKey, ExceptionNotExist
    {
        register.updateTitle(oldVal, newVal);
    }

    public void saveUpdate (Movie oldVersion, Movie newVersion)
    {
        register.updateMovie(oldVersion,newVersion);
    }

    public void saveGenre(TableColumn.CellEditEvent<Movie, Genre> e) {
        Movie m = e.getRowValue();
        m.setGenre(e.getNewValue());
    }

    public void saveDirector(Event e) {
        TableColumn.CellEditEvent<Movie,String> event = (TableColumn.CellEditEvent<Movie,String>) e;
        Movie m = event.getRowValue();
        m.setDirector(event.getNewValue());
    }

    public void saveYear(TableColumn.CellEditEvent<Movie, Integer> e) {
        Movie m = e.getRowValue();
        m.setYear(e.getNewValue());
    }

    public void saveLength(TableColumn.CellEditEvent<Movie, Integer> e) {
        Movie m = e.getRowValue();
        m.setLength(e.getNewValue());
    }

    public void saveFSK(TableColumn.CellEditEvent<Movie, Integer> e) {
        Movie m = e.getRowValue();
        m.setFsk(e.getNewValue());
    }

    public void saveInHD(TableColumn.CellEditEvent<Movie, Boolean> e)
    {
        Movie m = e.getRowValue();
        m.setHd(true);
    }

    public void saveGmTitle(TableColumn.CellEditEvent<Movie, String> e) throws ExceptionIsExist, ExceptionNoKey, ExceptionNotExist
    {
        register.updateTitle(e.getOldValue(), e.getNewValue());
    }

    public void saveTitle(TableColumn.CellEditEvent<Movie, String> e) throws ExceptionIsExist, ExceptionNoKey, ExceptionNotExist
    {
        register.updateTitle(e.getOldValue(), e.getNewValue());
    }

}
