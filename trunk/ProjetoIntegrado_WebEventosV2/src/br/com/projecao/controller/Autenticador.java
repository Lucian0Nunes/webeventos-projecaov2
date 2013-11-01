package br.com.projecao.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.projecao.entidades.Usuario;
import br.com.projecao.facade.UsuarioFacade;

@ManagedBean(name = "autenticador")
public class Autenticador {

	private String usuario;
	private String senha;
	Usuario user;

	public String autentica() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = this.getManager();
		UsuarioFacade dao = new UsuarioFacade(manager);
		String retorno = null;

		if (dao.usuarioValido(this.usuario, this.senha) != null) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);
			this.user = dao.usuarioValido(this.usuario, this.senha);
			if (this.user.getNivelAcesso() == 2) {
				retorno = "/administrador";
			}if (this.user.getNivelAcesso() == 1) {
				retorno = "/login";
			}
			return retorno;
		} else {
			FacesMessage fm = new FacesMessage("Usuario ou senha invalidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/webeventos";
		}
	}

	public String registraSaida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "/webeventos";

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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute(" EntityManager ");
	}

}
