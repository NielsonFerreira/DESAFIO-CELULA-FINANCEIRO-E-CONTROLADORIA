package com.nielsonferreira.dcfc.resource;

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
import com.nielsonferreira.dcfc.model.Cliente;
import com.nielsonferreira.dcfc.model.PessoaFisica;
import com.nielsonferreira.dcfc.model.PessoaJuridica;
import com.nielsonferreira.dcfc.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@PostMapping
	@RequestMapping("/pessoafisica")
	public ResponseEntity<Cliente> criarPessoaFisica(@Valid @RequestBody PessoaFisica cliente, HttpServletResponse response){
		
		Cliente clienteSalvo = clienteRepository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PostMapping
	@RequestMapping("/pessoajuridica")
	public ResponseEntity<Cliente> criarPessoaJuridica(@Valid @RequestBody PessoaJuridica cliente, HttpServletResponse response){
		
		Cliente clienteSalvo = clienteRepository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> buscarClientePeloId(@PathVariable Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("pessoafisica/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisica cliente){
		Cliente clienteSalvo = clienteRepository.getOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		clienteRepository.save(clienteSalvo);
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
}
