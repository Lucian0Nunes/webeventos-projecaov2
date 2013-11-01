package br.com.projecao.facade;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.projecao.dao.EventoDao;
import br.com.projecao.entidades.Evento;

public class EventoFacade {
	
	private EventoDao dao;	
	
	public EventoFacade(EntityManager manager) {
		dao = new EventoDao(manager);
	}
	
	public void adiciona(Evento evento){
		dao.adiciona(evento);
	}
	
	public Evento busca(Long id){
		return dao.procura(id);
	}
	
	public void remove(Long id){
		dao.remove(id);
	}
	public Evento atualiza(Evento evento){
		return dao.atualiza(evento);
	}
	
	public List<Evento> getLista(){
		return dao.getLista();
	}
	

}
