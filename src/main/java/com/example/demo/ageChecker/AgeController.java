package com.example.demo.ageChecker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AgeController {

	//フォーマット用メソッド
	public static LocalDate convertStringToLocalDate(String date,DateTimeFormatter formatter) {
		return LocalDate.parse(date,formatter);
	}

	@GetMapping("/AgeSpringboot")
	public String showForm(BirthdayForm form) {
		return "index";
	}


	//年齢計算メソッド
	@PostMapping("/result")
	public String postRequest(@Valid @ModelAttribute BirthdayForm form,
			BindingResult bindingResult,Model model) {

		if ( bindingResult. hasErrors() ) {
			return "index";
		}

		//本日
		LocalDate today = LocalDate.now();
		//誕生日
		Date d = form.getBirthday();
		LocalDate birthday = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//年齢計算(時刻基準)
		long age=ChronoUnit.YEARS.between(birthday, today);

		model. addAttribute("age", age);

		return "result";
	}
}
