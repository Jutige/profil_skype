package com.bnpparibas.projetfilrouge.pskype.infrastructure.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 
 * Liste des méthodes de type dao du CIL 
 * @author Judicael
 *
 */

@Repository
public interface IItCorrespondantRepository extends JpaRepository <ItCorrespondantEntity, Long>, JpaSpecificationExecutor<ItCorrespondantEntity>{
//	void create(ItCorrespondantEntity itc);
	List<ItCorrespondantEntity> findAllItCorrespondantEntities();
	ItCorrespondantEntity findByCollaboraterId(String id);
//	List<ItCorrespondantEntity> recupItCorrespondantByName(String lastName,String firstName);
//	ItCorrespondantEntity recupItCorrespondantByIdAgent(String id);
}
