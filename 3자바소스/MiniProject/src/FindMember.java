
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

public class FindMember extends JFrame {

	   private JPanel contentPane;
	   private JTextField idField;
	   private JTextField phoneField;
	   private LineBorder bb = new LineBorder(Color.black, 1);

	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               FindMember frame = new FindMember();
	               frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }

	   public FindMember() {
	      super("PW 찾기");
	      
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setBounds(300, 300, 600, 600);
	      contentPane = new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	      setContentPane(contentPane);
	      contentPane.setBorder(bb);
	      contentPane.setLayout(null);
	      
	      JLabel header = new JLabel("PW 찾기");
	      header.setFont(new Font("굴림", Font.PLAIN, 30));
	      header.setBorder(bb);
	      header.setHorizontalAlignment(SwingConstants.CENTER);      
	      header.setBounds(0, 0, 584, 40);
	      contentPane.add(header);
	      
	      idField = new JTextField();
	      phoneField = new JTextField();
	      
	      JLabel memberId = new JLabel("ID 입력");
	      memberId.setFont(new Font("굴림", Font.PLAIN, 24));
	      memberId.setBounds(112, 203, 180, 40);
	      contentPane.add(memberId);
	      
	      JLabel memberPhone = new JLabel("전화번호 입력");
	      memberPhone.setFont(new Font("굴림", Font.PLAIN, 24));
	      memberPhone.setBounds(112, 263, 180, 40);
	      contentPane.add(memberPhone);
	      
	      idField = new JTextField();
	      idField.setBounds(302, 203, 180, 40);
	      contentPane.add(idField);
	      idField.setColumns(10);
	      
	      phoneField = new JTextField();
	      phoneField.setBounds(302, 263, 180, 40);
	      contentPane.add(phoneField);
	      phoneField.setColumns(10);

	      JButton cancelBtn = new JButton("뒤로 가기");
	      cancelBtn.setBounds(112, 353, 180, 40);
	      contentPane.add(cancelBtn);
	      
	      JButton findBtn = new JButton("찾기");
	      findBtn.setBounds(302, 353, 180, 40);
	      contentPane.add(findBtn);
	      
	      findBtn.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            String id = idField.getText();
	            String phone = phoneField.getText();
	            
	            if(id.isBlank() || phone.isBlank()) {
	               JOptionPane.showMessageDialog(null, "ID 또는 전화번호를 입력해주세요", "전화번호 입력 오류", JOptionPane.ERROR_MESSAGE);
	               return;
	            }
	            
	            // 전화번호 검증
	                if (!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}", phone)) {
	                   JOptionPane.showMessageDialog(null, "전화번호는(000-0000-0000)과 같은 형태로 입력해주세요", "전화번호 입력 오류", JOptionPane.ERROR_MESSAGE);
	                  return;
	                }
	            
	            MemberDTO member = new MemberDTO();
	                member.setMember_id(id);
	                member.setMember_phone(phone);
	            
	            MemberService service = new MemberServiceImpl();
	                service.setDao(new MemberDAO());
	                
	                try {
	                   if(service.findUser(member) != null) {
	                      JOptionPane.showInternalMessageDialog(null, "비밀번호 변경 페이지로 이동합니다", "회원 정보 찾기", JOptionPane.INFORMATION_MESSAGE);
	                      
	                      // 존재하는 회원일 경우 비밀번호 변경 페이지로 이동
	                      ChangePw changePwPage = new ChangePw(member);
	                      changePwPage.setVisible(true);
	                       setVisible(false);
	                   } else {
	                      JOptionPane.showInternalMessageDialog(null, "해당하는 정보의 회원은 존재하지 않습니다", "회원 정보 찾기", JOptionPane.INFORMATION_MESSAGE);   
	                   }
	                } catch (Exception e1) {
	                   e1.printStackTrace();
	                   JOptionPane.showInternalMessageDialog(null, "입력하신 내용을 다시 확인해주세요", "회원 정보 찾기", JOptionPane.INFORMATION_MESSAGE);
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