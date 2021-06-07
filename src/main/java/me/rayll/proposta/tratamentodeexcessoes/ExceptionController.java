package me.rayll.proposta.tratamentodeexcessoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value = ResponseStatusException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> tratarResponseStatusException(ResponseStatusException ex){
		List<String> listaDeErros = new ArrayList<>();
		//busca a mensagem de erro da excessão
		String message = ex.getReason();
		listaDeErros.add(message);
		
		return listaDeErros;
	}
	
	
}
