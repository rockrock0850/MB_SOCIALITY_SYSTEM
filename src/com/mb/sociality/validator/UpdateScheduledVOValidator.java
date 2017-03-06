package com.mb.sociality.validator;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mb.sociality.vo.api.UpdateScheduledVO;

@Component
public class UpdateScheduledVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateScheduledVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UpdateScheduledVO vo = (UpdateScheduledVO) target;
		if(vo != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				sdf.parse(vo.getStartDateTime());
			} catch (Exception e) {
				errors.rejectValue("startDateTime", null, "格式錯誤: yyyy-MM-dd HH:mm:ss[" + vo.getStartDateTime() + "]");
			}

			try {
				sdf.parse(vo.getEndDateTime());
			} catch (Exception e) {
				errors.rejectValue("endDateTime", null, "格式錯誤: yyyy-MM-dd HH:mm:ss[" + vo.getEndDateTime() + "]");
			}

			if(StringUtils.isNotBlank(vo.getEmailNotifyTimeStr())){
				try {
					if(StringUtils.isNotBlank(vo.getEmailNotifyTimeStr())){
						sdf.parse(vo.getEmailNotifyTimeStr());
					}
				} catch (Exception e) {
					errors.rejectValue("emailNotifyTimeStr", null, "格式錯誤: yyyy-MM-dd HH:mm:ss[" + vo.getEmailNotifyTimeStr() + "]");
				}
			}

			if(StringUtils.isNotBlank(vo.getAlarmTimeStr())){
				try {
					if(StringUtils.isNotBlank(vo.getAlarmTimeStr())){
						sdf.parse(vo.getAlarmTimeStr());
					}
				} catch (Exception e) {
					errors.rejectValue("alarmTimeStr", null, "格式錯誤: yyyy-MM-dd HH:mm:ss[" + vo.getAlarmTimeStr() + "]");
				}
			}
		}
	}

}