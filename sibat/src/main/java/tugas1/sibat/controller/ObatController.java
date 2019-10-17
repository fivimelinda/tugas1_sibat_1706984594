package tugas1.sibat.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tugas1.sibat.model.ObatModel;
import tugas1.sibat.service.ObatService;


@Controller
public class ObatController {
	
	@Qualifier("obatServiceImpl")
	@Autowired
	private ObatService obatService;
	
	@RequestMapping("/")
	public String home() { return "home";}
	
	//URL mapping view
		@RequestMapping("/obat/view-all")
		public String viewall(Model model) {
			
			//Mengambil semua objek ObatModel yang ada
			List<ObatModel> listObat = obatService.getObatList();
			//Add model restoran ke "resto" untuk dirender
			model.addAttribute("obatList", listObat);
			//return view template
			return "viewall-obat";
		}
}
