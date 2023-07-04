package med.voll.api.paciente;

import med.voll.api.endereco.DadosEndereco;

public record DadosRegistroPaciente(
		
		String nome, 
		String telefone, 
		String email, 
		String cpf, 
		DadosEndereco endereco
		
		) {

}
