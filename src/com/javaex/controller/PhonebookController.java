package com.javaex.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")   //이 이름대로 주소적고 들어감 
public class PhonebookController extends HttpServlet {
	
       
  
	//주소치면 대부분 겟 방식 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		//System.out.println("PhonebookController"); //콘솔에서 실행되는 거 확인 가능
		
		
		//입력한 파라미터에 따라 꺼내줘야 함 
		//wirteForm --> 전화번호 등록폼 
		//list ---> list 출력 
		String act = request.getParameter("action");
		
		//test
		System.out.println(act);
		
		if("list".equals(act)) {
			
			System.out.println("action=list");
			//PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = new PhoneDao().getPersonList();		
			
			//test
			//System.out.println(personList.toString());  //프린트엘엔은 tostring 안써도 알아서 찾아가게 되어있음 
			
			//html 과 list를 섞어서 표현해야함 
			//servlet 으로는 표현이 복잡하다  --> jsp를 이용한다 
			//어트리뷰트 영역에 넣음 파라미터 아님 주의 
			request.setAttribute("pList", personList); //(주소만 준 것) "별명" 이건 별명 이거로 꺼내오려고 데이터가 여러개라면 꺼낼 때 이름을 주어야지 꺼내서 쓸 수 있지 
			//포워드와 어트리뷰트 개념 잘 알아두기 
			//어트리뷰트에는 어떤것이든 올 수 있기 때문에 리스트에서 형변환해야함  
			
			//포워드
			RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/list.jsp"); ///list.jsp한테 시키겠다 
			rd.forward(request, response);  //list.jsp에 포워드한다. 슬쩍 밀어넣는다 어트리뷰트에 넣어놓고 포워드 해야함 순서가 중요
											//WEB_INF 안에 넣어주면 위치도 바꿔줘야 함 /WEB-INF/list.jsp
											//코드로는 어려운 게 없음 메소드 전부 가져다 쓰면 됨. 개념을 잘 안아둘 것 
	
		}else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			System.out.println("전화번호 등록 폼");   
			//꼬리표(파라미터)를 보고 분류시킨것 
			//이제 사용자에게 보내줄 writeForm을 만들어줌 html만 잔뜩 그려지는 --> 포워드 시켜줌 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp"); //포워드 하는 문법 
			rd.forward(request, response); //문법 
		
		}else if("write".equals(act)) {
			System.out.println("action=write"); //test 여기까지 로그 찍는 거 확인하기 
			
			//순서 
			//1.파라미터 3개의 값을 꺼내온다 
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//2.vo로 만든다 
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//3.dao 메모리 올린다
			PhoneDao phoneDao = new PhoneDao();
			
			//4.dao.insert(vo); 올린다
			phoneDao.personInsert(personVo);
			
			//포워드보다 리다이렉트가 더 좋은 선택 
			//빈페이지를 보내주고 두 번 돌려버림 
			//response에 넣어줌 
			//포워드 아니면 리다이렉트로 끝이 나야함 빈 화면을 보내주는 일이 없기 때문 
			response.sendRedirect("/phonebook2/pbc?action=list"); //주소로 요청 
			
			
		//지우기
		} else if ("delete".equals(act)){
			//파라미터 형변환
			System.out.println("action=delete");
			int id =  Integer.parseInt(request.getParameter("id"));
			new PhoneDao().personDelete(id);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		
			
        //업데이트 폼
		} else if ("updateForm".equals(act)){ 
			System.out.println("action=updateForm");
			
			int id = Integer.parseInt(request.getParameter("id"));
			PersonVo personVo = new PhoneDao().getPerson(id);
			request.setAttribute("pV", personVo); //주소만 준다
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
			
	    //업데이트 입력
		} else  if("update".equals(act)) {
			System.out.println("action=update");
			
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int id =  Integer.parseInt(request.getParameter("id"));
			PersonVo personVo= new PersonVo(id, name, hp, company);
			//PhoneDao를 메모리에 올린다. //저장
			new PhoneDao().personUpdate(personVo);
			response.sendRedirect("/phonebook2/pbc?action=list");
		
		
		}else {
			System.out.println("파라미터 값 없음");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
