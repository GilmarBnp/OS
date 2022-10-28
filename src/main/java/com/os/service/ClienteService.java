package com.os.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.os.dto.ClienteDTO;
import com.os.model.Cliente;
import com.os.model.Pessoa;
import com.os.repository.ClienteRepository;
import com.os.repository.PessoaRepository;
import com.os.services.exception.DataIntegratyViolationException;
import com.os.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		if (findByCPF(clienteDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));

	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
		Cliente oldObj = findById(id);
		if (findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		oldObj.setNome(clienteDTO.getNome());
		oldObj.setCpf(clienteDTO.getCpf());
		oldObj.setTelefone(clienteDTO.getTelefone());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Cliente cliente = findById(id);
		if(cliente.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	
	

	public Pessoa findByCPF(ClienteDTO clienteDTO) {
		Pessoa pessoa = pessoaRepository.findByCPF(clienteDTO.getCpf());
		if (pessoa != null) {
			return pessoa;
		}
		return null;
	}
	

}
