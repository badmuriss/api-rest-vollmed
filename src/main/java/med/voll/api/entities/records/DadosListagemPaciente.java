package med.voll.api.entities.records;

import med.voll.api.entities.enums.Especialidade;

public record DadosListagemPaciente (
	
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade 
		
		) {
	
}
