package tugas1.sibat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.repository.JenisDb;
import tugas1.sibat.repository.ObatDb;

public class JenisServiceImpl implements JenisService{
	@Autowired
	private JenisDb jenisDb;
	
	@Override
	public JenisModel getJenisByIdObat(Long idObat) {
		return jenisDb.findJenisByIdObat(idObat);
	}
}
