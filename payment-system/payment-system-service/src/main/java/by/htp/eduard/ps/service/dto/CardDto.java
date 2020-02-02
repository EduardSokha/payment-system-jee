package by.htp.eduard.ps.service.dto;

import java.util.Date;

public class CardDto {
	
	private Integer id;

	private Date date;
	
	private Integer idAccount;
	
	private String numberAccount;
	
	private Integer idPaymentSystem;
	
	private String namePaymentSystem;
	
	private Integer idTradeNameCard;
	
	private String tradeNameCard;
	
	private String balance;
	
	private String currency;
	
	private String status;

	public CardDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Integer getIdPaymentSystem() {
		return idPaymentSystem;
	}

	public void setIdPaymentSystem(Integer idPaymentSystem) {
		this.idPaymentSystem = idPaymentSystem;
	}

	public String getNamePaymentSystem() {
		return namePaymentSystem;
	}

	public void setNamePaymentSystem(String namePaymentSystem) {
		this.namePaymentSystem = namePaymentSystem;
	}

	public Integer getIdTradeNameCard() {
		return idTradeNameCard;
	}

	public void setIdTradeNameCard(Integer idTradeNameCard) {
		this.idTradeNameCard = idTradeNameCard;
	}

	public String getTradeNameCard() {
		return tradeNameCard;
	}

	public void setTradeNameCard(String tradeNameCard) {
		this.tradeNameCard = tradeNameCard;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CardDto other = (CardDto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CardDto [id=" + id + ", date=" + date + ", idAccount=" + idAccount + ", numberAccount=" + numberAccount
				+ ", idPaymentSystem=" + idPaymentSystem + ", namePaymentSystem=" + namePaymentSystem
				+ ", idTradeNameCard=" + idTradeNameCard + ", tradeNameCard=" + tradeNameCard + ", balance=" + balance
				+ ", currency=" + currency + ", status=" + status + "]";
	}
}
