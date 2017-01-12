

public class InfoBean {
	
	private String smtp;

	private String fromAddress;
	
	private String fromPassword;
	
	private String toAddress;
	
	private String fromSta;//起发地
	
	private String toSta;//目的地
	
	private String date;//乘车日期
	
	private String train;//车次

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromSta() {
		return fromSta;
	}

	public void setFromSta(String fromSta) {
		this.fromSta = fromSta;
	}

	public String getToSta() {
		return toSta;
	}

	public void setToSta(String toSta) {
		this.toSta = toSta;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}
	
}
