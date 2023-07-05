package med.voll.api.entities.records.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.entities.enums.Especialidade;
import med.voll.api.entities.records.endereco.DadosEndereco;

public record DadosCadastroMedico(
		
		@NotBlank
		String nome, 
		
		@NotBlank
		@Email
		String email, 
		
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String telefone,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm, 
		
		@NotNull
		Especialidade especialidade, 
		
		@NotNull
		@Valid
		DadosEndereco endereco
		
		) {
	
}
