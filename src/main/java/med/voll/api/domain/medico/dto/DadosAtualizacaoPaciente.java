package med.voll.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPaciente(
		
		@NotNull
		Long id,
		
		String nome, 
		
		@Pattern(regexp = "\\d{11}")
		String telefone,
		
		DadosEndereco endereco
		
		) {
	
}
