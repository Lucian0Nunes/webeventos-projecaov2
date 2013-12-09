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
		
		evento.setDescricaoEvento("No próximo dia 07 de agosto, na Faculdade Projeção - unidade GUARA, Brasília , acontece o IV Seminário de Inovação em Governo Eletrônico, evento que terá como tema principal Tecnologias Móveis Integrando Governo e Sociedade.O seminário tem por objetivo debater o uso da tecnologia da informação para aproximar o Estado e o cidadão, com economia de tempo e recursos, serviços e informações. Na programação, painéis como [O Relacionamento do Governo e da Sociedade através do uso de Tecnologias Móveis], com a participação de representantes do Governo, Sociedade e Academia, e [Infraestrutura para expandir os serviços públicos ao cidadão – Infovias Estaduais], com a presença de representantes do Ministério das Comunicações, da Telebras, da Associação Brasileira de Entidades Estaduais de Tecnologia da Informação e Comunicação (ABEP), e  da InfoVia RS.Haverá também apresentação de casos de sucesso e o anúncio dos vencedores do Desafio Tecnológico. A participação no evento é gratuita.");
		dao.atualiza2(evento);
	}
		
}
