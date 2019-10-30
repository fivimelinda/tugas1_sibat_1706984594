package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.model.SupplierModel;
public interface ObatService {
	void addObat(ObatModel obat);
	
	List<ObatModel> getObatList();
	
	Optional<ObatModel> getObatById(Long idObat);
	
	ObatModel getObatByNomorRegistrasi(String nomorReg);
	
	String generateKodeObat(ObatModel obat);
	
	ObatModel changeObat(ObatModel obat);
	
	List<ObatModel> getObatByIdGudang(Long idGudang);
	void addGudang(ObatModel obat);
	List<ObatModel> cekExpired(List<ObatModel> list);
	List<ObatModel> getListObatByGudangAndSupplier(GudangModel gudang, SupplierModel supplier);
	List<ObatModel> getListObatByGudangAndJenis(GudangModel gudang, JenisModel jenis);
	List<ObatModel> getListObatBySupplierAndJenis(SupplierModel supplier, JenisModel jenis);
	List<ObatModel> getListObatBySupplier(SupplierModel supplier);
	List<ObatModel> getListObatByJenis(JenisModel jenis);
	List<ObatModel> getListObatByGudangAndSupplierAndJenis(GudangModel gudang, SupplierModel supplier, JenisModel jenis);
}
