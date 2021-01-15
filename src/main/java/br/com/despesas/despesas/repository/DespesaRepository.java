package br.com.despesas.despesas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.despesas.despesas.model.Despesa;

public interface DespesaRepository extends MongoRepository<Despesa, String> {

}
