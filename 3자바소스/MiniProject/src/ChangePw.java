
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

public class ChangePw extends JFrame {

	   private JPanel contentPane;
	   private JPasswordField pwField;
	   private JPasswordField pwFieldChk;
	   
	   private LineBorder bb = new LineBorder(Color.black, 1);
	   
	   private MemberDTO member;
	   
	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {

	         public void run() {
	            try {
	               MemberDTO member = new MemberDTO();
	               ChangePw frame = new ChangePw(member);
	               frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }

	   public ChangePw(MemberDTO member) {
	      super("비밀번호 변경");
	      
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setBounds(300, 300, 600, 600);
	      contentPane = new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	      setContentPane(contentPane);
	      contentPane.setBorder(bb);
	      contentPane.setLayout(null);
	      
	      JLabel header = new JLabel("비밀번호 변경");
	      header.setFont(new Font("굴림", Font.PLAIN, 30));
	      header.setBorder(bb);
	      header.setHorizontalAlignment(SwingConstants.CENTER);
	      header.setBounds(0, 0, 584, 40);
	      contentPane.add(header);
	      
	      pwField = new JPasswordField();
	      pwFieldChk = new JPasswordField();
	      
	      JLabel memberPw = new JLabel("새 비밀번호");
	      memberPw.setFont(new Font("굴림", Font.PLAIN, 23));
	      memberPw.setBounds(112, 203, 180, 40);
	      contentPane.add(memberPw);
	      
	      JLabel memberPwChk = new JLabel("새 비밀번호 확인");
	      memberPwChk.setFont(new Font("굴림", Font.PLAIN, 23));
	      memberPwChk.setBounds(112, 263, 180, 40);
	      contentPane.add(memberPwChk);
	      
	      pwField = new JPasswordField();
	      pwField.setBounds(302, 203, 180, 40);
	      contentPane.add(pwField);
	      pwField.setColumns(10);
	      
	      pwFieldChk = new JPasswordField();
	      pwFieldChk.setBounds(302, 263, 180, 40);
	      contentPane.add(pwFieldChk);
	      pwFieldChk.setColumns(10);

	      JButton cancelBtn = new JButton("메인으로");
	      cancelBtn.setBounds(112, 353, 180, 40);
	      contentPane.add(cancelBtn);
	      
	      JButton findBtn = new JButton("변경 하기");
	      findBtn.setBounds(302, 353, 180, 40);
	      contentPane.add(findBtn);
	      findBtn.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            String id = member.getMember_id();
	            String pw = new String(pwField.getPassword());  
	            String pwChk = new String(pwFieldChk.getPassword());
	            
	            MemberService service = new MemberServiceImpl();
	                service.setDao(new MemberDAO());
	                
	                // 공백 검증
	            if(pw.trim().isEmpty()) {
	               JOptionPane.showInternalMessageDialog(null, "수정할 비밀번호를 입력해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
	               return;
	            }
	            
	            // 비밀번호 일치 여부 확인
	            if(!pw.equals(pwChk)) {
	               JOptionPane.showInternalMessageDialog(null, "비밀번호가 일치하지 않습니다", "회원 정보 수정", JOptionPane.INFORMATION_MESSAGE);
	               return;
	            }
	                
	                member.setMember_id(id);
	                member.setMember_pw(pw);
	                
	                try {
	                    service.changePw(member);
	                    JOptionPane.showMessageDialog(null, "비밀번호 변경 완료", "회원 정보 수정", JOptionPane.INFORMATION_MESSAGE);
	                    // 비밀번호 변경 완료 시 메인페이지로 이동
	                    Main mainPage = new Main();
	                  mainPage.setVisible(true);
	                  setVisible(false);
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "회원 정보 수정 실패", "회원 정보 수정", JOptionPane.ERROR_MESSAGE);
	                }
	         }
	      });
	      
	      // 뒤로가기
	        cancelBtn.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            Main FindMemberPage = new Main();
	            FindMemberPage.setVisible(true);
	              setVisible(false);
	         }
	      });
	   }

	}