package org.example.customer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 도메인 : 손님, 메뉴판, 돈까스/냉면/만두(o), 요리사(o), 요리(o)
 * 관계 : 손님 -- 메뉴판
 *       손님 -- 요리사
 *       요리사 -- 요리
 * 동적인 객체를 정적인 타입으로 추상화
 *     : 손님 -- 손님 타입
 *       돈까스/냉면/만두 -- 요리 타입
 *       메뉴판 -- 메뉴판 타입
 *       메뉴 -- 메뉴 타입
 *
 */
public class CustomerTest {

	@DisplayName("메뉴이름에 해당하는 요리를 주문한다.")
	@Test
	void orderTest() {
		Customer customer = new Customer();
		Menu menu = new Menu(List.of(new MenuItem("돈까스", 5000), new MenuItem("냉면", 7000)));
		Cooking cooking = new Cooking();

		assertThatCode(() -> customer.order("돈까스", menu, cooking))
			.doesNotThrowAnyException();

	}
}
