package kr.co.yj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.yj.service.YjService;

@Controller
public class YjController {

	
	@Resource(name="service")
	private YjService yjService;

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@RequestMapping("signup")
	public String signup(@RequestParam Map<String,Object> map, Model model) {
		
		System.out.println("회원가입"+map);
		Map<String, Object> idchk = yjService.idchk(map);
		
		if(idchk!=null) {
			int idkk=2;
			model.addAttribute("idkk", idkk);
			return "yj/member";
		}else if(idchk==null) {
			
			int idkk=1;
			model.addAttribute("idkk", idkk);	
			int sing=yjService.sing(map);
			
			return "yj/login";
		}
		
		
		
		return "yj/login";
	}
	
	@RequestMapping("idchk")
	public String check(@RequestParam Map<String,Object> map, Model model) {
		
		System.out.println("중복확인"+map);
		Map<String, Object> idchkk = yjService.idchk(map);
		String id= (String) map.get("id");
		String pass= (String) map.get("pass");
		String password= (String) map.get("password");
		String rank= (String) map.get("rank");
		String name= (String) map.get("name");
		
		model.addAttribute("id", id);
		model.addAttribute("pass", pass);
		model.addAttribute("password", password);
		model.addAttribute("rank", rank);
		model.addAttribute("name", name);

		System.out.println(idchkk);
		if(idchkk==null) {
			int idk=1;
			System.out.println(idk);
			model.addAttribute("idk", idk);
		}if(idchkk!=null) {
			int idk=2;
			System.out.println(idk);
			model.addAttribute("idk", idk);
		}
		
		
		return "yj/member";
	}
	
	@RequestMapping("member")
	public String member() {
		
		return "yj/member";
	}
	
	@RequestMapping("proxysave")
	public String proxy(@RequestParam Map<String,Object> map,HttpSession session,Model model) {
		

		
		Map<String, Object> sessionMap = (Map<String, Object>)session.getAttribute("login");

		map.put("name", sessionMap.get("name").toString());
		map.put("rank", sessionMap.get("rank").toString());
		map.put("id", sessionMap.get("id").toString());
		System.out.println("대리결재="+map);

		
		Map<String,Object> proxy3=yjService.proxy3(map);
		//선택된 네임값과 랭크값 가져오기
		System.out.println("proxy2="+proxy3);

		map.put("namel",proxy3.get("name").toString());
		map.put("rankl",proxy3.get("rank").toString());
		//권한 받을 사람 권한 주기
		int proxy=yjService.proxy4(map);

		
		return "redirect:proxy";
	}	
	
	
	@RequestMapping("proxy")
	public String proxy(Model model,@RequestParam Map<String,Object> map,HttpSession session) {
		
		List<Map<String,Object>> proxy =new ArrayList<Map<String,Object>>();
		
		
		Map<String, Object> sessionMap = (Map<String, Object>)session.getAttribute("login");
		
		map.put("name", sessionMap.get("name").toString());
		map.put("id", sessionMap.get("id").toString());
		map.put("pass", sessionMap.get("pass").toString());
		map.put("rank", sessionMap.get("rank").toString());
		
		//직급에 맞게 대리결재자 이름 표시
		proxy=yjService.proxy(map);

		model.addAttribute("proxy", proxy);
		System.out.println(map);
		
		return "yj/proxy";
	}

	
	@RequestMapping(value="proxyajax" ,produces = "application/text; charset=UTF-8")
	@ResponseBody
	//@ResponseBody yjpproxy2단일값을 그냥 념겨줄때 상용
	//@RequestMapping에서 produces = "application/text; charset=UTF-8" jsp에서 받은 한글 값을 jsp로 보낼때 인코딩하고보내는법
	public String proxyajax(Model model,@RequestParam String proxyId) {
		//선택된 아이디 값에 따라 직급 표시
		String proxy2=yjService.proxy2(proxyId);
		System.out.println("proxyId="+proxyId);
		
		return proxy2;
		
	}
	
	
	@RequestMapping("log")
	public String log(@RequestParam Map<String,Object> map,HttpSession session) {

		Map<String, Object> sessionMap = (Map<String, Object>)session.getAttribute("login");
		
		System.out.println("sessionMap::::::::::::::"+sessionMap);
				
		
	
		if(sessionMap != null) {
	  
			map.put("id", sessionMap.get("id").toString());
			map.put("pass",sessionMap.get("pass").toString());
			Map<String,Object>passchk=yjService.passchk(map);
			  

			return "redirect:list"; 
		
		}		
		
		
		return "yj/login";
	}
	
	@RequestMapping("login")
	public String login(@RequestParam Map<String,Object> map,Model model,HttpSession session) {
		
		Map<String,Object> idchk = yjService.idchk(map);
		Map<String,Object> passchk = yjService.passchk(map);
		
		if(idchk==null){
			
			int id= 1;
			model.addAttribute("id", id);
			
			
			return "yj/login";
		}else if(passchk==null) {

			int pass= 1;
			model.addAttribute("pass", pass);
			
			
			return "yj/login";
		}else {
			session.setAttribute("login", passchk);

			Map<String,Object> proxy=yjService.proxyone(map);
			
			if(proxy != null) {
				
				System.out.println("proxy:::"+proxy);
				int delete=sqlSession.delete("mapper.delete", map);
				
			}			
			
			
			
			return "redirect:list";
		}
		
		
		
	}
	

	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("login");
		
