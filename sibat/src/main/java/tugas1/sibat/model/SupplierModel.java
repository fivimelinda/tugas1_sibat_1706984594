package tugas1.sibat.model;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="supplier")
public class SupplierModel implements Serializable{
	@Id
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSupplier;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "nama_supplier", nullable = false)
	private String namaSupplier;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "alamat_supplier", nullable = false)
	private String alamatSupplier;
	
	@NotNull
	@Column(name= "nomor_telepon", nullable = false)
	private String nomorTelepon;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ObatSupplierModel> listObatSupplier;

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

	public String getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(String nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}
}
