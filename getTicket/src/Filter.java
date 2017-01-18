
public class Filter {

	private String[] trainCode;
	
	private String trainType;
	
	private String seatType;
	
	private String ticketCount;
	
	private String startTimeBegin;
	
	private String startTimeEnd;


	/**
	 * @return the trainType
	 */
	public String getTrainType() {
		return trainType;
	}

	/**
	 * @param trainType
	 *            the trainType to set
	 */
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	/**
	 * @return the seatType
	 */
	public String getSeatType() {
		return seatType;
	}

	/**
	 * @param seatType
	 *            the seatType to set
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	/**
	 * @return the ticketCount
	 */
	public String getTicketCount() {
		return ticketCount;
	}

	/**
	 * @param ticketCount
	 *            the ticketCount to set
	 */
	public void setTicketCount(String ticketCount) {
		this.ticketCount = ticketCount;
	}

	/**
	 * @return the startTimeBegin
	 */
	public String getStartTimeBegin() {
		return startTimeBegin;
	}

	/**
	 * @param startTimeBegin
	 *            the startTimeBegin to set
	 */
	public void setStartTimeBegin(String startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}

	/**
	 * @return the startTimeEnd
	 */
	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	/**
	 * @param startTimeEnd
	 *            the startTimeEnd to set
	 */
	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	public String[] getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(String[] trainCode) {
		this.trainCode = trainCode;
	}
}
