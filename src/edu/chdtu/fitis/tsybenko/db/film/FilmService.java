package edu.chdtu.fitis.tsybenko.db.film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmService {
    Connection con = null;

    FilmService() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", "postgres", "20foxmax22");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    List<Film> readFilms() {
        List<Film> films = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * From films");
            while (rs.next()) {
                Film film1 = new Film();
                film1.setName(rs.getString("name"));
                film1.setDuration(rs.getInt("duration"));
                film1.setGenre(rs.getString("genre"));
                film1.setYear(rs.getInt("year"));
                film1.setActors(rs.getString("actors"));
                films.add(film1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    List<Hall> readHalls() {
        List<Hall> halls = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * From halls");
            while (rs.next()) {
                Hall hall1 = new Hall();
                hall1.setName(rs.getString("name"));
                hall1.setForChildren(rs.getString("for_children"));
                hall1.setPlaceNumber(rs.getInt("place_number"));
                halls.add(hall1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return halls;
    }

    void addFilm(Film film) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO films(genre, name, duration, actors, year) VALUES ("+film.getGenre()+","+film.getName()+","+film.getDuration()+","+film.getActors()+","+film.getYear()+")");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connection failed...");
        }
    }
}
