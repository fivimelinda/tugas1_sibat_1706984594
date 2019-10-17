package tugas1.sibat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1.sibat.model.ObatModel;


@Repository
public interface ObatDb extends JpaRepository<ObatModel, Long>{
	
}



