package com.mb.sociality.validator;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mb.sociality.vo.api.UpdateBusinessCardVO;

@Component
public class UpdateBusinessCardVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateBusinessCardVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UpdateBusinessCardVO vo = (UpdateBusinessCardVO) target;
		if(vo != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(vo.getBirthday());
			} catch (Exception e) {
				errors.rejectValue("birthday", null, "格式錯誤: yyyy-MM-dd[" + vo.getBirthday() + "]");
			}

			try {
				if(StringUtils.isNotBlank(vo.getFirstMeetTimeStr())){
					sdf.parse(vo.getFirstMeetTimeStr());
				}
			} catch (Exception e) {
				errors.rejectValue("firstMeetTimeStr", null, "格式錯誤: yyyy-MM-dd[" + vo.getFirstMeetTimeStr() + "]");
			}
		}
	}
}