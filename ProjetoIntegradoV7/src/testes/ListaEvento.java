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
		
		evento.setDescricaoEvento("No pr�ximo dia 07 de agosto, na Faculdade Proje��o - unidade GUARA, Bras�lia , acontece o IV Semin�rio de Inova��o em Governo Eletr�nico, evento que ter� como tema principal Tecnologias M�veis Integrando Governo e Sociedade.O semin�rio tem por objetivo debater o uso da tecnologia da informa��o para aproximar o Estado e o cidad�o, com economia de tempo e recursos, servi�os e informa��es. Na programa��o, pain�is como [O Relacionamento do Governo e da Sociedade atrav�s do uso de Tecnologias M�veis], com a participa��o de representantes do Governo, Sociedade e Academia, e [Infraestrutura para expandir os servi�os p�blicos ao cidad�o � Infovias Estaduais], com a presen�a de representantes do Minist�rio das Comunica��es, da Telebras, da Associa��o Brasileira de Entidades Estaduais de Tecnologia da Informa��o e Comunica��o (ABEP), e  da InfoVia RS.Haver� tamb�m apresenta��o de casos de sucesso e o an�ncio dos vencedores do Desafio Tecnol�gico. A participa��o no evento � gratuita.");
		dao.atualiza2(evento);
	}
		
}
