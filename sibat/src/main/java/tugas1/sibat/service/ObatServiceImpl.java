package tugas1.sibat.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
	public void addObat(ObatModel obat) {
		obatDb.save(obat);
	}
	
	@Override
	public ObatModel getObatByNomorRegistrasi(String nomorReg) {
		return obatDb.findByNomorRegistrasi(nomorReg);
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
		System.out.println(obat.getTanggalTerbit());
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
}
