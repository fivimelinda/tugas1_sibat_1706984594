package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tugas1.sibat.model.SupplierModel;
import tugas1.sibat.repository.SupplierDb;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	private SupplierDb supplierDb;
	
	@Override
	public List<SupplierModel> getSupplierList(){
		return supplierDb.findAll();
	}
	
	@Override
	public void addSupplier(SupplierModel supplier) {
		supplierDb.save(supplier);
	}
	
	@Override
	public Optional<SupplierModel> getSupplierById(Long idSupplier) {
		return supplierDb.findById(idSupplier);
	}
}
