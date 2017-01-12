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
//						String result = "{\"validateMessagesShowId\":\"_validatorMessage\",\"status\":true,\"httpstatus\":200,\"data\":[{\"queryLeftNewDTO\":{\"train_no\":\"6i000G291204\",\"station_train_code\":\"G2912\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NFZ\",\"end_station_name\":\"南宁东\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"07:04\",\"arrive_time\":\"10:22\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"03:18\",\"canWebBuy\":\"Y\",\"lishiValue\":\"198\",\"yp_info\":\"aGxCgg74XtpHQpgjtzOZXfZ4w7XyMsHddnp8iFUgmFZhh5wK\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170204\",\"seat_feature\":\"O3M393\",\"yp_ex\":\"O0M090\",\"train_seat_feature\":\"3\",\"seat_types\":\"OM9\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"05\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"17\",\"end_city_code\":\"1607\",\"yz_num\":\"--\",\"rz_num\":\"--\",\"yw_num\":\"--\",\"rw_num\":\"--\",\"gr_num\":\"--\",\"zy_num\":\"无\",\"ze_num\":\"有\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"--\",\"qt_num\":\"--\",\"swz_num\":\"无\"},\"secretStr\":\"TP8EL5XdF6BmOjj6gFyefyRWWVlpGG%2BZHjh9IBQKg6DdmYelNfN3b7JKA872AnN8BMOH6SAVU4%2B8%0AB7VfAvBPGFCGjXdDcsdwxHv3RJsCFiS605oZwgsv2f3K8X1GV1a21qoUhtMc%2F2WIZILXcXcb%2Fxal%0AjXt7HcQOFolQA4BpAJ35I5HZYgcV2W5c0AJnRcYWZKHtErpyq7mX4SYiN98GF%2FEhdS1roCcAMy%2B%2B%0AMH0%2F50JqX7NGuSC36ifa7MN0DbyobtmXXGCW7KqQl09GMlFPOuVfW9LzNmiMnBn8Sl6KsB4Hq7yE%0AFCUy3Q%3D%3D\",\"buttonTextInfo\":\"预订\"},{\"queryLeftNewDTO\":{\"train_no\":\"690000K5860F\",\"station_train_code\":\"K586\",\"start_station_telecode\":\"OSQ\",\"start_station_name\":\"深圳西\",\"end_station_telecode\":\"CDW\",\"end_station_name\":\"成都\",\"from_station_telecode\":\"OSQ\",\"from_station_name\":\"深圳西\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"09:00\",\"arrive_time\":\"21:57\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"12:57\",\"canWebBuy\":\"Y\",\"lishiValue\":\"777\",\"yp_info\":\"UzG8zFp6mJafDSbIAWFYIymyUX1yvcPGfHsRmdp8F8RaXwAAwq%2FZY45m%2FGA%3D\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170204\",\"seat_feature\":\"W3431333\",\"yp_ex\":\"10401030\",\"train_seat_feature\":\"3\",\"seat_types\":\"1413\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"10\",\"control_day\":29,\"sale_time\":\"1030\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"yz_num\":\"有\",\"rz_num\":\"--\",\"yw_num\":\"6\",\"rw_num\":\"无\",\"gr_num\":\"--\",\"zy_num\":\"--\",\"ze_num\":\"--\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"有\",\"qt_num\":\"--\",\"swz_num\":\"--\"},\"secretStr\":\"EIlX0M7Lfz2WNc99Lw%2Fzdu0FGsulF4PyIsqL4xHTqHYpIOiIlZR06WjBZQRmxiBiPHRHH%2BgtAr3t%0A3lsUoGKJILdH5uGG%2BTWoiiO5kpzt40OvM1qjGuZAFEPRhegST2hiarVDH4BWs3K3dEGVu4RRKOpX%0AO4O%2FjkRTPurFvP1ATKE63jlyympVB8BP1mfC%2BNRnpZtBHeS8Ud04iwlNfT8c3UjW4jCx6afDyL10%0A4dr4y7aw3fnOSpfsNa1j6s77f2vxz4Y2xU%2F0XyaSzPs5CWieNE9NZS9cs1yhv6cg2kSDqp0rtx4B%0A2aR%2BfxaaqLPfEi42YF6Yx1WGul8%3D\",\"buttonTextInfo\":\"预订\"},{\"queryLeftNewDTO\":{\"train_no\":\"6i000G291403\",\"station_train_code\":\"G2914\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NFZ\",\"end_station_name\":\"南宁东\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"12:22\",\"arrive_time\":\"15:47\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"03:25\",\"canWebBuy\":\"Y\",\"lishiValue\":\"205\",\"yp_info\":\"o1j8fSuVqRlhyAwAczYrViIYRd7BAFluJMP8VEb4zd9L%2FBtM\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170204\",\"seat_feature\":\"O3M393\",\"yp_ex\":\"O0M090\",\"train_seat_feature\":\"3\",\"seat_types\":\"OM9\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"06\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"17\",\"end_city_code\":\"1607\",\"yz_num\":\"--\",\"rz_num\":\"--\",\"yw_num\":\"--\",\"rw_num\":\"--\",\"gr_num\":\"--\",\"zy_num\":\"无\",\"ze_num\":\"1\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"--\",\"qt_num\":\"--\",\"swz_num\":\"3\"},\"secretStr\":\"7i2hh30WVIcu%2F03ABxBGGS8aM3AkL%2BIib%2FG9QoPylIAUi9dJ3iJp%2BeI7CEW8NF33d7R0kVG4iKUc%0Abo8P4obNl%2FKoTm2sf6GNQVgSkI9lIvK8mfKwX1REYF0my2BMR1lyvi83dbUstWjwC9Yfe%2BgIFynw%0Avv1e0lDowljbTDKnkyJ77oX%2Fcoi4hXqjrgleqdqlw71Es%2BxT06cfKjsVDXV7A0JT9zljhf%2Bpm0cc%0AaMAGTy%2BY4TWmeRXFeCSzX4Du9pQEvk3mrhVoDfCLu9Sq8wKKPjQGiWn47YRZu8fsh86KTOoK97Um%0AM73oSA%3D%3D\",\"buttonTextInfo\":\"预订\"}],\"messages\":[],\"validateMessages\":{}}";
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
