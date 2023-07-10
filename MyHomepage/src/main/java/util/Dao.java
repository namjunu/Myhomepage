package util;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Board;

public class Dao extends Da {
	
	//삭제
	public void del(String no) {
		super.connect(); //connect()라고 해도된다
		String sql = String.format("delete from %s where b_no=%s"
				,Db.TABLE_PS_BOARD_FREE, no);
		super.update(sql);
		super.close();
	}
	
	//쓰기
	public void write(Dto d) {
		super.connect();
		String sql = String.format("insert into %s (b_title,b_id,b_text) values ('%s','%s','%s')"
				,Db.TABLE_PS_BOARD_FREE, d.title,d.id,d.text);
		super.update(sql);
		super.close();
	}
	
	//글 읽기
	public Dto read(String no) {
		super.connect();
		Dto post = null;
		try {
			String sql = String.format("select * from %s where b_no=%s"
					,Db.TABLE_PS_BOARD_FREE, no);
			System.out.println("sql : " + sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(
					rs.getString("B_NO"),
					rs.getString("B_TITLE"),
					rs.getString("B_ID"),
					rs.getString("B_DATETIME"),
					rs.getString("B_HIT"),
					rs.getString("B_TEXT"),
					rs.getString("B_REPLY_COUNT"),
					rs.getString("B_REPLY_ORI")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return post;
	}
	
	//글리스트
	public ArrayList<Dto> list(String page){
		super.connect();
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			int startIndex = ((Integer.parseInt(page))-1)*Board.LIST_AMOUNT;
			
			String sql = String.format("select * from %s limit %s,%s",Db.TABLE_PS_BOARD_FREE,startIndex,Board.LIST_AMOUNT);
			System.out.println(" sql : " + sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				posts.add(new Dto(
						rs.getString("B_NO"),
						rs.getString("B_TITLE"),
						rs.getString("B_ID"),
						rs.getString("B_DATETIME"),
						rs.getString("B_HIT"),
						rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"),
						rs.getString("B_REPLY_ORI")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return posts;
	}
	
	
//	글 수정
	public void edit(Dto d,String no) {
		super.connect();
			String sql = String.format("update %s set b_title='%s',b_text='%s' where b_no=%s"
					,Db.TABLE_PS_BOARD_FREE, d.title,d.text,no);
			super.update(sql);
			super.close();
	}
	
	// 총 글 수 세기
	public int getPostCount() {
		int count = 0;
		super.connect();
		try {
			String sql = String.format("select count(*) from %s"
					,Db.TABLE_PS_BOARD_FREE);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return count;
	}
	
//	검색 글 수 구하기
	public int getSearchPostCount(String word) {
		int count = 0;
		super.connect();
		try {
			String sql = String.format("select count(*) from %s where b_title like '%%%s%%'"
					,Db.TABLE_PS_BOARD_FREE,word);
			System.out.println("sql : " + sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return count;
	}
//	글리스트 검색
	public ArrayList<Dto> listSearch(String word,String page){
		super.connect();
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			int startIndex = ((Integer.parseInt(page))-1)*Board.LIST_AMOUNT;
			
			String sql = String.format("select * from %s where b_title like '%%%s%%' limit %s,5"
					,Db.TABLE_PS_BOARD_FREE,word,startIndex,Board.LIST_AMOUNT);
			System.out.println("sql : " + sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				posts.add(new Dto(
						rs.getString("B_NO"),
						rs.getString("B_TITLE"),
						rs.getString("B_ID"),
						rs.getString("B_DATETIME"),
						rs.getString("B_HIT"),
						rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"),
						rs.getString("B_REPLY_ORI")
						));
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return posts;
	}
	
//	총 페이지 수 구하기
	public int getTotalPageCount() {
		int totalPageCount = 0;
		int count = getPostCount();
		if(count % 3 == 0) {
			totalPageCount = count / Board.LIST_AMOUNT ;
		} else {
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}
	
//	총 페이지 수 구하기<검색>
	public int getSearchTotalPageCount(String word) {
		int totalPageCount = 0;
		int count = getSearchPostCount(word);
		if(count % 3 == 0 ) {
			totalPageCount = count / Board.LIST_AMOUNT;
		} else {
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}
}
