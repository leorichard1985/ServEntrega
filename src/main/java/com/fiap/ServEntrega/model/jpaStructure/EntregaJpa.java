package com.fiap.ServEntrega.model.jpaStructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_MOV_Entrega")
public class EntregaJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEntrega")
	public Integer idEntrega;
	
	@Column(name = "idCliente")
	public Integer idCliente;
	
	@Column(name = "idPedido")
	public Integer	idPedido;
	
	@Column(name = "idStatus")
	public Integer idStatus;

}
