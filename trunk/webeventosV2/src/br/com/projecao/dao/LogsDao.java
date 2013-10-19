package br.com.projecao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projecao.entidades.Logs;

public class LogsDao {
	
	private EntityManager manager;
	
	public LogsDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Logs log){
		this.manager.persist(log);
	}	
	
	public Logs procura(Long id){
		return this.manager.find(Logs.class, id);
	}
	
	public void remove(Long id){
		Logs log = this.procura(id);
		this.manager.remove(log);
	}
	
	public Logs atualiza(Logs log){
		return this.manager.merge(log);
	}
	
	@SuppressWarnings("unchecked")
	public List<Logs> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM Logs x");
		return query.getResultList();
	}


}
