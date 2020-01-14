package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.NameCardDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.NameCard;
import by.htp.eduard.service.NameCardService;

public class NameCardServiceImpl implements NameCardService{

	private final NameCardDao nameCardDao;
	
	public NameCardServiceImpl() {
		nameCardDao =  DaoProvider.getInstance().getNameCardDao();
	}

	@Override
	public List<NameCard> getAllNamesCard() {
		return nameCardDao.getAllNamesCard();
	}

	@Override
	public NameCard getNameCardById(Integer id) {
		return nameCardDao.getNameCardById(id);
	}

	@Override
	public NameCard saveNameCard(NameCard nameCard) {
		if(nameCard.getId() == null) {
			return nameCardDao.saveNameCard(nameCard);
		}
		
		return nameCardDao.updateNameCard(nameCard);
	}

	@Override
	public void deleteNameCard(Integer id) {
		nameCardDao.deleteNameCard(id);
	}
}
