package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Logs;

public class LogsDao {
	
	private EntityManager manager;
	
	public LogsDao() {
		this.manager = FactoryManager.getInstance();
	}
	
	public void adiciona(Logs log){
		try {
			manager.getTransaction().begin();
			this.manager.persist(log);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}	
	
	public Logs procura(Long id){
		Logs log = null;
		try {
			manager.getTransaction().begin();
			log = this.manager.find(Logs.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		return log;
	}
	
	public void remove(Long id){
		
		try {
			manager.getTransaction().begin();
			Logs log = this.procura(id);
			this.manager.remove(log);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}

	}
	
	public void atualiza(Logs log){
		try {
			manager.getTransaction().begin();
			this.manager.merge(log);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Logs> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM Logs x");
		return query.getResultList();
	}


}
