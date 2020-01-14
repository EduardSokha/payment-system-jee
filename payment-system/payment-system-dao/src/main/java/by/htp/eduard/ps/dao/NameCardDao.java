package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.NameCard;

public interface NameCardDao {

	List<NameCard> getAllNamesCard();

	NameCard getNameCardById(Integer id);

	NameCard saveNameCard(NameCard nameCard);

	NameCard updateNameCard(NameCard nameCard);

	void deleteNameCard(Integer id);

}
