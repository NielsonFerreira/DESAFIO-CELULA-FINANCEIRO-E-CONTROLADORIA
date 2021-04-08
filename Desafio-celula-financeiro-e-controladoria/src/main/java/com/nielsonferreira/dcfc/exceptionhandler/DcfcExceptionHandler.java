package com.nielsonferreira.dcfc.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DcfcExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String resumoDaException = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String exceptionCompleta = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(resumoDaException, exceptionCompleta), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro{
		
		private String resumoDaException;
		private String exceptionCompleta;
		
		public Erro(String resumoDaException, String exceptionCompleta) {
			this.resumoDaException = resumoDaException;
			this.exceptionCompleta = exceptionCompleta;
		}

		public String getResumoDaException() {
			return resumoDaException;
		}

		public String getExceptionCompleta() {
			return exceptionCompleta;
		}
	}
}
