package model;
public class TbSpinfo implements java.io.Serializable {
	private String id;
	private String spname;
	private String jc;
	private String cd;
	private String dw;
	private String gg;
	private String bz;
	private String ph;
	private String pzwh;
	private String memo;
	private String gysname;
	public TbSpinfo() {
	}

	public TbSpinfo(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpname() {
		return this.spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String getJc() {
		return this.jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getCd() {
		return this.cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getDw() {
		return this.dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getGg() {
		return this.gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPh() {
		return this.ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getPzwh() {
		return this.pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGysname() {
		return this.gysname;
	}

	public void setGysname(String gysname) {
		this.gysname = gysname;
	}

	public String toString() {
		return getSpname();
	}
	
}