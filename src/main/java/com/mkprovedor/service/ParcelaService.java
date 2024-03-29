package com.mkprovedor.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.mkprovedor.model.Mensalidade;
import com.mkprovedor.model.Parcela;
import com.mkprovedor.repository.Parcelas;
import com.mkprovedor.util.jpa.Transactional;

public class ParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Parcelas parcelas;

	@Transactional
	public void createNew(Parcela parcela) {
		parcelas.persist(parcela);
	}

	@Transactional
	public void update(Parcela parcela) {
		parcelas.update(parcela);
	}

	@Transactional
	public Parcela findById(Long id) {
		return parcelas.findById(id);
	}

	@Transactional
	public void deleteByMensalidade(Mensalidade mensalidade) {
		parcelas.deleteByMensalidade(mensalidade);
	}

	@Transactional
	public List<Parcela> findByMensalidade(Mensalidade mensalidade) {
		return parcelas.findByMensalidade(mensalidade);
	}

	@Transactional
	public void deleteMensalidadeAndParcela(Mensalidade mensalidade) {
		parcelas.deleteMensalidadeAndParcela(mensalidade);
	}

	@Transactional
	public List<Parcela> findByParcelaVencida(Mensalidade mensalidade) {
		return parcelas.findByParcelaVencida(mensalidade);
	}

	@Transactional
	public int findByTotalParcelas() {
		return parcelas.findByTotalParcelas();
	}

	@Transactional
	public int findByTotalParcelasAVencer() {
		return parcelas.findByTotalParcelasAVencer();
	}

	@Transactional
	public int findByTotalParcelasVencidas() {
		return parcelas.findByTotalParcelasVencidas();
	}

	public double findByParcelaVencidaValor(Parcela parcela) {
 		return parcelas.findByParcelaVencidaValor(parcela);
	}

}
