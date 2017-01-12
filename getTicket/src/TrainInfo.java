
public class TrainInfo {

	private String trainCode;
	
	private String startStationName;
	
	private String endStationName;
	
	private String fromStationName;
	
	private String toStationName;
	
	private String startTime;
	
	private String arriveTime;
	
	private String zeNum;//二等
	
	private String swzNum;//商务
	
	private String zyNum;//一等
	
	private String ywNum;//硬卧
	
	private String rwNum;//软卧

	private String wzNum;//无座

	/**
	 * @return the trainCode
	 */
	public String getTrainCode() {
		return trainCode;
	}

	/**
	 * @param trainCode the trainCode to set
	 */
	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}

	/**
	 * @return the startStationName
	 */
	public String getStartStationName() {
		return startStationName;
	}

	/**
	 * @param startStationName the startStationName to set
	 */
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}

	/**
	 * @return the endStationName
	 */
	public String getEndStationName() {
		return endStationName;
	}

	/**
	 * @param endStationName the endStationName to set
	 */
	public void setEndStationName(String endStationName) {
		this.endStationName = endStationName;
	}

	/**
	 * @return the fromStationName
	 */
	public String getFromStationName() {
		return fromStationName;
	}

	/**
	 * @param fromStationName the fromStationName to set
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	/**
	 * @return the toStationName
	 */
	public String getToStationName() {
		return toStationName;
	}

	/**
	 * @param toStationName the toStationName to set
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the arriveTime
	 */
	public String getArriveTime() {
		return arriveTime;
	}

	/**
	 * @param arriveTime the arriveTime to set
	 */
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	/**
	 * @return the zeNum
	 */
	public String getZeNum() {
		return zeNum;
	}

	/**
	 * @param zeNum the zeNum to set
	 */
	public void setZeNum(String zeNum) {
		this.zeNum = zeNum;
	}

	/**
	 * @return the swzNum
	 */
	public String getSwzNum() {
		return swzNum;
	}

	/**
	 * @param swzNum the swzNum to set
	 */
	public void setSwzNum(String swzNum) {
		this.swzNum = swzNum;
	}

	/**
	 * @return the zyNum
	 */
	public String getZyNum() {
		return zyNum;
	}

	/**
	 * @param zyNum the zyNum to set
	 */
	public void setZyNum(String zyNum) {
		this.zyNum = zyNum;
	}

	/**
	 * @return the ywNum
	 */
	public String getYwNum() {
		return ywNum;
	}

	/**
	 * @param ywNum the ywNum to set
	 */
	public void setYwNum(String ywNum) {
		this.ywNum = ywNum;
	}

	/**
	 * @return the rwNum
	 */
	public String getRwNum() {
		return rwNum;
	}

	/**
	 * @param rwNum the rwNum to set
	 */
	public void setRwNum(String rwNum) {
		this.rwNum = rwNum;
	}

	/**
	 * @return the wzNum
	 */
	public String getWzNum() {
		return wzNum;
	}

	/**
	 * @param wzNum the wzNum to set
	 */
	public void setWzNum(String wzNum) {
		this.wzNum = wzNum;
	}

	@Override
	public String toString() {
		return "车次信息 [车次=" + trainCode + ", 始发=" + startStationName + ", 终点="
				+ endStationName + ", 上车=" + fromStationName + ", 下车=" + toStationName
				+ ", 开车时间=" + startTime + ", 到站时间=" + arriveTime + ", 二等座=" + zeNum + ", 商务座=" + swzNum
				+ ", 一等座=" + zyNum + ", 硬卧=" + ywNum + ", 软卧=" + rwNum + ", 无座=" + wzNum + "]";
	}
	
	
	
	
}
