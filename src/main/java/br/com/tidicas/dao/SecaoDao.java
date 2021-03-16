package br.com.tidicas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tidicas.model.Secao;

/**
 * Classe para manipular as operacoes de banco de dados da tabela categoria
 * 
 * @author Evaldo Junior
 */
public class SecaoDao {
	private final Dao<Secao> dao;
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(SecaoDao.class.getName());

	public SecaoDao() {
		this.em = JpaUtil.getEntityManager();
		this.dao = new Dao<Secao>(em, Secao.class);
	}

	public void adiciona(Secao secao) {
		this.dao.adiciona(secao);
	}

	public void remove(Secao secao) {
		this.dao.remove(secao);
	}

	public Secao atualiza(Secao secao) {
		secao = this.dao.atualiza(secao);
		return secao;
	}

	public List<Secao> lista() {
		return this.dao.lista();
	}

	public Secao busca(Integer id) {
		return dao.busca(id);
	}

	public List<Secao> buscaPorDescricao(String descricao) {
		List<Secao> result = null;

		try {

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Secao> criteriaQuery = criteriaBuilder.createQuery(Secao.class);
			Root<Secao> secao_ = criteriaQuery.from(Secao.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.like(secao_.get("descricao").as(String.class), "%" + descricao + "%"));
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<Secao> typedQuery = em.createQuery(criteriaQuery);             
			result = typedQuery.getResultList();

		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
		}

		return result;
	}

}