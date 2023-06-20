package edu.chdtu.fitis.tsybenko.db.film;

import java.util.Date;

public class Session {
    private int id;
    private Film film;
    private Date sessionsTime;
    private Hall hall;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getSessionsTime() {
        return sessionsTime;
    }

    public void setSessionsTime(Date sessionsTime) {
        this.sessionsTime = sessionsTime;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
