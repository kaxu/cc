package com.cc.student.converter;

import com.cc.student.model.Student;
import com.cc.student.vo.StudentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StudentToVoConverter implements Converter<Student, StudentVo> {

	@Override
	public StudentVo convert(Student source) {
		StudentVo target=new StudentVo();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}
