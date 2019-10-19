package tugas1.sibat.service;

import java.util.List;

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
}
