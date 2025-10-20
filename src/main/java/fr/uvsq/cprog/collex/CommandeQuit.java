package fr.uvsq.dns.commandes;

public class CommandeQuit implements Commande {
    @Override
    public String execute() {
        System.exit(0);
        return "";
    }
}