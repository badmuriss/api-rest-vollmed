package med.voll.api.entities.records.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.entities.records.endereco.DadosEndereco;

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
		@Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$")
		String cpf, 
		
		@NotNull
		@Valid
		DadosEndereco endereco
		
		) {

}
