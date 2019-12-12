package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tugas1.sibat.model.JenisModel;
import tugas1.sibat.repository.JenisDb;

@Service
@Transactional
public class JenisServiceImpl implements JenisService{
	@Autowired
	private JenisDb jenisDb;
	
	@Override
	public List<JenisModel> getJenisList(){
		return jenisDb.findAll();
	}
	
	@Override
	public Optional<JenisModel> getJenisById(Long idJenis){
		return jenisDb.findById(idJenis);
	}

}
