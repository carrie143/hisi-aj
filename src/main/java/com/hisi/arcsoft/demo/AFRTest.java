package com.hisi.arcsoft.demo;
import com.hisi.arcsoft.AFD_FSDK_USE;



public class AFRTest {
	/*public static final String APPID = "3eDu2UTVungPP9xX5LX2aoFQEYFso2Q7SZHqx7XZKYYQ";
	public static final String FD_SDKKEY = "BrL5zTivvfqqGbWDvnMH98bXyU8Da8AYc6daPKtmoUcp";
	public static final String FR_SDKKEY = "BrL5zTivvfqqGbWDvnMH98bf8sPQQ9Phbafe8zKnceRR";*/
	
	/*public static final String APPID = "JDN9ZMCnynVvdUEEvbeiNQ3rxGVgmbJWBiizNPw5qRKg";
	public static final String FD_SDKKEY = "AAz8AmyQDso1RYw42zYHdKE81Q7TwbSTYMZZzYtunSUT";
	public static final String FR_SDKKEY = "AAz8AmyQDso1RYw42zYHdKEFAoNfLLupeRXiSUapw5eD";*/
	
	public static final String APPID = "3eDu2UTVungPP9xX5LX2aoFQEYFso2Q7SZHqx7XZKYYQ";
	public static final String FD_SDKKEY = "BrL5zTivvfqqGbWDvnMH98bXyU8Da8AYc6daPKtmoUcp";
	public static final String FR_SDKKEY = "BrL5zTivvfqqGbWDvnMH98bf8sPQQ9Phbafe8zKnceRR";
	
	public static final String filePathA = "C:\\1.jpg";
	public static final String filePathB = "C:\\2.jpg";
	
	/*public static final int FD_WORKBUF_SIZE = 20*1024*1024;
	public static final int FR_WORKBUF_SIZE = 40*1024*1024;*/
	public static final int MAX_FACE_NUM = 50;
	
	public static final boolean bUseYUVFile = false;
	
    public static void main(String[] args) {
      
    	float result = AFD_FSDK_USE.doFD(filePathA, filePathB, APPID,
				FD_SDKKEY, FR_SDKKEY);
		System.out.println(result);
		
	}
}
