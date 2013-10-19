package br.com.projecao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean(name="autenticador")
public class Autenticador {
	
	private static Map<String,String> mapa = new HashMap<String,String>();
	
	static{
		Autenticador.mapa.put("bb", "bb");
		Autenticador.mapa.put("jj", "jj");
	}
	
	private String usuario;
	private String senha;
	
	public String autentica(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(Autenticador.mapa.containsKey(this.usuario)	&& Autenticador.mapa.get(this.usuario).equals(this.senha)){
			
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);
			return "/login";
		}else{
			FacesMessage fm = new FacesMessage("usuário ou senha invalidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/webeventos";
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
	
}
