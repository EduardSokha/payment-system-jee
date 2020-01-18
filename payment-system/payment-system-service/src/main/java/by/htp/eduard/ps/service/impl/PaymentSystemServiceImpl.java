package by.htp.eduard.ps.service.impl;

import java.util.List;

import by.htp.eduard.ps.dao.PaymentSystemDao;
import by.htp.eduard.ps.dao.entities.PaymentSystem;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.PaymentSystemService;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.PaymentSystemDto;

public class PaymentSystemServiceImpl implements PaymentSystemService {

	private final PaymentSystemDao paymentSystemDao;

	private EntityDtoConverter converter;

	public PaymentSystemServiceImpl() {
		paymentSystemDao = DaoProvider.getInstance().getPaymentSystemDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<PaymentSystemDto> getAllPaymentSystems() {
		List<PaymentSystem> allPaymentSystems = paymentSystemDao.getAllPaymentSystems();
		List<PaymentSystemDto> convertToDtoList = converter.convertToDtoList(allPaymentSystems, PaymentSystemDto.class);
		return convertToDtoList;
	}

	@Override
	public PaymentSystemDto savePaymentSystem(PaymentSystemDto paymentSystemDto) {
		PaymentSystem paymentSystem = converter.convertToEntity(paymentSystemDto, PaymentSystem.class);

		if (paymentSystem.getId() == null) {
			paymentSystem = paymentSystemDao.savePaymentSystem(paymentSystem);
			PaymentSystemDto dto = converter.convertToDto(paymentSystem, PaymentSystemDto.class);
			return dto;
		}

		paymentSystem = paymentSystemDao.updatePaymentSystem(paymentSystem);
		PaymentSystemDto dto = converter.convertToDto(paymentSystem, PaymentSystemDto.class);
		return dto;
	}

	@Override
	public PaymentSystemDto getPaymentSystemById(Integer id) {
		PaymentSystem paymentSystem = paymentSystemDao.getPaymentSystemById(id);
		PaymentSystemDto dto = converter.convertToDto(paymentSystem, PaymentSystemDto.class);
		return dto;
	}

	@Override
	public void deletePaymentSystem(Integer id) {
		paymentSystemDao.deletePaymentSystem(id);
	}
}
