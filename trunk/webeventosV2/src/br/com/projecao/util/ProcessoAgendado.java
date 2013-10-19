package br.com.projecao.util;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.projecao.dao.EventoDao;
import br.com.projecao.dao.UsuarioDao;
import br.com.projecao.entidades.Evento;
import br.com.projecao.testes.SegundaFabrica;

public class ProcessoAgendado implements Job {

	Logger log = Logger.getLogger(ProcessoAgendado.class);
	
	@Override
	public void execute(JobExecutionContext job) throws JobExecutionException {

		EntityManager manager = SegundaFabrica.getInstance();
		Calendar cal = Calendar.getInstance();
		
		try {
			EventoDao eventoDao = new EventoDao(manager);
			Evento evento = eventoDao.procura(1l);
			
			if(cal.get(Calendar.DATE) == (evento.getDias_float() - evento.getData_inicio().get(Calendar.DATE) )){
				UsuarioDao usuarioDao = new UsuarioDao(manager);
				usuarioDao.enviaEmailConfirmacao();
			}
			if(cal.get(Calendar.DATE) == ((1 - evento.getData_inicio().get(Calendar.DATE) ))){
				UsuarioDao usuarioDao = new UsuarioDao(manager);
				usuarioDao.enviaEmailCadastroReservaDiasAntesDoEvento();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
