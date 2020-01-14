package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Pay;

public interface PayDao {

	List<Pay> getAllPay();
	Pay getPayById(Integer id);
	Pay savePay(Pay pay);
	void deletePay(Integer id);

}
