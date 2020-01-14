package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.Account;
import by.htp.eduard.entities.Card;
import by.htp.eduard.ps.service.dto.CardDto;

public interface CardService {
	List<CardDto> getAllCards();
	List<CardDto> getCardByIdAccount(Integer id);
	CardDto getCardById(Integer id);
	CardDto saveCard(CardDto cardDto);
	void deleteCard(Integer id);
	boolean createAccountAndCard(Account account, Card card);
}
