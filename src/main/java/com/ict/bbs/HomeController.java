package com.ict.bbs;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BoardDAO;
import mybatis.vo.BoardVO;
import spring.util.Paging;

@Controller
public class HomeController {
	
	
	@Autowired 
	private BoardDAO bDao;
	
	int nowPage;
	
	//상수
	public static final int BLOCK_LIST = 5; //한 페이지당 보여질 게시물 수
	public static final int BLOCK_PAGE = 5; //한 블록당 보여질 페이지 수
	
	int rowTotal; //전체 게시물 수
	String pageCode; //페이징 처리된 HTML코드가 저장될 곳!
		 
	
	@RequestMapping("board")
	public ModelAndView home(String nowPage,String bname) {

		if(nowPage == null)
			this.nowPage = 1;
		else this.nowPage = Integer.parseInt(nowPage);
		
		
		if(bname == null)
			bname = "BBS";
		
		rowTotal = bDao.getToatalCount(bname);
		
		Paging page = new Paging(this.nowPage,rowTotal,BLOCK_LIST,BLOCK_PAGE);
		
		pageCode = page.getSb().toString();
		
		
		ModelAndView mv = new ModelAndView();
		
		
		int begin = page.getBegin();
		int end = page.getEnd();
				
		BoardVO[] ar = bDao.list(String.valueOf(begin),String.valueOf(end),bname);
		
		mv.addObject("pageCode",pageCode);
		mv.addObject("nowPage",this.nowPage);
		mv.addObject("rowTotal",rowTotal);
		mv.addObject("blockList",BLOCK_LIST);
		
		
		mv.addObject("list",ar);
		mv.setViewName("list");
		
		return mv;
	}
	
}
