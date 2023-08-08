package de.woock.infra.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.woock.domain.Anfrage;

public class AnfrageValidation implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Anfrage.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
	    ValidationUtils.rejectIfEmptyOrWhitespace(e, "frage", "feld.nicht.leer");
		
		Anfrage anfrage = (Anfrage) obj;
		int zeichen = anfrage.getFrage().length();
		if (zeichen > 0 && zeichen < 4) {
			e.rejectValue("frage", "frage.sinnvoll");
		}
	}
}