		return "redirect:log";
	}

	@RequestMapping("listajax")
	public String listajax(@RequestParam Map<String,Object> map, Model model,HttpSession session) {
		
		
		System.out.println("listajax::::::::::::::::"+map);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute("login");
		map.put("rank", sessionMap.get("rank").toString());
		map.put("id", sessionMap.get("id").toString());
		
		list=yjService.list(map);
		model.addAttribute("list", list);
		
		return "yj/listajax";
	}	
	
	
	@RequestMapping("list")
	public String list(@RequestParam Map<String,Object> map,Model model,HttpSession session) {
		
		Map<String,Object> sessionMap=(Map<String, Object>) session.getAttribute("login");
		
		if(sessionMap ==null) {
			
			return "redirect:log";
		}
		
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		
		map.put("rank", sessionMap.get("rank").toString());
		map.put("id", sessionMap.get("id").toString());
		
		Map<String,Object> proxyone=yjService.proxyone(map);
		System.out.println("proxyone"+proxyone);
		String type= (String) map.get("searchType");
		String txt= (String) map.get("search");
		String type2= (String) map.get("searchType2");
		String strDay= (String) map.get("startDay");
		String endDay= (String) map.get("endDay");

		if(proxyone ==null) {
			list=yjService.list(map);
			System.out.println("리스트::"+map);
		}		
		
		if(proxyone !=null) {
		map.put("proxyRank", proxyone.get("proxyRank").toString());
		map.put("proxyId", proxyone.get("proxyId").toString());
		list=yjService.list(map);
		System.out.println("프록시::"+map);
		}
		
		model.addAttribute("proxyone", proxyone);		
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		model.addAttribute("txt", txt);
		model.addAttribute("type2", type2);
		model.addAttribute("strDay", strDay);
		model.addAttribute("endDay", endDay);
		
		
		
		
		return "yj/list";
	}
	
	@RequestMapping("history")
	public String history(Model model) {
		
		int seq=yjService.seq();
		
		System.out.println("seq::::::::::::"+seq);
		model.addAttribute("seq", seq);
		
		return "yj/history";
	}
	
	@RequestMapping("save")
	public String save(@RequestParam Map<String,Object> map,HttpSession session) {
		
		
		Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute("login");

		map.put("id", sessionMap.get("id").toString());	

		int seq=yjService.seqqq(map);
		

			if(seq==0) {
				int save=yjService.save(map);
			}else {
				int reSave=yjService.reSave(map);
			}
				int historysave=yjService.historysave(map);

		return "redirect:list";
	
	}

	@RequestMapping("payment")
	public String payment(@RequestParam Map<String,Object> map,HttpSession session) {
		
		
		Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute("login");
		
		map.put("id", sessionMap.get("id").toString());	
		map.put("rank", sessionMap.get("rank").toString());	
		
		
		int seq=yjService.seqqq(map);
		
		Map<String,Object> proxyone=yjService.proxyone(map);

		if(proxyone ==null) {
			if(seq==0) {
				System.out.println("ㅁ누리ㅏㄴㅁ루ㅏㅣㄴㅁ");
				int payment=yjService.payment(map);
			}else {
				int repayment=yjService.repayment(map);
			}                         
			int historypayment=yjService.historypayment(map);
		}		
		
		if(proxyone !=null) {
		map.put("proxyRank", proxyone.get("proxyRank").toString());

				if(seq==0) {	
					int payment=yjService.payment(map);
				}else {
					System.out.println("ㅁ누리ㅏㄴㅁ루ㅏㅣㄴㅁ");
					int repayment=yjService.repayment(map);
				}                         
				int historypayment=yjService.historypayment(map);
		
		}
		
		
		return "redirect:list";
	}

	@RequestMapping("detail")
	public String detail(@RequestParam Map<String,Object> map,Model model,HttpSession session) {
		
		
		Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute("login");
		map.put("name", sessionMap.get("name").toString());	
		map.put("rank", sessionMap.get("rank").toString());	
		map.put("id", sessionMap.get("id").toString());	

		Map<String,Object> proxyone=yjService.proxyone(map);


	
		if(proxyone !=null) {
			map.put("proxyRank", proxyone.get("proxyRank").toString());
		}	
		
		String proxyRank= (String) map.get("proxyRank");
		System.out.println(map);
		Map<String,Object> detail=yjService.detail(map);
		List<Map<String,Object>> historydetail=yjService.historydetail(map);
		System.out.println("detail"+detail);
		System.out.println("historydetail"+historydetail);
		
		model.addAttribute("proxyone", proxyone);
		model.addAttribute("detail", detail);
		model.addAttribute("stu", "stu");
		model.addAttribute("historydetail", historydetail);
		return "yj/history";
	}
	
	
	@RequestMapping("back")
	public String back(@RequestParam Map<String,Object> map,HttpSession session) {
		
		Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute("login");
		
		map.put("rank", sessionMap.get("rank").toString());	
		map.put("id", sessionMap.get("id").toString());	
		System.out.println(map);
		
		int back=yjService.back(map);
		int historyback=yjService.historyback(map);
		
		
		
		return "redirect:list";
	}
	
	
}
