package com.bnpparibas.projetfilrouge.pskype.infrastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bnpparibas.projetfilrouge.pskype.domain.Collaborater;
import com.bnpparibas.projetfilrouge.pskype.domain.ICollaboraterDomain;

/**
 * Dédié au collaborateur uniquement
 * Elle assure la correspondance entre les méthodes exposées de la couche domaine et celles da la couche infrastructure
 * liste des méthodes :
 * - creation du collaborateur
 * - récupération du collaborateur à partir de l'id annuaire
 * @author Judicaël
 *
 */
@Repository
public class CollaboraterRepositoryImpl implements ICollaboraterDomain {

	@Autowired
	CollaboraterEntityMapper mapperCollab;
	
	@Autowired
	ICollaboraterRepository collaboraterRepository;
	
	@Override
	public void create(Collaborater collaborater) {
		
	//	System.out.println(collaborater.toString());
		
		collaboraterRepository.save(mapperCollab.mapToEntity(collaborater));
	}

	@Override
	public Collaborater findByCollaboraterId(String idAnnuaire) {
		// TODO Auto-generated method stub
		return mapperCollab.mapToDomain(collaboraterRepository.findByCollaboraterId(idAnnuaire));
	}

}
