package med.voll.api.entities.records;

import med.voll.api.entities.Medico;
import med.voll.api.entities.enums.Especialidade;

public record DadosListagemMedico (
	
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade 
		
		) {
	
	public DadosListagemMedico(Medico medico) {
		this(medico.getNome(), medico.getEmail(),medico.getCrm(), medico.getEspecialidade());
	}
	
}
