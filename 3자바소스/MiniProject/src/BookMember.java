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
import com.dto.BookDTO;
import com.service.BookService;
import com.service.BookServiceImpl;

public class BookMember extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable bookTable;
    private DefaultTableModel dm;
    private JButton searchBook;
    private JButton bookRental;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookMember frame = new BookMember();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BookMember() {
        super("도서 둘러보기");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("도서명");
        lblNewLabel.setBounds(12, 10, 52, 28);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(63, 11, 144, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        searchBook = new JButton("검색");
        searchBook.setBounds(217, 13, 91, 23);
        contentPane.add(searchBook);

        bookRental = new JButton("대여");
        bookRental.setBounds(320, 13, 91, 23);
        contentPane.add(bookRental);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 53, 962, 200);
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
        
        bookTable = new JTable();
        bookTable.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(bookTable);

        BookService bservice = new BookServiceImpl();
        bservice.setDAO(new BookDAO());
        List<BookDTO> booklist = bservice.findAllBook();

        String[] header = {"도서번호", "도서명", "저자", "출판사", "발행년도", "장르", "ISBN", "가격", "대여상태"};
        Object[][] rowData = new Object[booklist.size()][header.length];

        for (int i = 0; i < booklist.size(); i++) {
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

        bookRental.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = bookTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "대여할 책을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String bookRentString2 = (String) bookTable.getValueAt(selectedRow, 8); // 대여 상태

                // 대여 상태가 'N'인 경우에만 대여 가능하도록 처리
                if (bookRentString2.equals("N")) {
                    JOptionPane.showMessageDialog(null, "이 책은 현재 대여 중입니다. 다른 책을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int bookIdx = (int) bookTable.getValueAt(selectedRow, 0); // 도서 번호
	            String bookName = (String) bookTable.getValueAt(selectedRow, 1); // 도서명
	            String bookAuthor = (String) bookTable.getValueAt(selectedRow, 2); // 저자
	            String bookPub = (String) bookTable.getValueAt(selectedRow, 3); // 출판사
	            String bookYear = (String) bookTable.getValueAt(selectedRow, 4); // 발행년도
	            String bookGenre = (String) bookTable.getValueAt(selectedRow, 5); // 장르
	            String bookISBN = (String) bookTable.getValueAt(selectedRow, 6); // ISBN
	            String bookPrice = (String) bookTable.getValueAt(selectedRow, 7); // 가격
	            String bookRentString = (String) bookTable.getValueAt(selectedRow, 8); // 대여 상태

	            // 대여 상태가 'N'인 경우에만 대여 가능하도록 처리
	            if (bookRentString.equals("대여 중")) {
	                JOptionPane.showMessageDialog(null, "이 책은 현재 대여 중입니다. 다른 책을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            
	            char bookRent = bookRentString.charAt(0);
                RentalAdd rentalAdd = new RentalAdd();
                rentalAdd.setBookInfo(bookIdx, bookName, bookAuthor, bookPub, bookYear, bookGenre, bookISBN, bookPrice, bookRent);
                RentalMember rentalMember = new RentalMember();
//                rentalMember.setBookInfo(bookIdx, bookName, bookAuthor, bookPub, bookYear, bookGenre, bookISBN, bookPrice, bookRent);
                rentalAdd.setVisible(true);
		        setVisible(false);
		        
            }
        });

    }

    private void performSearch() {
        String search = textField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);

        if (search.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            RowFilter<DefaultTableModel, Integer> filter = RowFilter.regexFilter("(?i)" + search, 1);
            sorter.setRowFilter(filter);
        }

        bookTable.setRowSorter(sorter);
    }

}
