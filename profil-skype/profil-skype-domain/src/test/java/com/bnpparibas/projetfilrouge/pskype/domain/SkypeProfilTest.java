package com.bnpparibas.projetfilrouge.pskype.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


public class SkypeProfilTest {
	
	private static final Site site = new Site("8802", "Valmy 2", "41 rue de Valmy", "93100", "Montreuil");
	private static final OrganizationUnity uo = new OrganizationUnity("SDI1", "I", "Business intelligence", site);

	@Test
	@DisplayName("Contrôle sur la date d'expiration à la création du profil")
	public void expirationDateControlWhenCreated () {
		SkypeProfile profil = new SkypeProfile();
		
	    LocalDate deb = LocalDate.now();
	    LocalDate fin = profil.getExpirationDate().toInstant()
	    	      .atZone(ZoneId.systemDefault())
	    	      .toLocalDate();

		assertThat(Period.between(deb, fin).minusYears(2).isZero()).isTrue();
		
	}

	@Test
	@DisplayName("Contrôle sur quelques paramètres std d'un collaborateur")
	public void validityBeanCollaborator () {
		
		Collaborater collab1 = new Collaborater("McEnroe", "John", "112114", "01-43-34-45-56", "06-12-13-14-15", "john.tennis@gmail.com",uo);
		Collaborater collab2 = new Collaborater("McEnroe", "John", "112115112115112115112115112115", null, null, null ,uo);
		Collaborater collab3 = new Collaborater("112115112115112115112115112115112115112115112115112115112115112115112115112115112115112115", "Stephen", "118116", null, null, "stephen.horror@gmail.com",uo);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Collaborater>> valid1 = validator.validate(collab1);
		Set<ConstraintViolation<Collaborater>> valid2 = validator.validate(collab2);
		Set<ConstraintViolation<Collaborater>> valid3 = validator.validate(collab3);

		assertThat(valid1).isEmpty();
		assertThat(valid2).isNotEmpty();
		assertThat(valid3).isNotEmpty();
		
	}
	
	@Test
	@DisplayName("Contrôle sur le contrôle de validité de l'email à la norme RFC")
	public void validityEmail () {
		// cf https://fr.qwe.wiki/wiki/Email_address#Examples
		
		Collaborater collab1 = new Collaborater("McEnroe", "John", "112114", null, null, "user.name+tag+sorting@example.com",uo);
		Collaborater collab2 = new Collaborater("McEnroe", "John", "112114", null, null, "Abc.example.com",uo);
		Collaborater collab3 = new Collaborater("McEnroe", "John", "112114", null, null, "Abc..123@example.com",uo);
		Collaborater collab4 = new Collaborater("McEnroe", "John", "112114", null, null, "Abc.123@example.com",uo);
		Collaborater collab5 = new Collaborater("McEnroe", "John", "112114", null, null, "disposable.style.email.with+symbol@example.com",uo);
		Collaborater collab6 = new Collaborater("McEnroe", "John", "112114", null, null, "other.email-with-hyphen@example.com",uo);
		Collaborater collab7 = new Collaborater("McEnroe", "John", "112114", null, null, "other. email-with-hyphen@example.com",uo);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Collaborater>> valid1 = validator.validate(collab1);
		Set<ConstraintViolation<Collaborater>> valid2 = validator.validate(collab2);
		Set<ConstraintViolation<Collaborater>> valid3 = validator.validate(collab3);
		Set<ConstraintViolation<Collaborater>> valid4 = validator.validate(collab4);
		Set<ConstraintViolation<Collaborater>> valid5 = validator.validate(collab5);
		Set<ConstraintViolation<Collaborater>> valid6 = validator.validate(collab6);
		Set<ConstraintViolation<Collaborater>> valid7 = validator.validate(collab7);
		
		assertThat(valid1).isEmpty();
		assertThat(valid2).isNotEmpty();
		assertThat(valid3).isNotEmpty();
		assertThat(valid4).isEmpty();
		assertThat(valid5).isEmpty();
		assertThat(valid6).isEmpty();
		assertThat(valid7).isNotEmpty();
		
	}
	
	@Test
	@DisplayName("Contrôle sur le contrôle de validité du numéro de téléphone")
	public void validityTel () {
		
		// TO COMPLETE : ne fonctionne pas actuellement.
		assertThat(true).isFalse();
		
	}
}
