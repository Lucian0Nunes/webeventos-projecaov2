package br.com.projecao.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.projecao.interfaces.InterfaceDeAcessoAoBanco;

/**
 * Classe que vai controlar o Evento, mantendo todas as descrições e Oficinas disponiveis
 *  
 * @author javadroid
 *
 */

@Entity
@Table(name="Evento")
public class Evento implements InterfaceDeAcessoAoBanco{
	
	private static final long serialVersionUID = 5742420363732536065L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome")
	private String nome;
	@Column(name="desc_evento")
	private String descricaoEvento;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_inicio = new GregorianCalendar();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_termino = new GregorianCalendar();;
	@Column(name="qtd_vagas")
	private int qtd_vagas;
	@Column(name="dias_float")
	private int dias_float;
	
	@OneToMany
	@JoinColumn(name="id_oficina")
	private List<Oficina> oficinas;
	
	@Column(name="endereco")
	private String endereco_evento;
	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricaoEvento() {
		return descricaoEvento;
	}
	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}
	public Calendar getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Calendar data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Calendar getData_termino() {
		return data_termino;
	}
	public void setData_termino(Calendar data_termino) {
		this.data_termino = data_termino;
	}
	public int getQtd_vagas() {
		return qtd_vagas;
	}
	public void setQtd_vagas(int qtd_vagas) {
		this.qtd_vagas = qtd_vagas;
	}
	public int getDias_float() {
		return dias_float;
	}
	public void setDias_float(int dias_float) {
		this.dias_float = dias_float;
	}
	public List<Oficina> getOficinas() {
		return oficinas;
	}
	public void setOficinas(List<Oficina> oficinas) {
		this.oficinas = oficinas;
	}
	public String getEndereco_evento() {
		return endereco_evento;
	}
	public void setEndereco_evento(String endereco_evento) {
		this.endereco_evento = endereco_evento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
