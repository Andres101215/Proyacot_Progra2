package co.edu.uptc.model.persontypes;

import co.edu.uptc.model.Person;

public class Professor extends Person {


    private boolean director;



    public Professor(String id, String name, String lastname) {
        super(id, name, lastname);
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Professor [director=" + director + "]";
    }
}
