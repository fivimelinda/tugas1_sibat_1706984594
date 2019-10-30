package tugas1.sibat.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.model.SupplierModel;
import tugas1.sibat.service.GudangService;
import tugas1.sibat.service.JenisService;
import tugas1.sibat.service.ObatService;
import tugas1.sibat.service.SupplierService;

@Controller
public class ObatController {
	
	@Qualifier("obatServiceImpl")
	@Autowired
	private ObatService obatService;
	
	@Autowired
	private JenisService jenisService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private GudangService gudangService;
	
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
		ArrayList<SupplierModel> listSupplier = new ArrayList<SupplierModel>();
		listSupplier.add(new SupplierModel());
		newObat.setListSupplier(listSupplier);
		model.addAttribute("obat", newObat);
		model.addAttribute("listJenis", jenisList);
		model.addAttribute("listBentuk", bentukList);
		model.addAttribute("listSupplier", supplierList);
		return "form-add-obat";
	}
	
	//URL mapping yang digunakan untuk submit form yang telah dimasukkan pada halaman tambah obat
	@RequestMapping(value = "/obat/tambah", method = RequestMethod.POST)
	public String addRestoranSubmit(@ModelAttribute ObatModel obat, Model model) {
		obatService.addObat(obat);
		ObatModel target = obatService.getObatById(obat.getIdObat()).get();
		for (int i = 0; i < target.getListSupplier().size(); i++) {
			SupplierModel supplier = target.getListSupplier().get(i);
			if(supplier.getListObat() == null) {
				supplier.setListObat(new ArrayList<ObatModel>());		
			}
			supplier.getListObat().add(target);
			supplierService.addSupplier(supplier);
		}
		model.addAttribute("kodeObat", obat.getKodeObat());
		model.addAttribute("nama", obat.getNamaObat());
		return "add-obat";
	}
	
	@RequestMapping(value="/obat/tambah", method = RequestMethod.POST, params= {"addRow"})
    public String addRowSupplier(@ModelAttribute ObatModel obat, BindingResult bindingResult, Model model) {
        if (obat.getListSupplier() == null) {
            obat.setListSupplier(new ArrayList<SupplierModel>());
        }
        obat.getListSupplier().add(new SupplierModel());
        model.addAttribute("obat", obat);
        
		List<JenisModel> jenisList = jenisService.getJenisList();
		List<String> bentukList = Arrays.asList("Cairan", "Kapsul", "Tablet");
        List<SupplierModel> supplierList = supplierService.getSupplierList();
		model.addAttribute("listJenis", jenisList);
		model.addAttribute("listBentuk", bentukList);
        model.addAttribute("listSupplier", supplierList);

        return "form-add-obat";
    }
		
	@RequestMapping(value ="obat/ubah", method = RequestMethod.GET)
	public String changeObat(@RequestParam("id") String idObat, Model model) {
		ObatModel existing = obatService.getObatById(Long.valueOf(idObat)).get();
		List<String> bentukList = Arrays.asList("Cairan", "Kapsul", "Tablet");
		model.addAttribute("obat", existing);
		model.addAttribute("listBentuk", bentukList);
		return "form-change-obat";
		
	}
		
	@RequestMapping(value ="obat/ubah", method = RequestMethod.POST)
	public String changeObatSubmit(@RequestParam("id") Long idObat, @ModelAttribute ObatModel obat, Model model) {
		ObatModel newDataObat = obatService.changeObat(obat);
		model.addAttribute("obat", newDataObat);
		return "change-obat";
		
	}
	
	@RequestMapping(value = "obat/filter")
	public String filterView(
			@RequestParam(value ="idGudang", required = false) Long idGudang, 
			@RequestParam(value ="idSupplier", required = false) Long idSupplier,
			@RequestParam(value ="idJenis", required = false) Long idJenis,
			Model model) {
		GudangModel gudang;
		SupplierModel supplier;
		JenisModel jenis;
		List<ObatModel> obatList = obatService.getObatList();
		List<GudangModel> gudangList = gudangService.getGudangList();
		List<SupplierModel> supplierList = supplierService.getSupplierList();
		List<JenisModel> jenisList = jenisService.getJenisList();
		String namaGudang = "";
		String namaSupplier = "";
		String namaJenis = "";
		if (idGudang != null) {
			gudang = gudangService.getGudangById(idGudang).get();
			namaGudang = gudang.getNamaGudang();
			if(idSupplier != null) {
				supplier = supplierService.getSupplierById(idSupplier).get();
				namaSupplier = supplier.getNamaSupplier();
				if(idJenis != null) {
					jenis = jenisService.getJenisById(idJenis).get();
					namaJenis = jenis.getNamaJenis();
					obatList = obatService.getListObatByGudangAndSupplierAndJenis(gudang, supplier, jenis); //gudang dan supplier dan jenis
				} else {
					obatList = obatService.getListObatByGudangAndSupplier(gudang, supplier); //gudang dan supplier
				}
				
			} else if(idJenis != null) { 
				jenis = jenisService.getJenisById(idJenis).get();
				namaJenis = jenis.getNamaJenis();
				obatList = obatService.getListObatByGudangAndJenis(gudang, jenis); //gudang dan jenis
			} else {
				obatList = obatService.getObatByIdGudang(idGudang); //gudang
			}
		} else if (idSupplier != null) {
			supplier = supplierService.getSupplierById(idSupplier).get();
			namaSupplier = supplier.getNamaSupplier();
			if (idJenis != null) {
				jenis = jenisService.getJenisById(idJenis).get();
				namaJenis = jenis.getNamaJenis();
				obatList = obatService.getListObatBySupplierAndJenis(supplier, jenis); //supplier dan jenis
			} else {
				obatList = obatService.getListObatBySupplier(supplier); //supplier
			}
		} else if (idJenis != null) {
			jenis = jenisService.getJenisById(idJenis).get();
			namaJenis = jenis.getNamaJenis();
			obatList = obatService.getListObatByJenis(jenis); //jenis
		}
		
		model.addAttribute("listObat", obatList);
		model.addAttribute("listGudang", gudangList);
		model.addAttribute("listSupplier", supplierList);
		model.addAttribute("listJenis", jenisList);
		model.addAttribute("supplierName", namaSupplier);
		model.addAttribute("gudangName", namaGudang);
		model.addAttribute("jenisName", namaJenis);
		return "filter";
	}
	
	@RequestMapping("/bonus")
	public String bonus(Model model) {
		List<ObatModel> listObat = obatService.getObatList();
		model.addAttribute("obatList", listObat);
		return "bonus";
	}
	

}
