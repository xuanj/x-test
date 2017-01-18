import java.util.List;

public class TicketTaskInfo {
	
	private SendMailInfo sendMail;
	
	private List<Task> task;

	/**
	 * @return the sendMail
	 */
	public SendMailInfo getSendMail() {
		return sendMail;
	}

	/**
	 * @param sendMail the sendMail to set
	 */
	public void setSendMail(SendMailInfo sendMail) {
		this.sendMail = sendMail;
	}

	/**
	 * @return the task
	 */
	public List<Task> getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(List<Task> task) {
		this.task = task;
	}

}
