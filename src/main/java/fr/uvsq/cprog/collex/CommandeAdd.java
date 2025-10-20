package fr.uvsq.dns.commandes;

import fr.uvsq.dns.*;
import java.io.IOException;

public class CommandeAdd implements Commande {
    private final Dns dns;
    private final AdresseIP ip;
    private final NomMachine nom;

    public CommandeAdd(Dns dns, AdresseIP ip, NomMachine nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute() throws IOException {
        dns.addItem(ip, nom);
        return "Ajouté : " + nom + " -> " + ip;
    }
}
