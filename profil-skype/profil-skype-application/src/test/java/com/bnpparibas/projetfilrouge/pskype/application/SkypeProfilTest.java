package com.bnpparibas.projetfilrouge.pskype.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
	
import com.bnpparibas.projetfilrouge.pskype.domain.Collaborater;
import com.bnpparibas.projetfilrouge.pskype.domain.ICollaboraterDomain;
import com.bnpparibas.projetfilrouge.pskype.domain.IItCorrespondantDomain;
import com.bnpparibas.projetfilrouge.pskype.domain.ISkypeProfileDomain;
import com.bnpparibas.projetfilrouge.pskype.domain.ItCorrespondant;
import com.bnpparibas.projetfilrouge.pskype.domain.OrganizationUnity;
import com.bnpparibas.projetfilrouge.pskype.domain.RoleTypeEnum;
import com.bnpparibas.projetfilrouge.pskype.domain.Site;
import com.bnpparibas.projetfilrouge.pskype.domain.SkypeProfile;
import com.bnpparibas.projetfilrouge.pskype.domain.StatusSkypeProfileEnum;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SkypeProfilTest {
	
	private static final Site site = new Site("8802", "Valmy 2", "41 rue de Valmy", "93100", "Montreuil");
	private static final OrganizationUnity uo = new OrganizationUnity("SDI1", "I", "Business intelligence", site);
	
	@Autowired
	private ISkypeProfileManagement skypeProfilManagement;
	
	@Autowired
	private ICollaboraterDomain collaboraterDomain;
	
	@Autowired
	private IItCorrespondantDomain itCorrespondantDomain;
	
	@Autowired
	private ICollaboraterManagment collaboraterManagemenent;
	
	@Autowired
	private ISkypeProfileDomain skypeProfilDomain;
	
/*	@Test
	//@Rollback(false)
	@DisplayName("Vérifier que tous les évènements reviennent bien après la création")
	public void verifyGettingAllEventsofProfil () {
		Collaborater collab = new Collaborater("McEnroe", "John", "112114", "01-43-34-45-56", "06-12-13-14-15", "john.doe@gmail.com",uo);		
		collaboraterDomain.create(collab);
		
		//en attente de suppression des DTO pour faire le test

		assertThat(true).isFalse();

	}*/

	@Test
	//@Rollback(false)
	@DisplayName("Vérifier que l'on crée bien un nouveau profil lorsque le collaborateur n'en possède pas")
	public void verifyCreationNewProfilWhenNonExisting () {
		Collaborater collab = new Collaborater("McEnroe", "John", "112114", "01-43-34-45-56", "06-12-13-14-15", "john.doe@gmail.com",uo);		
		collaboraterDomain.create(collab);
		
		Collaborater collabCil = new Collaborater("Power", "Monsieur", "212114", "01-45-34-45-56", "07-12-13-14-15", "mr.power@gmail.com",uo);		
		collaboraterDomain.create(collabCil);		
		Set<RoleTypeEnum> roles = new HashSet<RoleTypeEnum>();
		roles.add(RoleTypeEnum.ROLE_RESP);
		itCorrespondantDomain.createRoleCILtoCollab("212114", roles);
		
		SkypeProfile skypeProfile = new SkypeProfile("sip:stefan.radelle@live.bnpparibas.com", false, "InternationalNonAuthorized", "DP-FR", "M002117014", false, "Linked Mailbox", "user", collab);
		String idAnnuaireCIL = "212114";
		String eventComment = "c'est mon premier test";

		assertThat(skypeProfilManagement.addNewSkypeProfile(skypeProfile, idAnnuaireCIL, eventComment)).isTrue();

	}
	
}
