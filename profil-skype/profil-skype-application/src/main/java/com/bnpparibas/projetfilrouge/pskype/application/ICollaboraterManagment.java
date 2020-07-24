package com.bnpparibas.projetfilrouge.pskype.application;
import java.util.List;
import com.bnpparibas.projetfilrouge.pskype.domain.Collaborater;;

/**
 * 
 * Liste des méthodes Collaborater disponibles pour la couche exposition
 * @author Judicaël
 *
 */

public interface ICollaboraterManagment {

	boolean createCollaborater(Collaborater collaborater);
	Collaborater findCollaboraterbyIdAnnuaire(String idAnnuaire);
	List<Collaborater> listCollaborater();
	
}
