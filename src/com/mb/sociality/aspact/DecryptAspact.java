package com.mb.sociality.aspact;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;

import com.mb.sociality.setting.SecretSetting;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.RequestVO;

@Controller
@Aspect
public class DecryptAspact {
	@Before("execution(* com.mb.sociality.controller.api..*.*(..))")
	public void before(JoinPoint joinPoint) throws Exception{
		Object[] argList = joinPoint.getArgs();
		for(Object arg : argList){
			if(arg instanceof RequestVO){
				RequestVO requestVO = (RequestVO) arg;
				Field field = requestVO.getClass().getDeclaredField("data");
				SecretSetting secretSetting = field.getAnnotation(SecretSetting.class);
				if(secretSetting.decrypt()){
					String iv = ShareTool.getProperty("aes.iv");
					String key = ShareTool.getProperty("aes.key");
					requestVO.setData(ShareTool.decrypt(iv, key, requestVO.getData()));
				}
			}
		}
	}
}
