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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="obat")
public class ObatModel implements Serializable{
	//Obat punya KODE blabla
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger idObat;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "nomor_registrasi", nullable = false)
	private String nomorRegistrasi; //unique
	
	@NotNull
	@Size(max = 50)
	@Column(name="nama_obat", nullable = false)
	private String namaObat;
	
	@NotNull
	@Size(max = 10)
	@Column(name= "kode", nullable = false)
	private String kodeObat; //unique
	
	@NotNull
	@Size(max = 10)
	@Column(name= "bentuk", nullable = false)
	private String bentukObat;
	
	@NotNull
	@Column(name= "tanggal_terbit", nullable = false)
	private Date tanggalTerbit;

	@NotNull
	@Column(name= "harga_obat", nullable = false)
	private Double hargaObat;
	
	@OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GudangObatModel> listGudangObat;
	
	@OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ObatSupplierModel> listObatSupplier;

	public void setIdObat(BigInteger idObat) {
		this.idObat = idObat;
	}

	public String getNomorRegistrasi() {
		return nomorRegistrasi;
	}

	public void setNomorRegistrasi(String nomorRegistrasi) {
		this.nomorRegistrasi = nomorRegistrasi;
	}

	public String getNamaObat() {
		return namaObat;
	}

	public void setNamaObat(String namaObat) {
		this.namaObat = namaObat;
	}

	public String getKodeObat() {
		return kodeObat;
	}

	public void setKodeObat(String kodeObat) {
		this.kodeObat = kodeObat;
	}

	public String getBentukObat() {
		return bentukObat;
	}

	public void setBentukObat(String bentukObat) {
		this.bentukObat = bentukObat;
	}

	public Double getHargaObat() {
		return hargaObat;
	}

	public void setHargaObat(Double hargaObat) {
		this.hargaObat = hargaObat;
	}

	public Date getTanggalTerbit() {
		return tanggalTerbit;
	}

	public void setTanggalTerbit(Date tanggalTerbit) {
		this.tanggalTerbit = tanggalTerbit;
	}

	public List<GudangObatModel> getListGudangObat() {
		return listGudangObat;
	}

	public void setListGudangObat(List<GudangObatModel> listGudangObat) {
		this.listGudangObat = listGudangObat;
	}

	public BigInteger getIdObat() {
		return idObat;
	}
	
}
