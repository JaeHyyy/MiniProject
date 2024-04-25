import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.BookDAO;
import com.dto.BookDTO;
import com.service.BookService;
import com.service.BookServiceImpl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BookCreate extends JFrame {

	private JPanel contentPane;
	private JTextField tfBName;
	private JTextField tfBAuthor;
	private JTextField tfBPub;
	private JTextField tfBYear;
	private JTextField tfBGenre;
	private JTextField tfBISBN;
	private JTextField tfBPrice;
	JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCreate frame = new BookCreate();
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
	public BookCreate() {
		super("도서 추가");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도서명");
		lblNewLabel.setBounds(26, 30, 52, 15);
		contentPane.add(lblNewLabel);
		
		tfBName = new JTextField();
		tfBName.setColumns(10);
		tfBName.setBounds(128, 30, 150, 21);
		contentPane.add(tfBName);
		
		JLabel lblNewLabel_1 = new JLabel("저자");
		lblNewLabel_1.setBounds(26, 70, 52, 15);
		contentPane.add(lblNewLabel_1);
		
		tfBAuthor = new JTextField();
		tfBAuthor.setColumns(10);
		tfBAuthor.setBounds(128, 70, 150, 21);
		contentPane.add(tfBAuthor);
		
		JLabel lblNewLabel_1_1 = new JLabel("출판사");
		lblNewLabel_1_1.setBounds(26, 110, 52, 15);
		contentPane.add(lblNewLabel_1_1);
		
		tfBPub = new JTextField();
		tfBPub.setColumns(10);
		tfBPub.setBounds(128, 110, 150, 21);
		contentPane.add(tfBPub);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("발행년도");
		lblNewLabel_1_1_1.setBounds(26, 150, 52, 15);
		contentPane.add(lblNewLabel_1_1_1);
		
		tfBYear = new JTextField();
		tfBYear.setColumns(10);
		tfBYear.setBounds(128, 150, 150, 21);
		contentPane.add(tfBYear);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("장르");
		lblNewLabel_1_1_2.setBounds(26, 190, 52, 15);
		contentPane.add(lblNewLabel_1_1_2);
		
		tfBGenre = new JTextField();
		tfBGenre.setColumns(10);
		tfBGenre.setBounds(128, 190, 150, 21);
		contentPane.add(tfBGenre);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("ISBN");
		lblNewLabel_1_1_3.setBounds(26, 230, 52, 15);
		contentPane.add(lblNewLabel_1_1_3);
		
		tfBISBN = new JTextField();
		tfBISBN.setColumns(10);
		tfBISBN.setBounds(128, 230, 150, 21);
		contentPane.add(tfBISBN);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("가격");
		lblNewLabel_1_1_4.setBounds(26, 270, 52, 15);
		contentPane.add(lblNewLabel_1_1_4);
		
		tfBPrice = new JTextField();
		tfBPrice.setColumns(10);
		tfBPrice.setBounds(128, 270, 150, 21);
		contentPane.add(tfBPrice);
		
		btnAdd = new JButton("추가");
		btnAdd.setBounds(237, 415, 97, 23);
		contentPane.add(btnAdd);
		
		//button 이벤트 처리
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String bName = tfBName.getText();
				String bAuthor = tfBAuthor.getText();
				String bPub = tfBPub.getText();
				String bYear = tfBYear.getText();
				String bGenre = tfBGenre.getText();
				String bISBN = tfBISBN.getText();
				String bPrice = tfBPrice.getText();
				
				System.out.println(bName+"\t"+bAuthor+"\t"+bPub+"\t"+bYear+"\t"+bGenre+"\t"+bISBN+"\t"+bPrice);
				
				
				BookDTO dto = new BookDTO();
				dto.setBook_name(bName);
				dto.setBook_author(bAuthor);
				dto.setBook_pub(bPub);
				dto.setBook_year(bYear);
				dto.setBook_genre(bGenre);
				dto.setBook_ISBN(bISBN);
				dto.setBook_price(bPrice);
		        dto.setBook_rent('Y');

				
				BookService service = new BookServiceImpl();
				service.setDAO(new BookDAO());
				int n = service.createBook(dto);
				System.out.println(n+"개가 저장됨");
				JOptionPane.showInternalMessageDialog(null, n+"개가 저장됨",
			             "저장", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
	}
}
