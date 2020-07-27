package com.bnpparibas.projetfilrouge.pskype.infrastructure.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * Liste des méthodes de type dao du collaborateur 
 * @author Judicael
 *
 */

@Repository
public interface ICollaboraterRepository extends JpaRepository<CollaboraterEntity, Long>{

	List<CollaboraterEntity> findByLastNameAndFirstName(String lastName,String firstName);
	CollaboraterEntity findByCollaboraterId(String id);
//	CollaboraterEntity findDistinctByCollaboraterId(String id);
//	@Query(value = "select p from CollaboraterEntity p where p.collaboraterId = :id and dtype = 'Collaborater'", nativeQuery = false)
//	CollaboraterEntity getCollabOnlyByIdCollab (@Param("id") String idAnnuaire);
	List<CollaboraterEntity> findByCollaboraterIdNotNull();
}