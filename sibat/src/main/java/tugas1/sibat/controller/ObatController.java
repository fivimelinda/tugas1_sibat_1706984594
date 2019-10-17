package tugas1.sibat.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.service.JenisService;
import tugas1.sibat.service.ObatService;


@Controller
public class ObatController {
	
	@Qualifier("obatServiceImpl")
	@Autowired
	private ObatService obatService;
	
	@Autowired
	private JenisService jenisService;
	
	//URL mapping view all obat
	@RequestMapping("/")
	public String viewall(Model model) {
	
		//Mengambil semua objek ObatModel yang ada
		List<ObatModel> listObat = obatService.getObatList();
		//Add model restoran ke "resto" untuk dirender
		model.addAttribute("obatList", listObat);
		//return view template
		return "viewall-obat";
	}
	
	//url mapping view obat by nomor registrasi
	@RequestMapping(value ="/obat/view/{nomorRegistrasi}", method = RequestMethod.GET)
	public String viewByNoReg(@PathVariable String nomorRegistrasi, Model model) {
		ObatModel existingObat = obatService.getObatByNomorRegistrasi(nomorRegistrasi);
		JenisModel jenisObat = jenisService.getJenisByIdObat(existingObat.getIdObat());
		existingObat.setJenis(jenisObat);
		model.addAttribute("obat", existingObat);
		return "view-obat";
	}
}
