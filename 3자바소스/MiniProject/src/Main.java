
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

public class Main extends JFrame {

	private JPanel contentPane;
	private LineBorder bb = new LineBorder(Color.black, 1);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
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
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		loginBtn.setBounds(200, 100, 200, 50);
		contentPane.add(loginBtn);
		
		JButton joinBtn = new JButton("회원 가입");
		joinBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		joinBtn.setBounds(200, 200, 200, 50);
		contentPane.add(joinBtn);
		
		JButton findBtn = new JButton("PW 찾기");
		findBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		findBtn.setBounds(200, 300, 200, 50);
		contentPane.add(findBtn);
		
		JButton ExitBtn = new JButton("종료");
		ExitBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		ExitBtn.setBounds(200, 400, 200, 50);
		contentPane.add(ExitBtn);
		
		// 로그인 페이지 이동
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login LoginPage = new Login();
				LoginPage.setVisible(true);
		        setVisible(false); 
			}
		});
		
		// 회원가입 페이지 이동
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Join joinPage = new Join();
		        joinPage.setVisible(true);
		        setVisible(false); 
			}
		});
		
		// ID/PW 찾기 페이지 이동
		findBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FindMember findMemberPage = new FindMember();
				findMemberPage.setVisible(true);
		        setVisible(false); 
			}
		});
		
		// 프로그램 종료
		ExitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
	}
}