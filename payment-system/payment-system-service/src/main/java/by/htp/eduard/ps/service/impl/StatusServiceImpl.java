package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.StatusDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.Status;
import by.htp.eduard.service.StatusService;

public class StatusServiceImpl implements StatusService{
	
	private final StatusDao statusDao;

	public StatusServiceImpl() {
		statusDao = DaoProvider.getInstance().getStatusDao();
	}

	@Override
	public List<Status> getAllStatus() {
		return statusDao.getAllStatus();
	}

	@Override
	public Status getNameStatusById(Integer id) {
		return statusDao.getNameStatusById(id);
	}

	@Override
	public Status saveStatus(Status status) {
		if(status.getId() == null) {
			return statusDao.saveStatus(status);
		}
		
		return statusDao.updateNameStatus(status);
	}

	@Override
	public void deleteStatus(Integer id) {
		statusDao.deleteStatus(id);
	}

}
