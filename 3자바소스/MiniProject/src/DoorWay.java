
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dto.MemberDTO;

public class DoorWay extends JFrame {

	LogUser logMember = LogUser.getInstance();
	MemberDTO logUser = logMember.getLogUser();

	private JPanel contentPane;

	private LineBorder bb = new LineBorder(Color.black, 1);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoorWay frame = new DoorWay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoorWay() {
		super("도서 대여 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setBorder(bb);
		contentPane.setLayout(null);

		JLabel header = new JLabel("도서 대여 프로그램");
		header.setFont(new Font("굴림", Font.PLAIN, 30));
		header.setBorder(bb);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBounds(0, 0, 584, 40);
		contentPane.add(header);

		JLabel host = new JLabel(logUser.getMember_name() + "님 환영합니다.");
		host.setFont(new Font("굴림", Font.PLAIN, 16));
		host.setBounds(337, 50, 235, 30);
		host.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(host);

		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(475, 82, 97, 23);
		contentPane.add(logoutBtn);

		JButton listBtn = new JButton("책 둘러보기");
		listBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		listBtn.setBounds(36, 251, 250, 50);
		contentPane.add(listBtn);

		JButton editBtn = new JButton("회원정보 수정");
		editBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		editBtn.setBounds(304, 251, 250, 50);
		contentPane.add(editBtn);

		JButton rentalInfo = new JButton("대여 정보");
		rentalInfo.setFont(new Font("굴림", Font.PLAIN, 20));
		rentalInfo.setBounds(174, 324, 250, 50);
		contentPane.add(rentalInfo);

		// 대여 정보 페이지 이동
		rentalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (logUser.getMember_role() == 1) { // 회원 등급이 1인 경우
					RentalMember rentalMember = new RentalMember();
					rentalMember.setVisible(true);
					setVisible(false);
				} else if (logUser.getMember_role() == 2) { // 회원 등급이 2인 경우
					RentalManager rentalManager = new RentalManager();
					rentalManager.setVisible(true);
					setVisible(false);
				}
			}
		});

		// 도서 둘러보기 페이지 이동
		listBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (logUser.getMember_role() == 1) { // 회원 등급이 1인 경우
					BookMember bookRead = new BookMember();
					bookRead.setVisible(true);
					setVisible(false);
				} else if (logUser.getMember_role() == 2) { // 회원 등급이 2인 경우
					BookManager bookManager = new BookManager();
					bookManager.setVisible(true);
					setVisible(false);
				}
			}
		});

		// 회원 정보 수정
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (logUser.getMember_role() == 1) { // 회원 등급이 1인 경우
					EditUser userEditPage = new EditUser();
					userEditPage.setVisible(true);
					setVisible(false);
				} else if (logUser.getMember_role() == 2) { // 회원 등급이 2인 경우
					MemberRUD memberRUD = new MemberRUD();
					memberRUD.setVisible(true);
					setVisible(false);
				}
			}
		});

		// 로그아웃
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main mainPage = new Main();
				mainPage.setVisible(true);
				setVisible(false);
			}
		});

	}
}