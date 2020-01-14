package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.NameCard;

public interface NameCardService {
	
	List<NameCard> getAllNamesCard();
	NameCard getNameCardById(Integer id);
	NameCard saveNameCard(NameCard nameCard);
	void deleteNameCard(Integer id);
}
