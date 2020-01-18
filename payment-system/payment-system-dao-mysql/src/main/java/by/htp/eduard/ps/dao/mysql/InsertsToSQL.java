package by.htp.eduard.ps.dao.mysql;

public class InsertsToSQL {
	
	private InsertsToSQL() {
	}
	
	 public static final String ACCOUNT_DAO_IMPL_GET_ALL_ACCOUNTS = "SELECT `id`, `number`, `balance`, `creation_date`, `iduser`, `idstatus`, `currency_idcurrency` FROM `account`";
	 public static final String ACCOUNT_DAO_IMPL_GET_ACCOUNT_BY_ID_USER = "SELECT `id`, `number`, `balance`, `creation_date`, `iduser`, `idstatus`, `currency_idcurrency` FROM `account` WHERE `iduser` = ";
	 public static final String ACCOUNT_DAO_IMPL_GET_ACCOUNT_BY_ID = "SELECT `id`, `number`, `balance`, `creation_date`, `iduser`, `idstatus`, `currency_idcurrency` FROM `account` WHERE `id` = ";
	 public static final String ACCOUNT_DAO_IMPL_SAVE_ACCOUNT = "INSERT INTO `account` (`number`, `balance`, `creation_date`, `iduser`, `idstatus`, `currency_idcurrency`) VALUES (?,?,?,?,?,?)";
	 public static final String ACCOUNT_DAO_IMPL_UPDATE_ACCOUNT = "UPDATE `account` SET `balance` = ?, `idstatus` = ? WHERE `id` = ?";
	 public static final String ACCOUNT_DAO_IMPL_LOCK_UNLOCK_ACCOUNT = "UPDATE `account` SET `idstatus` = ? WHERE `id` = ?";
	 public static final String ACCOUNT_DAO_IMPL_UPDATE_BALANCE_ACCOUNT = "UPDATE `account` SET `balance` = ? WHERE `id` = ?";
	 public static final String ACCOUNT_DAO_IMPL_DELETE_ACCOUNT = "DELETE FROM `account` WHERE `id` = ?";
	 
	 
//	 public static final String AUTHENTICATION_DAO_IMPL_SIGN_IN = "UPDATE payment_system.usersж";
//	 public static final String AUTHENTICATION_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String CARD_DAO_IMPL_GET_ALL_CARDS = "SELECT `id`, `creation_date`, `account_idaccount`, `payment_system_card_idpayment_system_card`, `name_card_idname_card` FROM `card`";
	 public static final String CARD_DAO_IMPL_GET_CARD_BY_ID_ACCOUNT = "SELECT `id`, `creation_date`, `account_idaccount`, `payment_system_card_idpayment_system_card`, `name_card_idname_card` FROM `card` WHERE `account_idaccount` = ";
	 public static final String CARD_DAO_IMPL_GET_CARD_BY_ID = "SELECT `id`, `creation_date`, `account_idaccount`, `payment_system_card_idpayment_system_card`, `name_card_idname_card` FROM `card` WHERE `id` = ";
	 public static final String CARD_DAO_IMPL_SAVE_CARD = "INSERT INTO `card` (`creation_date`, `account_idaccount`, `payment_system_card_idpayment_system_card`, `name_card_idname_card`) VALUES (?, ?, ?, ?)";
	 public static final String CARD_DAO_IMPL_DELETE_CARD = "DELETE FROM `card` WHERE `id` = ?";
	 
	 public static final String CURRENCY_DAO_IMPL_GET_ALL_CURRENCIES = "SELECT `id`, `name_currency` FROM `currency`";
	 public static final String CURRENCY_DAO_IMPL_GET_NAME_CURRENCY_BY_ID = "SELECT `id`, `name_currency` FROM `currency` WHERE `id` = ";
	 public static final String CURRENCY_DAO_IMPL_SAVE_CURRENCY = "INSERT INTO `currency` (`name_currency`) VALUES (?)";
	 public static final String CURRENCY_DAO_IMPL_UPDATE_NAME_CURRENCY = "UPDATE `currency` SET `name_currency` = ? WHERE `id` = ?";
	 public static final String CURRENCY_DAO_IMPL_DELETE_CURRENCY = "DELETE FROM `currency` WHERE `id` = ?";
	 
	 public static final String NAME_CARD_DAO_IMPL_GET_ALL_NAMES_CARD = "SELECT `id`, `name_card` FROM `name_card`";
	 public static final String NAME_CARD_DAO_IMPL_GET_NAME_CARD_BY_ID = "SELECT `id`, `name_card` FROM `name_card` WHERE `id` = ";
	 public static final String NAME_CARD_DAO_IMPL_SAVE_NAME_CARD = "INSERT INTO `name_card` (`name_card`) VALUES (?)";
	 public static final String NAME_CARD_DAO_IMPL_UPDATE_NAME_CARD = "UPDATE `name_card` SET `name_card` = ? WHERE `id` = ?";
	 public static final String NAME_CARD_DAO_IMPL_DELETE_NAME_CARD = "DELETE FROM `name_card` WHERE `id` = ?";
	 
