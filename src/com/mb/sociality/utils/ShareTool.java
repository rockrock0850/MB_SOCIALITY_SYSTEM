package com.mb.sociality.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.SimpleFieldVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

@Log4j
public class ShareTool{
	private static Logger log = Logger.getLogger(ShareTool.class);
	
	/**
	 * 日期轉字串
	 * @return
	 */
	public static String dateToStringV2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//進行轉換
		String dateString = sdf.format(date);
		return dateString;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 字串轉日期: yyyy/MM/dd HH:mm:ss
	 * @return
	 */
	public static Date stringToDate(String string) {
		String pattern = "yyyy/MM/dd HH:mm:ss";
		try {
			Date parseDate = new SimpleDateFormat(pattern).parse(string);
			return parseDate;
		} catch (ParseException e) {
			log.error(e, e);
		}
		return new Date();
	}
	
	/**
	 * 字串轉日期
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String string, String pattern) {
		try {
			if(StringUtils.isNotBlank(string)){
				Date parseDate = new SimpleDateFormat(pattern).parse(string);
				return parseDate;
			}
		} catch (ParseException e) {
			log.error(e, e);
		}
		return new Date();
	}
	
	/**
	 * 取當前時間
	 * @return
	 */
	public static Date nowDate() {
		Date date = new Date();
		return date;
	}
	
	/**
	 * 新的guid
	 * @return
	 */
	public static String newGUID() {
		String guid = UUID.randomUUID().toString();
		return guid;
	}
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String toStringBuilder(Object object){
		try {
			if(object instanceof String || object instanceof Map || 
					object instanceof Integer || object instanceof Double || object instanceof Long){
				return object.toString();
			}
			
			if(object instanceof List){
				List<?> list = (List<?>) object;
				
				if(list == null || list.isEmpty()){
					return "";
				}
				
				/*
				 * List 最多10筆
				 */
				int size = list.size() > 10 ? 10 : list.size();
				list = list.subList(0, size);
				
				List<String> dataList = new ArrayList<>();
				for(Object o : list){
					dataList.add(toStringBuilder(o));
				}

				return StringUtils.join(dataList, ", ");
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		
		return ReflectionToStringBuilder.toString(object, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static String toJsonStringBuilder(Object object){
		JSONObject json = new JSONObject();
		try {
			json = JSONObject.fromObject(object);
		} catch (Exception e) {
			log.error(e, e);
		}
		return json.toString();
	}
	
	/**
	 * 
	 * @param prop
	 * @return
	 */
	public static String getProperty(String prop){
		return ResourceBundle.getBundle("system").getString(prop);
	}
	
	/**
	 * 
	 * @param file
	 * @param prop
	 * @return
	 */
	public static String getProperty(String file, String prop){
		return ResourceBundle.getBundle(file).getString(prop);
	}
	
	/**
	 * Base64 decode->AES decode
	 * 
	 * @param iv
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String iv, String key, String data) throws Exception{
		try {
			if(StringUtils.isBlank(iv) || StringUtils.isBlank(key) || StringUtils.isBlank(data)){
				return "";
			}
			
			byte[] decode = Base64.getDecoder().decode(data);
			
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] doFinal = cipher.doFinal(decode);
			
			data = new String(doFinal, "utf-8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return data;
	}

	/**
	 * AES encode->Base64 encode
	 * 
	 * @param iv
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String iv, String key, String data){
		String encode = "";
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ciper.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] doFinal = ciper.doFinal(data.getBytes());//data.toString()
			
			encode = Base64.getEncoder().encodeToString(doFinal);
		} catch (Exception e) {
			log.error(e, e);
		}
		return encode;
	}

	/**
	 * 
	 * @param url
	 * @param data
	 * @param requestMethod
	 * @return
	 * @throws IOException
	 */
	public static ResponseVO callApi(String url, String data, String requestMethod){
		ResponseVO responseVO = new ResponseVO();
		try {
			URL obj = null;
			HttpURLConnection con = null;
			StringBuffer result = new StringBuffer();
			
			requestMethod = StringUtils.upperCase(requestMethod);
			switch (requestMethod) {
				case "POST":
					obj = new URL(url);
					con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod(requestMethod);
					con.setRequestProperty("Content-Type", "application/json");
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.write(data.getBytes("utf-8"));
					wr.flush();
					wr.close();
					break;
					
				case "GET":
					obj = new URL(url + "/" + data);
					con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod(requestMethod);
					con.setRequestProperty("Content-Type", "application/json");
					break;
					
				case "PUT":
					obj = new URL(url);
					con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod(requestMethod);
					con.setRequestProperty("Content-Type", "application/json");
					con.setDoOutput(true);
					DataOutputStream wr2 = new DataOutputStream(con.getOutputStream());
					wr2.write(data.getBytes("utf-8"));
					wr2.flush();
					wr2.close();
					break;
					
				case "DELETE":
					obj = new URL(url + "/" + data);
					con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod(requestMethod);
					con.setRequestProperty("Content-Type", "application/json");
					break;
			}
		
			int responseCode = con.getResponseCode();
			log.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
			in.close();
			
			responseVO = new ObjectMapper().readValue(result.toString(), ResponseVO.class);
			
		} catch (Exception e) {
			log.error(e, e);
		}
		
		return responseVO;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<SimpleFieldVO> simpleFieldError(List<FieldError> list){
		List<SimpleFieldVO> fieldList = new ArrayList<>();
		try {
			for(FieldError fieldError : list){
				SimpleFieldVO field = new SimpleFieldVO();
				field.setField(fieldError.getField());
				field.setMessage(fieldError.getDefaultMessage());
				fieldList.add(field);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return fieldList;
	}
	
	/**
	 * 
	 * @param multipartFile
	 * @param account
	 * @return
	 */
	public static File getUploadFile(String root, MultipartFile multipartFile, String account){
		try {
			if(multipartFile != null && !multipartFile.isEmpty()){
				String[] retvals = multipartFile.getOriginalFilename().split("\\.");
				File file = new File(root + Constant.IMG_ROOT + "/" +  account + "." + retvals[1]);
				if (!file.exists()) {
					file.mkdirs();
				}
				multipartFile.transferTo(file);
				return file;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param baseUri
	 * @param img
	 * @return
	 */
	public static String getImgSrc(String baseUri, String img){
		String src = "";
		try {
			src = baseUri + img.substring(img.indexOf("/pic"));
		} catch (Exception e) {
			log.error(e, e);
		}
		return src;
	}
	
	/**
	 * 上傳圖片
	 * 
	 * @param baseDir
	 * @param image
	 * @param name
	 * @return
	 */
	public static String uploadImg(String path, String image, String name) {
		/*
		 * 建立圖片檔案
		 */
		
		try {
			BufferedImage bufferedImage = null;
			File file = new File(path);
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decryptFrom = decoder.decode(image);
			if (!file.exists()) {
				file.mkdirs();
			}
			ByteArrayInputStream bis = new ByteArrayInputStream(decryptFrom);
            bufferedImage = ImageIO.read(bis);
            bis.close();
            ImageIO.write(bufferedImage, "jpeg", new File(path)); 
            log.debug("圖片上傳完成: " + decryptFrom.length);

	        return path;
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}
}
