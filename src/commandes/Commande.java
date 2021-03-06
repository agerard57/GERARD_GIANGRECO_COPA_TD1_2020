package commandes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Commande {
	protected int idCommande;
	protected LocalDate dateCommande;
	protected int idClient;
	protected ArrayList<LigneDeCommande> listeLigneDeCommande;
	
	public Commande(int idCommande) {
		this.idCommande = idCommande;
	}
	
	public Commande(int idCommande,LocalDate dateCommande, int idClient, ArrayList<LigneDeCommande> listeLigneDeCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.idClient = idClient;
		this.listeLigneDeCommande = listeLigneDeCommande;
	}
	
	public Commande(int idCommande, LocalDate dateCommande, int idClient) {
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.idClient = idClient;
	}
	
	public Commande() {
		idCommande = -1;
		dateCommande = LocalDate.now();
		idClient = -1;
		listeLigneDeCommande = new ArrayList<LigneDeCommande>();
	}
	
	
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	
	public LocalDate getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	public ArrayList<LigneDeCommande> getListeLigneDeCommande() {
		return listeLigneDeCommande;
	}
	public void setListeLigneDeCommande(ArrayList<LigneDeCommande> listeLigneDeCommande) {
		this.listeLigneDeCommande = listeLigneDeCommande;
	}
	
	@Override
	public boolean equals(Object o) { 
		Commande c = (Commande) o ;
		if ( o == null )
			return (this == null);
		else 
			return this.idCommande == c.idCommande;
	}
	
	
	@Override
	public String toString() {
		String str = new String();
		int c = 0;
		
		str = ("La commande numero " + idCommande + " effectuee le " + dateCommande + "contient : ");
		while ( c < listeLigneDeCommande.size())
		{
			str += "\n" + listeLigneDeCommande.get(c).toString(); 
		}
		return str;
	}
}