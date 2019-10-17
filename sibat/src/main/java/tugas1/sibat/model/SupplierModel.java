package tugas1.sibat.model;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="supplier")
public class SupplierModel implements Serializable {
	@Id
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSupplier;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "nama", nullable = false)
	private String namaSupplier;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "alamat", nullable = false)
	private String alamatSupplier;
	
	@NotNull
	@Column(name= "nomor_telepon", nullable = false)
	private Long nomorTelepon;
	
	@ManyToMany
	@JoinTable(
			name= "obat_supplier",
			joinColumns = @JoinColumn(name = "id_supplier"),
			inverseJoinColumns = @JoinColumn(name = "id_obat"))
	private List<ObatModel> listObat;

	public Long getIdSupplier() {
		return idSupplier;
	}
	
	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getNamaSupplier() {
		return namaSupplier;
	}

	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}

	public String getAlamatSupplier() {
		return alamatSupplier;
	}

	public void setAlamatSupplier(String alamatSupplier) {
		this.alamatSupplier = alamatSupplier;
	}

	public Long getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(Long nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public List<ObatModel> getListObat() {
		return listObat;
	}

	public void setListObat(List<ObatModel> listObat) {
		this.listObat = listObat;
	}


}
