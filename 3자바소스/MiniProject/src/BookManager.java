
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import com.service.BookService;
import com.service.BookServiceImpl;
import com.dao.BookDAO;
import com.dto.BookDTO;

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

public class BookManager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable bookTable;

	private DefaultTableModel dm;

	private JButton addBook;
	private JButton delBook;
	private JButton searchBook;
	JButton updateBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManager frame = new BookManager();
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
	public BookManager() {
		super("도서관리자");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("도서명");
		lblNewLabel.setBounds(12, 10, 52, 28);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(63, 11, 144, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		// 검색버튼
		searchBook = new JButton("검색");
		searchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchBook.setBounds(219, 15, 91, 23);
		contentPane.add(searchBook);

		// 추가 버튼
		addBook = new JButton("추가");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addBook.setBounds(350, 15, 91, 23);
		contentPane.add(addBook);

		// 수정 버튼
		updateBook = new JButton("수정");
		updateBook.setBounds(453, 15, 91, 23);
		contentPane.add(updateBook);

		// 삭제버튼
		delBook = new JButton("삭제");
		delBook.setBounds(554, 15, 91, 23);
		contentPane.add(delBook);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 53, 1200, 200);
		contentPane.add(scrollPane);

		JButton btnHome = new JButton("홈");
		btnHome.setBounds(718, 13, 52, 23);
		contentPane.add(btnHome);

		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				DoorWay doorwayPage = new DoorWay();
        		doorwayPage.setVisible(true);
		        setVisible(false);
		        
			}
		});

		// J테이블
		bookTable = new JTable();
		bookTable.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(bookTable);

		// 추가 페이지 열기
		addBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookCreate bookCreate = new BookCreate();
				bookCreate.setVisible(true);
				bookCreate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// 수정 페이지 열기
		// 수정 버튼 이벤트 리스너
		updateBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택된 행의 인덱스를 가져옵니다.
				int selectedRow = bookTable.getSelectedRow();

				// 선택된 행이 없으면 메시지를 표시하고 종료합니다.
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "수정할 항목을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 선택된 행에서 도서 정보를 가져옵니다.
				int bookIdx = (int) bookTable.getValueAt(selectedRow, 0); // 도서 번호
				String bookName = (String) bookTable.getValueAt(selectedRow, 1); // 도서명
				String bookAuthor = (String) bookTable.getValueAt(selectedRow, 2); // 저자
				String bookPub = (String) bookTable.getValueAt(selectedRow, 3); // 출판사
				String bookYear = (String) bookTable.getValueAt(selectedRow, 4); // 발행년도
				String bookGenre = (String) bookTable.getValueAt(selectedRow, 5); // 장르
				String bookISBN = (String) bookTable.getValueAt(selectedRow, 6); // ISBN
				String bookPrice = (String) bookTable.getValueAt(selectedRow, 7); // 가격
				String bookRentString = (String) bookTable.getValueAt(selectedRow, 8); // 대여 상태
				char bookRent = bookRentString.charAt(0);

				// BookUpdate 창에 해당 도서 정보를 전달하여 열기
				BookUpdate bookUpdate = new BookUpdate();
				bookUpdate.setBookInfo(bookIdx, bookName, bookAuthor, bookPub, bookYear, bookGenre, bookISBN, bookPrice,
						bookRent);
				bookUpdate.setVisible(true);
				bookUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// 삭제버튼 클릭 이벤트 처리
		delBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택된 행의 인덱스를 가져옵니다.
				int selectedRow = bookTable.getSelectedRow();

				// 선택된 행이 없으면 메시지를 표시하고 종료합니다.
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "삭제할 항목을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 선택된 행에서 회원번호를 가져옵니다.
				int bookIdx = (int) bookTable.getValueAt(selectedRow, 0); // 회원번호가 첫 번째 열에 있다고 가정합니다.

				// DAO를 통해 회원 삭제를 수행합니다.
				BookService bservice = new BookServiceImpl();
				bservice.setDAO(new BookDAO());
				int result = bservice.deleteByBookIdx(bookIdx); // 삭제 메서드의 반환값은 성공 여부 등을 나타내는 정수일 수 있습니다.

				// 삭제 결과에 따라 메시지를 표시합니다.
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "책이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

					// 테이블에서 해당 행을 제거합니다.
					dm.removeRow(selectedRow);
				} else {
					JOptionPane.showMessageDialog(null, "책 삭제에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 서비스 생성
		BookService bservice = new BookServiceImpl();
		bservice.setDAO(new BookDAO());
		List<BookDTO> booklist = bservice.findAllBook();
		String[] header = { "도서번호", "도서명", "저자", "출판사", "발행년도", "장르", "ISBN", "가격", "대여상태" };
		Object[][] rowData = new Object[booklist.size()][header.length];

		for (int i = 0; i < booklist.size(); i++) {
			for (int j = 0; j < header.length; j++)
				rowData[i][0] = booklist.get(i).getBook_idx();
			rowData[i][1] = booklist.get(i).getBook_name();
			rowData[i][2] = booklist.get(i).getBook_author();
			rowData[i][3] = booklist.get(i).getBook_pub();
			rowData[i][4] = booklist.get(i).getBook_year();
			rowData[i][5] = booklist.get(i).getBook_genre();
			rowData[i][6] = booklist.get(i).getBook_ISBN();
			rowData[i][7] = booklist.get(i).getBook_price();
			rowData[i][8] = booklist.get(i).getBook_rent() == 'Y' ? "대여 가능" : "대여 중";
		}
		
		
		// 이벤트 : 검색
		try {
			dm = new DefaultTableModel(rowData, header);
			bookTable.setModel(dm);
			TableColumnModel columnModel = bookTable.getColumnModel();
	        TableColumn column = columnModel.getColumn(1); 
	        column.setPreferredWidth(200); 
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		searchBook.addActionListener(new ActionListener() {
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

			// 도서명 (컬럼1) 책제목 검색
			RowFilter<DefaultTableModel, Integer> filter = RowFilter.regexFilter("(?i)" + search, 1);
			sorter.setRowFilter(filter);

		}
		// 테이블 결과
		bookTable.setRowSorter(sorter);

	}
}
