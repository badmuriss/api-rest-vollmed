package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.enums.Especialidade;

public record DadosDetalhamentoMedico (
	
		Long id,
		String nome, 
		String email, 
		String telefone,
		String crm, 
		Especialidade especialidade ,
		Endereco endereco
		
		) {
	
	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId() ,medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
	}
	
}
