package com.bnpparibas.projetfilrouge.pskype.infrastructure.skypeprofile;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bnpparibas.projetfilrouge.pskype.domain.ISkypeProfileEventDomain;
import com.bnpparibas.projetfilrouge.pskype.domain.ItCorrespondant;
import com.bnpparibas.projetfilrouge.pskype.domain.SkypeProfile;
import com.bnpparibas.projetfilrouge.pskype.domain.SkypeProfileEvent;
import com.bnpparibas.projetfilrouge.pskype.infrastructure.user.IItCorrespondantRepository;
import com.bnpparibas.projetfilrouge.pskype.infrastructure.user.ItCorrespondantEntity;

@Repository
public class SkypeProfileEventRepositoryImpl implements ISkypeProfileEventDomain {

	
	private static Logger logger = LoggerFactory.getLogger(SkypeProfileEventRepositoryImpl.class);
	
	@Autowired
	private SkypeProfileEventEntityMapper entityMapper;
	
	@Autowired
	private SkypeProfileEntityMapper entityMapperSkypeProfile;
	
	@Autowired
	private ISkypeProfileEventRepository skypeProfileEventRepository;
	
	@Autowired
	private IItCorrespondantRepository itCorrespondantRepository;
	
	@Override
	public void create(SkypeProfileEvent skypeProfileEvent) {
		
		SkypeProfileEventEntity entity = entityMapper.mapToEntity(skypeProfileEvent);
		
		//System.out.println("Mapper-Event :" + entity.getItCorrespondant().getCollaboraterId()) ;
		
		skypeProfileEventRepository.save(entity);
	}

	@Override
	public void update(SkypeProfileEvent skypeProfileEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(SkypeProfileEvent skypeProfileEvent) {
		
		SkypeProfileEventEntity entity = entityMapper.mapToEntity(skypeProfileEvent);
		skypeProfileEventRepository.delete(entity);
	}

	@Override
	public void deleteAllEventByProfile(SkypeProfile skypeProfile) {
		
		List<SkypeProfileEventEntity> listEventProfile = skypeProfileEventRepository.findBySkypeProfileSIP(skypeProfile.getSIP());
		for (SkypeProfileEventEntity entity : listEventProfile) {
			skypeProfileEventRepository.delete(entity);
		}
	}

	@Override
	public List<SkypeProfileEvent> findAllEventBySkypeProfile(SkypeProfile skypeProfile) {		
		
		List<SkypeProfileEventEntity> Entitys = skypeProfileEventRepository.findBySkypeProfileSIP(skypeProfile.getSIP());		
		for (SkypeProfileEventEntity entity:Entitys) {
			logger.info(entity.toString());
		}
		return entityMapper.mapToDomainList(Entitys);	
		
	}

	@Override
	public List<SkypeProfileEvent> findAllEventByItCorrespondantId(String collaboraterId) {
		
		List<SkypeProfileEventEntity> Entitys = skypeProfileEventRepository.findByItCorrespondantItCorrespondantId(collaboraterId);
		if (Entitys==null) {
			return null;
		}else {
			return entityMapper.mapToDomainList(Entitys);
		}
	}

	@Override
	public boolean updateEventItCorrespondant(ItCorrespondant itCorrespondant, ItCorrespondant itCorrespondantNew) {
		
		ItCorrespondantEntity correspondantEntityNew;
		if (itCorrespondantNew ==null) {
			correspondantEntityNew = null;
		}else {
			correspondantEntityNew = itCorrespondantRepository.findByItCorrespondantId(itCorrespondantNew.getCollaboraterId());
		}
		for (SkypeProfileEventEntity entity : skypeProfileEventRepository.findByItCorrespondantItCorrespondantId(itCorrespondant.getCollaboraterId())) {
			entity.setItCorrespondant(correspondantEntityNew);
			if (skypeProfileEventRepository.save(entity)==null) {
				return false;
			}
		}
		return true;
	}

}
