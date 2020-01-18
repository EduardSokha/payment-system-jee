package by.htp.eduard.ps.service.impl;

import java.util.List;

import by.htp.eduard.ps.dao.StatusDao;
import by.htp.eduard.ps.dao.entities.Status;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.StatusService;
import by.htp.eduard.ps.service.dto.StatusDto;

public class StatusServiceImpl implements StatusService{
	
	private final StatusDao statusDao;
	
	private EntityDtoConverter converter;

	public StatusServiceImpl() {
		statusDao = DaoProvider.getInstance().getStatusDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<StatusDto> getAllStatus() {
		List<Status> allStatus = statusDao.getAllStatus();
		List<StatusDto> dtoList = converter.convertToDtoList(allStatus, StatusDto.class);
		return dtoList;
	}

	@Override
	public StatusDto getNameStatusById(Integer id) {
		Status status = statusDao.getNameStatusById(id);
		StatusDto statusDto = converter.convertToDto(status, StatusDto.class);
		return statusDto; 
	}

	@Override
	public StatusDto saveStatus(StatusDto statusDto) {
		Status status = converter.convertToEntity(statusDto, Status.class);
		
		if(status.getId() == null) {
			status = statusDao.saveStatus(status);
			StatusDto dto = converter.convertToDto(status, StatusDto.class);
			return dto;
		}
		
		status = statusDao.updateNameStatus(status);
		StatusDto dto = converter.convertToDto(status, StatusDto.class);
		return dto;
	}

	@Override
	public void deleteStatus(Integer id) {
		statusDao.deleteStatus(id);
	}

}
