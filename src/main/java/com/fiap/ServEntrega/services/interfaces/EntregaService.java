package com.fiap.ServEntrega.services.interfaces;

import java.util.List;

import com.fiap.ServEntrega.records.EntregaRecords;

public interface EntregaService {
	
	List<EntregaRecords> ListarEntregas();

	List<EntregaRecords> ListarEntregasPorIdCliente(Integer idCliente);

	EntregaRecords CriarEntrega(EntregaRecords objCriarEntrega);
	
	EntregaRecords AtualizarEntrega(Integer idEntrega, Integer idStatus);


}
