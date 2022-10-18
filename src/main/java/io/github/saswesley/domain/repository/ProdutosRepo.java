package io.github.saswesley.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.Produto;

public interface ProdutosRepo extends JpaRepository<Produto, Integer> {

}
