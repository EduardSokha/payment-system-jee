package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.Status;

public interface StatusService {
	
	List<Status> getAllStatus();
	Status getNameStatusById(Integer id);
	Status saveStatus(Status status);
	void deleteStatus(Integer id);

}
