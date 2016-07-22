/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CSMovieIO
{
    public static String createStringFromMovie (Movie m, String trennung)
    {
        String movieString=
                        m.getTitle() +  trennung + m.getGermanTitle() + trennung +
                        m.getGenre() + trennung + m.getDirector() + trennung +
                        m.getYear() + trennung + m.getLength() + trennung +
                                m.getFsk() + trennung + m.getHd() + '\n';
        return movieString;
    }

    public static Movie createMovieFromString (String movieString, String trennung)
    {
        Movie m = new Movie();
        String[] inhalt = movieString.split(trennung);

        m.setTitle(inhalt[0]);
        m.setGermanTitle(inhalt[1]);
        m.setGenre(Genre.valueOf(inhalt[2]));
        m.setDirector(inhalt[3]);
        m.setYear(Integer.parseInt(inhalt[4]));
        m.setLength(Integer.parseInt(inhalt[5]));
        m.setFsk(Integer.parseInt(inhalt[6]));

        return m;
    }

    public static void writeMovieFile(Collection<Movie> movies, String dateiname, String trennung) throws IOException
    {
            PrintWriter ausgabe = new PrintWriter(new FileWriter(dateiname));

        for (Movie m:movies)
        {
            ausgabe.write(createStringFromMovie(m,trennung));
        }
        ausgabe.close();
    }

    public static List<Movie> readMovieFile (String dateiname, String trennung) throws IOException
    {
        List<Movie> movies = new ArrayList<Movie>();

        BufferedReader eingabe = new BufferedReader(new FileReader(dateiname));
        String inhalt;
        while ((inhalt = eingabe.readLine()) !=null)
        {
            movies.add(createMovieFromString(inhalt,trennung));
        }
        eingabe.close();

        return movies;
    }
    
}
