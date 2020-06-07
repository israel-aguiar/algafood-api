package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

	@Query("from Pedido p"
			+ " join fetch p.cliente"
			+ " join fetch p.restaurante r"
			+ " join fetch r.cozinha")
	List<Pedido> findAll();
	
//	@Query("from Pedido p"
//			+ " join fetch p.cliente"
//			+ " join fetch p.restaurante r"
//			+ " join fetch r.cozinha"
//			+ " join fetch p.formaPagamento"
//			+ " join fetch p.enderecoEntrega e"
//			+ "	join fetch e.cidade c"
//			+ " join fetch c.estado"
//			+ " join fetch p.itens i"
//			+ " join fetch i.produto pr"
//			+ " where p.id = :id")
//	Optional<Pedido> findById(@Param("id") Long id);
}
