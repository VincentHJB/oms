package com.feifang.oms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Setting {
	//============= AsyncHttpClient 设置 ================
	@Value("${http.client.request_time_out}")
	public  int Http_Client_Request_Time_Out;
	
	@Value("${http.client.connnection_time_out}")
	public int Http_Client_Conn_Time_Out;
	
	@Value("${http.client.retry_times}")
	public int Http_Client_Retry_Times;
	
	@Value("${http.client.future.request_time_out}")
	public int Http_Client_Future_Request_Time_Out;

	@Value("${user.upload.avtar.base.dir}")
	public String User_Upload_Avtar_Base_Dir;

	@Value("${user.upload.avtar.base.virt}")
	public String User_Upload_Avtar_Base_Virt;

	@Value("${user.upload.avtar.domain}")
	public String User_Upload_Avtar_Domain;
	
	@Value("${server_pay_notify_url}")
	public String server_pay_notify_url;
	
	@Value("${server_pay_certificate_key}")
	public String server_pay_certificate_key;
}
  