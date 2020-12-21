package com.example.demo.ageChecker;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BirthdayForm {
	@NotNull
	@DateTimeFormat(pattern="yyyymmdd")
	private Date birthday; // 誕生日
}
