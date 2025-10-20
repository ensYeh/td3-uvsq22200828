package fr.uvsq.dns.commandes;

import fr.uvsq.dns.*;

public class CommandeIP implements Commande {
    private Dns dns;
    private NomMachine nom;

    public CommandeIP(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(nom);
        if (item == null) return "Inconnu";
        return item.getAdresse().toString();
  }
}
