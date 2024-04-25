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

public class RentalManager extends JFrame {

   private JPanel contentPane;
   private JTextField textField;
   private JTable rentaltable;
   private DefaultTableModel dm;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               RentalManager frame = new RentalManager();
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
   public RentalManager() {
      super("도서 대여");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(300, 300, 900, 500);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBounds(0, 10, 874, 443);
      contentPane.add(panel);
      panel.setLayout(null);

      textField = new JTextField();
      textField.setBounds(53, 32, 267, 43);
      panel.add(textField);
      textField.setColumns(10);

      // 검색
      JButton btnSearch = new JButton("검색");
      btnSearch.setBounds(559, 42, 67, 33);
      panel.add(btnSearch);

      JButton btnDelete = new JButton("삭제");
      btnDelete.setBounds(650, 42, 65, 33);
      panel.add(btnDelete);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(39, 108, 765, 307);
      panel.add(scrollPane);
      
      btnDelete.addActionListener(new ActionListener() {
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
               JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

               // 테이블에서 해당 행을 제거합니다.
               dm.removeRow(selectedRow);
            } else {
               JOptionPane.showMessageDialog(null, "삭제 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
         }
      });

      // 테이블에 데이터 불러오기
      rentaltable = new JTable();
      //테이블 컬럼 더블클릭으로 수정 안되게 막기
      rentaltable.setDefaultEditor(Object.class, null);
      scrollPane.setViewportView(rentaltable);

      RentalService service = new RentalServiceImpl();
      service.setDao(new RentalDAO());
      List<RentalDTO> Rentallist = service.findAll();

      // 데이터 불러오기
      String[] header = { "대여 번호", "회원아이디", "책이름", "대여 날짜", "반납 날짜", "연체료" };

      Object[][] rowData = new Object[Rentallist.size()][header.length];

      for (int i = 0; i < Rentallist.size(); i++) {
         rowData[i][0] = Rentallist.get(i).getRental_idx();
         rowData[i][1] = getMemberId(Rentallist.get(i).getMember_idx());// 회원 아아디 가져오기
         rowData[i][2] = getBookTitle(Rentallist.get(i).getBook_idx()); // 책 제목 가져오기
         rowData[i][3] = Rentallist.get(i).getRental_period();
         rowData[i][4] = Rentallist.get(i).getRental_return();
         rowData[i][5] = Rentallist.get(i).getRental_latefee();
      }

      dm = new DefaultTableModel(rowData, header);
      rentaltable.setModel(dm);
      
      // 특정 컬럼의 너비 조절
        TableColumnModel columnModel = rentaltable.getColumnModel();
        TableColumn column = columnModel.getColumn(2); 
        column.setPreferredWidth(200); 

      JLabel lblNewLabel = new JLabel("도서명");
      lblNewLabel.setBounds(53, 0, 77, 33);
      panel.add(lblNewLabel);
      
            JButton btnHome = new JButton("홈");
            btnHome.setBounds(752, 47, 52, 23);
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

   // 책 번호를 받아서 책 제목을 반환하는 메서드
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