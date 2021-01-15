package br.com.despesas.despesas.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.despesas.despesas.model.Despesa;
import br.com.despesas.despesas.repository.DespesaRepository;

@Service
public class DespesaServiceImpl implements DespesaService{
	
	private final DespesaRepository repository;
	
	public DespesaServiceImpl(DespesaRepository repository) {
		this.repository = repository;
	}

	public List<Despesa> findAll() {
		return repository.findAll();
	}

	public Despesa findById(String id) {
		return getDespesaById(id);
	}

	public Despesa create(Despesa despesa) {
		return repository.save(despesa);
	}

	public Despesa update(Despesa despesa) {
		return repository.save(despesa);
	}

	public void delete(Despesa despesa) {
		repository.delete(despesa);
	}

    private Despesa getDespesaById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
	

}
