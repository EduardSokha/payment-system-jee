package by.htp.eduard.ps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.eduard.ps.dao.AccountDao;
import by.htp.eduard.ps.dao.CardDao;
import by.htp.eduard.ps.dao.entities.Account;
import by.htp.eduard.ps.dao.entities.Card;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.CardService;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AccountDto;
import by.htp.eduard.ps.service.dto.CardDto;

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
	public CardDto getCardByIdAccount(Integer id) {
		Card card = cardDao.getCardByIdAccount(id);
		CardDto dtoCard = converter.convertToDto(card, CardDto.class);
		return dtoCard;
	}

	@Override
	public List<CardDto> getCardByIdUser(Integer id) {
		List<Card> cardsByIdUser = new ArrayList<Card>();
		List<Account> accountsByIdUser = accountDao.getAccountByIdUser(id);
		
		if(accountsByIdUser == null) {
			return null;
		}
		
		for (Account account : accountsByIdUser) {
			Card card = cardDao.getCardByIdAccount(account.getId());
			cardsByIdUser.add(card);
		}
		List<CardDto> dtoList = converter.convertToDtoList(cardsByIdUser, CardDto.class);
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
	public CardDto createAccountAndCard(AccountDto accountDto, CardDto cardDto) {
		Account account = converter.convertToEntity(accountDto, Account.class);
		Card card = converter.convertToEntity(cardDto, Card.class);
		account.setDate(new Date());
		Account newAccount = accountDao.saveAccount(account);
		card.setIdAccount(newAccount.getId());
		Card newCard = cardDao.saveCard(card);
		CardDto dtoCard = converter.convertToDto(newCard, CardDto.class);
		return dtoCard;
	}
}
