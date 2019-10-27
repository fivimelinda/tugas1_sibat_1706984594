package tugas1.sibat.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.RestoranModel;
import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.model.SupplierModel;
import tugas1.sibat.service.JenisService;
import tugas1.sibat.service.ObatService;
import tugas1.sibat.service.SupplierService;

@Controller
public class ObatController {
	
//	@Qualifier("obatServiceImpl")
	@Autowired
	private ObatService obatService;
	
	@Autowired
	private JenisService jenisService;
	
	@Autowired
	private SupplierService supplierService;
	
	//URL mapping view all obat
	@RequestMapping("/")
	public String viewall(Model model) {
	
		//Mengambil semua objek ObatModel yang ada
		List<ObatModel> listObat = obatService.getObatList();

		model.addAttribute("obatList", listObat);
		//return view template
		return "viewall-obat";
	}
	
	//url mapping view obat by nomor registrasi
	@RequestMapping("obat/view")
	public String viewByNoReg(
			@RequestParam("noReg") String noReg, Model model) {
		ObatModel existingObat = obatService.getObatByNomorRegistrasi(noReg);
		model.addAttribute("obat", existingObat);
		return "view-obat";
	}
	
	//URL mapping yang digunakan untuk mengakses halaman tambah obat
	@RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
	public String tambahObatFormPage(Model model) {
		ObatModel newObat = new ObatModel();
		List<JenisModel> jenisList = jenisService.getJenisList();
		List<String> bentukList = Arrays.asList("Cairan", "Kapsul", "Tablet");
		List<SupplierModel> supplierList = supplierService.getSupplierList();
		model.addAttribute("obat", newObat);
		model.addAttribute("listJenis", jenisList);
		model.addAttribute("listBentuk", bentukList);
		model.addAttribute("existsSupplier", supplierList);
		return "form-add-obat";
	}
	
	//URL mapping yang digunakan untuk submit form yang telah dimasukkan pada halaman tambah obat
		@RequestMapping(value = "/obat/tambah", method = RequestMethod.POST)
		public String addRestoranSubmit(@ModelAttribute ObatModel obat, Model model) {
			String kode = obatService.generateKodeObat(obat);
			obat.setKodeObat(kode);
			obatService.addObat(obat);
			model.addAttribute("kodeObat", obat.getKodeObat());
			model.addAttribute("nama", obat.getNamaObat());
			return "add-obat";
		}
		
//		@RequestMapping(value ="restoran/change/{idRestoran}", method = RequestMethod.GET)
//		public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
//			//mengambil existing data restoran
//			RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//			model.addAttribute("restoran", existingRestoran);
//			return "form-change-restoran";
//			
//		}
	
}
