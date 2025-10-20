package fr.uvsq.dns.commandes;

import fr.uvsq.dns.*;
import java.util.List;

public class CommandeListe implements Commande {
  private Dns dns;
  private String domaine;
  private boolean sortByIp;

  public CommandeListe(Dns dns, String domaine, boolean sortByIp) {
    this.dns = dns;
    this.domaine = domaine;
    this.sortByIp = sortByIp;
  }

  @Override
  public String execute() {
    List<DnsItem> items = dns.getItems(domaine, sortByIp);

    if (items.isEmpty()) {
      return "Aucune machine trouvée.";
    }

    String resultat = "";
    for (DnsItem item : items) {
      resultat = resultat + item.getAdresse() + " " + item.getNom() + "\n";
    }

    return resultat.trim();
  }
}

