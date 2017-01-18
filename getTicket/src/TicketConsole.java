import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class TicketConsole {

	private static final Logger LOG = LogManager.getLogger(TicketConsole.class);

	static TicketTaskInfo taskInfo;

	public static void main(String[] args) {
		getProperties();
		List<Task> TaskList = taskInfo.getTask();
		for (Task task : TaskList) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setName(task.getTaskName());
					LOG.info(Thread.currentThread().getName() + "-开始任务。");
					String url = Test.finishSearchUrl(task.getFromSta(), task.getToSta(), task.getDate(), "ADULT");
					LOG.info(url);
					while (true) {
						try {
							String result = Test.SendGet(url, "UTF-8");
							JSONObject json = JSON.parseObject(result);
							JSONArray data = json.getJSONArray("data");
							StringBuilder sb = new StringBuilder();
							for (Object object : data) {
								JSONObject j = (JSONObject)object;
								JSONObject train = j.getJSONObject("queryLeftNewDTO");
								TrainInfo info = parseJson(train);
								if(checkTrain(info, task)){
									sb.append("车次："+ info.getStationTrainCode() + "，开车时间：" + info.getStartTime() + "<br>");
									sb.append(JSON.toJSONString(info));
								}
							}
							if(sb.length()>0){
								LOG.info("发送邮件内容：\n" + sb.toString());
								Mail.send(taskInfo.getSendMail().getSmtp(),
										taskInfo.getSendMail().getAddress(),
										task.getToMail(),
										"大道信息",
										sb.toString() +  "<br> 来自_三川_",
										taskInfo.getSendMail().getAddress(),
										taskInfo.getSendMail().getPassword()
										);
							}
							try {
								Thread.sleep(60 * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
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
	}
	
	public static boolean checkTrain(TrainInfo info,Task task){
		Filter filter = task.getFilter();
		boolean flag = false;
		if(filter != null){
			LOG.debug("过滤车次：" + info.getStationTrainCode() + "\n"+ JSON.toJSONString(info));
			LOG.debug("过滤条件:" + JSON.toJSONString(filter));
			flag = true;
		} 
		
		if(flag && checkTicketCount(info, filter.getSeatType())){
			LOG.debug("有票!");
		} else {
			flag = false;
			LOG.debug("无票!");
		}
		
		if(flag && filter.getTrainCode()!=null&&filter.getTrainCode().length>0){
			LOG.debug("过滤车次!" + filter.getTrainCode());
			if(checkTrainCode(info,filter.getTrainCode())){
				LOG.debug("是要抢车次");
				return true;
			} else {
				LOG.debug("不是想这趟车");
				flag = false;
			}
		}
		if(flag){
			flag = checkTrainType(info, filter.getTrainType());
		}
		
		if(flag && checkStartTime(filter.getStartTimeBegin(), filter.getStartTimeEnd(), info.getStartTime())){
			LOG.debug("设置时间段内有车有座");
		} else {
			flag = false;
		}
		return flag;
	}
	
	private static boolean checkTrainCode(TrainInfo info,String[] trainCodes){
		for (String trainCode : trainCodes) {
			if(info.getStationTrainCode().equals(trainCode)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkTrainType(TrainInfo info,String trainType){
		if(StringUtils.isEmpty(trainType)){
			LOG.debug("没有过滤车类型");
			return true;
		}
		if(info.getStationTrainCode().indexOf(trainType) > -1){
			LOG.debug("是要过滤车类型:" + info.getStationTrainCode());
			return true;
		} else {
			LOG.debug("不是要过滤车类型:" + info.getStationTrainCode());
		}
		return false;
	}
	
	private static boolean checkTicketCount(TrainInfo info,String seatType){
		boolean flag = false;
		if(StringUtils.isEmpty(seatType)){
			flag = true;
		}
		
		if(flag ||"商务座".indexOf(seatType) > -1){
			if(!info.getSwzNum().equals("无") && !info.getSwzNum().equals("--"))
				return true;
		}
		if(flag ||"特等座".indexOf(seatType) > -1){
			if(!info.getTzNum().equals("无") && !info.getTzNum().equals("--"))
				return true;
		}
		if(flag ||"一等座".indexOf(seatType) > -1){
			if(!info.getZyNum().equals("无") && !info.getZyNum().equals("--"))
				return true;
		}
		if(flag ||"二等座".indexOf(seatType) > -1){
			if(!info.getZeNum().equals("无") && !info.getZeNum().equals("--"))
				return true;
		}
		if(flag ||"高级软卧".indexOf(seatType) > -1){
			if(!info.getGrNum().equals("无") && !info.getGrNum().equals("--"))
				return true;
		}
		if(flag ||"软卧".indexOf(seatType) > -1){
			if(!info.getRwNum().equals("无") && !info.getRwNum().equals("--"))
				return true;
		}
		if(flag ||"硬卧".indexOf(seatType) > -1){
			if(!info.getYwNum().equals("无") && !info.getYwNum().equals("--"))
				return true;
		}
		if(flag ||"软座".indexOf(seatType) > -1){
			if(!info.getRzNum().equals("无") && !info.getRzNum().equals("--"))
				return true;
		}
		if(flag ||"硬座".indexOf(seatType) > -1){
			if(!info.getYzNum().equals("无") && !info.getYzNum().equals("--"))
				return true;
		}
		if(flag ||"无座".indexOf(seatType) > -1){
			if(!info.getWzNum().equals("无") && !info.getWzNum().equals("--"))
				return true;
		}
		return false;
	}
	
	private static boolean checkStartTime(String sTime, String eTime, String time){
		int st = Integer.parseInt(sTime.replaceAll(":", ""));
		int et = Integer.parseInt(eTime.replaceAll(":", ""));
		int t = Integer.parseInt(time.replaceAll(":", ""));
		if(t > st && t < et){
			return true;
		} else {
			LOG.debug(time + "不在设置时间段");
			return false;
		}
	}
	
	public static TrainInfo parseJson(JSONObject json){
		TrainInfo info = new TrainInfo();
		try {
			info = (TrainInfo)JSON.parseObject(json.toJSONString(), TrainInfo.class);
			LOG.debug(JSON.toJSONString(info));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return info;
	}
	

	public static void getProperties() {
		LOG.info("读取json文件...");
		try {
			InputStream in = TicketConsole.class.getClassLoader().getResourceAsStream("ticket.json");
			JSONObject ticketTaskJson = JSONObject.parseObject(in, Charset.forName("UTF-8"), null, Feature.AllowArbitraryCommas);
			taskInfo = (TicketTaskInfo) JSON.parseObject(ticketTaskJson.toJSONString(), TicketTaskInfo.class);
			LOG.info(JSON.toJSONString(taskInfo));
			in.close();
		} catch (Exception e) {
			LOG.error("读取json文件异常：" + e.getMessage());
			e.printStackTrace();
		} 
	}

}
