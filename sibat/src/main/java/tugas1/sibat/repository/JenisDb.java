package tugas1.sibat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;

public interface JenisDb extends JpaRepository<ObatModel, Long>{
	JenisModel findJenisByIdObat(Long idObat);
}
