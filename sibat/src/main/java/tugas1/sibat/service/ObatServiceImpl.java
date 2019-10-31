package tugas1.sibat.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.model.SupplierModel;
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
	public void addObat(ObatModel obat) {
		String kode = generateKodeObat(obat);
		List<String> listKode = new ArrayList<String>();
		List<ObatModel> listObat = obatDb.findAll();
		for(int i = 0; i<listObat.size(); i++) {
			listKode.add(listObat.get(i).getKodeObat());
		}
		while(listKode.contains(kode)) {
			kode = generateKodeObat(obat);
		}
		obat.setKodeObat(kode);
		obatDb.save(obat);
	}
	
	@Override
	public Optional<ObatModel> getObatById(Long idObat) {
		return obatDb.findByIdObat(idObat);
	}
	
	@Override
	public ObatModel getObatByNomorRegistrasi(String nomorReg) {
		return obatDb.findByNomorRegistrasi(nomorReg);
	}
	
	@Override
	public List<ObatModel> getObatByIdGudang(Long idGudang){
		return obatDb.findByListGudangIdGudang(idGudang);
	}
	
	@Override
	public String generateKodeObat(ObatModel obat) {
		String kode = "";
		if(obat.getJenisObat().getNamaJenis().equals("Generik")) {
			kode+= 1;
		} else {
			kode+= 2;
		}
		
		if(obat.getBentukObat().equals("Cairan")) {
			kode+= "01";
		} else if (obat.getBentukObat().equals("Kapsul")) {
			kode+= "02";
		} else {
			kode+= "03";
		}
		int tahunInput = java.time.LocalDate.now().getYear();
		kode +=  tahunInput;
        String [] tahunTerbit = obat.getTanggalTerbit().toString().split(" ");
		int tahunPlus = Integer.parseInt(tahunTerbit[tahunTerbit.length-1]) + 5;
        kode += String.valueOf(tahunPlus);

        Random capital = new Random();
        char c = (char) (capital.nextInt(26) + 'A');
        char d = (char) (capital.nextInt(26) + 'A');
        kode += c;
        kode += d;
        return kode;
	}
	
	@Override
	public ObatModel changeObat(ObatModel newObat) {
		ObatModel targetObat = obatDb.findByIdObat(newObat.getIdObat()).get();
		try {
			targetObat.setNamaObat(newObat.getNamaObat());
			targetObat.setHargaObat((double)newObat.getHargaObat());
			targetObat.setTanggalTerbit(newObat.getTanggalTerbit());
			targetObat.setBentukObat(newObat.getBentukObat());
			targetObat.setKodeObat(generateKodeObat(targetObat));
			obatDb.save(targetObat);
			return targetObat;
		} catch (NullPointerException nullException) {
			return null;
		}
	}
	
	@Override
	public void addGudang(ObatModel obat) {
		 obatDb.save(obat);
	}
	
	@Override
	public List<ObatModel> cekExpired(List<ObatModel> listObat){
		List<ObatModel> expiredList = new ArrayList<ObatModel>();
		for(int i = 0; i < listObat.size(); i++) {
			String [] tahunTerbit = listObat.get(i).getTanggalTerbit().toString().split("-");
			int tahun = Integer.parseInt(tahunTerbit[0]);
			int yearNow = java.time.LocalDate.now().getYear();
			if ( yearNow-tahun > 5) {
				expiredList.add(listObat.get(i));
			}
		}
		return expiredList;
	}
	
	@Override
	public List<ObatModel> getListObatByGudangAndSupplierAndJenis(GudangModel gudang, SupplierModel supplier, JenisModel jenis){
		return obatDb.findByListGudangInAndListSupplierInAndJenisObatIn(gudang, supplier, jenis);
	}
	
	@Override
	public List<ObatModel> getListObatByGudangAndSupplier(GudangModel gudang, SupplierModel supplier){
		return obatDb.findByListGudangInAndListSupplierIn(gudang, supplier);
	}
	
	@Override
	public List<ObatModel> getListObatByGudangAndJenis(GudangModel gudang, JenisModel jenis){
		return obatDb.findByListGudangInAndJenisObatIn(gudang, jenis);
	}
	
	@Override
	public List<ObatModel> getListObatBySupplierAndJenis(SupplierModel supplier, JenisModel jenis){
		return obatDb.findByListSupplierInAndJenisObatIn(supplier, jenis);
	}
	
	@Override
	public List<ObatModel> getListObatBySupplier(SupplierModel supplier){
		return obatDb.findByListSupplierIn(supplier);
	}
	
	@Override
	public List<ObatModel> getListObatByJenis(JenisModel jenis){
		return obatDb.findByJenisObatIn(jenis);
	}
	
}
