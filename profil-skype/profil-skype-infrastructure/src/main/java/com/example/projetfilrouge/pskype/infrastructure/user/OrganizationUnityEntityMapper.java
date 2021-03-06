package com.example.projetfilrouge.pskype.infrastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetfilrouge.pskype.domain.OrganizationUnity;
import com.example.projetfilrouge.pskype.infrastructure.AbstractMapper;

@Component
public class OrganizationUnityEntityMapper extends AbstractMapper<OrganizationUnity, OrganizationUnityEntity>{
	
	@Autowired
	SiteEntityMapper siteMapper;
	@Autowired
	IOrganizationUnityRepository orgaUnityRepo;
	
	@Override
	public OrganizationUnity mapToDomain(OrganizationUnityEntity entity) {
		
		if (entity !=null) {
			OrganizationUnity uo = new OrganizationUnity(entity.getOrgaUnityCode(),
			entity.getOrgaUnityType(), entity.getOrgaShortLabel(), siteMapper.mapToDomain(entity.getOrgaSite()));
			return uo;
		}else {
			return null;
		}
	}

	@Override
	public OrganizationUnityEntity mapToEntity(OrganizationUnity dto) {
		
		OrganizationUnityEntity uoEntityRepo = getOrganizationUnityByCode(dto.getOrgaUnityCode());
		
		// on vérifie si l'entité existe en base et on la renvoie tel quel si c'est le cas
		// (problème de duplication sinon avec le cascade)
		if (uoEntityRepo == null) {
			OrganizationUnityEntity uoEntity = new OrganizationUnityEntity();
			uoEntity.setOrgaShortLabel(dto.getOrgaShortLabel());
			uoEntity.setOrgaUnityCode(dto.getOrgaUnityCode());
			uoEntity.setOrgaUnityType(dto.getOrgaUnityType());
			if (dto.getOrgaSite()!=null) {
				uoEntity.setOrgaSite(siteMapper.mapToEntity(dto.getOrgaSite()));
			}
			return uoEntity;
		} else return uoEntityRepo;

	}
	
	private OrganizationUnityEntity getOrganizationUnityByCode (String orgaUnityCode) {
		return orgaUnityRepo.findByOrgaUnityCode(orgaUnityCode);

	}
}
