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

import br.com.tidicas.model.Categoria;

/**
 * Classe para manipular as operacoes de banco de dados da tabela categoria
 * 
 * @author Evaldo Junior
 */
public class CategoriaDao {
	private final Dao<Categoria> dao;
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(CategoriaDao.class.getName());

	public CategoriaDao() {
		this.em = JpaUtil.getEntityManager();
		this.dao = new Dao<Categoria>(em, Categoria.class);
	}

	public void adiciona(Categoria categoria) {
		this.dao.adiciona(categoria);
	}

	public void remove(Categoria categoria) {
		this.dao.remove(categoria);
	}

	public Categoria atualiza(Categoria categoria) {
		categoria = this.dao.atualiza(categoria);
		return categoria;
	}

	public List<Categoria> lista() {
		return this.dao.lista();
	}

	public Categoria busca(Integer id) {
		return dao.busca(id);
	}

	public List<Categoria> buscaPorDescricao(String descricao) {
		List<Categoria> result = null;

		try {

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
			Root<Categoria> categoria_ = criteriaQuery.from(Categoria.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.like(categoria_.get("descricao").as(String.class), "%" + descricao + "%"));
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<Categoria> typedQuery = em.createQuery(criteriaQuery);             
			result = typedQuery.getResultList();

		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
		}

		return result;
	}

}