package com.omg.employee.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;


@Controller
public class EmployeeController {
	final Logger LOG=LoggerFactory.getLogger(EmployeeController.class);
	
	/*
	 * @Resource(name = "uploadPath") private String uploadPath;
	 */
	
	@Autowired 
	private JavaMailSenderImpl mailSenderImpl;
	 
	
	@Autowired
	EmployeeService employeeService;
	
	public EmployeeController() {}
	
	//사원 추가
	@RequestMapping(value="employee/employee_reg.do",method=RequestMethod.GET)
	public String employee_view() {
		LOG.debug("== employee_view ==");
		
		return "employee/employee_reg";
	}
	
	//로그인 화면
	@RequestMapping(value="employee/login.do",method=RequestMethod.GET)
	public String login_view() {
		LOG.debug("== login_view ==");
		
		return "employee/login";
	}
	
	//사원목록
	@RequestMapping(value="employee/employee_list.do",method=RequestMethod.GET)
	public String employee_list() {
		LOG.debug("== employee_list ==");
		
		return "employee/employee_list";
	}
	
	//관리자모드 사원 수정
	@RequestMapping(value="employee/employee_mng.do",method=RequestMethod.GET)
	public String employee_mng() {
		LOG.debug("== employee_mng ==");
		
		return "employee/employee_mng";
	}
	
	
	//비밀번호 찾기
	@RequestMapping(value="employee/forgot_password.do",method=RequestMethod.GET)
	public String forgot_password() {
		LOG.debug("== forgot_password ==");
		
		return "employee/forgot_password";
	}
	
	//마이페이지
	@RequestMapping(value="employee/mypage.do",method=RequestMethod.GET)
	public String mypage() {
		LOG.debug("== mypage ==");
		
		return "employee/mypage";
	}
	
	/*
	 * //uploadController 생성
	 * 
	 * @RequestMapping(value = "/uploadForm", method = RequestMethod.GET) public
	 * void uploadForm() throws Exception { }
	 * 
	 * @RequestMapping(value = "/uploadForm", method = RequestMethod.POST) public
	 * String uploadForm(MultipartFile file, Model model) throws Exception {
	 * 
	 * LOG.debug("originalName: " + file.getOriginalFilename()); LOG.debug("size: "
	 * + file.getSize()); LOG.debug("contentType: " + file.getContentType());
	 * 
	 * String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
	 * 
	 * model.addAttribute("savedName", savedName);
	 * 
	 * return "uploadResult"; }
	 * 
	 * private String uploadFile(String originalName, byte[] fileData) throws
	 * Exception {
	 * 
	 * UUID uid = UUID.randomUUID(); String savedName = uid.toString() + "_" +
	 * originalName; File target = new File(uploadPath, savedName);
	 * FileCopyUtils.copy(fileData, target); return savedName; }
	 */
	
	 
	
	
	
	  //사원 비밀번호 메일로 보내기
	  @RequestMapping(value="employee/sendMail.do",method=RequestMethod.GET) 
	  @ResponseBody
	  public String sendMail(EmployeeVO employee,HttpServletRequest request) {
		  LOG.debug("=sendMail=");
		  LOG.debug("=employee="+employee);
		  
		  EmployeeVO outVO=employeeService.doSelectOne(employee);
		  LOG.debug("=outVO="+outVO);
		  
		  try {
			  MimeMessage message=mailSenderImpl.createMimeMessage();
			  MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"UTF-8");
			  
			  messageHelper.setFrom("casse2045@naver.com");
			  messageHelper.setTo(outVO.getEmail());
			  messageHelper.setSubject(outVO.getName()+"님 사원 비밀번호 찾기");
			  messageHelper.setText(outVO.getName()+"님의 비밀번호는 "+outVO.getPassword()+"입니다.");
			  
			  mailSenderImpl.send(message);
			  
		  }catch(Exception e) {
			  e.getMessage();
		  }
		 		  
		  return "result";
	  
	  }
	 
	 
	
	@RequestMapping(value="employee/doSelectOne.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doSelectOne(EmployeeVO employee,Model model) {
		
		LOG.debug("=doSelectOne=");	
		LOG.debug("=param="+employee);
		  
        EmployeeVO outVO=this.employeeService.doSelectOne(employee);
        LOG.debug("==================");
        LOG.debug("=outVO="+outVO);
        LOG.debug("==================");
        
        Gson gson=new Gson();
        String json = gson.toJson(outVO);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
         
        return json;
	}
	
	
	
	
	@RequestMapping(value="employee/doSelectList.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public List<EmployeeVO> doSelectList(HttpServletRequest req,Search search) {
		LOG.debug("1==================");
        LOG.debug("=search="+search);
        LOG.debug("==================");
        if(search.getPageNum()==0) {
        	search.setPageNum(1);
        }
        
        //페이지 사이즈 기본값 처리 
        if(search.getPageSize()==0) {
        	search.setPageSize(10);
        }
        
        //검색구분
        search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
        
        //검색어
        search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
        LOG.debug("2==================");
        LOG.debug("=null처리 search="+search);
        LOG.debug("==================");       
        
        List<EmployeeVO> list= this.employeeService.doSelectList(search);
        
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");   		
		
		
		return list;
	}
	
	@RequestMapping(value="employee/doUpdate.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doUpdate(EmployeeVO employee) {
		LOG.debug("1==================");
        LOG.debug("=user="+employee);
        LOG.debug("==================");	
        
        int flag=this.employeeService.doUpdate(employee);
        LOG.debug("2==================");
        LOG.debug("=flag="+flag);
        LOG.debug("==================");     
        
        Message message=new Message();
        message.setMsgId(String.valueOf(flag));
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 수정 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 수정 실패.");
        }
        
        Gson   gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");		
		return json;
	}
	

	@RequestMapping(value="employee/doLogin.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doLogin(EmployeeVO employee,HttpServletRequest request) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.doLogin(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        
        //메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getEmployee_id()+"로그인에 성공하셨습니다.");
        }else {
        	message.setMsgContents(employee.getEmployee_id()+"로그인에 실패하셨습니다.");
        }
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
        //세션처리
        EmployeeVO sessionEmployee=this.employeeService.doSelectOne(employee);
        LOG.debug("==================");
        LOG.debug("=sessionEmployee="+sessionEmployee);
        LOG.debug("==================");  
        
        HttpSession session=request.getSession();
        session.setAttribute("employee", sessionEmployee);
        
        LOG.debug("==================");
        LOG.debug("=session="+session.getAttribute("employee"));
        LOG.debug("==================");  
        
        
		return json;
	}
	
	
	@RequestMapping(value="employee/idConfirm.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String idConfirm(EmployeeVO employee) {
		LOG.debug("=idConfirm***********");
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.idConfirm(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        
        //메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getEmployee_id()+"존재하는 아이디 입니다.");
        }else {
        	message.setMsgContents(employee.getEmployee_id()+"존재하지 않는 아이디 입니다.");
        }
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
		return json;
        
	}
	
	@RequestMapping(value="employee/doDelete.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doDelete (EmployeeVO employee) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.doDelete(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        
        Message message=new Message();
        message.setMsgId(String.valueOf(flag));
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 삭제 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 삭제 실패.");
        }
        
        Gson   gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");		
		return json;    
	}
	
	

	@RequestMapping(value="employee/doInsert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doInsert(EmployeeVO employee) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.doInsert(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 등록 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 등록 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
		return json;
	}
	
	
}
