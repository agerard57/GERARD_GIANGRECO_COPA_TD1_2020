package dao.listememoire;

import java.util.ArrayList;

import commandes.LigneDeCommande;
import dao.LigneCommandeDAO;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO{
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static ListeMemoireLigneCommandeDAO instance;

	private ArrayList<LigneDeCommande> donnees;

	public static ListeMemoireLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneCommandeDAO();
		}

		return (instance);
	}

		public ListeMemoireLigneCommandeDAO() {
			donnees = new ArrayList<LigneDeCommande>();

		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		public boolean create(LigneDeCommande object) {
			if (donnees.indexOf(object) > -1)
				return (false);
			donnees.add(object);
			
			return (true);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public boolean update(LigneDeCommande object) {

			if (donnees.indexOf(object) < 0)
				return (false);
			else
				donnees.set(donnees.indexOf(object), object);
			return (true);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public boolean delete(LigneDeCommande object) {

			if (donnees.indexOf(object) < 0)
				return (false);
			else
				donnees.remove(donnees.indexOf(object));
			return (true);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		@Override
		public LigneDeCommande getById(int idCommande, int idProduit) {
			if (donnees.indexOf(new LigneDeCommande(idCommande, idProduit)) < 0)
				return (null);
			else
				return (donnees.get(donnees.indexOf(new LigneDeCommande(idCommande, idProduit))));
		}


		public ArrayList<LigneDeCommande> findAll() {
			return (donnees);

		}


	}
	

