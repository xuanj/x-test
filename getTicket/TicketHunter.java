/**   
 * @Title: GetCodeImg.java
 * @Package com.renfy.test
 * @Description: TODO
 * @author renfy 
 * @date 2013-7-24 ����04:59:58
 * @version V1.0   
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @ClassName GetCodeImg
 * @Description TODO
 * @author renfy
 * @date 2013-7-24 ����04:59:58
 *
 */
public class TicketHunter {
	
	private static final String myStore = "D:\\Users\\XUANJIAN671\\Downloads\\JAVA��12306��Ʊ�������\\ticket\\t1547";//store�ļ�
	private static final String codeImg = "D:\\Users\\XUANJIAN671\\Downloads\\JAVA��12306��Ʊ�������\\ticket\\chkcode1.png";//��½��֤�������ļ�
	private static final String loginUserName = "XXXXXXXXXX@126.com";//12306�˺�
	private static final String loginPassWord = "**********";//12306����
	
	public static void main(String[] args) throws Exception {
		
		//����HttpClient����
        HttpClient httpclient = zeroGoal();
        
        //���sessionId
        oneGoal(httpclient);
		
        //�����֤��
        twoGoal(httpclient);
		
        //��õ�½�����
		String threeResult = threeGoal(httpclient);
		
		//��½
		fourGoal(httpclient,threeResult);
		
		//��ѯ��Ʊ
		fiveGoal(httpclient);
		
	}

	/** 
	 * @Title: fiveGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-25 ����11:59:09
	 * @param @param httpclient
	 *
	 */
	private static List<String> fiveGoal(HttpClient httpclient) throws Exception{
		
		long times = new Date().getTime() + 1l * 24 * 60 * 60 * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String trainDate = "2013-09-11";//��ѯ�Ļ�Ʊʱ��
		trainDate = sdf.format(new Date(times));
		System.out.println("��ѯ�Ļ�Ʊ����:" + trainDate);
		System.out.println("Ĭ�ϲ�ѯ�ı������Ϻ�վ");
		String url = "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=queryLeftTicket" +
				"&orderRequest.train_date="+trainDate +
				"&orderRequest.from_station_telecode=BJP" +
				"&orderRequest.to_station_telecode=SHH" +
				"&orderRequest.train_no=" +
				"&trainPassType=QB" +
				"&trainClass=QB%23D%23Z%23T%23K%23QT%23" +
				"&includeStudent=00" +
				"&seatTypeAndNum=" +
				"&orderRequest.start_time_str=00%3A00--24%3A00";
		
        //���HttpGet����
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("x-requested-with", "XMLHttpRequest");
        httpGet.addHeader("Accept-Language", "zh-cn");
        httpGet.addHeader("Referer", "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init");
        httpGet.addHeader("Accept:", "text/plain, */*");
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)");
        httpGet.addHeader("Host", "dynamic.12306.cn");
        httpGet.addHeader("DNT", "1");
        httpGet.addHeader("Connection", "Keep-Alive");
        
        //��������
        HttpResponse response = httpclient.execute(httpGet);
        //�������ֵ
        Header hr = response.getFirstHeader("Content-Encoding");
        System.out.println("5");
        if(hr != null){
        	System.out.println("Content-Encoding:" + hr.getValue());
        }
		
		//
		String result = "";
		InputStream is = null;
		if(hr != null && "gzip".equalsIgnoreCase(hr.getValue())){
			//�������ֵ
		      is = new GzipDecompressingEntity(response.getEntity()).getContent();
		}else{
			System.out.println("no gzip");
			//�������ֵ
		      is = response.getEntity().getContent();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	      String line = "";
	      while((line = br.readLine())!=null){
//	          System.out.println(line);
	          result+=line;
	      }

	      is.close();
	      br.close();
      
		httpGet.releaseConnection();
		//
		List<String> list = new ArrayList<String>(10);
		
		//
		if(!"".equals(result)){
			String[] ss = result.split("\\\\n");
			for(String s : ss){
				if(s.indexOf("onclick=javascript:getSelected") > -1){
//					System.out.println(s);
					
					int index = s.indexOf("onclick=javascript:getSelected") + "onclick=javascript:getSelected".length() + 2;
					s = s.substring(index).split("Ԥ        ��")[0];
					s = s.substring(0,s.length() - 3);
					System.out.println(s);
					list.add(s);
				}
			}
		}
		System.out.println("--------");
		
		return list;
	}

