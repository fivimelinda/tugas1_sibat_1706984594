package tugas1.sibat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.service.GudangService;
import tugas1.sibat.service.ObatService;


@Controller
public class GudangController {
	@Autowired
	private GudangService gudangService;
	
	@Autowired
	private ObatService obatService;
	
	
	//URL mapping view all gudang
		@RequestMapping("/gudang")
		public String viewall(Model model) {
		
			//Mengambil semua objek GudangModel yang ada
			List<GudangModel> listGudang = gudangService.getGudangList();
			model.addAttribute("gudangList", listGudang);
			//return view template
			return "viewall-gudang";
		}
		
		@RequestMapping(path ="gudang/view")
		public String viewDetailGudang(
				@RequestParam("idGudang") String idGudang, Model model) {
			GudangModel existingGudang = gudangService.getGudangById(Long.valueOf(idGudang)).get();
			List<ObatModel> obatList = obatService.getObatByIdGudang(Long.valueOf(idGudang));
			List<ObatModel> allObat = obatService.getObatList();
			model.addAttribute("gudang", existingGudang);
			model.addAttribute("listObat", obatList);
			model.addAttribute("allObat", allObat);
			model.addAttribute("jumlahObat", obatList.size());
			return "view-detail-gudang";
		}
		
		@RequestMapping(value = "gudang/tambah", method = RequestMethod.GET)
		public String tambahGudangFormPage(Model model) {
			GudangModel newGudang = new GudangModel();
			model.addAttribute("gudang", newGudang);
			return "form-add-gudang";
		}
		
		@RequestMapping(value = "gudang/tambah", method = RequestMethod.POST)
		public String tambahGudangSubmitSubmit(@ModelAttribute GudangModel gudang, Model model) {
			gudangService.addGudang(gudang);
			model.addAttribute("alamat", gudang.getAlamatGudang());
			model.addAttribute("nama", gudang.getNamaGudang());
			return "add-gudang";
		}
		
		@RequestMapping(path="gudang/hapus/{idGudang}")
		public String deleteGudang(@PathVariable("idGudang") String idGudang, Model model) {
				List<ObatModel> cekObat = obatService.getObatByIdGudang(Long.valueOf(idGudang));
				GudangModel target = gudangService.getGudangById(Long.valueOf(idGudang)).get();
				model.addAttribute("nama", target.getNamaGudang());
				boolean cekDelete = false;
				if(cekObat.isEmpty()) {
					gudangService.deleteGudang(target);
					cekDelete = true;
				}
				model.addAttribute("isDelete", cekDelete);
				return"delete-gudang";			
		}
		
		@RequestMapping(value = "gudang/tambah-obat")
		public String tambahObatView(
				@RequestParam("idObat") String idObat, @ModelAttribute GudangModel target, Model model) {
			GudangModel gudang = gudangService.getGudangById(target.getIdGudang()).get();
			ObatModel obat = obatService.getObatById(Long.valueOf(idObat)).get();
			if (target.getListObat() == null) {
				target.setListObat(new ArrayList<ObatModel>());
			}
			gudang.getListObat().add(obat);
			gudangService.addGudang(gudang);
			List<ObatModel> obatList = obatService.getObatByIdGudang(gudang.getIdGudang());
			List<ObatModel> allObat = obatService.getObatList();
			model.addAttribute("gudang", gudang);
			model.addAttribute("listObat", obatList);
			model.addAttribute("allObat", allObat);
			model.addAttribute("jumlahObat", obatList.size());
			return "view-detail-gudang";
		}
		
		@RequestMapping("/cari")
		public String cari(Model model) {
		
			//Mengambil semua objek GudangModel yang ada
			List<GudangModel> gudangList = gudangService.getGudangList();
			model.addAttribute("listGudang", gudangList);
			//return view template
			return "cari";
		}
		
		@RequestMapping(value ="gudang/expired-obat", method = RequestMethod.GET)
		public String obatExpired(
				@RequestParam("idGudang") String idGudang, Model model) {
			GudangModel gudangTarget = gudangService.getGudangById(Long.valueOf(idGudang)).get();
			List<ObatModel> listObat = gudangTarget.getListObat();
			List<ObatModel> expiredList = obatService.cekExpired(listObat);			
			//return view template
			model.addAttribute("expiredList", expiredList);
			return "view-expired";
		}
		
}
