import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
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
//						String result = Test.SendGet(url, "UTF-8");
						String result = "{\"validateMessagesShowId\":\"_validatorMessage\",\"status\":true,\"httpstatus\":200,\"data\":[{\"queryLeftNewDTO\":{\"train_no\":\"6i000G291204\",\"station_train_code\":\"G2912\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NFZ\",\"end_station_name\":\"南宁东\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"07:04\",\"arrive_time\":\"10:22\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"03:18\",\"canWebBuy\":\"N\",\"lishiValue\":\"198\",\"yp_info\":\"1TTzgiSp8yjQoKNO7cKClG%2BSD86iRpNeEVUqylXlyXI0mC0V\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170126\",\"seat_feature\":\"O3M393\",\"yp_ex\":\"O0M090\",\"train_seat_feature\":\"3\",\"seat_types\":\"OM9\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"05\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"17\",\"end_city_code\":\"1607\",\"yz_num\":\"--\",\"rz_num\":\"--\",\"yw_num\":\"--\",\"rw_num\":\"--\",\"gr_num\":\"--\",\"zy_num\":\"无\",\"ze_num\":\"无\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"--\",\"qt_num\":\"--\",\"swz_num\":\"无\"},\"secretStr\":\"\",\"buttonTextInfo\":\"预订\"},{\"queryLeftNewDTO\":{\"train_no\":\"690000K5860F\",\"station_train_code\":\"K586\",\"start_station_telecode\":\"OSQ\",\"start_station_name\":\"深圳西\",\"end_station_telecode\":\"CDW\",\"end_station_name\":\"成都\",\"from_station_telecode\":\"OSQ\",\"from_station_name\":\"深圳西\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"09:00\",\"arrive_time\":\"21:57\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"12:57\",\"canWebBuy\":\"N\",\"lishiValue\":\"777\",\"yp_info\":\"rMjFUt0mM1BfTvHxrmYAnyvszHxejewxdG2zYiWCBHnDSIXm0UOYQWVeqts%3D\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170126\",\"seat_feature\":\"W3431333\",\"yp_ex\":\"10401030\",\"train_seat_feature\":\"3\",\"seat_types\":\"1413\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"10\",\"control_day\":29,\"sale_time\":\"1030\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"19\",\"end_city_code\":\"1707\",\"yz_num\":\"无\",\"rz_num\":\"--\",\"yw_num\":\"无\",\"rw_num\":\"无\",\"gr_num\":\"--\",\"zy_num\":\"--\",\"ze_num\":\"--\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"无\",\"qt_num\":\"--\",\"swz_num\":\"--\"},\"secretStr\":\"\",\"buttonTextInfo\":\"预订\"},{\"queryLeftNewDTO\":{\"train_no\":\"6i000G291403\",\"station_train_code\":\"G2914\",\"start_station_telecode\":\"IOQ\",\"start_station_name\":\"深圳北\",\"end_station_telecode\":\"NFZ\",\"end_station_name\":\"南宁东\",\"from_station_telecode\":\"IOQ\",\"from_station_name\":\"深圳北\",\"to_station_telecode\":\"GGZ\",\"to_station_name\":\"贵港\",\"start_time\":\"12:22\",\"arrive_time\":\"15:47\",\"day_difference\":\"0\",\"train_class_name\":\"\",\"lishi\":\"03:25\",\"canWebBuy\":\"N\",\"lishiValue\":\"205\",\"yp_info\":\"1TTzgiSp8yjQoKNO7cKClG%2BSD86iRpNeEVUqylXlyXI0mC0V\",\"control_train_day\":\"20991231\",\"start_train_date\":\"20170126\",\"seat_feature\":\"O3M393\",\"yp_ex\":\"O0M090\",\"train_seat_feature\":\"3\",\"seat_types\":\"OM9\",\"location_code\":\"Q9\",\"from_station_no\":\"01\",\"to_station_no\":\"06\",\"control_day\":29,\"sale_time\":\"0900\",\"is_support_card\":\"0\",\"controlled_train_flag\":\"0\",\"controlled_train_message\":\"正常车次，不受控\",\"train_type_code\":\"2\",\"start_province_code\":\"16\",\"start_city_code\":\"1505\",\"end_province_code\":\"17\",\"end_city_code\":\"1607\",\"yz_num\":\"--\",\"rz_num\":\"--\",\"yw_num\":\"--\",\"rw_num\":\"--\",\"gr_num\":\"--\",\"zy_num\":\"无\",\"ze_num\":\"无\",\"tz_num\":\"--\",\"gg_num\":\"--\",\"yb_num\":\"--\",\"wz_num\":\"--\",\"qt_num\":\"--\",\"swz_num\":\"无\"},\"secretStr\":\"\",\"buttonTextInfo\":\"预订\"}],\"messages\":[],\"validateMessages\":{}}";
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
		try {
			info = (TrainInfo)JSON.parseObject(json.toJSONString(), TrainInfo.class);
			LOG.debug(JSON.toJSONString(info));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return info;
	}
	

	public static void getProperties() {
		LOG.info("读取属性文件...");
		Properties props = new Properties();
		try {
			props.load(new InputStreamReader(TicketConsole.class.getClassLoader().getResourceAsStream("ticket.properties"), "UTF-8"));
			LOG.info(JSONObject.toJSONString(info));
			BeanUtils.copyProperties(info, props);
		} catch (Exception e) {
			LOG.error("读取属性文件异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

}
