package de.woock.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AnfrageValidation implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Anfrage.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "frage", "Darf nicht leer sein.");
		Anfrage anfrage = (Anfrage) obj;
		if (anfrage.getFrage().isBlank()) {
			e.rejectValue("frage", "Darf nicht leer sein.");
		} else if (anfrage.getId() > 0) {
			e.rejectValue("id", "muss gesetzt sein");
		}
	}
}
