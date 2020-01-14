package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.AccountDao;
import by.htp.eduard.dao.CardDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.dto.CardDto;
import by.htp.eduard.entities.Account;
import by.htp.eduard.entities.Card;
import by.htp.eduard.service.CardService;
import by.htp.eduard.service.EntityDtoConverter;
import by.htp.eduard.service.ServiceProvider;

public class CardServiceImpl implements CardService {

	private final CardDao cardDao;
	private final AccountDao accountDao;
	private EntityDtoConverter converter;

	public CardServiceImpl() {
		cardDao = DaoProvider.getInstance().getCardDao();
		accountDao = DaoProvider.getInstance().getAccountDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<CardDto> getAllCards() {
		List<Card> allCards = cardDao.getAllCards();
		List<CardDto> dtoList = converter.convertToDtoList(allCards, CardDto.class);
		return dtoList;
	}

	@Override
	public List<CardDto> getCardByIdAccount(Integer id) {
		List<Card> listCards = cardDao.getCardByIdAccount(id);
		List<CardDto> dtoList = converter.convertToDtoList(listCards, CardDto.class);
		return dtoList;
	}

	@Override
	public CardDto getCardById(Integer id) {
		Card card = cardDao.getCardById(id);
		CardDto cardDto = converter.convertToDto(card, CardDto.class);
		return cardDto;
	}

	@Override
	public CardDto saveCard(CardDto cardDto) {
		Card card = converter.convertToEntity(cardDto, Card.class);
		card = cardDao.saveCard(card);
		CardDto dtoCard = converter.convertToDto(card, CardDto.class);
		return dtoCard;
	}

	@Override
	public void deleteCard(Integer id) {
		cardDao.deleteCard(id);
	}

	@Override
	public boolean createAccountAndCard(Account account, Card card) {
		Account newAccount = accountDao.saveAccount(account);
		card.setIdAccount(newAccount.getId());
		cardDao.saveCard(card);
		return true;
	}
}
