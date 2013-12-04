package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
	
	private static EntityManager instance;
	private static EntityManagerFactory instanceFactory;
	
	public static EntityManager getInstance() {
		if (instance == null) {
			instanceFactory =  Persistence.createEntityManagerFactory("jpa");
			instance = instanceFactory.createEntityManager();
		}if(!instance.isOpen()){
			instance = instanceFactory.createEntityManager();
		}
		return instance;
	}

}
