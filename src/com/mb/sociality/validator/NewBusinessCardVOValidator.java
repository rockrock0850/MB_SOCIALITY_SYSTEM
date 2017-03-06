package com.mb.sociality.validator;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mb.sociality.vo.api.NewBusinessCardVO;

@Component
public class NewBusinessCardVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return NewBusinessCardVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewBusinessCardVO vo = (NewBusinessCardVO) target;
		if(vo != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(vo.getBirthday())){
				try {
					sdf.parse(vo.getBirthday());
				} catch (Exception e) {
					errors.rejectValue("birthday", null, "格式錯誤: yyyy-MM-dd[" + vo.getBirthday() + "]");
				}
			}

			if(StringUtils.isNotBlank(vo.getFirstMeetTimeStr())){
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

}
