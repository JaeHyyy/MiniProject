
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

public class Login extends JFrame {

	private JPanel contentPane;
	
	private JTextField idField;
    private JPasswordField pwField;
	
	private LineBorder bb = new LineBorder(Color.black, 1);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		super("로그인");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setBorder(bb);
		contentPane.setLayout(null);
		
		JLabel header = new JLabel("로그인");
		header.setFont(new Font("굴림", Font.PLAIN, 30));
		header.setBorder(bb);
		header.setHorizontalAlignment(SwingConstants.CENTER);		
		header.setBounds(0, 0, 584, 40);
		contentPane.add(header);
		
		idField = new JTextField();
		pwField = new JPasswordField();
		
		JLabel memberId = new JLabel("ID");
		memberId.setFont(new Font("굴림", Font.PLAIN, 24));
		memberId.setBounds(112, 203, 180, 40);
		contentPane.add(memberId);
		
		JLabel memberPw = new JLabel("PW");
		memberPw.setFont(new Font("굴림", Font.PLAIN, 24));
		memberPw.setBounds(112, 263, 180, 40);
		contentPane.add(memberPw);
		
		idField.setBounds(302, 203, 180, 40);
		contentPane.add(idField);
		idField.setColumns(10);
		
		pwField.setBounds(302, 263, 180, 40);
		contentPane.add(pwField);	
		
		JButton cancelBtn = new JButton("뒤로 가기");
		cancelBtn.setBounds(112, 353, 180, 40);
		contentPane.add(cancelBtn);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(302, 353, 180, 40);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String pw = new String(pwField.getPassword());
				
				MemberDTO member = new MemberDTO();
                member.setMember_id(id);
                member.setMember_pw(pw);
				
				MemberService service = new MemberServiceImpl();
				service.setDao(new MemberDAO());
                
                try {
                	if(service.login(member) != null) {
                		JOptionPane.showInternalMessageDialog(null, "로그인 성공", "로그인", JOptionPane.INFORMATION_MESSAGE);
                		
                		// 로그인된 유저 객체 저장
                		member = service.login(member);
                		LogUser user = LogUser.getInstance();
                		user.setLogUser(member);
                		
                		// 로그인 후 대문페이지로 이동
                		DoorWay doorwayPage = new DoorWay();
                		doorwayPage.setVisible(true);
        		        setVisible(false);
                		
                	} else {
                		JOptionPane.showInternalMessageDialog(null, "아이디 또는 비밀번호를 확인해주세요", "로그인", JOptionPane.INFORMATION_MESSAGE);
                	}
                } catch (Exception e1) {
                	e1.printStackTrace();
                	JOptionPane.showInternalMessageDialog(null, "로그인 실패", "로그인", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		// 뒤로가기
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main mainPage = new Main();
		        mainPage.setVisible(true);
		        setVisible(false);
			}
		});
	}
}