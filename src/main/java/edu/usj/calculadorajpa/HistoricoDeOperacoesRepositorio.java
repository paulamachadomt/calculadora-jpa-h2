package edu.usj.calculadorajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HistoricoDeOperacoesRepositorio extends CrudRepository<HistoricoDeOperacoes, Long> {
    List<HistoricoDeOperacoes> findAll();
}
