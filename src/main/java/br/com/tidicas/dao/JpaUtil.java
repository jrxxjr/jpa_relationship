package br.com.tidicas.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que controla as instancias da conexao com o banco de dados
 * 
 * @author Evaldo Junior
 */
public class JpaUtil {
	  
	private static EntityManagerFactory entityManagerFactory;
	private static final String LOCAL = "jpalocal";		
	private static final Logger LOGGER = Logger.getLogger(JpaUtil.class.getName());
	
	public static EntityManager getEntityManager() {
		
		entityManagerFactory = Persistence.createEntityManagerFactory(LOCAL);
		return entityManagerFactory.createEntityManager();
		
	}
}