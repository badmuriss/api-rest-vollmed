package med.voll.api.infra;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErroEntityNotFound() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();

		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).collect(Collectors.toList()));
	}
	
	public record DadosErroValidacao(String campo, String mensagem) {
		
		public DadosErroValidacao(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
		
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity tratarErroSQLDuplicado(SQLIntegrityConstraintViolationException ex) {
		return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
	}
	
}
