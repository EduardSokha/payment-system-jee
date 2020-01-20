package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;

public interface CardService {
	List<CardDto> getAllCards();
	CardDto getCardByIdAccount(Integer id);
	List<CardDto> getCardByIdUser(Integer id);
	CardDto getCardById(Integer id);
	CardDto saveCard(CardDto cardDto);
	void deleteCard(Integer id);
	CardDto createAccountAndCard(AccountDto accountDto, CardDto cardDto);
}
