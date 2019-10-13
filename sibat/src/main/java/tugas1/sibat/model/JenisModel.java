package tugas1.sibat.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="jenis")
public class JenisModel implements Serializable{
	@Id
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger idJenis;
	
	@NotNull
	@Size(max = 255)
	@Column(name="nama_jenis", nullable = false)
	private String namaJenis;
	
	@NotNull
	@Size(max = 255)
	@Column(name="deskripsi", nullable = false)
	private String deskripsi;

	public BigInteger getIdJenis() {
		return idJenis;
	}

	public void setIdJenis(BigInteger idJenis) {
		this.idJenis = idJenis;
	}

	public String getNamaJenis() {
		return namaJenis;
	}

	public void setNamaJenis(String namaJenis) {
		this.namaJenis = namaJenis;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}
