package br.com.despesas.despesas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.despesas.despesas.model.Despesa;
import br.com.despesas.despesas.service.DespesaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("despesas")
public class DespesaController {
	
	private final DespesaService service;
	
    public DespesaController(DespesaService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Despesa>> getAllMensagens() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Despesa> getMensagem(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesa create(@RequestBody Despesa mensagem) {
        return service.create(mensagem);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        Despesa despesa = new Despesa();
        despesa.setId(id);

        service.delete(despesa);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Despesa update(@RequestBody Despesa despesa, @PathVariable String id) {
        Despesa updateDespesa = service.findById(id);

        updateDespesa.setCategoria(despesa.getCategoria() != null ? despesa.getCategoria() : updateDespesa.getCategoria());
        updateDespesa.setDataDespesa(despesa.getDataDespesa() != null ? despesa.getDataDespesa() : updateDespesa.getDataDespesa());
        updateDespesa.setDescricao(despesa.getDescricao() != null ? despesa.getDescricao() : updateDespesa.getDescricao());
        updateDespesa.setValor(despesa.getValor() != null ? despesa.getValor() : updateDespesa.getValor());

        return service.update(updateDespesa);
    }

}
