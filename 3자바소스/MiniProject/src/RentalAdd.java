

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dao.BookDAO;
import com.dao.RentalDAO;
import com.dto.BookDTO;
import com.dto.MemberDTO;
import com.dto.RentalDTO;
import com.service.BookService;
import com.service.BookServiceImpl;
import com.service.RentalService;
import com.service.RentalServiceImpl;

public class RentalAdd extends JFrame {

	LogUser logMember = LogUser.getInstance();
	MemberDTO logUser = logMember.getLogUser();
	
	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JTextField tFMemberId;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public int BookIdx;
	public String BookName;
	public String BookAuthor;
	public String BookPub;
	public String BookYear;
	public String BookGenre;
	public String BookISBN;
	public String BookPrice;
	public char BookRent;
	
	public int MemberIdx;
	/**
	 * Launch the application.
	 */
	public void setBookInfo(int bookIdx, String bookName, String bookAuthor, String bookPub, String bookYear, String bookGenre, String bookISBN, String bookPrice, char bookRent) {
		BookIdx = bookIdx;
		BookName = bookName;
		BookAuthor = bookAuthor;
		BookPub = bookPub;
		BookYear = bookYear;
		BookGenre = bookGenre;
		BookISBN = bookISBN;
		BookPrice = bookPrice;
		BookRent = bookRent;
		MemberIdx = logUser.getMember_idx();
		tFMemberId.setText(logUser.getMember_id());
	    textField_1.setText(bookName);
	    textField_2.setText(getCurrentDate());
	    textField_3.setText(getDatePlusSevenDays());
	}
	
	

	private String getCurrentDate() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	
	private String getDatePlusSevenDays() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // 7일 추가
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalAdd frame = new RentalAdd();
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
	public RentalAdd() {
		super("도서 대여");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		btnNewButton = new JButton("대여");
		btnNewButton.setBounds(340, 344, 105, 37);
		contentPane.add(btnNewButton);
				
		lblNewLabel = new JLabel("회원ID");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(104, 33, 69, 37);
		contentPane.add(lblNewLabel);
		
		tFMemberId = new JTextField();
		tFMemberId.setBounds(185, 34, 149, 37);
		contentPane.add(tFMemberId);
		tFMemberId.setColumns(10);
		
		JLabel lblid = new JLabel("도서명");
		lblid.setFont(new Font("굴림", Font.PLAIN, 18));
		lblid.setBounds(104, 108, 69, 37);
		contentPane.add(lblid);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 108, 149, 37);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("대여일자");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(84, 185, 89, 37);
		contentPane.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(186, 185, 149, 37);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("반납일자");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(84, 266, 89, 37);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(186, 267, 149, 37);
		contentPane.add(textField_3);
		
		JButton btnHome = new JButton("홈");
	      btnHome.setBounds(100, 344, 105, 37);
	      contentPane.add(btnHome);
	      btnHome.addActionListener(new ActionListener() {
	          
	          @Override
	          public void actionPerformed(ActionEvent e) {
	             
	             BookMember BookMemberPage = new BookMember();
	             BookMemberPage.setVisible(true);
	               setVisible(false);
	               
	          }
	       });
		// button 이벤트 처리
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				   // 대여 상태를 'N'으로 업데이트
		        BookDTO bookDTO = new BookDTO();
		        bookDTO.setBook_idx(BookIdx); // 수정 대상 도서의 도서 번호 설정
		        bookDTO.setBook_name(BookName);
		        bookDTO.setBook_author(BookAuthor);
		        bookDTO.setBook_pub(BookPub);
		        bookDTO.setBook_year(BookYear);
		        bookDTO.setBook_genre(BookGenre);
		        bookDTO.setBook_ISBN(BookISBN);
		        bookDTO.setBook_price(BookPrice);
		        bookDTO.setBook_rent('N'); // 문자열로 가져온 대여 상태에서 첫 번째 문자를 대여 상태로 설정

		        BookServiceImpl bookService = new BookServiceImpl();
		        bookService.setDAO(new BookDAO());
				int n2 = bookService.updateBook(bookDTO);


				
				String member_id = tFMemberId.getText();
				String book_name = textField_1.getText();
				String rental_period = textField_2.getText();
				String rental_return = textField_3.getText();
				
				System.out.println(member_id+"\t"+book_name+"\t"+rental_period+"\t"+rental_return);
				
				RentalDTO dto = new RentalDTO();
				dto.setMember_idx(logUser.getMember_idx());
				dto.setBook_idx(BookIdx);
				dto.setRental_period(rental_period);
				dto.setRental_return(rental_return);
				
				RentalService service = new RentalServiceImpl();
				service.setDao(new RentalDAO());
				int n = service.rentAdd(dto);
				
				JOptionPane.showInternalMessageDialog(null, "대여 완료",
			             "대여", JOptionPane.INFORMATION_MESSAGE);
				dispose(); // 현재 창 닫기
				
	            if (logUser.getMember_role() == 1) { // 회원 등급이 1인 경우
	                RentalMember rentalMember = new RentalMember();
	                rentalMember.setVisible(true);
	                setVisible(false);
	            } else if (logUser.getMember_role() == 2) { // 회원 등급이 2인 경우
	            	RentalManager rentalManager = new RentalManager();
	            	rentalManager.setVisible(true);
	                setVisible(false);
	            }
				
			}
		});
		
		
	}
	
}
