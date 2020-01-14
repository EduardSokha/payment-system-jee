package by.htp.eduard.ps.dao.entities;

import java.io.Serializable;

public class ListAccountsAndCards implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idAccount;
	private int balance;
	private String statusAccount;
	private String creationDateAccount;
	private String currency;
	private int idCard;
	private String typePaySyst;
	private String nameCard;

	public ListAccountsAndCards() {
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getStatusAccount() {
		return statusAccount;
	}

	public void setStatusAccount(String statusAccount) {
		this.statusAccount = statusAccount;
	}

	public String getCreationDateAccount() {
		return creationDateAccount;
	}

	public void setCreationDateAccount(String creationDateAccount) {
		this.creationDateAccount = creationDateAccount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public String getTypePaySyst() {
		return typePaySyst;
	}

	public void setTypePaySyst(String typePaySyst) {
		this.typePaySyst = typePaySyst;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		result = prime * result + ((creationDateAccount == null) ? 0 : creationDateAccount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + idAccount;
		result = prime * result + idCard;
		result = prime * result + ((nameCard == null) ? 0 : nameCard.hashCode());
		result = prime * result + ((statusAccount == null) ? 0 : statusAccount.hashCode());
		result = prime * result + ((typePaySyst == null) ? 0 : typePaySyst.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListAccountsAndCards other = (ListAccountsAndCards) obj;
		if (balance != other.balance)
			return false;
		if (creationDateAccount == null) {
			if (other.creationDateAccount != null)
				return false;
		} else if (!creationDateAccount.equals(other.creationDateAccount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (idAccount != other.idAccount)
			return false;
		if (idCard != other.idCard)
			return false;
		if (nameCard == null) {
			if (other.nameCard != null)
				return false;
		} else if (!nameCard.equals(other.nameCard))
			return false;
		if (statusAccount == null) {
			if (other.statusAccount != null)
				return false;
		} else if (!statusAccount.equals(other.statusAccount))
			return false;
		if (typePaySyst == null) {
			if (other.typePaySyst != null)
				return false;
		} else if (!typePaySyst.equals(other.typePaySyst))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListAccountsAndCards [idAccount=" + idAccount + ", balance=" + balance + ", statusAccount="
				+ statusAccount + ", creationDateAccount=" + creationDateAccount + ", currency=" + currency
				+ ", idCard=" + idCard + ", typePaySyst=" + typePaySyst + ", nameCard=" + nameCard + "]";
	}

}
