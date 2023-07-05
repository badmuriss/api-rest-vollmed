package med.voll.api.entities.records.medico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.entities.records.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id,
		
		String nome, 
		
		@Pattern(regexp = "\\d{11}")
		String telefone,
		
		DadosEndereco endereco
		
		) {
	
}