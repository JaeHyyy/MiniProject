
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import com.dao.BookDAO;
import com.dao.MemberDAO;
import com.dao.RentalDAO;
import com.dto.BookDTO;
import com.dto.MemberDTO;
import com.dto.RentalDTO;
import com.service.BookService;
import com.service.BookServiceImpl;
import com.service.MemberService;
import com.service.MemberServiceImpl;
import com.service.RentalService;
import com.service.RentalServiceImpl;
import javax.swing.JScrollBar;

public class RentalMember extends JFrame {

	LogUser logMember = LogUser.getInstance();
	MemberDTO logUser = logMember.getLogUser();

	private JPanel contentPane;
	private JTextField textField;
	private JTable rentaltable;
	public int BookIdx;
	public String BookName;
	public String BookAuthor;
	public String BookPub;
	public String BookYear;
	public String BookGenre;
	public String BookISBN;
	public String BookPrice;
	public char BookRent;

	/**
	 * Launch the application.
	 */

	public void setBookInfo(int bookIdx, String bookName, String bookAuthor, String bookPub, String bookYear,
			String bookGenre, String bookISBN, String bookPrice, char bookRent) {
		BookIdx = bookIdx;
		BookName = bookName;
		BookAuthor = bookAuthor;
		BookPub = bookPub;
		BookYear = bookYear;
		BookGenre = bookGenre;
		BookISBN = bookISBN;
		BookPrice = bookPrice;
		BookRent = bookRent;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalMember frame = new RentalMember();
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
	public RentalMember() {
		super("도서 대여목록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 886, 443);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(53, 32, 267, 43);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnReturn = new JButton("반납");
		btnReturn.setBounds(475, 37, 65, 33);
		panel.add(btnReturn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 108, 548, 307);
		panel.add(scrollPane);

		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(334, 37, 67, 33);
		panel.add(btnSearch);

		// 테이블에 데이터 불러오기
		JTable rentaltable = new JTable();
		scrollPane.setViewportView(rentaltable);

		RentalService service = new RentalServiceImpl();
		service.setDao(new RentalDAO());
		List<RentalDTO> Rentallist = service.findAll();

		// 로그인한 회원의 아이디와 대여목록의 회원번호가 일치하는 것만 필터링
		Rentallist.removeIf(rental -> rental.getMember_idx() != (logUser.getMember_idx()));

		// 데이터 불러오기
		String[] header = { "대여 번호", "회원아이디", "책이름", "대여 날짜", "반납 날짜", "연체료" };

		Object[][] rowData = new Object[Rentallist.size()][header.length];
		logUser.getMember_id();
		for (int i = 0; i < Rentallist.size(); i++) {
			rowData[i][0] = Rentallist.get(i).getRental_idx();
			rowData[i][1] = getMemberId(Rentallist.get(i).getMember_idx());// 회원 아아디 가져오기
			rowData[i][2] = getBookTitle(Rentallist.get(i).getBook_idx()); // 책 제목 가져오기
			rowData[i][3] = Rentallist.get(i).getRental_period();
			rowData[i][4] = Rentallist.get(i).getRental_return();
			rowData[i][5] = Rentallist.get(i).getRental_latefee();
		}

		DefaultTableModel dm = new DefaultTableModel(rowData, header);
		rentaltable.setModel(dm);
		
		TableColumnModel columnModel = rentaltable.getColumnModel();
        TableColumn column = columnModel.getColumn(2); 
        column.setPreferredWidth(200); 

		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 선택된 행의 인덱스를 가져옵니다.
				int selectedRow = rentaltable.getSelectedRow();

				// 선택된 행이 없으면 메시지를 표시하고 종료합니다.
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "반납할 항목을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 선택된 행에서 회원번호를 가져옵니다.
				int rentalIdx = (int) rentaltable.getValueAt(selectedRow, 0);

				// 반납(삭제)
				RentalService rservice = new RentalServiceImpl();
				rservice.setDao(new RentalDAO());

				int bookIdx = rservice.findBookIdxByRentalIdx(rentalIdx);

				// 책 정보를 업데이트합니다.
				BookDTO bookDTO = new BookDTO();

				bookDTO.setBook_idx(bookIdx); // 수정 대상 도서의 도서 번호 설정
				bookDTO.setBook_rent('Y'); // 문자열로 가져온 대여 상태에서 첫 번째 문자를 대여 상태로 설정

				BookServiceImpl bookService = new BookServiceImpl();
				bookService.setDAO(new BookDAO());
				int n2 = bookService.updateBookRent(bookDTO);

				int result = rservice.rentDelete(rentalIdx); // 삭제 메서드의 반환값은 성공 여부 등을 나타내는 정수일 수 있습니다.

				// 삭제 결과에 따라 메시지를 표시합니다.
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

					// 테이블에서 해당 행을 제거합니다.
					dm.removeRow(selectedRow);
				} else {
					JOptionPane.showMessageDialog(null, "반납 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lblNewLabel = new JLabel("도서명");
		lblNewLabel.setBounds(53, 0, 77, 33);
		panel.add(lblNewLabel);

		JButton btnHome = new JButton("홈");
		btnHome.setBounds(558, 35, 63, 36);
		panel.add(btnHome);

		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DoorWay doorwayPage = new DoorWay();
				doorwayPage.setVisible(true);
				setVisible(false);

			}
		});

		// 검색 기능
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rentSearch();
			}

			private void rentSearch() {
				String search = textField.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);

				if (search.isEmpty()) {
					// 검색값 없이 검색할 경우 필터없이 모든 값 출력
					sorter.setRowFilter(null);
				} else {
					// 도서명 (컬럼0) 책제목 검색
					RowFilter<DefaultTableModel, Integer> filter = RowFilter.regexFilter(search, 2);
					sorter.setRowFilter(filter);
				}
				// 테이블 결과
				rentaltable.setRowSorter(sorter);
			}
		});
	}


	private String getBookTitle(int bookIdx) {
		String bookTitle = "책을 찾을 수 없습니다."; // 책을 찾을 수 없는 경우를 기본값으로 설정

		BookService bookService = new BookServiceImpl(); // BookService 객체 생성
		BookDAO bookDAO = new BookDAO(); // BookDAO 객체 생성

		// BookDAO를 BookServiceImpl에 설정
		bookService.setDAO(bookDAO);

		// BookService를 사용하여 책 정보 조회
		BookDTO bookDTO = bookService.selectByBookIdx(bookIdx);

		// 책 정보가 있는 경우 책 제목을 가져옴
		if (bookDTO != null) {
			bookTitle = bookDTO.getBook_name();
		}

		return bookTitle;
	}

	// 회원 번호를 받아서 회원 아이디를 반환하는 메서드
	private String getMemberId(int memberIdx) {
		String memberId = "회원을 찾을 수 없습니다.";

		MemberService memberService = new MemberServiceImpl();
		MemberDAO memberDAO = new MemberDAO();

		memberService.setDao(memberDAO);

		MemberDTO memberDTO = memberService.selectByMemberIdx(memberIdx);

		if (memberDTO != null) {
			memberId = memberDTO.getMember_id();
		}

		return memberId;
	}
}
