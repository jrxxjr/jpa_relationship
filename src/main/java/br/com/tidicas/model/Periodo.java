package br.com.tidicas.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe para implementar a tabela categoria no banco de dados
 * 
 * @author Evaldo Junior
 */
@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {

	private static final long serialVersionUID = -2191354725965989719L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer codigo;
	private String descricao;
	
	@ManyToMany(mappedBy = "periodos")
	private Set<Blog> blogs;

	public Periodo() {

	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		if (blogs == null) {
			if (other.blogs != null)
				return false;
		} else if (!blogs.equals(other.blogs))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result = "Periodo [codigo=" + codigo + ", descricao=" + descricao + "]";
		
		if (blogs != null || blogs.size()>0) {
			result = "Periodo [codigo=" + codigo + ", descricao=" + descricao + ", descricao=" + descricao ;
			for (Blog item:blogs) {
				result += ", blog=" +item.getCodigo() +"]";
			}
			 
		}
		return result;	
	}

}