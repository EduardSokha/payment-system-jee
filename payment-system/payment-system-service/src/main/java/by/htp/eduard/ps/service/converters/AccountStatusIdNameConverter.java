package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.StatusDao;
import by.htp.eduard.ps.dao.entities.Status;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

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
