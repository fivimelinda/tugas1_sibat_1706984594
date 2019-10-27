package tugas1.sibat.service;

import java.util.List;

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
}
