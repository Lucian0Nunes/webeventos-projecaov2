package br.com.projecao.facade;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.projecao.dao.OficinasDao;
import br.com.projecao.entidades.Oficina;

public class OficinasFacade {
	
	private OficinasDao dao;	
	
	public OficinasFacade(EntityManager manager) {
		dao = new OficinasDao(manager);
	}
	
	public void adiciona(Oficina oficina){
		dao.adiciona(oficina);
	}
	
	public Oficina busca(Long id){
		return dao.procura(id);
	}
	
	public void remove(Long id){
		dao.remove(id);
	}
	
	public List<Oficina> getLista(){
		return dao.getLista();
	}

	public Oficina atualiza(Oficina oficina) {
		return dao.atualiza(oficina);
		
	}
	

}
