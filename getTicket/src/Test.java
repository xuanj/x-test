
import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.*;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Test {

	private static final Logger LOG = LogManager.getLogger(Test.class);
	
	
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "失败！遇到严重错误，请重启！");
		}
	}

	public static String SendGet(String url) throws IOException {
		trustAllHosts();
		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		// 将string转成url对象
		URL realUrl = new URL(url);
		// 初始化一个链接到那个url的连接
		// URLConnection connection =realUrl.openConnection();
		HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
		// 开始实际的连接
		// connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		connection.connect();
		@SuppressWarnings("unused")
		String charset = "UTF-8";
		String encoding = connection.getHeaderField("Content-Type");
		ArrayList<String> temp = RegexString(encoding, "charset=(.*+)");
		temp.add("0");
		String type = temp.toArray(new String[0])[0];// .toString();
		// System.out.println( type);
		if (type == "0")
			type = "UTF-8";
		InputStream beforeDecompress = connection.getInputStream();
		try {
			GZIPInputStream afterDecompress = new GZIPInputStream(beforeDecompress);
			in = new BufferedReader(new InputStreamReader(afterDecompress, type)); // 尝试用gzip解压，失败则直接解压
			// 用来临时存储抓取到的每一行的数据
		} catch (Exception e3) {

			in = new BufferedReader(new InputStreamReader(beforeDecompress, type));
		}

		// 初始化 BufferedReader输入流来读取URL的响应

		String line;
		while ((line = in.readLine()) != null) {
			// 遍历抓取到的每一行并将其存储到result里面
			result += line + "\n";
		}

		try {
			if (in != null) {
				in.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public static String SendGet(String url, String type) throws IOException {
		trustAllHosts();
		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		// 将string转成url对象
		URL realUrl = new URL(url);
		// 初始化一个链接到那个url的连接
		// URLConnection connection =realUrl.openConnection();
		HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
		// 开始实际的连接
		// connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		// Post
		connection.setDoOutput(true);
		connection.setDoInput(true);
		LOG.debug("开始连接...");
		connection.connect();
		LOG.debug("连接成功");
		long st = System.currentTimeMillis();
		InputStream beforeDecompress = connection.getInputStream();
		LOG.debug("成功返回");
		try {
			GZIPInputStream afterDecompress = new GZIPInputStream(beforeDecompress);
			in = new BufferedReader(new InputStreamReader(afterDecompress, type)); // 尝试用gzip解压，失败则直接解压
			// 用来临时存储抓取到的每一行的数据
		} catch (Exception e3) {
			System.err.println(e3);
			in = new BufferedReader(new InputStreamReader(beforeDecompress, type));
		}
		System.err.println("=======================耗时" + (System.currentTimeMillis() - st));
		// 初始化 BufferedReader输入流来读取URL的响应

		String line;
		while ((line = in.readLine()) != null) {
			// 遍历抓取到的每一行并将其存储到result里面
			result += line + "\n";
		}
		// 使用finally来关闭输入流

		try {
			if (in != null) {
				in.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return result;
	}

	static ArrayList<String> RegexString(String targetStr, String patternStr) {
		// 预定义一个ArrayList来存储结果
		ArrayList<String> results = new ArrayList<String>();
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		boolean isFind = matcher.find();
		while (isFind) {
			// 添加成功匹配的结果
			results.add(matcher.group(1));
			// 继续查找下一个匹配对象
			isFind = matcher.find();
		}
		return results;
	}

	public static void main(String[] args) throws Exception {
		updateStationList();
	}

	public static void updateStationList() throws UnsupportedEncodingException {
		System.out.println("更新信息开始");
		String url = "https://kyfw.12306.cn/otn/lcxxcx/init";
		String result;
		try {
			result = SendGet(url, "utf-8");
			ArrayList<String> url2 = RegexString(result, "station_version=([^\"]*)\"");
			// System.out.println(url2);
			// result=RegexString(result,"|([^\"]*)\"}").toString();
			// ArrayList<String> target= RegexString(result,"async
			// src=\"([^\"]*)");
			url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?" + url2.get(0);
			result = SendGet(url, "utf-8");
			System.err.println("更新结果：\n" + result);
		} catch (IOException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "更新信息失败！");
			return;
		}
		File file = new File("station.txt");
		try {
			file.createNewFile(); // 创建文件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// byte bt[] = new byte[1024];
		// bt = result.getBytes();
		try {
			BufferedWriter in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			try {
				in.write(result);
				in.close();
				// boolean success=true;
				JOptionPane.showMessageDialog(null, "更新信息成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(result);
	}

	public static String readTxtFile(String filePath, String encoding) {
		String result = "";

		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					result += lineTxt;
				}
				read.close();
			} else {
				JOptionPane.showMessageDialog(null, "找不到车站信息文件！请更新车站信息！");
				return "";
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "文件读取出错，请更新车站信息！");
			e.printStackTrace();
		}
		return result;

	}

	public static String getStationName(String name) {
		String result;
		String get = readTxtFile("station.txt", "UTF-8");
		if (get.length() == 0)
			return "";
		try {
			result = RegexString(get, name + "[^\u2E80-\u9FFF]([A-Z]*)").get(0);
			if (result.length() == 0)
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}

	public static String finishSearchUrl(String fromstation, String tostation, String date, String type) {
		String url = "https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=" + date
				+ "&leftTicketDTO.from_station=";
		String aString = getStationName(fromstation);
		if (aString.length() == 0)
			return "";
		String bString = getStationName(tostation);
		if (bString.length() == 0)
			return "";
		return url + aString + "&leftTicketDTO.to_station=" + bString + "&purpose_codes=" + type;

	}

	public static ArrayList<String> getTrainList(String data) {
		ArrayList<String> trainlist = RegexString(data, "\"station_train_code\":\"([^\"]+)");
		return trainlist;
	}

	public static String getTrainInfo(String train_num, String type, String data) {// type为需求的票的类型
		// #tz特等座 gr高级软卧 yz硬座
		try {
			String temp = RegexString(data, "(" + train_num + "\",\"[^}]+})").get(0);
			return RegexString(temp, type + "\":\"([^\"]+)\"").get(0);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			// JOptionPane.showMessageDialog(null,"找不到该车次信息");
		}
		return "";
	}
}
