/**
 * Created by Patrick and Ole Schröter
 * Übung 7 (MVC-Konzept)
 */

import javafx.beans.value.ObservableValue;

import java.util.HashSet;
import java.util.Set;

public class Movie {

    private String title;
    private String germanTitle;
    private Genre genre;
    private String director;
    private int year;
    private int length;
    private int fsk;
    private boolean hd;

    public Movie(Movie m) {
        setTitle(m.getTitle());
        setGermanTitle(m.getGermanTitle());
        setGenre(m.getGenre());
        setDirector(m.getDirector());
        setYear(m.getYear());
        setLength(m.getLength());
        setFsk(m.getFsk());
    }

    public Movie(String title, String germanTitle) {
        this.title = title;
        this.germanTitle = germanTitle;
        this.genre = Genre.Unspecified;
        this.director = "";
        this.year = 0;
        this.fsk = -100;
        this.length = 0;
    }

    public Movie(String title, String germanTitle, String director) {

        this.director = director;
        this.germanTitle = germanTitle;
        this.title = title;
        this.genre = Genre.Unspecified;
        this.fsk = -100;
        this.length = 0;
        this.year = 0;

    }

    public Movie(String title, String germanTitle, Genre genre, String director, int length) {
        this.title = title;
        this.germanTitle = germanTitle;
        this.genre = genre;
        this.director = director;
        this.length = length;
        this.fsk = -100;
        this.year = 0;
    }

    public Movie(String title, String germanTitle, Genre genre, int year, int length, int fsk) {
        this.title = title;
        this.germanTitle = germanTitle;
        this.genre = genre;
        this.year = year;
        this.length = length;
        this.fsk = fsk;
        this.director = "";
    }

    public Movie(String title, String germanTitle, Genre genre, String director, int year, int length, int fsk) {
        this.title = title;
        this.germanTitle = germanTitle;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.length = length;
        this.fsk = fsk;
    }


    public Movie(String title, String germanTitle, Genre genre, String director) {
        this.title = title;
        this.germanTitle = germanTitle;
        this.genre = genre;
        this.director = director;
        this.fsk = -100;
        this.length = 0;
        this.year = 0;
    }

    public Movie()
    {

    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getGermanTitle() {
        return germanTitle;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public int getFsk() {
        return fsk;
    }

    public boolean getHd() {return hd;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGermanTitle(String germanTitle) {
        this.germanTitle = germanTitle;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    public void setHd (boolean hd) {this.hd = hd;}

    @Override
    public String toString() {
        return  "Title: '" + title + "'" + '\n' +
                "GermanTitle: '" + germanTitle + "'" + '\n' +
                "Genre: " + genre + '\n' +
                "Director: '" + director + "'" + '\n' +
                "Year: " + year + '\n' +
                "Length: " + length + '\n' +
                "FSK: " + fsk + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (length != movie.length) return false;
        if (fsk != movie.fsk) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (germanTitle != null ? !germanTitle.equals(movie.germanTitle) : movie.germanTitle != null) return false;
        if (genre != movie.genre) return false;
        return director != null ? director.equals(movie.director) : movie.director == null;

    }

    public ObservableValue<Boolean> inHDProberty() {
        return null;
    }

}
