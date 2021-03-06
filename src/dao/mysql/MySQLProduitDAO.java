package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ProduitDAO;
import mysql.Connexion;
import produits.Produit;

public class MySQLProduitDAO implements ProduitDAO{
	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance() {

		if (instance == null) {
			instance = new MySQLProduitDAO();
		}

		return instance;
	}

	
	
	public MySQLProduitDAO() {
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	public boolean create(Produit p) {
		Connexion connect = new Connexion();
		@SuppressWarnings("unused")
		int i = 0;
		try {
			Connection connect1 = connect.creeConnexion();
			PreparedStatement requete = connect1.prepareStatement("INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES ( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			requete.setString(1,  p.getNom());
			requete.setString(2,  p.getDescription());
			requete.setFloat(3,  p.getTarif());
			requete.setString(4,  p.getVisuel());
			requete.setInt(5,  p.getIdCategorie());


			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();

			if ( res.next())
				p.setIdProduit(res.getInt(1));

			connect1.close();
			return (true);
		}
		catch(SQLException sqle)
		{
			System.out.println("Erreur !");
		}
		return (false);



	}
	
	
public boolean update(Produit objet) {
		
		Connexion c = new Connexion();
		int i = 0;
		
		try {
			Connection c1 = c.creeConnexion();
			
			PreparedStatement requete = c1.prepareStatement("UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE id_client = ?");
			requete.setString(1, objet.getNom());
			requete.setString(2, objet.getDescription());
			requete.setFloat(3, objet.getTarif());
			requete.setString(4, objet.getVisuel());
			requete.setInt(5, objet.getIdCategorie());
			requete.setInt(6, objet.getIdProduit());

			i = requete.executeUpdate();
			
			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme update produit");
		}
		
		return (i == 1);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
public boolean delete(Produit objet) {
		
		Connexion c = new Connexion();
		int i = 0;
		
		try {
			Connection c1 = c.creeConnexion();
			
			PreparedStatement requete = c1.prepareStatement("DELETE FROM Produit WHERE id_produit = ? ");
			requete.setInt(1, objet.getIdProduit());
			i = requete.executeUpdate();
			
			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Probleme delete produit");
		}
		
		return (i == 1);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public ArrayList<Produit> findAll() {
		Connexion c = new Connexion();
		ArrayList<Produit> liste = new ArrayList<Produit>();

		try {
			Connection c1 = c.creeConnexion();
			
			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Produit");
			while (res.next()) {

				liste.add(new Produit(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6)));

			}
			
			c1.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * produit");
		}
		
		return (liste);
	}



	@Override
	public Produit getById(int id) {
		Connexion c = new Connexion();
		Produit produit = null;
		try {
			Connection c1 = c.creeConnexion();

			Statement requete = c1.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM Produit WHERE id_produit = " + id);
			while (res.next()) {

				produit = new Produit(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6));
			}

			c1.close();
			res.close();
		}
		catch (SQLException sqle) {
			System.out.println("Problemes select * Produit");
		}
		return produit;
	}


}
