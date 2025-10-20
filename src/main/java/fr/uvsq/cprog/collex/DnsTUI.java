package fr.uvsq.dns;

import fr.uvsq.dns.commandes.*;
import java.util.Scanner;

public class DnsTUI {
    private Dns dns;
    private Scanner scanner = new Scanner(System.in);

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();
        if (ligne.isEmpty()) return () -> "";

        try {
            if (ligne.equals("quit")) return new CommandeQuit();
            if (ligne.startsWith("add ")) {
                String[] p = ligne.split("\\s+");
                return new CommandeAdd(dns, new AdresseIP(p[1]), new NomMachine(p[2]));
        }

        if (ligne.startsWith("ls")) {
            String[] p = ligne.split("\\s+");
            boolean sortByIp = p.length > 2 && "-a".equals(p[1]);
            String domaine = sortByIp ? p[2] : p[1];
            return new CommandeListe(dns, domaine, sortByIp);
        }

        if (ligne.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
            return new CommandeNom(dns, new AdresseIP(ligne));
        }
        return new CommandeIP(dns, new NomMachine(ligne));

        } catch (Exception e) {
        return () -> "Erreur : " + e.getMessage();
        }

    }

    public void affiche(String resultat) {
        if (resultat != null && !resultat.isEmpty()) {
        System.out.println(resultat);
        }
    }
}