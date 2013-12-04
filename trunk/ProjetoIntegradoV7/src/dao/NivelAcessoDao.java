package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.NivelDeAcesso;

public class NivelAcessoDao {
	
	private EntityManager manager;
	
	public NivelAcessoDao() {
		this.manager = FactoryManager.getInstance();
	}
	
	public void adiciona(NivelDeAcesso nivel){		
		try {
			manager.getTransaction().begin();
			this.manager.persist(nivel);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}	
	
	public NivelDeAcesso procura(Long id){
		NivelDeAcesso nivel = null;
		try {
			manager.getTransaction().begin();
			nivel = this.manager.find(NivelDeAcesso.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		return nivel;
	}
	
	public void remove(Long id){
		try {
			manager.getTransaction().begin();
			NivelDeAcesso nivel = this.procura(id);
			this.manager.remove(nivel);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}

	}
	
	public void atualiza(NivelDeAcesso nivel){
		try {
			manager.getTransaction().begin();
			this.manager.merge(nivel);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<NivelDeAcesso> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM NivelDeAcesso x");
		return query.getResultList();
	}

}
