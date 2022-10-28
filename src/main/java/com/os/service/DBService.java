package com.os.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.os.domain.enuns.Prioridade;
import com.os.domain.enuns.Status;
import com.os.model.Cliente;
import com.os.model.OS;
import com.os.model.Tecnico;
import com.os.repository.ClienteRepository;
import com.os.repository.OsRepository;
import com.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private OsRepository osRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Gilmar Braga", "407.500.870-30","(88)5545-4545");
		Cliente c1 = new Cliente(null, "João Max", "185.924.950-75", "(83)9898-8454");
		Tecnico t2 = new Tecnico(null, "Aaron Masters", "196.133.380-55","(88)5545-4545");
		Cliente c2 = new Cliente(null, "Peter Jordan", "903.180.190-94", "(83)9898-8454");
		Tecnico t3 = new Tecnico(null, "Homer Simpson", "061.442.470-41","(88)5545-4545");
		Cliente c3 = new Cliente(null, "Drako Targarian", "421.146.880-46", "(83)9898-8454");
		Tecnico t4 = new Tecnico(null, "Mary Jane", "204.352.380-51","(88)5545-4545");
		Cliente c4 = new Cliente(null, "Jinxs Night", "630.327.520-60", "(83)9898-8454");
		Tecnico t5 = new Tecnico(null, "Bob Earl", "153.820.870-91","(88)5545-4545");
		Cliente c5 = new Cliente(null, "Hilda Trass", "641.814.510-03", "(83)9898-8454");
		
		OS os1 = new OS(null, Prioridade.MEDIA,"Teste",Status.ANDAMENTO,t1,c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		tecnicoRepository.saveAll(Arrays.asList(t2));
		clienteRepository.saveAll(Arrays.asList(c2));
		tecnicoRepository.saveAll(Arrays.asList(t3));
		clienteRepository.saveAll(Arrays.asList(c3));
		tecnicoRepository.saveAll(Arrays.asList(t4));
		clienteRepository.saveAll(Arrays.asList(c4));
		tecnicoRepository.saveAll(Arrays.asList(t5));
		clienteRepository.saveAll(Arrays.asList(c5));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
