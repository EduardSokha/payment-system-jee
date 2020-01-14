package by.htp.eduard.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.dao.NameCardDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.NameCard;

public class TradeNameCardIdNameConverter extends DozerConverter<Integer, String>{

	private final NameCardDao cameCardDao;
	
	public TradeNameCardIdNameConverter() {
		super(Integer.class, String.class);
		cameCardDao = DaoProvider.getInstance().getNameCardDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {
		NameCard nameCard = cameCardDao.getNameCardById(source);
		return nameCard.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}

}
