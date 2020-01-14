package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Status;

public interface StatusDao {
	
	List<Status> getAllStatus();
	Status getNameStatusById(Integer id);
	Status saveStatus(Status status);
	Status updateNameStatus(Status status);
	void deleteStatus(Integer id);

}
