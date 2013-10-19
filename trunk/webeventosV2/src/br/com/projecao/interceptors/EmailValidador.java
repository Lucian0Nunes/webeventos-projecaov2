package br.com.projecao.interceptors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.projecao.interfaces.Validador;

@ManagedBean (name="validadorEmail")
public class EmailValidador implements Validador {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(enteredEmail);

		boolean matchFound = m.matches();

		if (!matchFound) {
			FacesMessage message = new FacesMessage();
			message.setDetail("E-mail incorreto!");
			message.setSummary("E-mail incorreto!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}
