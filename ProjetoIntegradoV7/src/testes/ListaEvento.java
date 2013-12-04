package testes;

import java.text.SimpleDateFormat;

import dao.EventoDao;
import entidades.Evento;

public class ListaEvento {

	
	public static void main(String[] args) {
		EventoDao dao = new EventoDao();
		
		Evento evento = dao.procuraEvento(1l);

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nome:" +evento.getNome());
		System.out.println("Descricao: "+evento.getDescricaoEvento());
		System.out.println("Quantidade de vagas disponiveis: "+evento.getQtd_vagas());
		System.out.println("Data inicio: " +format1.format(evento.getData_inicio().getTime()));
		System.out.println("Data fim: "+format1.format(evento.getData_termino().getTime()));
		System.out.println("Dias_Float:" +evento.getDias_float());
		System.out.println("Segunda chamada: " +evento.getDias_segunda_chamada());
		System.out.println("formatada: "+evento.getData_fim_formatada());
	}
		
}
