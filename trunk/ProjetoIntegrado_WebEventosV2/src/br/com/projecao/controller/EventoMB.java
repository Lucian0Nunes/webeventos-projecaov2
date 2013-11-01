package br.com.projecao.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.projecao.entidades.Evento;
import br.com.projecao.facade.EventoFacade;

@ManagedBean(name = "evento")
public class EventoMB {
	private Evento evento = new Evento();
	private List<Evento> lista;

	public void adiciona() {
		EntityManager manager = this.getManager();
		EventoFacade dao = new EventoFacade(manager);
		if (this.evento.getId() == null) {
			dao.adiciona(this.evento);
		} else {
			dao.atualiza(evento);
		}
		this.evento = new Evento();
		this.lista = null;
	}

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		EventoFacade dao = new EventoFacade(manager);
		this.evento = dao.busca(id);

	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		EventoFacade dao = new EventoFacade(manager);
		dao.remove(id);
		this.lista = null;

	}

	public List<Evento> getEventos() {
		if (this.lista == null) {
			EntityManager manager = this.getManager();
			EventoFacade dao = new EventoFacade(manager);
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getLista() {
		return lista;
	}

	public void setLista(List<Evento> lista) {
		this.lista = lista;
	}

}