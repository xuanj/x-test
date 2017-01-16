import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TicketConsole {

	private static final Logger LOG = LogManager.getLogger(TicketConsole.class);

	static InfoBean info = new InfoBean();

	public static void main(String[] args) {
		getProperties();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				String url = Test.finishSearchUrl(info.getFromSta(), info.getToSta(), info.getDate(), "ADULT");
				LOG.info(url);
				while (true) {
					try {
						String result = Test.SendGet(url, "UTF-8");
						JSONObject json = JSON.parseObject(result);
						JSONArray data = json.getJSONArray("data");
						StringBuilder sb = new StringBuilder();
						for (Object object : data) {
							System.out.println(object.toString());
							JSONObject j = (JSONObject)object;
							JSONObject train = j.getJSONObject("queryLeftNewDTO");
							TrainInfo info = parseJson(train);
							System.out.println(info);
							if(checkTrain(info)){
								sb.append(info + "\n");
							}
						}
						if(sb.length()>0){
							LOG.info("发送邮件内容：\n" + sb.toString());
							Mail.send(sb.toString());
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
	
	public static boolean checkTrain(TrainInfo info){
		if(!info.getZeNum().equals("无") && !info.getZeNum().equals("--"))
			return true;
		if(!info.getWzNum().equals("无") && !info.getWzNum().equals("--"))
			return true;
		if(!info.getZyNum().equals("无") && !info.getZyNum().equals("--"))
			return true;
		return false;
	}
	
	public static TrainInfo parseJson(JSONObject json){
		TrainInfo info = new TrainInfo();
		info.setTrainCode(json.getString("station_train_code"));
		info.setStartStationName(json.getString("start_station_name"));
		info.setEndStationName(json.getString("end_station_name"));
		info.setFromStationName(json.getString("from_station_name"));
		info.setToStationName(json.getString("to_station_name"));
		info.setStartTime(json.getString("start_time"));
		info.setArriveTime(json.getString("arrive_time"));
		info.setZeNum(json.getString("ze_num"));
		info.setSwzNum(json.getString("swz_num"));
		info.setZyNum(json.getString("zy_num"));
		info.setYwNum(json.getString("yw_num"));
		info.setRwNum(json.getString("rw_num"));
		info.setWzNum(json.getString("wz_num"));
		return info;
	}
	

	public static void getProperties() {
		LOG.info("读取属性文件");
		Properties props = new Properties();
		try {
			props.load(TicketConsole.class.getClassLoader().getResourceAsStream("ticket.properties.."));
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
