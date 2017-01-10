import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TicketConsole {

	private static final Logger LOG = LogManager.getLogger(TicketConsole.class);

	static InfoBean info = new InfoBean();

	public static void main(String[] args) {
		getProperties();
		Thread t = new Thread(new Runnable() {
			String subject = "邮件主题";
			String content = "";
			String ticketConvertType[] = { "商务座", "特等座", "一等座", "二等座", "高级软卧", "软卧", "硬卧", "软座", "硬座" };
			String ticketType[] = { "swz_num", "tz_num", "zy_num", "ze_num", "gr_num", "rw_num", "yw_num", "rz_num", "yz_num", "wz_num" };
			
			@Override
			public void run() {
				int flag = 0, flag2 = 0;
				String s0 = "";
				String url = Test.finishSearchUrl(info.getFromSta(), info.getToSta(), info.getDate(), "ADULT");
				LOG.info(url);
				while (true) {
					try {
						String result = Test.SendGet(url, "UTF-8");
						for (int i = 0; i < 9; i++) {
							LOG.debug(result);
							s0 = Test.getTrainInfo(info.getTrain(), ticketType[i], result);
							if (s0.length() > 0 && (s0.charAt(0) == '有' || s0.charAt(0) > '0' && s0.charAt(0) <= '9')) {
								flag = 1;
								flag2 = 1;
							}
							if (flag == 1) {
								content = content + ticketConvertType[i] + "剩余情况：" + s0 + '\n';
								flag = 0;
							}

						}
						if (flag2 == 1) {
							content = content + "您的查询结果已给出，谢谢您使用本系统";
							LOG.info(content);
							if (Mail.send(info.getSmtp(), info.getFromAddress(), info.getToAddress(), subject, content, info.getFromAddress(), info.getFromPassword())) {
								LOG.info("邮件已经发送");
							}
						}

						try {
							Thread.sleep(60 * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.out.println("quit");
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
	
		});
		t.start();
	}

	public static void getProperties() {
		LOG.info("读取属性文件");
		Properties props = new Properties();
		try {
			props.load(TicketConsole.class.getClassLoader().getResourceAsStream("ticket.properties"));
			for (Object key : props.keySet()) {
				LOG.info(key + ":");
				LOG.info(props.get(key));
			}
			info.setSmtp(props.getProperty("smtp"));
			info.setFromAddress(props.getProperty("fromAddress"));
			info.setFromPassword(props.getProperty("fromPassword"));
			info.setToAddress(props.getProperty("toAddress"));
			info.setFromSta(props.getProperty("fromSta"));
			info.setToSta(props.getProperty("toSta"));
			info.setDate(props.getProperty("date"));
			info.setTrain(props.getProperty("train"));
		} catch (Exception e) {
			LOG.error("读取属性文件异常：");
			e.printStackTrace();
		}
	}

}
