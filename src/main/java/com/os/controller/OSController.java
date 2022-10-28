package com.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.os.dto.OsDTO;
import com.os.service.OsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OSController {

	@Autowired
	private OsService service; 
	
	@GetMapping(value = "{id}")
	public ResponseEntity<OsDTO>findById(@PathVariable Integer id){
		OsDTO obj = new OsDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<OsDTO>>findAll(){
		List<OsDTO> list = service.findAll().stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<OsDTO>create(@Valid @RequestBody OsDTO obj){
		obj  = new OsDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<OsDTO>update(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
		
	}
}	
	
	
	
	
	
	