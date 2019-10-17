package tugas1.sibat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tugas1.sibat.model.ObatModel;
import tugas1.sibat.repository.ObatDb;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
	@Autowired
	private ObatDb obatDb;
	
	@Override
	public List<ObatModel> getObatList(){
		return obatDb.findAll();
	}
	
	@Override
	public ObatModel getObatByNomorRegistrasi(String nomorReg) {
		return obatDb.findByNomorRegistrasi(nomorReg);
	}
}
