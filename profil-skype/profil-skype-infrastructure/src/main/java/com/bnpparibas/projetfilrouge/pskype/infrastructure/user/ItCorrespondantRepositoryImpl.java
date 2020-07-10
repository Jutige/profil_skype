package com.bnpparibas.projetfilrouge.pskype.infrastructure.user;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.bnpparibas.projetfilrouge.pskype.domain.IItCorrespondantDomain;
import com.bnpparibas.projetfilrouge.pskype.domain.ItCorrespondant;


/**
 * Dédié au ItCorrespondant
 * Elle assure la correspondance entre les méthodes exposées de la couche domaine et celles da la couche infrastructure
 * liste des méthodes :
 * - Création d'un CIL (US010)
 * - Récupérer la liste des CIL (US007)
 * @author Judicaël
 *
 */
@Repository
public class ItCorrespondantRepositoryImpl implements IItCorrespondantDomain {

@Autowired
private ItCorrespondantEntityMapper entityMapper;

@Autowired
private IItCorrespondantRepository itCorrespondantRepository;
	
/**
 * Création en base d'un ItCorrespondant (US010)
 * @param ItCorrespondant
 * @return null
 * 
 */
	@Override
	public void create(ItCorrespondant itCorrespondant) {
		ItCorrespondantEntity entity = itCorrespondantRepository.findByCollaboraterId(itCorrespondant.getCollaboraterId());
		if (entity==null) {
			entity = entityMapper.mapToEntity(itCorrespondant);
			itCorrespondantRepository.save(entity);
		}else {
			throw new RuntimeException("It correspondant "+itCorrespondant.getCollaboraterId()+" existe déjà");
		}
	}

	/**
	 * Récupération de l'ensemble des ItCorrespondant tout rôle confondu (US007)
	 * @param null
	 * @return List<ItCorrespondant>
	 * 
	 */
	@Override
	public List<ItCorrespondant> findAllItCorrespondant() {
		// TODO Auto-generated method stub
		
		List<ItCorrespondant> listItCorrespondant = new ArrayList<ItCorrespondant>();
		for (ItCorrespondantEntity entity : itCorrespondantRepository.findAllItCorrespondantEntities()) {
			listItCorrespondant.add(entityMapper.mapToDomain(entity));
		}
		return listItCorrespondant;
	}
	
	/**
	 * Mise à jour du rôle du CIL
	 * @author Judicaël
	 */
	@Override
	public void update(ItCorrespondant itCorrespondant) {
		
		if (itCorrespondantRepository.findByCollaboraterId(itCorrespondant.getCollaboraterId()) == null) {
			throw new RuntimeException("Mise à jour impossible, id : "+itCorrespondant.getCollaboraterId()+" non trouvé");
		}else {
			itCorrespondantRepository.save(entityMapper.mapToEntity(itCorrespondant));
		}
		
	}

	/**
	 * @param String id annuaire du CIL
	 * @return l'objet CIL correspondant
	 * @author Judicaël
	 * @version V0.1
	 */
	@Override
	public ItCorrespondant findItCorrespondantByCollaboraterId(String id) {
		// TODO Auto-generated method stub
		ItCorrespondantEntity entity = itCorrespondantRepository.findByCollaboraterId(id);
		if (entity == null) {
			throw new RuntimeException("Pas de colaborateur trouvé pour id : "+id);
		}
		else {
			return entityMapper.mapToDomain(entity);
		}

	}


}
