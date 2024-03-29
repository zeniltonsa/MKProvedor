package com.mkprovedor.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mkprovedor.model.Cliente;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Cliente findById(Long id) {
		return this.entityManager.find(Cliente.class, id);
	}

	public void persist(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public void update(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	public void delete(Cliente cliente) {
		this.entityManager.remove(this.findById(cliente.getId()));
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cliente> filter(Cliente cliente) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (cliente.getNome() != null && !cliente.getNome().equals(""))
			criteria.add(Restrictions.ilike("nome", "%" + cliente.getNome() + "%"));

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cliente> findByNome(String nome) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (nome != null && !nome.equals(""))
			criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cliente> findByCpfCnpj(Cliente cliente) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (cliente.getCpfCnpj() != null && !cliente.getCpfCnpj().equals(""))
			criteria.add(Restrictions.like("cpfCnpj", "%" + cliente.getCpfCnpj() + "%"));

		criteria.add(Restrictions.and(Restrictions.sqlRestriction("`status` NOT IN('DESATIVADO')")));

		criteria.addOrder(Order.asc("nome"));
		criteria.addOrder(Order.asc("cpfCnpj"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByDesativados() {
		return entityManager
				.createQuery("SELECT m FROM Cliente m WHERE status = 'DESATIVADO' ORDER BY m.nome, m.cpfCnpj")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		return entityManager.createQuery("SELECT m FROM Cliente m ORDER BY m.nome").getResultList();
	}

	public int findByTotalClientes() {
		String total = "0";

		Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM cliente");
		Object valores = query.getSingleResult();

		if (valores != null)
			total = valores.toString();

		return Integer.parseInt(total);
	}

	public int findByTotalClientesLivres() {
		String total = "0";

		Query query = entityManager
				.createNativeQuery("SELECT COUNT(*) FROM cliente WHERE `status` NOT IN ('DESATIVADO')");
		Object valores = query.getSingleResult();

		if (valores != null)
			total = valores.toString();

		return Integer.parseInt(total);
	}

	public int findByTotalClientesBloqueados() {
		String total = "0";

		Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM cliente WHERE `status` = 'DESATIVADO'");
		Object valores = query.getSingleResult();

		if (valores != null)
			total = valores.toString();

		return Integer.parseInt(total);
	}
}