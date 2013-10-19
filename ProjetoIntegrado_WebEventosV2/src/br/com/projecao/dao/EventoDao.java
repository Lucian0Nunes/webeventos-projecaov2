package br.com.projecao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projecao.entidades.Evento;

public class EventoDao {

	private EntityManager manager;
	
	public EventoDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Evento evento){
		this.manager.persist(evento);
	}	
	
	public Evento procura(Long id){
		return this.manager.find(Evento.class, id);
	}
	
	public void remove(Long id){
		Evento evento = this.procura(id);
		this.manager.remove(evento);
	}
	
	public Evento atualiza(Evento evento){
		return this.manager.merge(evento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM evento x");
		return query.getResultList();
	}
	
	public boolean qtdVagasDisponiveis(){
		Query query = this.manager.createQuery("SELECT x.qtd_vagas FROM Evento x");
		int qtd = (Integer) query.getSingleResult();
		if(qtd > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int qtdDeDiasFloat(){
		Query query = this.manager.createQuery("SELECT x.dias_float FROM Evento x");
		int qtd = (Integer) query.getSingleResult();
		return qtd;
	}
	
	
	public void designaVaga(){
		if(qtdVagasDisponiveis()){
			Evento evento = procura(1l);
			evento.setQtd_vagas((evento.getQtd_vagas() -1));
			atualiza(evento);
		}
	}
	
	
}
