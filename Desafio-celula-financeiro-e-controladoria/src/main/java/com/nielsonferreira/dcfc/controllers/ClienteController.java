package com.nielsonferreira.dcfc.controllers;

import java.util.List;
import java.util.Optional;

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
import com.nielsonferreira.dcfc.models.Cliente;
import com.nielsonferreira.dcfc.models.PessoaFisica;
import com.nielsonferreira.dcfc.models.PessoaJuridica;
import com.nielsonferreira.dcfc.repository.ClienteRepository;
import com.nielsonferreira.dcfc.repository.PessoaFisicaRepository;
import com.nielsonferreira.dcfc.repository.PessoaJuridicaRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@PostMapping
	@RequestMapping("/pessoa-fisica")
	public ResponseEntity<Cliente> salvarPessoaFisica(@Valid @RequestBody PessoaFisica cliente, HttpServletResponse response){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PostMapping
	@RequestMapping("/pessoa-juridica")
	public ResponseEntity<Cliente> salvarPessoaJuridica(@Valid @RequestBody PessoaJuridica cliente, HttpServletResponse response){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> buscarClientePeloId(@PathVariable Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	// Atualizar Pessoa Física
	@PutMapping("pessoa-fisica/{id}")
	public ResponseEntity<PessoaFisica> atualizarPF(@PathVariable Long id, @Valid @RequestBody PessoaFisica cliente){
		PessoaFisica clienteSalvo = pessoaFisicaRepository.getOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		pessoaFisicaRepository.save(clienteSalvo);
		return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
	}
	
	// Atualizar Pessoa Jurídica
	@PutMapping("pessoa-juridica/{id}")
	public ResponseEntity<PessoaJuridica> atualizarPJ (@PathVariable Long id, @Valid @RequestBody PessoaJuridica cliente){
		PessoaJuridica clienteSalvo = pessoaJuridicaRepository.getOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		pessoaJuridicaRepository.save(clienteSalvo);
		return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
}
