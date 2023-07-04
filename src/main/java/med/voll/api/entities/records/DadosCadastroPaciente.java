package med.voll.api.entities.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.*;

public record DadosCadastroPaciente(
		
		@NotBlank
		String nome, 
		
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String telefone, 
		
		@NotBlank
		@Email
		String email, 
		
		@NotBlank
		@CPF
		String cpf, 
		
		@NotNull
		@Valid
		DadosEndereco endereco
		
		) {

}
