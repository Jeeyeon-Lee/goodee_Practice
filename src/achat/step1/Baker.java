package achat.step1;
<<<<<<< HEAD
//Baker.class, Baker$1.class -> 익명의 클래스로 생성됨 
//클래스 정의와 동시에 인스턴스를 생성하는 방식 → 재사용성 높임, 코드 간결해짐
public class Baker {

	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
			}
		};
	}
=======
// Baker.class, Baker$1.class
public class Baker {

	public static void main(String[] args) {
		Thread th = new Thread() {           //annoymous클래스 이다. 익명 클래스이다!!
			public void run() {
				
			}
		};

	}

>>>>>>> f22ff6d251a8738d0b3a1d87c95db76d1b1b82e2
}
