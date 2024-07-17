package com.fiap.ServEntrega.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ServEntrega.records.EntregaRecords;
import com.fiap.ServEntrega.services.interfaces.EntregaService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

	@Autowired
	private EntregaService service;

	@GetMapping
	public CompletableFuture<ResponseEntity<Object>> ListarEntregas(
			@RequestParam(value = "idCliente", required = false, defaultValue = "0") Integer idCliente) {

		try {

			List<EntregaRecords> entregas;

			if (idCliente != 0) {
				entregas = service.ListarEntregasPorIdCliente(idCliente);
			} else {
				entregas = service.ListarEntregas();
			}

			if (Objects.isNull(entregas)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(entregas));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

	@PostMapping()
	@Transactional
	public CompletableFuture<ResponseEntity<Object>> CriarCliente(@RequestBody EntregaRecords objCriarEntrega)
			throws URISyntaxException {

		try {

			EntregaRecords entrega = service.CriarEntrega(objCriarEntrega);

			if (Objects.isNull(entrega)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(entrega));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}

	}

	@PutMapping("/{idEntrega}")
	public CompletableFuture<ResponseEntity<Object>> AtualizarEntrega(@PathVariable("idEntrega") Integer idEntrega,
			@RequestParam(value = "idStatus", required = true) Integer idStatus) {

		try {

			EntregaRecords entrega = service.AtualizarEntrega(idEntrega, idStatus);

			if (Objects.isNull(entrega)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(entrega));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

}
