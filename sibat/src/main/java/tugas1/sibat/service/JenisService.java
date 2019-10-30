package tugas1.sibat.service;

import java.util.List;
import java.util.Optional;

import tugas1.sibat.model.JenisModel;

public interface JenisService {
	List<JenisModel> getJenisList();
	Optional<JenisModel> getJenisById(Long id);
}
