package br.com.projecao.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.projecao.entidades.Oficina;
import br.com.projecao.facade.OficinasFacade;


@ManagedBean(name="oficina")
@SessionScoped
public class OficinasMB {
	
	private OficinasFacade dao;
	private List<Oficina> lista;
	
	public List<Oficina> getLista(){
		if(lista == null || lista.size() == 0){
			dao = new OficinasFacade(getManager());
			lista = dao.getLista();
		}
		return lista;
	}
	
	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute(" EntityManager ");
	}

	public void setLista(List<Oficina> lista) {
		this.lista = lista;
	}

}
