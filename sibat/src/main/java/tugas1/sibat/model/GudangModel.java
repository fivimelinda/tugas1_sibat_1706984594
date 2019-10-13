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
@Table(name="gudang")
public class GudangModel implements Serializable {
	@Id
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGudang;
	
	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String namaGudang;
	
	@NotNull
	@Size(max = 255)
	@Column(name="alamat", nullable = false)
	private String alamatGudang;
	
	@OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GudangObatModel> listGudangObat;

	public Long getIdGudang() {
		return idGudang;
	}

	public void setIdGudang(Long idGudang) {
		this.idGudang = idGudang;
	}

	public String getNamaGudang() {
		return namaGudang;
	}

	public void setNamaGudang(String namaGudang) {
		this.namaGudang = namaGudang;
	}

	public String getAlamatGudang() {
		return alamatGudang;
	}

	public void setAlamatGudang(String alamatGudang) {
		this.alamatGudang = alamatGudang;
	}


}
