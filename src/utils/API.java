package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class cung cap cac phung thuc giup gui requet len server va nhan du lieu tra ve
 * Date: 07/12/2021
 * @author Nguyen Van Dung
 * @version 1.0
 *
 */
public class API {

	/**
	 * Thuoc tinh format ngay thang theo dinh dang
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Thuoc tinh giup log ra console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * phuong thuc giup goi den cac api get
	 * @param url: duong dan toi server can request
	 * @param token: doan ma dung de xac thuc nguoi dung
	 * @return return: phan hoi tu server (dang String)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		//phan 1: setup
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = setUpConnection(token, line_api_url, "GET");
		
		//phan 2: doc va tra ve tu server
		return readRespone(conn);
	}


	// Nguyen Van Dung - 20183900
	private static String readRespone(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}



	private static HttpURLConnection setUpConnection(String token, URL line_api_url, String method) throws IOException, ProtocolException {
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}

	int var;

	/**
	 * phuong thuc giup goi cac api post
	 * @param url: duong dan toi server can request
	 * @param data: du lieu dua len server de xu ly (dang Json)
	 * @return return: phan hoi tu server (dang String)
	 * @throws IOException
	 */
	public static String post(String url, String data) throws IOException {
		//phan 1: setup
		allowMethods("PATCH");
		URL line_api_url = new URL(url);
		String payload = data;
		String token = "";
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		HttpURLConnection conn = setUpConnection(token, line_api_url, "POST");
//		conn.setRequestProperty("Authorization", "Bearer " + token);
		
		//phan2: gui du lieu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		
		//phan3: doc du lieu gui ve tu server
		return readRespone(conn);
	}
	
	

	/**
	 * phuong thuc cho phep goi cac API khac nhu PUT, PATH, ...
	 * @param methods: giao thc cho cho phep (PATH, PUT,...)
	 * @deprecated
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
