package br.com.projecao.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.projecao.entidades.Usuario;
import br.com.projecao.facade.UsuarioFacade;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class CadastroUsuarioMB {
	private Usuario usuario;
	private List<Usuario> lista;

	public void adiciona() {
		EntityManager manager = this.getManager();
		UsuarioFacade dao = new UsuarioFacade(manager);
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}
		this.usuario = new Usuario();
		this.lista = null;
	}

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		UsuarioFacade dao = new UsuarioFacade(manager);
		this.usuario = dao.busca(id);

	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		UsuarioFacade dao = new UsuarioFacade(manager);
		dao.remove(id);
		this.lista = null;

	}

	public List<Usuario> getUsuarios() {
		if (this.lista == null) {
			EntityManager manager = this.getManager();
			UsuarioFacade dao = new UsuarioFacade(manager);
			this.lista = dao.getLista();
		}
		return this.lista;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute(" EntityManager ");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

}
