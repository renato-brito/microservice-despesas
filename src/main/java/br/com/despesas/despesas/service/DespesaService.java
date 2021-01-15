package br.com.despesas.despesas.service;

import java.util.List;

import br.com.despesas.despesas.model.Despesa;

public interface DespesaService {
	
    List<Despesa> findAll();

    Despesa findById(String id);

    Despesa create(Despesa despesa);

    Despesa update(Despesa despesa);

    void delete(Despesa despesa);

}
