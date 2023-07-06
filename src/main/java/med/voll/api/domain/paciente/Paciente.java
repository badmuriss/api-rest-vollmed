package med.voll.api.domain.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.dto.DadosEndereco;
import med.voll.api.domain.medico.dto.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.dto.DadosCadastroPaciente;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

	public Paciente(DadosCadastroPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private Boolean ativo = true;
	
	@Embedded
	private Endereco endereco;

	public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {

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
		this.ativo = false;
	}
	
}
