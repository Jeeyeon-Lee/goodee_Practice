package achat.step2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TalkServer extends JFrame implements Runnable{
	/*선언부*/
	List<TalkServerThread> 	globalList 	= null;
	TalkServerThread 		tst 		= null;
	ServerSocket 			server 		= null;
	Socket 					socket 		= null;
	JTextArea 				jta_log = new JTextArea(10,30);
	JScrollPane 			jsp_log = new JScrollPane(jta_log
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel 		jp_north = new JPanel();
	/*생성자*/

	/*정의메소드*/
	//화면구현하기
	public void initDisplay() {
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jta_log.setBackground(Color.orange);
		this.add("North",jp_north);
		this.add("Center",jsp_log);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	//서버소켓과 클라이언트측 소켓을 연결하기
	@Override
	public void run() {
		//서버에 접속해온 클라이언트 스레드 정보를 관리할 벡터 생성하기 
		globalList = new Vector<>();
		boolean isStop = false;
		//예외처리하기 
		try {
			server = new ServerSocket(3002);
			jta_log.append("Server Ready.........\n");
			while(!isStop) {
				socket = server.accept(); //서버소켓이 모은 클라이언트 정보를 소켓에서 전달
				jta_log.append("client info:"+socket+"\n");				//사용자 로그를 줄바꿔가며 계속 더하기
				TalkServerThread tst = new TalkServerThread(this);   //스레드에 이 클래스 원본을 보내기
				tst.start(); //스레드의 run 실행
			}
		} catch (Exception e) {
			e.printStackTrace();  //에러발생시 추적
		}
	}

	/*메인메소드*/
	public static void main(String[] args) {
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		Thread th = new Thread(ts);  //스레드는 러너블의 형식으로 오버라이드하여 사용
		th.start();
	}

	public String setTimer() {
		Calendar cal = Calendar.getInstance(); 
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH)+1;
		int day =  cal.get(Calendar.DAY_OF_MONTH);
		return yyyy+"-"+
			   (mm < 10 ? "0"+mm:""+mm)+"-"+
			   (day < 10 ? "0"+day:""+day);
	}////////////////end of setTimer
}