package com.step3;

import java.util.Scanner;


public class Scan2 {

	public static void main(String[] args) {
		System.out.println("메뉴를 선택하세요.[새게임:1, 정답확인:2, 종료:3]");
		Scanner sc = new Scanner(System.in);
		//사용자가 선택한 메뉴 번호를 담을 변수 선언
		int menu = 0;
		int i = 0;
		//for(변수선언및초기화;조건식-true or false;증감연산자);
		for(i=0;i<3;i=i+1) {
			System.out.println(i);
			//당신의 선택은?
			menu =  sc.nextInt();
			//너 새게임을 원해? - 1
			if(menu == 1) {
				System.out.println("새게임을 선택하였습니다.");
			}
			//처음 조건을 수렴하면 다음 조건은 굳이 비교할 필요가 없잖아!!!
			//그러면 정답을 확인하고 싶어?  - 2
			if(menu == 2) {
				System.out.println("정답보기를 선택하였습니다.");				
			}
			//그만 하고 싶어? - 3
			if(3 == menu) {
				System.out.println("종료를 선택하였습니다.");								
			}///////end of if
		}//////////end of for
		System.out.println("for문 탈출 - 여기로.....");
	}////////////end of main
}///////////////end of Scan1
/*
	사용자와 개발자 사이의 소통하기
	이것은 화면 구현을 통해서 가능하다.
	우리는 아직 html 공부하지 않고 있어서 콘솔창을 활용한 소통하기 시도
	화면에서 입력받아야 하는 값들이 파라미터 자리를 채워야 한다.
	사용자가(업무담당자가) 입력한 값을 받아오기
	
*/