	 public static final String PAY_DAO_IMPL_GET_ALL_PAY = "SELECT `id`, `date`, `price`, `idaccount`, `description` FROM `payments`";
	 public static final String PAY_DAO_IMPL_GET_PAY_BY_ID = "SELECT `id`, `date`, `price`, `idaccount`, `description` FROM `payments` WHERE `id` = ";
	 public static final String PAY_DAO_IMPL_SAVE_PAY = "INSERT INTO `payments` (`date`, `price`, `idaccount`, `description`) VALUES (?,?,?,?)";
	 public static final String PAY_DAO_IMPL_DELETE_PAY = "DELETE FROM `payments` WHERE `id` = ?";	
	 
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_GET_ALL_PAYMENT_SYSTEMS = "SELECT `id`, `type_paym_syst_card` FROM `payment_system_card`";
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_GET_PAYMENT_SYSTEM_BY_ID = "SELECT `id`, `type_paym_syst_card` FROM `payment_system_card` WHERE `id` = ";
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_SAVE_PAYMENT_SYSTEM = "INSERT INTO `payment_system_card` (`type_paym_syst_card`) VALUES (?)";
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_UPDATE_PAYMENT_SYSTEM = "UPDATE `payment_system_card` SET `type_paym_syst_card` = ? WHERE `id` = ?";
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_DELETE_PAYMENT_SYSTEM = "DELETE FROM `payment_system_card` WHERE `id` = ?";
	 
	 public static final String ROLE_DAO_IMPL_GET_ALL_ROLES = "SELECT `id`, `title` FROM `role`";
	 public static final String ROLE_DAO_IMPL_GET_NAME_ROLE_BY_ID = "SELECT `id`, `title` FROM `role` WHERE `id` = ";
	 public static final String ROLE_DAO_IMPL_SAVE_ROLE = "INSERT INTO `role` (`title`) VALUES (?)";
	 public static final String ROLE_DAO_IMPL_UPDATE_NAME_ROLE = "UPDATE `role` SET `title` = ? WHERE `id` = ?";
	 public static final String ROLE_DAO_IMPL_DELETE_ROLE = "DELETE FROM `role` WHERE `id` = ?";
	 
	 public static final String STATUS_DAO_IMPL_GET_ALL_STATUS = "SELECT `id`, `name_status` FROM `status`";
	 public static final String STATUS_DAO_IMPL_GET_NAME_STATUS_BY_ID = "SELECT `id`, `name_status` FROM `status` WHERE `id` = ";
	 public static final String STATUS_DAO_IMPL_SAVE_STATUS = "INSERT INTO `status` (`name_status`) VALUES (?)";
	 public static final String STATUS_DAO_IMPL_UPDATE_NAME_STATUS = "UPDATE `status` SET `name_status` = ? WHERE `id` = ?";
	 public static final String STATUS_DAO_IMPL_DELETE_STATUS = "DELETE FROM `status` WHERE `id` = ?";
	 
	 public static final String USER_DAO_IMPL_GET_ALL_USERS = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, "
													          + "`series_number_passport`, `identification_number_passport`, `codeword`, "
													          + "`phone_number`, `residence_registr_data_passport` "
													          + "FROM `users`";
	 public static final String USER_DAO_IMPL_GET_USER_BY_ID = "SELECT `id`, `login`, `password`, `name`, `surname`, `address`, `role_idrole`, `series_number_passport`, `identification_number_passport`, `codeword`, `phone_number`, `residence_registr_data_passport` FROM `users` WHERE `id` = ";
	 public static final String USER_DAO_IMPL_SAVE_USER = "INSERT INTO users(login, password, name, surname, address, role_idrole, series_number_passport, identification_number_passport, codeword, phone_number, residence_registr_data_passport) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String USER_DAO_IMPL_UPDATE_USER = "UPDATE `users` SET `login` = ?, `password` = ?, `name` = ?, `surname` = ?, `address` = ?, `role_idrole` = ?, `series_number_passport` = ?, `identification_number_passport` = ?, `codeword` = ?, `phone_number` = ?, `residence_registr_data_passport` = ? WHERE `id` = ?";
	 public static final String USER_DAO_IMPL_DELETE_USER = "DELETE FROM `users` WHERE `id` = ?";
	
}
