package suporte;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.EventoDao;
import dao.UsuarioDao;
import entidades.Evento;

public class ProcessoAgendado implements Job {

	Logger log = Logger.getLogger(ProcessoAgendado.class);
	
	@Override
	public void execute(JobExecutionContext job) throws JobExecutionException {

		Calendar cal = Calendar.getInstance();
		Calendar calEvento = Calendar.getInstance();
		
		try {
			EventoDao eventoDao = new EventoDao();
			Evento evento = eventoDao.procura(1l);
			calEvento.setTime(evento.getData_inicio());
			
			if(cal.get(Calendar.DATE) == (evento.getDias_float() - calEvento.get(Calendar.DATE) )){
				UsuarioDao usuarioDao = new UsuarioDao();
				usuarioDao.enviaEmailConfirmacao();
			}
			if(cal.get(Calendar.DATE) == ((1 - calEvento.get(Calendar.DATE) ))){
				UsuarioDao usuarioDao = new UsuarioDao();
				usuarioDao.enviaEmailCadastroReservaDiasAntesDoEvento();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
