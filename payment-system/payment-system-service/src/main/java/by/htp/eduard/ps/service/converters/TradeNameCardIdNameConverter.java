package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.NameCardDao;
import by.htp.eduard.ps.dao.entities.NameCard;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

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
