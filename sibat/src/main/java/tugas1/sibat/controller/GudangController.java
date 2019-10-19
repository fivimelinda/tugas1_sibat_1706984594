package tugas1.sibat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.service.GudangService;


@Controller
public class GudangController {
	@Autowired
	private GudangService gudangService;
	
	//URL mapping view all gudang
		@RequestMapping("/gudang")
		public String viewall(Model model) {
		
			//Mengambil semua objek GudangModel yang ada
			List<GudangModel> listGudang = gudangService.getGudangList();
			model.addAttribute("gudangList", listGudang);
			//return view template
			return "viewall-gudang";
		}
		
		//url mapping view obat by nomor registrasi
//		@RequestMapping(value ="/obat/view/{nomorRegistrasi}", method = RequestMethod.GET)
//		public String viewByNoReg(@PathVariable String nomorRegistrasi, Model model) {
//			ObatModel existingObat = obatService.getObatByNomorRegistrasi(nomorRegistrasi);
//			model.addAttribute("obat", existingObat);
//			return "view-obat";

}
