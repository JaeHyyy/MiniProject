import com.dto.MemberDTO;

public class LogUser {
	private static LogUser instance;
	private MemberDTO logMember;
	
	private LogUser() {
		
	}
    
	public static synchronized LogUser getInstance() {
        if (instance == null) {
            instance = new LogUser();
        }
        return instance;
    }
	
	public MemberDTO getLogUser() {
        return logMember;
    }

    public void setLogUser(MemberDTO logMember) {
        this.logMember = logMember;
    }
}
