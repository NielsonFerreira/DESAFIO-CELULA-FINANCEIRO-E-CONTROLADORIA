package com.nielsonferreira.dcfc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nielsonferreira.dcfc.event.RecursoCriadoEvent;
import com.nielsonferreira.dcfc.models.Conta;
import com.nielsonferreira.dcfc.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Conta> ListarContas(){
		return contaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> buscarContaPeloId(@PathVariable Long id){
		Conta conta = contaRepository.getOne(id);
		return conta != null ? ResponseEntity.ok(conta) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Conta> salvarConta(@Valid @RequestBody Conta conta, HttpServletResponse response){
		Conta contaSalva = contaRepository.save(conta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, conta.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Conta> atualizarConta(@PathVariable Long id, @Valid @RequestBody Conta conta){
		Conta contaSalva = contaRepository.getOne(id);
		BeanUtils.copyProperties(conta, contaSalva, "id");
		contaRepository.save(contaSalva);
		return ResponseEntity.status(HttpStatus.OK).body(contaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerConta(@PathVariable Long id) {
		contaRepository.deleteById(id);
	}

}
