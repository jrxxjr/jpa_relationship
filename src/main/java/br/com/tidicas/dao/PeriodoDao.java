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

import br.com.tidicas.model.Periodo;

/**
 * Classe para manipular as operacoes de banco de dados da tabela categoria
 * 
 * @author Evaldo Junior
 */
public class PeriodoDao {
	private final Dao<Periodo> dao;
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(PeriodoDao.class.getName());

	public PeriodoDao() {
		this.em = JpaUtil.getEntityManager();
		this.dao = new Dao<Periodo>(em, Periodo.class);
	}

	public void adiciona(Periodo periodo) {
		this.dao.adiciona(periodo);
	}

	public void remove(Periodo periodo) {
		this.dao.remove(periodo);
	}

	public Periodo atualiza(Periodo periodo) {
		periodo = this.dao.atualiza(periodo);
		return periodo;
	}

	public List<Periodo> lista() {
		return this.dao.lista();
	}

	public Periodo busca(Integer id) {
		return dao.busca(id);
	}

	public List<Periodo> buscaPorDescricao(String descricao) {
		List<Periodo> result = null;

		try {

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Periodo> criteriaQuery = criteriaBuilder.createQuery(Periodo.class);
			Root<Periodo> periodo_ = criteriaQuery.from(Periodo.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.like(periodo_.get("descricao").as(String.class), "%" + descricao + "%"));
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<Periodo> typedQuery = em.createQuery(criteriaQuery);             
			result = typedQuery.getResultList();

		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
		}

		return result;
	}

}