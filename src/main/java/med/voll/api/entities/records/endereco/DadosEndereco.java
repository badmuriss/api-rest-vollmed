package med.voll.api.entities.records.endereco;

import jakarta.validation.constraints.*;

public record DadosEndereco(
		
		@NotBlank
		String logradouro, 
		
		@NotBlank
		String bairro, 
		
		@NotBlank
		@Pattern(regexp = "(^[0-9]{5})-?([0-9]{3}$)")
		String cep, 
	
		String complemento, 
		
		@NotBlank
		String cidade, 
		
		@NotBlank
		String uf, 
		
		@Pattern(regexp = "\\d")
		String numero
		
		) {
}
