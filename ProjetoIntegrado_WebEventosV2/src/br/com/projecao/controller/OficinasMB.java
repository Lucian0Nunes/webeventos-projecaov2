package br.com.projecao.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.projecao.entidades.Oficina;
import br.com.projecao.facade.OficinasFacade;

@ManagedBean(name = "oficinaBean")
@SessionScoped
public class OficinasMB {
	private Oficina oficina = new Oficina();
	private List<Oficina> lista;

	public void adiciona() {
		EntityManager manager = this.getManager();
		OficinasFacade dao = new OficinasFacade(manager);
		if (this.oficina.getId() == null) {
			dao.adiciona(this.oficina);
		} else {
			dao.atualiza(this.oficina);
		}
		this.oficina = new Oficina();
		this.lista = null;
	}

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		OficinasFacade dao = new OficinasFacade(manager);
		this.oficina = dao.busca(id);
	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		OficinasFacade dao = new OficinasFacade(manager);
		dao.remove(id);
		this.lista = null;

	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public List<Oficina> getLista() {
		if (lista == null || lista.size() == 0) {
			EntityManager manager = this.getManager();
			OficinasFacade dao = new OficinasFacade(manager);
			dao = new OficinasFacade(getManager());
			lista = dao.getLista();
		}
		return lista;
	}

	public void setLista(List<Oficina> lista) {
		this.lista = lista;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute(" EntityManager ");
	}

}
