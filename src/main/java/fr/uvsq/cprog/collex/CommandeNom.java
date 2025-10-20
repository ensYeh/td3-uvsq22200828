package fr.uvsq.dns.commandes;

import fr.uvsq.dns.*;

public class CommandeNom implements Commande {
    private Dns dns;
    private AdresseIP ip;

    public CommandeNom(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(ip);
        if (item == null) return "Inconnu";
        return item.getNom().toString();
    }
}