package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import commandes.LigneDeCommande;
import dao.LigneCommandeDAO;
import mysql.Connexion;



public class MySQLigneCommandeDAO implements LigneCommandeDAO{


	private static MySQLigneCommandeDAO instance;

	public static MySQLigneCommandeDAO getInstance() {
		
		if (instance == null) {
			instance = new MySQLigneCommandeDAO();
		}

		return instance;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public MySQLigneCommandeDAO() {
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public boolean create(LigneDeCommande ligneDeCommande) {
		Connexion connect = new Connexion();
		@SuppressWarnings("unused")
		int i = 0;
		try {
			Connection connect1 = connect.creeConnexion();
			PreparedStatement requete = connect1.prepareStatement("INSERT INTO Ligne_Commande(id_produit, quantite, tarif_unitaire) VALUES ( ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1,  ligneDeCommande.getIdProduit());
			requete.setInt(2,  ligneDeCommande.getQuantite());
			requete.setFloat(3,  ligneDeCommande.getTarifUnitaire());

			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();

			if ( res.next())
				ligneDeCommande.setIdCommande(res.getInt(1));

			connect1.close();
			return true;
		}
		catch(SQLException sqle)
		{
			System.out.println("Erreur ajouter ligne de commande ");
		}
		return (false);



	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean update(LigneDeCommande ligneDeCommande) {

		Connexion c = new Connexion();
		int i = 0;

		try {
			Connection c1 = c.creeConnexion();

			PreparedStatement requete = c1.prepareStatement("UPDATE Ligne_Commande SET id_produit = ?, quantite = ?, tarif_unitaire = ? WHERE id_commande = ?");
			requete.setInt(1, ligneDeCommande.getIdProduit());
			requete.setInt(2, ligneDeCommande.getQuantite());
			requete.setFloat(3, ligneDeCommande.getTarifUnitaire());
			i = requete.executeUpdate();

			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme update ligne de commande");
		}

		return (i == 1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean delete(LigneDeCommande ligneDeCommande) {

		Connexion c = new Connexion();
		int i = 0;

		try {
			Connection c1 = c.creeConnexion();

			PreparedStatement requete = c1.prepareStatement("DELETE FROM Ligne_Commande WHERE id_commande = ? ");
			requete.setInt(1, ligneDeCommande.getIdCommande());
			i = requete.executeUpdate();

			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme delete ligne de commande");
		}

		return (i == 1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	public ArrayList<LigneDeCommande> findAll() {
		Connexion c = new Connexion();
		ArrayList<LigneDeCommande> liste = new ArrayList<LigneDeCommande>();

		try {
			Connection c1 = c.creeConnexion();

			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Ligne_Commande");
			while (res.next()) {

				liste.add(new LigneDeCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getFloat(4)));

			}

			c1.close();
			res.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * Ligne_Commande");
		}

		return (liste);
	}

	@Override
	public LigneDeCommande getById(int idCommande, int idProduit) {
		Connexion c = new Connexion();
		LigneDeCommande ligneDeCommande = null;
		try {
			Connection c1 = c.creeConnexion();

			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Ligne_commande WHERE id_commande = " + idCommande + " AND id_produit = " + idProduit);
			while (res.next()) {

				ligneDeCommande = new LigneDeCommande(res.getInt(1), res.getInt(2), res.getInt(3),  res.getInt(4));
			}

			c1.close();
			res.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * Ligne de Commande");
		}
		return ligneDeCommande;
	
	}








}


