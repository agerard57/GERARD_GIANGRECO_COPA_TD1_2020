package dao.listememoire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import categorie.Categorie;
import clients.Client;
import commandes.Commande;
import commandes.LigneDeCommande;
import dao.CommandeDAO;
import mysql.Connexion;

public class ListeMemoireCommandeDAO implements CommandeDAO{

	private static ListeMemoireCommandeDAO instance;

	private ArrayList<Commande> donnees;

	public static ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return (instance);
	}
	
	private ListeMemoireCommandeDAO() {
		donnees = new ArrayList<Commande>();

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public boolean create(Commande object) {
		object.setIdCommande(1);
		while (donnees.indexOf(object) > -1)
		{
			object.setIdCommande(object.getIdCommande() + 1);
		}
		donnees.add(object);
		
		return (true);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean update(Commande object) {

		if (donnees.indexOf(object) < 0)
			return (false);
		else
			donnees.set(donnees.indexOf(object), object);
		return (true);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean delete(Commande object) {
		if (donnees.indexOf(object) < 0)
			return (false);
		else
			donnees.remove(donnees.indexOf(object));
		return (true);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	public ArrayList<Commande> findAll() {
		return (donnees);

	}


	@Override
	public Commande getById(int id) {
		if (donnees.indexOf(new Commande(id)) < 0)
			return (null);
		else
			return (donnees.get(donnees.indexOf(new Commande(id))));
	}

}
