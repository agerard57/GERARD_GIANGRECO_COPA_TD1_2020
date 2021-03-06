package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import commandes.Commande;
import commandes.LigneDeCommande;
import dao.CommandeDAO;
import mysql.Connexion;

public class MySQLCommandeDAO implements CommandeDAO{
	private static MySQLCommandeDAO instance;

	public static MySQLCommandeDAO getInstance() {

		if (instance == null) {
			instance = new MySQLCommandeDAO();
		}

		return instance;
	}

	public MySQLCommandeDAO() {
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public boolean create(Commande commande) {
		Connexion connect = new Connexion();
		@SuppressWarnings("unused")
		int i = 0;
		try {
			Connection connect1 = connect.creeConnexion();
			PreparedStatement requete = connect1.prepareStatement("INSERT INTO Commande(date_commande, id_client) VALUES ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
			requete.setDate(1,  java.sql.Date.valueOf(commande.getDateCommande()));
			requete.setInt(2,  commande.getIdClient());


			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();

			if ( res.next())
				commande.setIdCommande(res.getInt(1));

			connect1.close();
			return true;
		}
		catch(SQLException sqle)
		{
			System.out.println("Erreur ajouter commande ");
		}
		return (false);



	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean update(Commande commande) {

		Connexion c = new Connexion();
		int i = 0;

		try {
			Connection c1 = c.creeConnexion();

			PreparedStatement requete = c1.prepareStatement("UPDATE Commande SET date_commande = ?, id_client = ? WHERE id_commande = ?");
			requete.setDate(1,  java.sql.Date.valueOf(commande.getDateCommande()));
			requete.setInt(2, commande.getIdClient());
			requete.setInt(3, commande.getIdCommande());
			i = requete.executeUpdate();

			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme update commande");
		}

		return (i == 1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean delete(Commande commande) {

		Connexion c = new Connexion();
		int i = 0;

		try {
			Connection c1 = c.creeConnexion();

			PreparedStatement requete = c1.prepareStatement("DELETE FROM Commande WHERE id_commande = ? ");
			requete.setInt(1, commande.getIdCommande());
			i = requete.executeUpdate();

			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme delete commande");
		}

		return (i == 1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	public ArrayList<Commande> findAll() {
		Connexion c = new Connexion();
		ArrayList<Commande> liste = new ArrayList<Commande>();
		ArrayList<LigneDeCommande> listeLigne = new ArrayList<LigneDeCommande>();
		
		try {
			Connection c1 = c.creeConnexion();

			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Commande");
			while (res.next()) {
				
				
				
				Statement requete2 = c1.createStatement();
				ResultSet res2 = requete2.executeQuery("SELECT * FROM Ligne_commande WHERE id_commande = " + res.getInt(1));
				listeLigne = new ArrayList<LigneDeCommande>();
				while (res2.next()) {
					listeLigne.add(new LigneDeCommande(res2.getInt(1),res2.getInt(2), res2.getInt(3), res2.getFloat(4)));
				}

				res2.close();
				
				
				
				liste.add(new Commande(res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3), listeLigne));

			}

			c1.close();
			res.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * Commande");
		}

		return (liste);
	}

	@Override
	public Commande getById(int id) {
		Connexion c = new Connexion();
		Commande commande = null;
		try {
			Connection c1 = c.creeConnexion();

			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Commande WHERE id_commande = " + id);
			while (res.next()) {

				commande = new Commande(res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3));
			}

			c1.close();
			res.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * Commande");
		}
		return commande;
	}

}
