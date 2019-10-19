package tugas1.sibat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1.sibat.model.GudangModel;

@Repository
public interface GudangDb extends JpaRepository<GudangModel, Long>{
//	List<GudangModel> findByObatIdObat(Long idObat);
}
