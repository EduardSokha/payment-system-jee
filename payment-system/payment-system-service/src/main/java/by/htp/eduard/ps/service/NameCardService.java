package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.NameCardDto;

public interface NameCardService {
	
	List<NameCardDto> getAllNamesCard();
	NameCardDto getNameCardById(Integer id);
	NameCardDto saveNameCard(NameCardDto nameCardDto);
	void deleteNameCard(Integer id);
}
