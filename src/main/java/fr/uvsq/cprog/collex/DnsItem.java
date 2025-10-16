package fr.uvsq.cprog.collex;

public class DnsItem
{
    private NomMachine nom;
    private AdresseIP ip;
    public DnsItem(NomMachine nom, AdresseIP ip) {
        this.nom = nom;
        this.ip = ip;
    }
    public NomMachine getNom() {
        return nom;
    }
    public AdresseIP getIp() {
        return ip;
    }
    @Override
    public String toString() {
        return nom + " -> " + ip;
    }
}