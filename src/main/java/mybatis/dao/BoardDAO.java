package mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BoardVO;

public class BoardDAO {

	@Autowired
	private SqlSessionTemplate temple;
	
	
	//전체 글 조회
	public BoardVO[] list(String begin,String end,String bname){
		
		BoardVO[] ar = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("bname",bname);
		
		List<BoardVO> list = temple.selectList("bbs.list",map);
		if(list != null && list.size() >0) {
			ar = new BoardVO[list.size()];
			list.toArray(ar);
		}
			
		return ar;
	}
	
	
	//전체 게시물 수를 반환하는 기능
	public int getToatalCount(String bname) {
			
		return temple.selectOne("bbs.totalCount",bname);
	}
	
}
