
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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

public class EditUser extends JFrame {

	private JPanel contentPane;
	
	private JTextField idField;
    private JPasswordField pwField;
    private JPasswordField pwChkField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField dateField;
    private JButton submitBtn;
    private JButton delUserBtn;
    
    LogUser logMember = LogUser.getInstance();
	MemberDTO logUser = logMember.getLogUser();
	
	private LineBorder bb = new LineBorder(Color.black, 1);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditUser() {
		super("회원 정보 수정");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setBorder(bb);
		contentPane.setLayout(null);
		
		JLabel header = new JLabel("회원 정보 수정");
		header.setFont(new Font("굴림", Font.PLAIN, 30));
		header.setBorder(bb);
		header.setHorizontalAlignment(SwingConstants.CENTER);		
		header.setBounds(0, 0, 584, 40);
		contentPane.add(header);
		
		idField = new JTextField();
		pwField = new JPasswordField();
		pwChkField = new JPasswordField();
		nameField = new JTextField();
		phoneField = new JTextField();
		dateField = new JTextField();

		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 24));
		idLabel.setBounds(110, 83, 180, 40);
        contentPane.add(idLabel);

        JLabel pwLabel = new JLabel("Password");
        pwLabel.setFont(new Font("굴림", Font.PLAIN, 24));
        pwLabel.setBounds(110, 143, 180, 40);
        contentPane.add(pwLabel);
        
        JLabel pwChkLabel = new JLabel("Password 확인");
        pwChkLabel.setFont(new Font("굴림", Font.PLAIN, 24));
        pwChkLabel.setBounds(110, 203, 180, 40);
        contentPane.add(pwChkLabel);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setFont(new Font("굴림", Font.PLAIN, 24));
        nameLabel.setBounds(110, 263, 180, 40);
        contentPane.add(nameLabel);

        JLabel phoneLabel = new JLabel("전화번호");
        phoneLabel.setFont(new Font("굴림", Font.PLAIN, 24));
        phoneLabel.setBounds(110, 323, 180, 40);
        contentPane.add(phoneLabel);

        JLabel dateLabel = new JLabel("생년월일");
        dateLabel.setFont(new Font("굴림", Font.PLAIN, 24));
        dateLabel.setBounds(110, 383, 180, 40);
        contentPane.add(dateLabel);
        
        JButton cancelBtn = new JButton("뒤로 가기");
        cancelBtn.setBounds(110, 450, 180, 40);
        contentPane.add(cancelBtn);

        submitBtn = new JButton("수정 하기");
        submitBtn.setBounds(300, 450, 180, 40);
        contentPane.add(submitBtn);
        
        delUserBtn = new JButton("회원 탈퇴");
        delUserBtn.setBounds(200, 510, 180, 40);
        contentPane.add(delUserBtn);

        idField.setBounds(300, 83, 180, 40);
        idField.setText(logUser.getMember_id());
        idField.setEditable(false);
        contentPane.add(idField);
        idField.setColumns(10);

        pwField.setBounds(300, 143, 180, 40);
        contentPane.add(pwField);
        
        pwChkField.setBounds(300, 203, 180, 40);
        contentPane.add(pwChkField);

        nameField.setBounds(300, 263, 180, 40);
        nameField.setText(logUser.getMember_name());
        contentPane.add(nameField);
        nameField.setColumns(10);

        phoneField.setBounds(300, 323, 180, 40);
        phoneField.setText(logUser.getMember_phone());
        contentPane.add(phoneField);
        phoneField.setColumns(10);

        dateField.setBounds(300, 383, 180, 40);
        dateField.setText(logUser.getMember_date());
        dateField.setEditable(false);
        contentPane.add(dateField);
        dateField.setColumns(10);
		
        submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String pw = new String(pwField.getPassword());  
				String pwChk = new String(pwChkField.getPassword());
				String name = nameField.getText();
				String phone = phoneField.getText();
                
                MemberService service = new MemberServiceImpl();
                service.setDao(new MemberDAO());
				
                // 비밀번호 일치 여부 확인
				if(!pw.equals(pwChk)) {
					JOptionPane.showInternalMessageDialog(null, "비밀번호가 일치하지 않습니다", "회원 정보 수정", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (pw.trim().isEmpty()) {
					JOptionPane.showInternalMessageDialog(null, "비밀번호를 입력해주세요", "회원 정보 수정", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// 전화번호 검증
                if (!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}", phone)) {
                	JOptionPane.showMessageDialog(null, "전화번호는(000-0000-0000)과 같은 형태로 입력해주세요", "전화번호 입력 오류", JOptionPane.ERROR_MESSAGE);
            		return;
                }
                
                MemberDTO member = new MemberDTO();
                member.setMember_id(id);
                member.setMember_pw(pw);
                member.setMember_name(name);
                member.setMember_phone(phone);
                
                try {
                    service.userEdit(member);
                    JOptionPane.showMessageDialog(null, "수정 성공", "회원 수정", JOptionPane.INFORMATION_MESSAGE);
                    
                    // 로그인된 유저 객체 저장
            		member = service.login(member);
            		LogUser user = LogUser.getInstance();
            		user.setLogUser(member);
                    
                    // 수정 성공시 대문페이지로 이동
                    DoorWay doorWayPage = new DoorWay();
                    doorWayPage.setVisible(true);
    		        setVisible(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "수정 실패", "회원 수정", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
        
        // 회원 탈퇴
        delUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberService service = new MemberServiceImpl();
                service.setDao(new MemberDAO());
				
				int response = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "탈퇴 확인", JOptionPane.YES_NO_OPTION);
				try {
					if(response == JOptionPane.YES_OPTION) {
						service.deleteUser(logUser);
						JOptionPane.showMessageDialog(null, "탈퇴 성공", "회원 탈퇴", JOptionPane.ERROR_MESSAGE);
						// 탈퇴 성공 시 메인페이지로 이동
						Main mainPage = new Main();
						mainPage.setVisible(true);
				        setVisible(false);
					}	
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "탈퇴 실패", "회원 탈퇴", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
        
        // 뒤로가기
        cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoorWay doorWayPage = new DoorWay();
				doorWayPage.setVisible(true);
		        setVisible(false);
			}
		});
	}

}