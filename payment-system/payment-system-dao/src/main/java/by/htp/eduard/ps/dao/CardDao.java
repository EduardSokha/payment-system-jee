package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Card;

public interface CardDao {
	List<Card> getAllCards();
	List<Card> getCardByIdAccount(Integer id);
	Card getCardById(Integer id);
	Card saveCard(Card card);
	void deleteCard(Integer id);
}
