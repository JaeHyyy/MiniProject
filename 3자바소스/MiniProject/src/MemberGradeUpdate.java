import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.ManagerDAO;
import com.dto.ManagerDTO;
import com.service.ManagerService;
import com.service.ManagerServiceImpl;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MemberGradeUpdate extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnUpdate;
	private int memberIdx;
	private JLabel lblMemberIdx; 
	private JLabel lblMemberId; 

	private JRadioButton rdbtnManager; 
	private JRadioButton rdbtnMember;

	/**
	 * Launch the application.
	 */
	public void setMemberInfo(int memberIdx, String memberName, String memberRole) {
		this.memberIdx = memberIdx;
		lblMemberIdx.setText(String.valueOf(memberIdx)); // 회원 번호를 JLabel에 설정
		lblMemberId.setText(memberName); // 회원 아이디를 JLabel에 설정

		if (memberRole.equals("매니저")) {
			rdbtnManager.setSelected(true); // 매니저
		} else {
			rdbtnMember.setSelected(true); // 회원
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberGradeUpdate frame = new MemberGradeUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberGradeUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		rdbtnManager = new JRadioButton("매니저");
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setBounds(164, 160, 74, 23);
		contentPane.add(rdbtnManager);

		rdbtnMember = new JRadioButton("회원");
		buttonGroup.add(rdbtnMember);
		rdbtnMember.setBounds(249, 160, 52, 23);
		contentPane.add(rdbtnMember);

		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(143, 208, 95, 23);
		contentPane.add(btnUpdate);

		JLabel lblNewLabel = new JLabel("회원 번호");
		lblNewLabel.setBounds(42, 43, 52, 15);
		contentPane.add(lblNewLabel);

		lblMemberIdx = new JLabel(); // 회원 번호를 표시할 JLabel 생성
		lblMemberIdx.setBounds(164, 40, 106, 21);
		contentPane.add(lblMemberIdx);

		JLabel lblNewLabel_1 = new JLabel("회원 아이디");
		lblNewLabel_1.setBounds(42, 87, 74, 15);
		contentPane.add(lblNewLabel_1);

		lblMemberId = new JLabel(); // 회원 아이디를 표시할 JLabel 생성
		lblMemberId.setBounds(164, 84, 106, 21);
		contentPane.add(lblMemberId);

		JLabel lblNewLabel_1_1 = new JLabel("회원 등급");
		lblNewLabel_1_1.setBounds(42, 164, 52, 15);
		contentPane.add(lblNewLabel_1_1);
		

		// 수정 버튼 이벤트 처리
		btnUpdate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // 수정할 회원 정보를 가져옵니다.
		        int memberIdx = Integer.parseInt(lblMemberIdx.getText());
		        String memberName = lblMemberId.getText();
		        int memberRole = rdbtnManager.isSelected() ? 2 : 1; // 매니저 선택 시 2, 회원 선택 시 1

		        // 회원 정보를 수정하기 위해 DTO에 값을 설정합니다.
		        ManagerDTO dto = new ManagerDTO();
		        dto.setMember_idx(memberIdx);
		        dto.setMember_name(memberName);
		        dto.setMember_role(memberRole);

		        // 회원 정보를 수정하기 위해 Service와 DAO를 사용합니다.
		        ManagerService mservice = new ManagerServiceImpl();
		        mservice.setDAO(new ManagerDAO());
		        int result = mservice.updateMemberRole(dto);

		        // 수정 결과에 따라 메시지를 표시합니다.
		        if (result > 0) {
		            JOptionPane.showMessageDialog(null, "회원 정보가 수정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		            // 수정이 완료되면 자신의 창을 닫고, MemberRUD 창을 다시 엽니다.
		            dispose(); // 현재 창 닫기
		            MemberRUD memberRUD = new MemberRUD();
		            memberRUD.setVisible(true); // MemberRUD 창 열기
		        } else {
		            JOptionPane.showMessageDialog(null, "회원 정보 수정에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
    }
}
