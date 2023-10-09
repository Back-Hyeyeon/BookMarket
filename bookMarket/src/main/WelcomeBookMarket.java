package main;

import java.util.Scanner;

public class WelcomeBookMarket {
	static final int NUM_BOOK = 3; // 도서 개수
	static final int NUM_ITEM = 8; // 도서 정보의 개수

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String userName; // 고객 이름
		int userMobile; // 연락처
		int numberSelection; // 번호 선택
		String[][] bookInfoList = new String[NUM_BOOK][NUM_ITEM];

		System.out.println("Book Market 고객 정보 입력");
		System.out.print("고객의 이름을 입력하세요 : ");
		userName = input.next();
		System.out.print("연락처를 입력하세요 : ");
		userMobile = input.nextInt();
		boolean quit = false;
		while (!quit) {
//			System.out.println("***************************************************");
//			System.out.println("\t\t" + "Book Market Menu");
//			System.out.println("***************************************************");
//			System.out.println(" 1. 고객 정보 확인하기 \t4. 장바구니에 항목 추가하기");
//			System.out.println(" 2. 장바구니 상품 목록 보기\t5. 장바구니의 항목 수량줄이기");
//			System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
//			System.out.println(" 7. 영수증 표시하기 \t8. 종료");
//			System.out.println("***************************************************");

			menuIntroduction();

			System.out.print("메뉴 번호를 선택해주세요 ");
			numberSelection = input.nextInt();
			if (numberSelection < 1 || numberSelection > 8) {
				System.out.println("1부터 8까지의 숫자를 입력하세요.");
			} else {
				switch (numberSelection) {
				case 1:
					menuGuestInfo(userName, userMobile);
//					System.out.println("현재 고객 정보");
//					System.out.printf("이름 : %s , 연락처 : %d \n", userName, userMobile);
					break;
				case 2:
					menuCartItemList();
//					System.out.println("2. 장바구니 상품 목록 보기 :");
					break;
				case 3:
					menuCartClear();
//					System.out.println("3. 장바구니 비우기");
					break;
				case 4:
					menuCartAddItem(bookInfoList);
//					System.out.println("4. 장바구니에 항목 추가하기 : ");
					break;
				case 5:
					menuCartRemoveItemCount();
//					System.out.println("5. 장바구니의 항목 수량 줄이기");
					break;
				case 6:
					menuCartRemoveItem();
//					System.out.println("6. 장바구니의 항목 삭제하기");
					break;
				case 7:
					menuCartBill();
//					System.out.println("7. 영수증 표시하기");
					break;
				case 8:
					menuExit();
//					System.out.println("8. 종료");
					quit = true;
					break;
				}
			}
		}
	}

	private static void menuExit() {
		System.out.println("8. 종료");
	}

	private static void menuCartBill() {
		System.out.println("7. 영수증 표시하기");
	}

	private static void menuCartRemoveItem() {
		System.out.println("6. 장바구니의 항목 삭제하기");
	}

	private static void menuCartRemoveItemCount() {
		System.out.println("5. 장바구니의 항목 수량 줄이기");
	}

	private static void menuCartAddItem(String[][] book) {
		System.out.println("4. 장바구니에 항목 추가하기 : ");
		bookList(book);// 도서 정보메서드 출력
		for (int i = 0; i < NUM_BOOK; i++) {
			for (int j = 0; j < NUM_ITEM; j++)
				System.out.print(book[i][j] + " | ");
			System.out.println("");
		}
		boolean quit = false;

		while (!quit) {
			Scanner input = new Scanner(System.in);
			System.out.print("장바구니에 추가할 도서의 ID 를 입력하세요 :");
			String inputStr = input.nextLine();
			boolean flag = false; // 일치 여부
			int numId = -1; // 인덱스 번호
			for (int i = 0; i < NUM_BOOK; i++) {
				if (inputStr.equals(book[i][0])) {
					numId = i;
					flag = true;
					break;
				} // if
			} // for
			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N ");
				inputStr = input.nextLine();
				if (inputStr.toUpperCase().equals("Y") || inputStr.toUpperCase().equals("y")) {
					System.out.println(book[numId][0] + " 도서가 장바구니에추가되었습니다.");
				}
				quit = true;
			} else
				System.out.println("다시 입력해 주세요");
		} // While
	}

	private static void menuCartClear() {
		System.out.println("3. 장바구니 비우기");
	}

	private static void menuCartItemList() {
		System.out.println("2. 장바구니 상품 목록 보기 :");
	}

	private static void menuGuestInfo(String userName, int userMobile) {
		System.out.println("현재 고객 정보");
		System.out.printf("이름 : %s , 연락처 : %d \n", userName, userMobile);
	}

	private static void menuIntroduction() {
		System.out.println("***************************************************");
		System.out.println("\t\t" + "Book Market Menu");
		System.out.println("***************************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기\t5. 장바구니의 항목 수량줄이기");
		System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t8. 종료");
		System.out.println("***************************************************");
	}

	private static void bookList(String[][] book) {
		book[0][0] = "book1";
		book[0][1] = "ISBN 978-89-01-26726-5";
		book[0][2] = "빅 히스토리";
		book[0][3] = "33000";
		book[0][4] = "데이비드 크리스천";
		book[0][5] = "우주와 지구, 인간을 하나로 잇는 새로운 역사";
		book[0][6] = "인문 교양";
		book[0][7] = "2022/12/23";
		book[1][0] = "book2";
		book[1][1] = "ISBN 979-11-6921-062-1";
		book[1][2] = "SICP";
		book[1][3] = "45000";
		book[1][4] = "해럴드 에이블슨, 류광";
		book[1][5] = "컴퓨터 프로그래밍의 구조와 해석";
		book[1][6] = "개발 방법론";
		book[1][7] = "2022/12/30";
		book[2][0] = "book3";
		book[2][1] = "ISBN 978-89-6626-366-0";
		book[2][2] = "러스트 프로그래밍";
		book[2][3] = "35000";
		book[2][4] = "팀 맥나마라, 장연호";
		book[2][5] = "러스트는 시스템 프로그래밍에 적합한 언어";
		book[2][6] = "프로그래밍 언어";
		book[2][7] = "2022/07/08";
	}
}
