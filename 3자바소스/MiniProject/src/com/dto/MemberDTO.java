package com.dto;

public class MemberDTO {

    private int member_idx;             // IDX
    private String member_id;           // 아이디
    private String member_name;         // 이름
    private String member_pw;     // 비밀번호
    private String member_phone;        // 핸드폰 번호
    private String member_date;         // 생년월일
    private int member_role;            // 관리자 체크 CHECK(1 OR 2)
                                        // 1 -> 회원   2 -> 관리자
    									// Default -> 1

    public MemberDTO() {
    }

	public MemberDTO(int member_idx, String member_id, String member_name, String member_pw, String member_phone,
			String member_date, int member_role) {
		super();
		this.member_idx = member_idx;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_pw = member_pw;
		this.member_phone = member_phone;
		this.member_date = member_date;
		this.member_role = member_role;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_date() {
		return member_date;
	}

	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}

	public int getMember_role() {
		return member_role;
	}

	public void setMember_role(int member_role) {
		this.member_role = member_role;
	}

	@Override
	public String toString() {
		return "MemberDTO [member_idx=" + member_idx + ", member_id=" + member_id + ", member_name=" + member_name
				+ ", member_pw=" + member_pw + ", member_phone=" + member_phone + ", member_date=" + member_date
				+ ", member_role=" + member_role + "]";
	}

	
	
}
