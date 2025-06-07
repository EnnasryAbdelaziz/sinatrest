package eai.devass.gsr.appli.utile;

public class RibTools {

	/*
	 * Trois premiers chiffres du RIB
	 */
	public synchronized static String getCodeEtablissement(String rib) throws Exception {

		validerRib(rib);
		return rib.substring(0, 3);

	}

	/*
	 * Du 4ème au 6ème chiffre du RIB
	 */
	public synchronized static String getCodeLocalite(String rib) throws Exception {

		validerRib(rib);
		return rib.substring(3, 6);

	}

	/*
	 * Du 7ème au 22ème chiffre du RIB
	 */
	public synchronized static String getNumCompte(String rib) throws Exception {

		validerRib(rib);
		return rib.substring(6, 22);

	}

	/*
	 * Du 23ème au 24ème chiffre du RIB
	 */
	public synchronized static String getCodeCle(String rib) throws Exception {

		validerRib(rib);
		return rib.substring(22, 24);

	}

	public synchronized static void validerRib(String rib) throws Exception {

		if (rib == null) {
			throw new Exception("Le rib doit être non null");
		}
		
//		if (rib.length() != 24)
//			throw new Exception("Le rib doit être sur 24 position");

	}

	public synchronized static void checkRib(String rib, String etab, String localite, String compte, String cle) throws Exception {

		StringBuffer sb = new StringBuffer();

		sb.append(etab).append(localite).append(compte).append(cle);

		if (rib == null || rib.length() != 24) {
			throw new Exception("Le RIB doit être sur 24 positions");
		} else if (!rib.equals(sb.toString())) {
			throw new Exception("Le RIB doit correspondre à la concaténation des 4 champs (code etablissement,code localite,numero de compte,clé rib)");
		}
	}
	
	public synchronized static String construireRIB(String codEtablissement, String codLocalite, String numCompte, String codeCle){
		return codEtablissement+codLocalite+numCompte+codeCle;
	}
	
}
