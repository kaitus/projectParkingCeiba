package co.ceiba.parking.persistence.repository;

import javax.persistence.EntityManager;

import co.ceiba.parking.dominio.Invoice;
import co.ceiba.parking.dominio.Vehicle;
import co.ceiba.parking.dominio.repositorio.InvoiceRepository;
import co.ceiba.parking.persistence.builder.InvoiceBuilder;
import co.ceiba.parking.persistence.entity.InvoiceEntity;
import co.ceiba.parking.persistence.repository.jpa.InvoiceRepositoryJPA;

public class InvoiceRepositotyPersistence implements InvoiceRepository {
	
	private EntityManager entityManager;
	private InvoiceRepositoryJPA invoiceRepositoryJPA;
	
	public InvoiceRepositotyPersistence(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Long getByVehicleAndInvoiceStore(String type) {
		Long countvehicleStore = invoiceRepositoryJPA.countByVehicleType(type);
		return countvehicleStore;
	}

}
