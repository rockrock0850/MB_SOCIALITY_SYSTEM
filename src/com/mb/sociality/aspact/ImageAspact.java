package com.mb.sociality.aspact;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.common.net.MediaType;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.User;
import com.mb.sociality.setting.ValidSetting;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.form.CardCreateVO;
import com.mb.sociality.vo.form.UserCreateVO;
import com.mb.sociality.vo.form.UserUpdateVO;

//@Controller
//@Aspect
public class ImageAspact {
	@Before("execution(* com.mb.sociality.controller..*.*(..))")
	public void before(JoinPoint joinPoint) throws Exception{
		CardCreateVO user = null;
		BindingResult result = null;
		HttpServletRequest request = null;
		
		Object[] argList = joinPoint.getArgs();
		for(Object arg : argList){
			if (arg instanceof HttpServletRequest) {
				request = (HttpServletRequest) arg;
			}
			
			if (arg instanceof CardCreateVO || arg instanceof CardCreateVO) {
				user = (CardCreateVO) arg;
			} 
			
			if (arg instanceof BindingResult) {
				result = (BindingResult) arg;
			}
		}
		
		if(user != null && result != null){
			Field field = user.getClass().getDeclaredField("file");
			ValidSetting validSetting = field.getDeclaredAnnotation(ValidSetting.class);
		    String root = request.getSession().getServletContext().getRealPath("").
		    		replace(".metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/", "");
			File file = ShareTool.getUploadFile(root, user.getFile(), user.getName());
			if(validSetting.isImg() && file != null){
				String type = Files.probeContentType(Paths.get(file.getAbsolutePath())).split("/")[0];
				if(!StringUtils.equals(type, MediaType.ANY_IMAGE_TYPE.type())){
					FieldError fieldError = new FieldError(user.getClass().getSimpleName(), "file", "請選擇圖片類型的檔案");
					result.addError(fieldError);
					file.delete();
				}
			}
		}
	}
}
