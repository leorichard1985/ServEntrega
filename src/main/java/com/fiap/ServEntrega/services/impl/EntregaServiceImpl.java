package com.fiap.ServEntrega.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fiap.ServEntrega.model.jpaStructure.EntregaJpa;
import com.fiap.ServEntrega.records.EntregaRecords;
import com.fiap.ServEntrega.repository.EntregaRepository;
import com.fiap.ServEntrega.services.interfaces.EntregaService;

@Service
public class EntregaServiceImpl implements EntregaService {

	private EntregaRepository repository;

	public EntregaServiceImpl(EntregaRepository repository) {
		this.repository = repository;
	}

	@Override
	public EntregaRecords AtualizarEntrega(Integer idEntrega, Integer idStatus) {

		Optional<EntregaJpa> optJpa = repository.findById(idEntrega);

		if (optJpa.isEmpty()) {

			return null;

		} else {

			optJpa.get().idStatus = idStatus;

			EntregaJpa optJpaPreSaved = optJpa.get();

			EntregaJpa optJpaSaved = repository.save(optJpaPreSaved);

			return new EntregaRecords(optJpaSaved.idEntrega, optJpaSaved.idCliente, optJpaSaved.idPedido,
					optJpaSaved.idStatus);

		}
	}

	@Override
	public List<EntregaRecords> ListarEntregasPorIdCliente(Integer idCliente) {

		List<EntregaJpa> lstJpa = repository.BuscarEntregaPorIdCliente(idCliente);

		if (lstJpa.isEmpty()) {

			return null;

		} else {

			List<EntregaRecords> lstEntrega = new ArrayList<>();

			for (int i = 0; i < lstJpa.size() - 1; i++) {

				EntregaRecords itemEntrega = new EntregaRecords(lstJpa.get(i).idEntrega, lstJpa.get(i).idCliente,
						lstJpa.get(i).idPedido, lstJpa.get(i).idStatus);

				lstEntrega.add(itemEntrega);

			}

			return lstEntrega;

		}
	}

	@Override
	public EntregaRecords CriarEntrega(EntregaRecords objCriarEntrega) {
		EntregaJpa entregaJpa = new EntregaJpa();

		entregaJpa.idCliente = objCriarEntrega.idCliente();
		entregaJpa.idPedido = objCriarEntrega.idPedido();
		entregaJpa.idStatus = 1;

		EntregaJpa optJpaSaved = repository.save(entregaJpa);

		return new EntregaRecords(optJpaSaved.idEntrega, optJpaSaved.idCliente, optJpaSaved.idPedido,
				optJpaSaved.idStatus);
	}

	@Override
	public List<EntregaRecords> ListarEntregas() {

		List<EntregaJpa> lstJpa = repository.findAll();

		if (lstJpa.isEmpty()) {

			return null;

		} else {

			List<EntregaRecords> lstEntrega = new ArrayList<>();

			for (int i = 0; i < lstJpa.size() - 1; i++) {

				EntregaRecords itemEntrega = new EntregaRecords(lstJpa.get(i).idEntrega, lstJpa.get(i).idCliente,
						lstJpa.get(i).idPedido, lstJpa.get(i).idStatus);

				lstEntrega.add(itemEntrega);

			}

			return lstEntrega;

		}
	}

}
