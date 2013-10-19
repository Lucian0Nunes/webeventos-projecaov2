package br.com.projecao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projecao.entidades.Oficina;

public class OficinasDao {
	
	private EntityManager manager;
	
	public OficinasDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Oficina oficina){
		this.manager.persist(oficina);
	}	
	
	public Oficina procura(Long id){
		return this.manager.find(Oficina.class, id);
	}
	
	public void remove(Long id){
		Oficina oficina = this.procura(id);
		this.manager.remove(oficina);
	}
	
	public Oficina atualiza(Oficina oficina){
		return this.manager.merge(oficina);
	}
	
	@SuppressWarnings("unchecked")
	public List<Oficina> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM Oficina x");
		return query.getResultList();
	}

}
