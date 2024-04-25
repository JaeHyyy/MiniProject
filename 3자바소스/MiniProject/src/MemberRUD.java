
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.service.ManagerService;
import com.service.ManagerServiceImpl;
import com.dao.ManagerDAO;
import com.dto.ManagerDTO;
import com.dto.MemberDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MemberRUD extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable memberTable;

	private DefaultTableModel dm;

	private JButton updateMember;
	private JButton delMember;
	private JButton searchMember;

	LogUser logMember = LogUser.getInstance();
	MemberDTO logUser = logMember.getLogUser();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberRUD frame = new MemberRUD();
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
	public MemberRUD() {
		super("사용자 관리");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("회원 아이디");
		lblNewLabel.setBounds(12, 10, 71, 28);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(85, 11, 144, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		// 검색버튼
		searchMember = new JButton("검색");
		searchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchMember.setBounds(241, 13, 91, 23);
		contentPane.add(searchMember);

		// 수정버튼
		updateMember = new JButton("수정");
		updateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updateMember.setBounds(369, 13, 91, 23);
		contentPane.add(updateMember);

		// 삭제버튼
		delMember = new JButton("탈퇴");
		delMember.setBounds(483, 13, 91, 23);
		contentPane.add(delMember);
		
		JButton btnHome = new JButton("홈");
		btnHome.setBounds(586, 13, 63, 23);
		contentPane.add(btnHome);
		
				btnHome.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						
						DoorWay doorwayPage = new DoorWay();
		        		doorwayPage.setVisible(true);
				        setVisible(false);
				        
					}
				});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 53, 562, 200);
		contentPane.add(scrollPane);

		// J테이블
		memberTable = new JTable();
		memberTable.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(memberTable);

		// 삭제버튼 클릭 이벤트 처리
		delMember.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = memberTable.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "삭제할 항목을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int memberIdx = (int) memberTable.getValueAt(selectedRow, 0); // 선택된 행의 회원번호를 가져옵니다.

		        // 현재 로그인한 회원의 회원번호를 가져옵니다.
		        int loggedInMemberIdx = logUser.getMember_idx();

		        // 로그인한 회원이 자기 자신을 삭제하려는지 확인합니다.
		        if (memberIdx == loggedInMemberIdx) {
		            JOptionPane.showMessageDialog(null, "자신을 삭제할 수 없습니다.", "알림", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // 삭제할 회원의 회원번호가 현재 로그인한 회원의 회원번호와 다른 경우에만 삭제 처리를 진행합니다.
		        ManagerService mservice = new ManagerServiceImpl();
		        mservice.setDAO(new ManagerDAO());
		        int result = mservice.deleteByMemberIdx(memberIdx);

		        if (result > 0) {
		            JOptionPane.showMessageDialog(null, "회원이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		            dm.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "회원 삭제에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		updateMember.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // 선택된 행의 인덱스를 가져옵니다.
		        int selectedRow = memberTable.getSelectedRow();

		        // 선택된 행이 없으면 메시지를 표시하고 종료합니다.
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "수정할 회원을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // 선택된 행에서 회원번호와 이름을 가져옵니다.
		        int memberIdx = (int) memberTable.getValueAt(selectedRow, 0); // 회원번호가 첫 번째 열에 있다고 가정합니다.
		        String memberName = (String) memberTable.getValueAt(selectedRow, 1); // 회원 이름이 세 번째 열에 있다고 가정합니다.
		        String memberRole = (String)memberTable.getValueAt(selectedRow, 5);
		        // 회원 등급 수정을 위한 창을 띄웁니다.
		        MemberGradeUpdate gradeUpdate = new MemberGradeUpdate();
		        gradeUpdate.setVisible(true);
		        gradeUpdate.setMemberInfo(memberIdx, memberName, memberRole);
		        gradeUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    }
		});

		// 서비스 생성
		ManagerService mservice = new ManagerServiceImpl();
		mservice.setDAO(new ManagerDAO());
		List<ManagerDTO> memberlist = mservice.findAllMember();

		String[] header = { "회원번호", "회원 아이디", "회원 이름", "회원 전화번호", "회원 생일", "회원 등급" };
		Object[][] rowData = new Object[memberlist.size()][header.length];

		for (int i = 0; i < memberlist.size(); i++) {
			for (int j = 0; j < header.length; j++)
				rowData[i][0] = memberlist.get(i).getMember_idx();
			rowData[i][1] = memberlist.get(i).getMember_id();
			rowData[i][2] = memberlist.get(i).getMember_name();
			rowData[i][3] = memberlist.get(i).getMember_phone();
			rowData[i][4] = memberlist.get(i).getMember_date();
			rowData[i][5] = memberlist.get(i).getMember_role();

			// 회원 등급 숫자를 문자열로 변환하여 저장
			if (memberlist.get(i).getMember_role() == 1) {
				rowData[i][5] = "회원";
			} else if (memberlist.get(i).getMember_role() == 2) {
				rowData[i][5] = "매니저";
			} else {
				rowData[i][5] = "기타"; // 다른 등급에 대한 처리를 원할 경우 추가
			}
		}

		// 이벤트 : 검색
		try {
			dm = new DefaultTableModel(rowData, header);
			memberTable.setModel(dm);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		searchMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performSearch();

			}
		});
	}

	// 검색 기능
	private void performSearch() {

		String search = textField.getText().trim().toLowerCase();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);

		if (search.isEmpty()) {
			// 검색값 없이 검색할 경우 필터없이 모든 값 출력
			sorter.setRowFilter(null);

		} else {

			RowFilter<DefaultTableModel, Integer> filter = RowFilter.regexFilter(search, 1);
			sorter.setRowFilter(filter);

		}
		// 테이블 결과
		memberTable.setRowSorter(sorter);
	}

}
