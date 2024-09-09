package org.example.grade;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GredeCalculatorTest {
	// 학점계산기 도메인 : 이수한 과목(객체지향 프로그래밍, 자료구조, 중국어 회화), 학점 계산
	// 객체지향 프로그래밍, 자료구조, 중국어 회화 --> 과목(코스) 클래스

	/**
	 * 핵심 포인트
	 */
	// 이수한 과목을 전달하여 평균학점 계산 요청 ----> 학점 계산기 ----> (학점수x교과목 평점)의 합계 ---> 과목(코스)
	//												  ----> 수강신청 총 학점 수       ---> 과목(코스)
	@DisplayName("평균 학점을 계산한다.")
	@Test
	void calculateGradeTest() {
		// 이수한 과목 리스트 생성
		List<Course> courses = List.of(new Course("OOP", 3, "A+"),
			new Course("자료구조", 3, "A+"));

		// 학점 계산기가 생성될 때 이수한 과목들을 전달
		GradeCalculator gradeCalculator = new GradeCalculator(courses);
		// 이수한 과목들을 가지고 성적 계산후 성적 전달
		double gradeResult = gradeCalculator.calculateGrade();

		assertThat(gradeResult).isEqualTo(4.5);
	}
}
