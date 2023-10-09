package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WelcomeBookMarket {
	static final int NUM_BOOK = 3; // 도서 개수
	static final int NUM_ITEM = 8; // 도서 정보의 개수

	static Cart cart = new Cart();
	static User user;
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String userName; // 고객 이름
		int userMobile; // 연락처
		int numberSelection; // 번호 선택
		Book[] bookInfoList = new Book[NUM_BOOK];

		System.out.println("Book Market 고객 정보 입력");
		System.out.print("고객의 이름을 입력하세요 : ");
		userName = input.next();
		System.out.print("연락처를 입력하세요 : ");
		userMobile = input.nextInt();

		user = new User(userName, userMobile);

		boolean quit = false;
		while (!quit) {

			menuIntroduction();

			System.out.print("메뉴 번호를 선택해주세요 ");
			numberSelection = input.nextInt();
			if (numberSelection < 1 || numberSelection > 9) {
				System.out.println("1부터 9까지의 숫자를 입력하세요.");
			} else {
				switch (numberSelection) {
				case 1:
					menuGuestInfo(userName, userMobile);
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
//					menuCartAddItem(bookInfoList);
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
				case 9:
					menuAdminLogin();
					break;
				}
			}
		}
	}

	private static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		Scanner input = new Scanner(System.in);
		System.out.print("아이디 : ");
		String adminId = input.next();
		System.out.print("비밀번호 : ");
		String adminPW = input.next();
		Admin admin = new Admin(user.getName(), user.getPhone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			System.out.printf("이름 : %s , 연락처 : %d\n", admin.getName(), admin.getPhone());
			System.out.printf("아이디 : %s , 비밀번호 : %s \n", admin.getId(), admin.getPassword());
		} else {
			System.out.println("관리자 정보가 일치하지 않습니다.");
		}
	}

	private static void menuExit() {
		System.out.println("8. 종료");
	}

	private static void menuCartBill() {
		// System.out.println("7. 영수증 표시하기");
		if (cart.cartCount == 0) {
			System.out.println("장비구니에 항목이 없습니다");
		} else {
			System.out.println("배송받을 분은 고객정보와 같습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
				System.out.print("배송지를 입력해주세요 ");
				String address = input.nextLine();
				// 주문 처리 후 영수증 출력 메서드 호출
				printBill(user.getName(), String.valueOf(user.getPhone()), address);
			} else {
				System.out.print("배송받을 고객명을 입력하세요 ");
				String name = input.nextLine();
				System.out.print("배송받을 고객의 연락처를 입력하세요 ");
				String phone = input.nextLine();
				System.out.print("배송받을 고객의 배송지를 입력해주세요 ");
				String address = input.nextLine();
				// 주문 처리 후 영수증 출력 메서드 호출
				printBill(name, phone, address);
			}
		}

	}


	private static void menuCartRemoveItem() {
//		System.out.println("6. 장바구니의 항목 삭제하기");
		if (cart.cartCount == 0) {
			System.out.println("장바구니에 항목이 없습니다");
		} else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의 ID 를 입력하세요 :");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId = -1;
				for (int i = 0; i < cart.cartCount; i++) {
					if (str.equals(cart.cartItem[i].getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N ");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
						System.out.println(cart.cartItem[numId].getBookID() + "장바구니에서 도서가 삭제되었습니다.");
						cart.removeCart(numId);
					}

					quit = true;
				} else {
					System.out.println("다시 입력해 주세요");
				}
			}
		}
	}

	private static void menuCartRemoveItemCount() {
		System.out.println("5. 장바구니의 항목 수량 줄이기");
	}

	private static void menuCartAddItem(Book[] book) {
//		System.out.println("4. 장바구니에 항목 추가하기 : ");
		bookList(book);// 도서 정보메서드 출력
		cart.printBookList(book);
		boolean quit = false;

		while (!quit) {
			Scanner input = new Scanner(System.in);
			System.out.print("장바구니에 추가할 도서의 ID 를 입력하세요 :");
			String inputStr = input.nextLine();
			boolean flag = false; // 일치 여부
			int numId = -1; // 인덱스 번호
			for (int i = 0; i < NUM_BOOK; i++) {
				if (inputStr.equals(book[i].getBookId())) {
					numId = i;
					flag = true;
					break;
				} // if
			} // for
			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N ");
				inputStr = input.nextLine();
				if (inputStr.toUpperCase().equals("Y") || inputStr.toUpperCase().equals("y")) {
					System.out.println(book[numId].getBookId() + " 도서가 장바구니에추가되었습니다.");
					if (!isCartInBook(book[numId].getBookId())) {
						cart.insertBook(book[numId]);
//						cartItem[cartCount++] = new CartItem(book[numId]);
					}
					quit = true;
				} else
					System.out.println("다시 입력해 주세요");
			} // While
		}
	}

	private static void menuCartClear() {
//		System.out.println("3. 장바구니 비우기");
		if (cart.cartCount == 0) {
			System.out.println("장바구니에 항목이 없습니다");
		} else {
			System.out.println("장바구니에 모든 항목을 삭제하겠습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
				System.out.println("장바구니에 모든 항목을 삭제했습니다");
				cart.deleteBook();
			}
		}

	}

	private static void menuCartItemList() {
		if (cart.cartCount >= 0) {
			cart.printCart();
		}
	}

	private static void menuGuestInfo(String userName, int userMobile) {
		System.out.println("현재 고객 정보");
		System.out.printf("이름 : %s  , 연락처 : %d \n", user.getName(), user.getPhone());

	}

	private static void menuIntroduction() {
		System.out.println("***************************************************");
		System.out.println("\t\t" + "Book Market Menu");
		System.out.println("***************************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기\t5. 장바구니의 항목 수량줄이기");
		System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t8. 종료");
		System.out.println(" 9. 관리자 로그인");
		System.out.println("***************************************************");
	}

	private static void bookList(Book[] book) {
		book[0] = new Book("book1", "ISBN 978-89-01-26726-5", "빅 히스토리", 33000);
		book[0].setAuthor("데이비드 크리스천");
		book[0].setDescription("우주와 지구, 인간을 하나로 잇는 새로운 역사");
		book[0].setCategory("인문 교양");
		book[0].setReleaseDate("2022/12/23");
		book[1] = new Book("book2", "ISBN 979-11-6921-062-1", "SICP", 45000);
		book[1].setAuthor("해럴드 에이블슨, 류광");
		book[1].setDescription("컴퓨터 프로그래밍의 구조와 해석");
		book[1].setCategory("개발 방법론");
		book[1].setReleaseDate("2022/12/30");
		book[2] = new Book("book3", "ISBN 978-89-6626-366-0", "러스트 프로그래밍", 35000);
		book[2].setAuthor("팀 맥나마라, 장연호");
		book[2].setDescription("러스트는 시스템 프로그래밍에 적합한 언어");
		book[2].setCategory("프로그래밍 언어");
		book[2].setReleaseDate("2022/07/08");

	}

	private static boolean isCartInBook(String bookId) {
//		boolean flag = false;
//		for (int i = 0; i < cartCount; i++) {
//			if (bookId == cartItem[i].getBookID()) {
//				cartItem[i].setQuantity(cartItem[i].getQuantity() + 1);
//				flag = true;
//			}
//		}
//		return flag;
		return cart.isCartInBook(bookId);
	}
	private static void printBill(String name, String phone, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("---------------배송 받을 고객 정보----------------");
		System.out.println("고객명 : " + name + " \t\t 연락처 : " + phone);
		System.out.println("배송지 : " + address + "\t 발송일 : " + strDate);
		// 장바구니에 담긴 항목 출력
		cart.printCart();
		// 장바구니에 담긴 항목의 총 금액 계산
		int sum = 0;
		for (int i = 0; i < cart.cartCount; i++) {
		sum += cart.cartItem[i].getTotalPrice();
		}
		System.out.println("\t\t\t 주문 총금액 : " + sum + "원\n");
		System.out.println("----------------------------------------------");
		System.out.println();
		}
	}

