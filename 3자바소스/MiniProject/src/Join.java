
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

public class Join extends JFrame {

    private JPanel contentPane;

    private JTextField idField;
    private JPasswordField pwField;
    private JPasswordField pwChkField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField dateField;
    private JRadioButton memberChkBtn; 
    private JButton submitBtn;
    
    private LineBorder bb = new LineBorder(Color.black, 1);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Join frame = new Join();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // 날짜 유효성 검사
    public boolean validationDate(String chkDate) {
    	SimpleDateFormat sdf1 = new  SimpleDateFormat("yyyyMMdd");
    	SimpleDateFormat sdf2 = new  SimpleDateFormat("yyMMdd");
    	
    	 try {
             sdf1.setLenient(false);
             sdf1.parse(chkDate);
             return true;  
         } catch(ParseException e1) {
             try {
                 sdf2.setLenient(false);
                 sdf2.parse(chkDate);
                 return true; 
             } catch (ParseException e2) {
                 return false;
             }
         }
     }

    public Join() {
        super("회원가입");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setBorder(bb);
        contentPane.setLayout(null);

        JLabel header = new JLabel("회원 가입");
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
		memberChkBtn = new JRadioButton();

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

        submitBtn = new JButton("가입 하기");
        submitBtn.setBounds(300, 450, 180, 40);
        contentPane.add(submitBtn);

        idField.setBounds(300, 83, 180, 40);
        contentPane.add(idField);
        idField.setColumns(10);

        pwField.setBounds(300, 143, 180, 40);
        contentPane.add(pwField);
        
        pwChkField.setBounds(300, 203, 180, 40);
        contentPane.add(pwChkField);

        nameField.setBounds(300, 263, 180, 40);
        contentPane.add(nameField);
        nameField.setColumns(10);

        phoneField.setBounds(300, 323, 180, 40);
        contentPane.add(phoneField);
        phoneField.setColumns(10);

        dateField.setBounds(300, 383, 180, 40);
        contentPane.add(dateField);
        dateField.setColumns(10);

        memberChkBtn = new JRadioButton("일반 회원");
        memberChkBtn.setSelected(true);
        memberChkBtn.setFont(new Font("굴림", Font.PLAIN, 16));
        memberChkBtn.setBounds(300, 383, 100, 40);
        contentPane.add(memberChkBtn);
        
        // 회원 가입
        submitBtn.addActionListener(new ActionListener() {
			
        	@Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = new String(pwField.getPassword());
                String pwChk = new String(pwChkField.getPassword());
                String name = nameField.getText();
                String phone = phoneField.getText();
                String date = dateField.getText();
                int memberType = memberChkBtn.isSelected() ? 1 : 2;
                
                MemberService service = new MemberServiceImpl();
                service.setDao(new MemberDAO());
                // 공백 검증
				if(id.trim().isEmpty() || name.trim().isEmpty() || pw.trim().isEmpty() || phone.trim().isEmpty() || date.trim().isEmpty()) {
					JOptionPane.showInternalMessageDialog(null, "모든 항목을 입력해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
                
                // ID 유효성 검사
                try {
                	// 사용 중인 ID 일 경우
                    if (service.existsId(id.toUpperCase()) != 0) {
                        JOptionPane.showMessageDialog(null, "이미 사용 중인 ID입니다", "ID 중복 오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // ID길이가 5이하 또는 30초과일 경우
                    if (id.length() < 5 || id.length() > 30) {
                    	JOptionPane.showMessageDialog(null, " ID는 5자리 이상 30자리 이하로 입력해주세요", "ID 중복 오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // 특수문자또는 공백이 들어간 경우
                    if (!id.matches("[a-zA-Z0-9!@#$%^&*()\\[\\]{};:,.<>?]*")) {
                    	JOptionPane.showMessageDialog(null, "ID에는 특수문자 또는 공백을 포함할 수 없습니다", "ID 입력 오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "DB 오류가 발생했습니다", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // 비밀번호 일치 여부 확인
				if(!pw.equals(pwChk)) {
					JOptionPane.showInternalMessageDialog(null, "비밀번호가 일치하지 않습니다", "회원 정보 수정", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
                
                // 전화번호 검증
                if (!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}", phone)) {
                	JOptionPane.showMessageDialog(null, "전화번호는(000-0000-0000)과 같은 형태로 입력해주세요", "전화번호 입력 오류", JOptionPane.ERROR_MESSAGE);
            		return;
                }
                
                // 날짜 유효성 검사
                if (!validationDate(date)) {
                    JOptionPane.showMessageDialog(null, "생년월일을 확인해주세요", "생년월일 입력 오류", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                MemberDTO member = new MemberDTO();
                member.setMember_id(id);
                member.setMember_pw(pw);
                member.setMember_name(name);
                member.setMember_phone(phone);
                member.setMember_date(date);
                member.setMember_role(memberType);
                
                try {
                    service.join(member);
                    JOptionPane.showMessageDialog(null, "회원가입 성공", "회원 가입", JOptionPane.INFORMATION_MESSAGE);
                    // 회원가입 성공시 메인페이지로 이동
                    Main mainPage = new Main();
    		        mainPage.setVisible(true);
    		        setVisible(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "회원가입 실패", "회원 가입", JOptionPane.ERROR_MESSAGE);
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