package br.com.tidicas.main;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

import br.com.tidicas.dao.BlogDao;
import br.com.tidicas.dao.CategoriaDao;
import br.com.tidicas.dao.PeriodoDao;
import br.com.tidicas.dao.SecaoDao;
import br.com.tidicas.model.Blog;
import br.com.tidicas.model.Categoria;
import br.com.tidicas.model.Periodo;
import br.com.tidicas.model.Secao;
import junit.framework.TestCase;

/**
 * Classe para geração das tabelas e teste com operacoes crud
 * 
 * @author Evaldo Junior
 *
 */
public class TesteCrudJpa extends TestCase {

	private static final Logger LOGGER = Logger.getLogger(TesteCrudJpa.class.getName());

	public static void testCrud() {

		CategoriaDao categoriaDao = new CategoriaDao();
		BlogDao blogDao = new BlogDao();
		SecaoDao secaoDao = new SecaoDao();
		PeriodoDao periodoDao = new PeriodoDao();

		Random random = new Random();
		
		/* 1 Entidade Categoria
		 * Simulando um tela web. Vai ser inserido a Categoria em uma tela 
		 * separada da tela do Blog */
		
		Categoria categoria1 = new Categoria();
		categoria1.setDescricao("Filmes " + random.nextInt());

		Categoria categoria2 = new Categoria();
		categoria2.setDescricao("Teatro "  + random.nextInt());
		LOGGER.info("Inclusao");
		
		categoriaDao.adiciona(categoria1);

		categoriaDao.adiciona(categoria2);
		
		categoria1 = categoriaDao.buscaPorDescricao(categoria1.getDescricao()).get(0);
		LOGGER.info("retorno Categoria:" + categoria1.toString());

		categoria2 = categoriaDao.buscaPorDescricao(categoria2.getDescricao()).get(0);
		LOGGER.info("retorno Categoria:" + categoria2.toString());

		categoria1.setDescricao(categoria1.getDescricao() + "update");
		categoria1 = categoriaDao.atualiza(categoria1);

		categoria2.setDescricao(categoria2.getDescricao() + "update");
		categoria2 = categoriaDao.atualiza(categoria2);

		/* 2 Entidade Secao
		 * Simulando um tela web. Vai ser inserido a Secao em uma tela 
		 * separada da tela do Blog */
		
		Secao secao1 = new Secao();
		secao1.setDescricao("Ficcao" + random.nextInt());
		secao1.setBlogs(new HashSet<Blog>());
		
		Secao secao2 = new Secao();
		secao2.setDescricao("Aventura"+ random.nextInt());
		secao2.setBlogs(new HashSet<Blog>());
		
		secaoDao.adiciona(secao1);
		secaoDao.adiciona(secao2);

		secao1 = secaoDao.buscaPorDescricao(secao1.getDescricao()).get(0);
		secao2 = secaoDao.buscaPorDescricao(secao2.getDescricao()).get(0);

		LOGGER.info("retorno Secao:" + secao1.toString());
		LOGGER.info("retorno Secao:" + secao2.toString());
		
		/* 2 Entidade Periodo
		 * Simulando um tela web. Vai ser inserido um Periodo em uma tela 
		 * separada da tela do Blog */

		Periodo periodo1 = new Periodo();
		periodo1.setDescricao("Diario" + random.nextInt());
		periodo1.setBlogs(new HashSet<Blog>());
		
		Periodo periodo2 = new Periodo();
		periodo2.setDescricao("Semanal"+ random.nextInt());
		periodo2.setBlogs(new HashSet<Blog>());
		
		Periodo periodo3 = new Periodo();
		periodo3.setDescricao("Mensal"+ random.nextInt());
		periodo3.setBlogs(new HashSet<Blog>());
		
		periodoDao.adiciona(periodo1);
		periodoDao.adiciona(periodo2);
		periodoDao.adiciona(periodo3);

		periodo1 = periodoDao.buscaPorDescricao(periodo1.getDescricao()).get(0);
		periodo2 = periodoDao.buscaPorDescricao(periodo2.getDescricao()).get(0);
		periodo3 = periodoDao.buscaPorDescricao(periodo3.getDescricao()).get(0);

		LOGGER.info("retorno Periodo:" + periodo1.toString());
		LOGGER.info("retorno Periodo:" + periodo2.toString());
		LOGGER.info("retorno Periodo:" + periodo3.toString());
		
		/* 3 Entidade Blog
		 * Simulando um tela web, do tipo master detail. Vai ser inserido agora o Blog em uma 
		 * tela separada dos outros itens */
		
		Blog blog1 = new Blog();
		blog1.setCategoria(categoria2);
		blog1.setConteudo("conteúdo " + random.nextInt());
		blog1.setDtevento(new Date());
		blog1.setPublicar(0);
		blog1.setTitulo("blog 1");

		// OneToMany
		secao1.getBlogs().add(blog1);

		// ManyToOne 
		blog1.setSecao(secao1);
		
		// ManyToMany
		periodo1.getBlogs().add(blog1);
		periodo3.getBlogs().add(blog1);
		blog1.setPeriodos(new HashSet<Periodo>());
		blog1.getPeriodos().add(periodo1);
		blog1.getPeriodos().add(periodo3);

		blogDao.adiciona(blog1);

		Blog blog2 = new Blog();
		blog2.setCategoria(categoria1);
		blog2.setConteudo("conteúdo " + random.nextInt());
		blog2.setDtevento(new Date());
		blog2.setPublicar(0);
		blog2.setTitulo("blog 2");

		// OneToMany		
		secao2.getBlogs().add(blog2);
		
		// ManyToOne
		blog2.setSecao(secao2);

		blogDao.adiciona(blog2);

		Blog blog3 = new Blog();
		blog3.setCategoria(categoria1);
		blog3.setConteudo("conteúdo " + random.nextInt());
		blog3.setDtevento(new Date());
		blog3.setPublicar(0);
		blog3.setTitulo("blog 3");
		
		// OneToMany
		secao2.getBlogs().add(blog3);
		
		// ManyToOne
		blog3.setSecao(secao2);

		blogDao.adiciona(blog3);
		
		// listagem dos registros manipulados  
		LOGGER.info("retorno Blog:" + blog1.toString());
		LOGGER.info("retorno Blog:" + blog2.toString());
		LOGGER.info("retorno Blog:" + blog3.toString());
		LOGGER.info("retorno Categoria:" + categoria1.toString());
		LOGGER.info("retorno Categoria:" + categoria2.toString());
		LOGGER.info("retorno Secao:" + secao1.toString());
		LOGGER.info("retorno Secao:" + secao2.toString());
		LOGGER.info("retorno Periodo:" + periodo1.toString());
		LOGGER.info("retorno Periodo:" + periodo2.toString());
		LOGGER.info("retorno Periodo:" + periodo3.toString());
		
		/* atualizacao dos registros
		 * 
		 * Na atualizacao o Blog troca de Secao 
		 * Na atualizacao o Blog troca de Periodos
		 */
		
		blog1.setConteudo("conteúdo teste update");
		blog1.setDtevento(new Date());
		blog1.setPublicar(0);
		blog1.setTitulo("blog 1 - update");		
		
		// OneToMany
		secao2.getBlogs().add(blog1);
		
		// ManyToOne
		blog1.setSecao(secao2);
		
		// ManyToMany
		periodo2.getBlogs().add(blog1);		
		blog1.setPeriodos(new HashSet<Periodo>());
		blog1.getPeriodos().add(periodo2);
		
		LOGGER.info("Atualizacao");
		blog1 = blogDao.atualiza(blog1);		
		
		final Integer blog1_codigo = blog1.getCodigo();				
		secao1.getBlogs().removeIf(blog -> blog.getCodigo().equals(blog1_codigo));
		
		secao1 = secaoDao.atualiza(secao1);
						
		periodo1.getBlogs().removeIf(blog -> blog.getCodigo().equals(blog1_codigo));
		periodo3.getBlogs().removeIf(blog -> blog.getCodigo().equals(blog1_codigo));
		periodo1 = periodoDao.atualiza(periodo1);
		periodo3 = periodoDao.atualiza(periodo3);
		
		LOGGER.info("retorno Blog:" + blog1.toString());
		LOGGER.info("retorno Blog:" + blog2.toString());
		LOGGER.info("retorno Blog:" + blog3.toString());
		LOGGER.info("retorno Categoria:" + categoria1.toString());
		LOGGER.info("retorno Categoria:" + categoria2.toString());
		LOGGER.info("retorno Secao:" + secao1.toString());
		LOGGER.info("retorno Secao:" + secao2.toString());
		LOGGER.info("retorno Periodo:" + periodo1.toString());
		LOGGER.info("retorno Periodo:" + periodo2.toString());
		LOGGER.info("retorno Periodo:" + periodo3.toString());
			
		/* Na atualizacao o Blog troca de Secao */
		blog2.setConteudo("conteúdo teste update");
		blog2.setDtevento(new Date());
		blog2.setPublicar(0);
		blog2.setTitulo("blog 2 - update");		
		
		// OneToMany
		secao1.getBlogs().add(blog2);
		
		// ManyToOne
		blog2.setSecao(secao1);
		
		blog2 = blogDao.atualiza(blog2);
		
		final Integer blog2_codigo = blog2.getCodigo();		
		secao2.getBlogs().removeIf(blog -> blog.getCodigo().equals(blog2_codigo));
		
		secao2 = secaoDao.atualiza(secao2);
		secao1 = secaoDao.atualiza(secao1);
				
		
		LOGGER.info("retorno Blog:" + blog1.toString());
		LOGGER.info("retorno Blog:" + blog2.toString());
		LOGGER.info("retorno Blog:" + blog3.toString());
		LOGGER.info("retorno Categoria:" + categoria1.toString());
		LOGGER.info("retorno Categoria:" + categoria2.toString());
		LOGGER.info("retorno Secao:" + secao1.toString());
		LOGGER.info("retorno Secao:" + secao2.toString());
		LOGGER.info("retorno Periodo:" + periodo1.toString());
		LOGGER.info("retorno Periodo:" + periodo2.toString());
		LOGGER.info("retorno Periodo:" + periodo3.toString());
		
		// 4 Remover registros
		LOGGER.info("Remocao");
		
		blog1.setPeriodos(new HashSet<Periodo>());
		blog1 = blogDao.atualiza(blog1);
		blogDao.remove(blog1);
		
		blog2.setPeriodos(new HashSet<Periodo>());
		blog2 = blogDao.atualiza(blog2);
		blogDao.remove(blog2);
		
		blog3.setPeriodos(new HashSet<Periodo>());
		blog3 = blogDao.atualiza(blog3);
		blogDao.remove(blog3);
		
		categoriaDao.remove(categoria1);
		categoriaDao.remove(categoria2);
		secaoDao.remove(secao1);
		secaoDao.remove(secao2);
		
		periodo1.setBlogs(new HashSet<Blog>());
		periodo1 = periodoDao.atualiza(periodo1);
		periodoDao.remove(periodo1);
		
		periodo2.setBlogs(new HashSet<Blog>());
		periodo2 = periodoDao.atualiza(periodo2);
		periodoDao.remove(periodo2);
		
		periodo3.setBlogs(new HashSet<Blog>());
		periodo3 = periodoDao.atualiza(periodo3);
		periodoDao.remove(periodo3);
		
		LOGGER.info("delete Blog:" + blog1.toString());
		LOGGER.info("delete Blog:" + blog2.toString());
		LOGGER.info("delete Blog:" + blog3.toString());

		LOGGER.info("delete Categoria:" + categoria1.toString());
		LOGGER.info("delete Categoria:" + categoria2.toString());
		
		LOGGER.info("delete Secao:" + secao1.toString());
		LOGGER.info("delete Secao:" + secao2.toString());
		
		LOGGER.info("delete Periodo:" + periodo1.toString());
		LOGGER.info("delete Periodo:" + periodo2.toString());
		LOGGER.info("delete Periodo:" + periodo3.toString());
		
	}
}