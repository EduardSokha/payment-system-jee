package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.StatusDto;

public interface StatusService {
	
	List<StatusDto> getAllStatus();
	StatusDto getNameStatusById(Integer id);
	StatusDto saveStatus(StatusDto statusDto);
	void deleteStatus(Integer id);

}
