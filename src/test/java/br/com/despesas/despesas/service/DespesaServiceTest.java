package br.com.despesas.despesas.service;

import br.com.despesas.despesas.model.Despesa;
import br.com.despesas.despesas.repository.DespesaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class DespesaServiceTest {

    DespesaService service;

    @MockBean
    DespesaRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new DespesaServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve criar uma despesa")
    public void saveDespesaTest() {
        // cenário
        Despesa despesa = createValidDespesa();
        Mockito.when(repository.existsById(Mockito.anyString())).thenReturn(false);

        Mockito.when(repository.save(despesa)).thenReturn(Despesa.builder()
                .id(String.valueOf(1))
                .dataDespesa(LocalDate.now())
                .descricao("almoço")
                .categoria("alimentação")
                .valor(BigDecimal.valueOf(30.0))
                .build());

        // execução
        Despesa savedDespesa = service.create(despesa);

        // verificação
        Assertions.assertThat(savedDespesa.getId()).isNotNull();
        Assertions.assertThat(savedDespesa.getValor()).isEqualTo(BigDecimal.valueOf(30.0));
        Assertions.assertThat(savedDespesa.getDescricao()).isEqualTo("almoço");
        Assertions.assertThat(savedDespesa.getCategoria()).isEqualTo("alimentação");
        Assertions.assertThat(savedDespesa.getDataDespesa()).isEqualTo(LocalDate.now());
    }

    public Despesa createValidDespesa() {
        return Despesa.builder()
                .dataDespesa(LocalDate.now())
                .categoria("alimentação")
                .descricao("almoço")
                .valor(BigDecimal.valueOf(30.0))
                .build();
    }

    @Test
    @DisplayName("Deve obter despesa por Id")
    public void getByIdTest() {
        // cenário
        String id = "1";

        Despesa despesa = createValidDespesa();
        despesa.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(despesa));

        // execução
        Optional<Despesa> foundDespesa = Optional.ofNullable(service.findById(id));

        // verificação
        Assertions.assertThat(foundDespesa.isPresent()).isTrue();
        Assertions.assertThat(foundDespesa.get().getValor()).isEqualTo(BigDecimal.valueOf(30.0));
        Assertions.assertThat(foundDespesa.get().getDescricao()).isEqualTo("almoço");
        Assertions.assertThat(foundDespesa.get().getCategoria()).isEqualTo("alimentação");
        Assertions.assertThat(foundDespesa.get().getDataDespesa()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Deve deletar uma despesa")
    public void deleteDespesaTest() {
        // cenário
        Despesa despesa = Despesa.builder().id("1").build();

        // execução
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> service.delete(despesa));

        // verificação
        Mockito.verify(repository, Mockito.times(1)).delete(despesa);
    }

    @Test
    @DisplayName("Deve atualizar uma despesa")
    public void updateDespesaTest() {
        // cenário
        String id = "1";

        // despesa a atualizar
        Despesa updatingDespesa = Despesa.builder().id(id).build();

        // simulação
        Despesa updatedDespesa = createValidDespesa();
        updatedDespesa.setId(id);

        Mockito.when(repository.save(updatingDespesa)).thenReturn(updatedDespesa);

        // execução
        Despesa despesa = service.update(updatingDespesa);

        // verificações
        Assertions.assertThat(despesa.getId()).isEqualTo(updatedDespesa.getId());
        Assertions.assertThat(despesa.getValor()).isEqualTo(updatedDespesa.getValor());
        Assertions.assertThat(despesa.getDescricao()).isEqualTo(updatedDespesa.getDescricao());
        Assertions.assertThat(despesa.getCategoria()).isEqualTo(updatedDespesa.getCategoria());
        Assertions.assertThat(despesa.getDataDespesa()).isEqualTo(updatedDespesa.getDataDespesa());
    }
}
