package by.htp.eduard.ps.service.impl;

import java.util.List;

import by.htp.eduard.ps.dao.NameCardDao;
import by.htp.eduard.ps.dao.entities.NameCard;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.NameCardService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.NameCardDto;

public class NameCardServiceImpl implements NameCardService {

	private final NameCardDao nameCardDao;

	private EntityDtoConverter converter;

	public NameCardServiceImpl() {
		nameCardDao = DaoProvider.getInstance().getNameCardDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<NameCardDto> getAllNamesCard() {
		List<NameCard> allNamesCard = nameCardDao.getAllNamesCard();
		List<NameCardDto> dtoList = converter.convertToDtoList(allNamesCard, NameCardDto.class);
		return dtoList;
	}

	@Override
	public NameCardDto getNameCardById(Integer id) {
		NameCard nameCard = nameCardDao.getNameCardById(id);
		NameCardDto nameCardDto = converter.convertToDto(nameCard, NameCardDto.class);
		return nameCardDto;
	}

	@Override
	public NameCardDto saveNameCard(NameCardDto nameCardDto) {
		NameCard nameCard = converter.convertToEntity(nameCardDto, NameCard.class);
		if (nameCard.getId() == null) {
			nameCard = nameCardDao.saveNameCard(nameCard);
			NameCardDto dto = converter.convertToDto(nameCard, NameCardDto.class);
			return dto;
		}

		nameCard = nameCardDao.updateNameCard(nameCard);
		NameCardDto dto = converter.convertToDto(nameCard, NameCardDto.class);
		return dto;
	}

	@Override
	public void deleteNameCard(Integer id) {
		nameCardDao.deleteNameCard(id);
	}
}
