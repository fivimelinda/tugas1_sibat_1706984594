package tugas1.sibat.model;
import java.io.Serializable;
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
@Table(name="obat")
public class ObatModel implements Serializable{
	//Obat punya KODE blabla
	@Id
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObat;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "nomor_registrasi", nullable = false)
	private String nomorRegistrasi; //unique
	
	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String namaObat;
	
	@NotNull
	@Size(max = 255)
	@Column(name= "kode", nullable = false)
	private String kodeObat; //unique
	
	@NotNull
	@Size(max = 255)
	@Column(name= "bentuk", nullable = false)
	private String bentukObat;
	
	@NotNull
	@Column(name= "tanggal_terbit", nullable = false)
	private Date tanggalTerbit;

	@NotNull
	@Column(name= "harga", nullable = false)
	private Double hargaObat;
	
	@ManyToMany(mappedBy = "listObat")
	private List<GudangModel> listGudang;
	
	@ManyToMany(mappedBy = "listObat")
	private List<SupplierModel> listSupplier;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_jenis", referencedColumnName= "idJenis", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JenisModel jenis;
	
	public List<SupplierModel> getListSupplier() {
		return listSupplier;
	}

	public void setListSupplier(List<SupplierModel> listSupplier) {
		this.listSupplier = listSupplier;
	}

	public JenisModel getJenis() {
		return jenis;
	}

	public void setJenis(JenisModel jenis) {
		this.jenis = jenis;
	}

	public Long getIdObat() {
		return idObat;
	}
	
	public void setIdObat(Long idObat) {
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

	public List<GudangModel> getListGudang() {
		return listGudang;
	}

	public void setListGudang(List<GudangModel> listGudang) {
		this.listGudang = listGudang;
	}

}
