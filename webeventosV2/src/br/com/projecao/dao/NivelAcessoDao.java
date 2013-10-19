package br.com.projecao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projecao.entidades.NivelDeAcesso;

public class NivelAcessoDao {
	
	private EntityManager manager;
	
	public NivelAcessoDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(NivelDeAcesso nivel){
		this.manager.persist(nivel);
	}	
	
	public NivelDeAcesso procura(Long id){
		return this.manager.find(NivelDeAcesso.class, id);
	}
	
	public void remove(Long id){
		NivelDeAcesso nivel = this.procura(id);
		this.manager.remove(nivel);
	}
	
	public NivelDeAcesso atualiza(NivelDeAcesso nivel){
		return this.manager.merge(nivel);
	}
	
	@SuppressWarnings("unchecked")
	public List<NivelDeAcesso> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM NivelDeAcesso x");
		return query.getResultList();
	}

}
