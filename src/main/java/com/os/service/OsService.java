package com.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.os.domain.enuns.Prioridade;
import com.os.domain.enuns.Status;
import com.os.dto.OsDTO;
import com.os.model.Cliente;
import com.os.model.OS;
import com.os.model.Tecnico;
import com.os.repository.OsRepository;
import com.os.services.exception.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return repository.findAll();
	}

	public OS create(@Valid OsDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OsDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
		
	}
	
	private OS fromDTO(OsDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

	
	
}
