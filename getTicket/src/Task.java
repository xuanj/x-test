
public class Task {
	
	private String status;//1 正常 0 停止

	private String taskName;
	
	private String toMail;
	
	private String fromSta;
	
	private String toSta;
	
	private String date;
	
	private Filter filter;

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the toMail
	 */
	public String getToMail() {
		return toMail;
	}

	/**
	 * @param toMail the toMail to set
	 */
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	/**
	 * @return the fromSta
	 */
	public String getFromSta() {
		return fromSta;
	}

	/**
	 * @param fromSta the fromSta to set
	 */
	public void setFromSta(String fromSta) {
		this.fromSta = fromSta;
	}

	/**
	 * @return the toSta
	 */
	public String getToSta() {
		return toSta;
	}

	/**
	 * @param toSta the toSta to set
	 */
	public void setToSta(String toSta) {
		this.toSta = toSta;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the filter
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
