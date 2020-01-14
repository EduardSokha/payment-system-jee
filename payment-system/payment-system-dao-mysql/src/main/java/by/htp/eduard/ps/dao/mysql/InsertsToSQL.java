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
	 
	 
	 public static final String AUTHENTICATION_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String CARD_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String CURRENCY_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String NAME_CARD_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String PAY_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String PAYMENT_SYSTEM_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String ROLE_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 
	 public static final String USER_DAO_IMPL_ = "UPDATE payment_system.usersж";
	 

}
