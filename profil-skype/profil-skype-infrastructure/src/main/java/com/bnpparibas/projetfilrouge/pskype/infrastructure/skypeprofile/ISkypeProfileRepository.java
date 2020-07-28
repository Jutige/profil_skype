package com.bnpparibas.projetfilrouge.pskype.infrastructure.skypeprofile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bnpparibas.projetfilrouge.pskype.domain.StatusSkypeProfileEnum;
import com.bnpparibas.projetfilrouge.pskype.infrastructure.user.CollaboraterEntity;
import com.bnpparibas.projetfilrouge.pskype.infrastructure.user.ItCorrespondantEntity;

@Repository
public interface ISkypeProfileRepository extends JpaRepository<SkypeProfileEntity, Long>, JpaSpecificationExecutor<SkypeProfileEntity> {
	

	SkypeProfileEntity findBySIP(String sip);
	
	List<SkypeProfileEntity> findBySIPNotNull();
	
	SkypeProfileEntity findBySIPAndStatusProfile(String sip, StatusSkypeProfileEnum status);
	
	SkypeProfileEntity findByCollaborater(CollaboraterEntity collaboraterEntity);
	

	@Query(value = "select p from SkypeProfileEntity p where p.collaborater.collaboraterId = :id", nativeQuery = false)
	SkypeProfileEntity getSkypeProfilByIdCollab (@Param("id") String idAnnuaire);
		
}
