package by.htp.eduard.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.dao.StatusDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.Status;

public class AccountStatusIdNameConverter extends DozerConverter<Integer, String> {

	private final StatusDao statusDao;
	
	public AccountStatusIdNameConverter() {
		super(Integer.class, String.class);
		statusDao = DaoProvider.getInstance().getStatusDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {
		Status status = statusDao.getNameStatusById(source);
		return status.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}

}
