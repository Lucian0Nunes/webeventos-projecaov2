package br.com.projecao.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.projecao.entidades.Usuario;

public class JavaMailApp {

	public void mandaEmail(final String mensagem, final Usuario usuario, final String localArquivo) {

		Thread thread = new Thread() {
			public void run() {

				Properties props = new Properties();
				/** Parâmetros de conexão com servidor Gmail */
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("WebEventosProjecao@gmail.com","lula5dedos");
							}
						});

				/** Ativa Debug para sessão */
				session.setDebug(false);

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("javadroid01@gmail.com")); // Remetente

					Address[] toUser = InternetAddress.parse(usuario.getEmail());

					message.setRecipients(Message.RecipientType.TO, toUser);
					message.setSubject("WebEventos - Proje��o (Beta)");// Assunto

					if (localArquivo != null) {
						
						 // create the message part 
					    MimeBodyPart messageBodyPart = new MimeBodyPart();

					    //fill message
					    messageBodyPart.setText("Olhem seus QrCode para verificar se foram certos.!!");

					    Multipart multipart = new MimeMultipart();
					    multipart.addBodyPart(messageBodyPart);

					    // Part two is attachment
					    messageBodyPart = new MimeBodyPart();
					    DataSource source = new FileDataSource(localArquivo);
					    messageBodyPart.setDataHandler(new DataHandler(source));
					    messageBodyPart.setFileName(localArquivo);
					    multipart.addBodyPart(messageBodyPart);

					    // Put parts in message
					    message.setContent(multipart);
						
						
					}else{
						message.setText(mensagem +"\n\n "+"seu login: "+usuario.getLogin() +"\n senha de acesso: "+usuario.getSenha());
						
					}
					/** Método para enviar a mensagem criada */
					Transport.send(message);

					System.out.println("Feito!!!");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			}
		};

		thread.start();

	}
}
