package fr.uvsq.dns;

import fr.uvsq.dns.commandes.Commande;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

public class DnsApp {

    public static void main(String[] args) throws Exception {
        new DnsApp().run();
    }

    public void run() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/dns.properties"));
        Path fichier = Paths.get("src/main/resources/" + props.getProperty("dns.file"));
        Dns dns = new Dns(fichier);
        DnsTUI tui = new DnsTUI(dns);

        while (true) {
            Commande cmd = tui.nextCommande();
            String res = cmd.execute();
            tui.affiche(res);
        }
    }
}