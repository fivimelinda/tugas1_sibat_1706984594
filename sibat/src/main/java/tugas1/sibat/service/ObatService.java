package tugas1.sibat.service;

import java.util.List;

import tugas1.sibat.model.ObatModel;
public interface ObatService {
	List<ObatModel> getObatList();
	
	ObatModel getObatByNomorRegistrasi(String nomorReg);
}
