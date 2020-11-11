package com.omg.schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omg.cmn.Criteria;
import com.omg.cmn.PageDTO;
import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {

<<<<<<< Updated upstream
    Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService service;

    /**
     * ?Ό?  μΆκ?
     * 
     * @param inVO
     * @param rttr
     * @author λ°μ λ―?
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String insert(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Insert]ScheduleVO: " + inVO);

	service.doInsert(inVO);

	return "redirect:/schedule/list.do"; // ??± ?λ£λλ©? ?Ό? κ΄?λ¦? ??΄μ§?λ‘? λ¦¬λ€?΄? ?Έ
    }
    
    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public void insert() {
    	
    }
    
    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public void insert() {
    	
    }

    /**
     * ?Ό?  ?­? 
     * 
     * @param scheduleNo
     * @param rttr
     * @author λ°μ λ―?
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public String remove(@RequestParam("scheduleNo") int scheduleNo, RedirectAttributes rttr) {
	log.debug("[Delete]scheduleNo: " + scheduleNo);

	ScheduleVO inVO = new ScheduleVO();
	inVO.setScheduleNo(scheduleNo);

	if (service.doDelete(inVO) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}

	return "redirect:/schedule/list.do";
    }

    /**
     * ?Ό?  ?? 
     * 
     * @param inVO
     * @param rttr
     * @author λ°μ λ―?
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String update(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Update]ScheduleVO: " + inVO);

	if (service.doUpdate(inVO) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}

	return "redirect:/schedule/list.do";
    }

    /**
     * ?Ό?  ? ?
     * 
     * @param scheduleNo
     * @param model
     * @author λ°μ λ―?
     */
    @RequestMapping(value = { "/get.do", "/update.do" }, method = RequestMethod.GET)
    public void get(@RequestParam("scheduleNo") int scheduleNo, Model model) {
	log.debug("doSelectOne or doUpdate.....");

	ScheduleVO inVO = new ScheduleVO();
	inVO.setScheduleNo(scheduleNo);

	model.addAttribute("schedule", service.doSelectOne(inVO));
    }

    /**
     * ?Ό?  κ²?? 
     * deptNo: 0(? μ²΄κ??) or λΆ??λ³κ??
     * 
     * @param deptNo
     * @param model
     * @author λ°μ λ―?
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public void list(Criteria cri, Model model) {
	log.debug("doSelectList: " + cri);
	
	model.addAttribute("list", service.doSelectList(cri));
	
	int total = service.getTotalCount(cri);

	model.addAttribute("pageMaker", new PageDTO(cri, total));
    }
=======
	Logger log = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService service;

	/**
	 * μΌμ  μΆκ°
	 * 
	 * @param inVO
	 * @param rttr
	 * @author λ°μ λ―Ό
	 */
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String insert(ScheduleVO inVO, RedirectAttributes rttr) {
		//λ¬Έμμ΄ T μΉν
		inVO.setStartDt(inVO.getStartDt().replace("T", " "));
		inVO.setEndDt(inVO.getEndDt().replace("T", " "));
		log.debug("[Insert]ScheduleVO: " + inVO);

		service.doInsert(inVO);
		rttr.addFlashAttribute("result", inVO.getScheduleNo());

		return "redirect:/schedule/list.do"; // μμ± μλ£λλ©΄ μΌμ κ΄λ¦¬ νμ΄μ§λ‘ λ¦¬λ€μ΄λ νΈ
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public void insert() {

	}

	/**
	 * μΌμ  μ­μ 
	 * 
	 * @param scheduleNo
	 * @param rttr
	 * @author λ°μ λ―Ό
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String remove(@RequestParam("scheduleNo") int scheduleNo, RedirectAttributes rttr) {
		log.debug("[Delete]scheduleNo: " + scheduleNo);

		ScheduleVO inVO = new ScheduleVO();
		inVO.setScheduleNo(scheduleNo);

		if (service.doDelete(inVO) == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/schedule/list.do";
	}

	/**
	 * μΌμ  μμ 
	 * 
	 * @param inVO
	 * @param rttr
	 * @author λ°μ λ―Ό
	 */
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(ScheduleVO inVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.debug("[Update]ScheduleVO: " + inVO);

		if (service.doUpdate(inVO) == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/schedule/list.do";
	}

	/**
	 * μΌμ  μ ν
	 * 
	 * @param scheduleNo
	 * @param model
	 * @author λ°μ λ―Ό
	 */
	@RequestMapping(value = { "/get.do", "/update.do" }, method = RequestMethod.GET)
	public void get(@RequestParam("scheduleNo") int scheduleNo, 
			@ModelAttribute("cri") Criteria cri, Model model) {
		log.debug("doSelectOne or doUpdate.....");

		ScheduleVO inVO = new ScheduleVO();
		inVO.setScheduleNo(scheduleNo);

		ScheduleVO outVO = service.doSelectOne(inVO);
				
		outVO.setStartDt(outVO.getStartDt().replace(" ", "T"));
		outVO.setEndDt(outVO.getEndDt().replace(" ", "T"));
		
		model.addAttribute("schedule", outVO);
	}

	/**
	 * μΌμ  κ²μ deptNo: 0(μ μ²΄κ²μ) or λΆμλ³κ²μ
	 * 
	 * @param deptNo
	 * @param model
	 * @author λ°μ λ―Ό
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public void list(Criteria cri, Model model) {
		log.debug("doSelectList: " + cri);

		model.addAttribute("list", service.doSelectList(cri));

		int total = service.getTotalCount(cri);

		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
>>>>>>> Stashed changes
}
