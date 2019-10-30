package tugas1.sibat.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1.sibat.model.GudangModel;
import tugas1.sibat.model.JenisModel;
import tugas1.sibat.model.ObatModel;
import tugas1.sibat.model.SupplierModel;


@Repository
public interface ObatDb extends JpaRepository<ObatModel, Long>{
	Optional<ObatModel> findByIdObat(Long idObat);
	ObatModel findByNomorRegistrasi(String nomorReg);
	List<ObatModel> findByListGudangIdGudang(Long idGudang);
	List<ObatModel> findByListSupplierIn(SupplierModel supplier);
	List<ObatModel> findByJenisObatIn(JenisModel jenis);
	List<ObatModel> findByListGudangInAndListSupplierIn(GudangModel gudang, SupplierModel supplier);
	List<ObatModel> findByListGudangInAndJenisObatIn(GudangModel gudang, JenisModel jenis);
	List<ObatModel> findByListSupplierInAndJenisObatIn(SupplierModel supplier, JenisModel jenis);
	List<ObatModel> findByListGudangInAndListSupplierInAndJenisObatIn(GudangModel gudang, SupplierModel supplier, JenisModel jenis);
}



