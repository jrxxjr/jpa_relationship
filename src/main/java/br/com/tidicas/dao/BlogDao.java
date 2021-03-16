package br.com.tidicas.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.tidicas.model.Blog;

/**
 * Classe para manipular as operacoes de banco de dados da tabela blog
 * 
 * @author Evaldo Junior
 */
public class BlogDao {
	private final Dao<Blog> dao;
	private EntityManager em;
	private static final Logger LOGGER = Logger.getLogger(BlogDao.class.getName());

	public BlogDao() {
		this.em = JpaUtil.getEntityManager();
		this.dao = new Dao<Blog>(em, Blog.class);
	}

	public void adiciona(Blog blog) {
		this.dao.adiciona(blog);
	}

	public void remove(Blog blog) {
		this.dao.remove(blog);
	}

	public Blog atualiza(Blog blog) {
		blog = this.dao.atualiza(blog);
		return blog;
	}

	public List<Blog> lista() {
		return this.dao.lista();
	}

	public Blog busca(Integer id) {
		return dao.busca(id);
	}

	public List<Blog> buscaPorTitulo(String titulo) {
		List<Blog> result = null;

		try {
			
			Query query = em.createQuery("select x from Blog x where x.titulo like :parametro ");
			query.setParameter("parametro", "%" + titulo + "%");
			result = query.getResultList();
			
		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
		}

		return result;
	}

}