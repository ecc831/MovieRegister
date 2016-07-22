/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieRegister implements MyMovieRegister, Serializable
{

    private HashMap<String, Movie> register;


    public MovieRegister() {
        register = new HashMap<>();
    }

    public MovieRegister(MovieRegister other) {
        register = other.register;
        if(register == null)
            register = new HashMap<>();
    }

    @Override
    public void addMovie(Movie m) throws ExceptionNullObject, ExceptionIsExist, ExceptionNoKey {

        if (m == null) throw new ExceptionNullObject();
        if (containsMovie(m)) throw new ExceptionIsExist();
        if (m.getTitle().equals("") || m.getGermanTitle().equals("")) throw new ExceptionNoKey();
        if (m.getTitle() == null || m.getGermanTitle() == null) throw new ExceptionNoKey();

        register.put(m.getTitle(), m);
        register.put(m.getGermanTitle(), m);
    }

    @Override
    public boolean containsMovie(Movie m) {
        return register.containsKey(m.getGermanTitle()) || register.containsKey(m.getTitle());
    }

    @Override
    public boolean containsKey(String key) {
        if (register.containsKey(key)) return true;
        return false;
    }


    @Override
    public Movie getMovie(String key) throws ExceptionNotExist, ExceptionNoKey {

        if (key == null || key.equals("")) throw new ExceptionNoKey();
        if (!register.containsKey(key)) throw new ExceptionNotExist();

        return register.get(key);
    }

    @Override
    public void updateMovie(Movie oldVersion, Movie newVersion) {
        register.remove(oldVersion);
        register.put(newVersion.getTitle(), newVersion);
        register.put(newVersion.getGermanTitle(), newVersion);
    }


    @Override
    public Set<String> searchMovie(String titlepart) {

        Set<String> result = new HashSet<>();

        for(String key : register.keySet()) {
            if (key.toLowerCase().contains(titlepart) || key.contains(titlepart)) {
                result.add(key);
            }
        }
        return result;
    }

    @Override
    public Set<Movie> movieList() {
        Set<Movie> movies = new HashSet<>(register.values());
        return movies;
    }



    @Override
    public void removeMovie(Movie m) throws ExceptionNullObject, ExceptionNotExist, ExceptionNoKey {
        if (m == null) throw new ExceptionNullObject();
        if (!containsMovie(m)) throw new ExceptionNotExist();
        if (m.getTitle().equals("") || m.getGermanTitle().equals("")) throw new ExceptionNoKey();
        if (m.getTitle() == null || m.getGermanTitle() == null) throw new ExceptionNoKey();

        register.remove(m.getTitle());
        register.remove((m.getGermanTitle()));

    }

    @Override
    public void removeMovie(String title) throws ExceptionNoKey, ExceptionNotExist {

        if (title == null || title.equals("")) throw new ExceptionNoKey();
        if (!register.containsKey(title)) throw new ExceptionNotExist();

        String titleOriginal = getMovie(title).getTitle();
        String titleGerman = getMovie(title).getGermanTitle();

        register.remove(titleOriginal);
        register.remove(titleGerman);

    }

    @Override
    public void updateTitle(String oldTitle, String newTitle) throws ExceptionNotExist, ExceptionIsExist, ExceptionNoKey {

        if (oldTitle == null || newTitle == null) throw new ExceptionNoKey();
        if (oldTitle.equals("") || newTitle.equals("")) throw new ExceptionNoKey();
        if (register.containsKey(newTitle)) throw new ExceptionIsExist();
        if (!register.containsKey(oldTitle)) throw new ExceptionNotExist();

        Movie oldTitleM = register.get(oldTitle);
        Movie newTitleM = oldTitleM;

        String oldOriginalTitle = oldTitleM.getTitle();
        String oldGermanTitle = oldTitleM.getGermanTitle();

        if(oldOriginalTitle.equals(oldTitle)) {
            newTitleM.setTitle(newTitle);
            register.put(newTitle, newTitleM);
            register.remove(oldTitle, oldTitleM);
        }

        if(oldGermanTitle.equals(oldTitle)) {
            newTitleM.setGermanTitle(newTitle);
            register.put(newTitle, newTitleM);
            register.remove(oldTitle, oldTitleM);
        }
    }


    public void fillRegister() {
        Movie HdR = new Movie("The Lord of the Rings", "Der Herr der Ringe", "Peter Jackson");
        Movie DieFliege = new Movie("The Fly", "Die Fliege", Genre.Horror, "David Cronenberg", 1986, 92, 18);
        Movie PulpFiction = new Movie("Pulp Fiction", "Pulp Fiction",Genre.Action, "Quentin Tarantino");


        register.put(HdR.getTitle(), HdR);
        register.put(HdR.getGermanTitle(), HdR);
        register.put(DieFliege.getTitle(), DieFliege);
        register.put(DieFliege.getGermanTitle(), DieFliege);
        register.put(PulpFiction.getTitle(), PulpFiction);
        register.put(PulpFiction.getGermanTitle(), PulpFiction);
    }

    public void fillFromFile (List<Movie> movies)
    {
        for (Movie m: movies)
        {
            register.put(m.getTitle(),m);
            register.put(m.getGermanTitle(), m);
        }
    }

    @Override
    public Set<String> keySet() {
        return register.keySet();
    }



    @Override
    public String toString() {
        return "MovieRegister" + '\n'  + register + '\n';
    }

}
