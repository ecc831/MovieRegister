/**
 * Created by Patrick and Ole Schröter
 * Übung 4 (FXTableView und Datensynchronisation)
 */

import java.util.Set;

public interface MyMovieRegister {

    void addMovie(Movie m) throws ExceptionIsExist, ExceptionNullObject, ExceptionNoKey;
    boolean containsMovie(Movie m);
    boolean containsKey(String key);
    Movie getMovie(String key) throws ExceptionNotExist, ExceptionNoKey;
    void updateMovie(Movie oldVersion, Movie newVersion);
    Set<String> searchMovie(String titlepart);
    void removeMovie(Movie m) throws ExceptionNullObject, ExceptionIsExist, ExceptionNoKey, ExceptionNotExist;
    void removeMovie(String title) throws ExceptionNoKey, ExceptionNotExist;
    void updateTitle(String oldTitle, String newTitle) throws ExceptionNotExist, ExceptionIsExist, ExceptionNoKey;
    Set<String> keySet();
    Set<Movie> movieList();


}


