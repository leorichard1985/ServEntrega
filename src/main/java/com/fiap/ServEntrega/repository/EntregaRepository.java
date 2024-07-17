package com.fiap.ServEntrega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.ServEntrega.model.jpaStructure.EntregaJpa;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaJpa, Integer> {
	
	@Query(value = "SELECT entrega FROM EntregaJpa entrega WHERE entrega.idCliente = :idCliente")
	public List<EntregaJpa> BuscarEntregaPorIdCliente(@Param(value = "idCliente") Integer idCliente);
	
}
