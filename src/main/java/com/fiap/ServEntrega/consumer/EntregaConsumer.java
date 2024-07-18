package com.fiap.ServEntrega.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fiap.ServEntrega.services.interfaces.EntregaService;

@Component
public class EntregaConsumer {

	private final EntregaService service;

	public EntregaConsumer(EntregaService service) {
		this.service = service;
	}
	
	@Bean(name = "solicita-entrega")
	Consumer<EntregaPedidoCliente> consumer() {
		return eClientePedido -> this.service.SolicitaEntregaCliente(eClientePedido.getIdPedido(), eClientePedido.getIdCliente());
	}

}
