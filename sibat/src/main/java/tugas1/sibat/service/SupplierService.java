package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import tugas1.sibat.model.SupplierModel;

public interface SupplierService {
	List<SupplierModel> getSupplierList();
	void addSupplier(SupplierModel supplier);
	Optional<SupplierModel> getSupplierById(Long id);
}
