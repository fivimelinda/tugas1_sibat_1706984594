package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.ObatModel;

public interface GudangService {
	List<GudangModel> getGudangList();
	Optional<GudangModel> getGudangById(Long idGudang);
	GudangModel addGudang(GudangModel gudang);
	void deleteGudang(GudangModel gudang);
	void addObat(GudangModel Gudang);
}
