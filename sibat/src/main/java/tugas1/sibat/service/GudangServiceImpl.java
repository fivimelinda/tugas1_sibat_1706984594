package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.repository.GudangDb;
import tugas1.sibat.repository.ObatDb;

@Service
@Transactional
public class GudangServiceImpl implements GudangService {
	@Autowired
	private GudangDb gudangDb;
	
	@Override
	public List<GudangModel> getGudangList(){
		return gudangDb.findAll();
	}
	
	@Override
	public Optional<GudangModel> getGudangById(Long idGudang) {
		return gudangDb.findByIdGudang(idGudang);
	}
	
	@Override
	public GudangModel addGudang(GudangModel gudang) {
		return gudangDb.save(gudang);
	}
	
	@Override
	public void deleteGudang(GudangModel gudang) {
		gudangDb.delete(gudang);
		
	}
	
	@Override
	public void addObat(GudangModel gudang) {
//		gudang.getListObat().add(obat);
//		obat.getListGudang().add(gudang);
		gudangDb.save(gudang);
//		obatDb.save(obat);
	}
}
