package fr.uvsq.cprog.collex;

public class NomMachine
{
    private String nom;
    public NomMachine(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    @Override
    public String toString() {
        return nom;
    }
}