	/** 
	 * @Title: zeroGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-25 ����11:08:33
	 * @param @return
	 *
	 */
	private static HttpClient zeroGoal() throws Exception{
		//���httpclient����
        HttpClient httpclient = new DefaultHttpClient();
        //����ܳ׿�
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File(myStore));
        //�ܳ׿������
        trustStore.load(instream, "123456".toCharArray());
        //ע���ܳ׿�
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        //��У������
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme sch = new Scheme("https", 443, socketFactory);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        
        return httpclient;
	}

	/** 
	 * @Title: twoGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-25 ����11:05:35
	 * @param @param httpclient
	 *
	 */
	private static void twoGoal(HttpClient httpclient) throws Exception {
		String urlString2 = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand&"+Math.random();
        //���HttpGet����
        HttpGet httpGet2 = new HttpGet(urlString2);
        
        //��������
        HttpResponse response2 = httpclient.execute(httpGet2);
        
        
        Header[] hrs2 = response2.getHeaders("Set-Cookie");
        System.out.println("2");
		for(Header hr : hrs2){
			System.out.println(hr.getValue());
		}
		System.out.println("--------");
        
        //�������ֵ
        InputStream is = response2.getEntity().getContent();
        
        BufferedInputStream in = new BufferedInputStream(is);

		// �����ͼƬ�ļ���
		File img = new File(codeImg);

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(img));

		byte[] buf = new byte[1];

		while (in.read(buf) != -1) {
			out.write(buf);
		}

		in.close();
		out.close();
		
		httpGet2.releaseConnection();
		
	}

	/** 
	 * @Title: oneGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-25 ����11:04:34
	 * @param @param httpclient
	 *
	 */
	private static void oneGoal(HttpClient httpclient) throws Exception{
		String urlString = "https://ad.12306.cn/sdk/webservice/rest/appService/getAdAppInfo.json?placementNo=0004&clientType=2&billMaterialsId=03e9a49b163a46519739a85e4cf15bd0";
        //���HttpGet����
        HttpGet httpGet = new HttpGet(urlString);
        //��������
        HttpResponse response = httpclient.execute(httpGet);
        //�������ֵ
        Header[] hrs = response.getHeaders("Set-Cookie");
        System.out.println("1");
		for(Header hr : hrs){
			System.out.println(hr.getValue());
		}
		System.out.println("--------");
		
		httpGet.releaseConnection();
		//
		
	}

	/** 
	 * @Title: FourGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-24 ����05:43:27
	 * @param @param httpclient
	 *
	 */
	private static void fourGoal(HttpClient httpclient,String threeResult) throws Exception{

		String loginRand = threeResult;
		String randCode = "";
		Scanner sc = new Scanner(System.in);
		System.out.println(" ��������֤��,·��Ϊ"+codeImg+":");
		String chkCode=sc.next();
		randCode = chkCode.toUpperCase();
		
		String urlString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=login";
		HttpPost post = new HttpPost(urlString);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("loginRand", loginRand));
		nvps.add(new BasicNameValuePair("refundLogin", "N"));
		nvps.add(new BasicNameValuePair("refundFlag", "Y"));
		nvps.add(new BasicNameValuePair("loginUser.user_name", loginUserName));
		nvps.add(new BasicNameValuePair("user.password", loginPassWord));
		nvps.add(new BasicNameValuePair("randCode", randCode));
		nvps.add(new BasicNameValuePair("randErrorFocus", "focus"));
		
		post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        //��������
        HttpResponse response = httpclient.execute(post);
        
        Header[] hrs = response.getHeaders("Set-Cookie");
        System.out.println(randCode);
        System.out.println("4");
		for(Header hr : hrs){
			System.out.println(hr.getValue());
		}
		System.out.println("--------");
        
        post.releaseConnection();
		
	}

	/** 
	 * @Title: ThreeGoal 
	 * @Description: TODO
	 * @author renfy
	 * @date 2013-7-24 ����05:39:35
	 * @param 
	 *
	 */
	private static String threeGoal(HttpClient httpclient) throws Exception{
		
		String urlString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=loginAysnSuggest";
        //���HttpGet����
//        HttpGet httpGet = new HttpGet(urlString);
        HttpPost httpPost = new HttpPost(urlString);
        //��������
        HttpResponse response = httpclient.execute(httpPost);
        
        Header[] hrs = response.getHeaders("Set-Cookie");
        System.out.println("3");
		for(Header hr : hrs){
			System.out.println(hr.getValue());
		}
		System.out.println("--------");
        
        //�������ֵ
        InputStream is = response.getEntity().getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String result = "";
        String line = "";
        while((line = br.readLine())!=null){
//            System.out.println(line);
            result+=line;
        }

        is.close();
        br.close();
        
        httpPost.releaseConnection();
        
        
        result = result.substring(14);
        result = result.split("\"")[0];
        
//        System.out.println(result);
		
		return result;
	}
}