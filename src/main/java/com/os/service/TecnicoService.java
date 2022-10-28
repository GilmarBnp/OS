package com.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.os.dto.TecnicoDTO;
import com.os.model.Pessoa;
import com.os.model.Tecnico;
import com.os.repository.PessoaRepository;
import com.os.repository.TecnicoRepository;
import com.os.services.exception.DataIntegratyViolationException;
import com.os.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo:" + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		if (findByCPF(tecnicoDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));

	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		Tecnico oldObj = findById(id);
		if (findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		oldObj.setNome(tecnicoDTO.getNome());
		oldObj.setCpf(tecnicoDTO.getCpf());
		oldObj.setTelefone(tecnicoDTO.getTelefone());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if(tecnico.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	
	

	public Pessoa findByCPF(TecnicoDTO tecnicoDTO) {
		Pessoa pessoa = pessoaRepository.findByCPF(tecnicoDTO.getCpf());
		if (pessoa != null) {
			return pessoa;
		}
		return null;
	}

}
