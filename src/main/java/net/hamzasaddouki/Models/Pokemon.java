package net.hamzasaddouki.Models;

import java.util.List;

public class Pokemon {
    private int id;
    private String nom;
    private float pes;
    private float punts_de_vida = 200;
    private List<Tipus> Tipus;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPes() {
        return pes;
    }

    public void setPes(float pes) {
        this.pes = pes;
    }

    public float getPunts_de_vida() {
        return punts_de_vida;
    }

    public void setPunts_de_vida(float punts_de_vida) {
        this.punts_de_vida = punts_de_vida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<net.hamzasaddouki.Models.Tipus> getTipus() {
        return Tipus;
    }

    public void setTipus(List<net.hamzasaddouki.Models.Tipus> tipus) {
        Tipus = tipus;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pes=" + pes +
                ", punts_de_vida=" + punts_de_vida +
                '}';
    }
}
