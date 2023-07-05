package med.voll.api.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.entities.enums.Especialidade;
import med.voll.api.entities.records.endereco.DadosEndereco;
import med.voll.api.entities.records.medico.DadosAtualizacaoMedico;
import med.voll.api.entities.records.medico.DadosCadastroMedico;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.crm = dados.crm();
		this.telefone = dados.telefone();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	private Boolean ativo = true;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {

		String nome = dados.nome();
		String telefone = dados.telefone();
		DadosEndereco endereco = dados.endereco();
		
		if(nome != null && !nome.isBlank() && nome != this.nome) {
			this.nome = nome;
		}
		if(telefone != null && !telefone.isBlank() && telefone != this.telefone) {
			this.telefone = telefone;
		}
		if(endereco != null) {
			this.endereco.atualizarInformacoes(endereco);
		}
	}
	
	public void desativarConta() {
		if(ativo = true) {
			this.ativo = false;
		} else {
			throw new IllegalArgumentException("A conta de id" + id + "já esta desativada!");
		}
	}
	
}
