package tugas1.sibat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1.sibat.model.SupplierModel;

@Repository
public interface SupplierDb extends JpaRepository<SupplierModel, Long>{

}
