package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStates;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @Servie @ Component
@RequestMapping
// 表示模块，http://localhost:8080/webdemo....
//若为@RequestMapping("/seckill"),则表示http://localhost:8080/webdemo/seckill....
public class SeckillController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	// 获取列表页
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list";// 在spring-web中配置有WEB-INF/jsp
	}
	
	@RequestMapping(value = "/{itemId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("itemId") Long itemId, Model model) {
		// 先判断itemId再判断seckill，减少数据库压力
		if (itemId == null) {
			return "redirect:/list";
		}
		Seckill seckill = seckillService.getById(itemId);
		if (seckill == null) {
			return "forward:/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	@RequestMapping(value = "/{itemId}/exposer", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("itemId")Long itemId) {
		SeckillResult<Exposer> result;
		System.out.println("exposer");
		try {
			Exposer exposer = seckillService.exportSeckillUrl(itemId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{itemId}/{md5}/execution", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("itemId") Long itemId,@PathVariable("md5") String md5,
			@CookieValue(value = "killPhone", required = false) Long userId) {
		System.out.println("1usrId:"+userId);
		if (userId == null) {
			userId = 12345678900L;//vv
			System.out.println("2usrId:"+userId);
			//return new SeckillResult<SeckillExecution>(false, "未注册"); //^^
		}
		try {
			SeckillExecution seckillExecution = seckillService
					.executionSeckill(itemId, userId, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.end));
		} catch (RepeatKillException e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.repeat));
		} catch (Exception e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.failed));
		}
	}
	
	@RequestMapping(value = "/{itemId}/execution", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<SeckillExecution> execute1(@PathVariable("itemId") Long itemId,
			@CookieValue(value = "killPhone", required = false) Long userId) {
		System.out.println("1usrId:"+userId);
		if (userId == null) {
			userId = 12345678900L; //vv
			System.out.println("2usrId:"+userId);
			//return new SeckillResult<SeckillExecution>(false, "未注册"); //^^
		}
		try {
			SeckillExecution seckillExecution = seckillService
					.executionSeckill(itemId, userId, userId.toString());
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.end));
		} catch (RepeatKillException e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.repeat));
		} catch (Exception e) {
			return new SeckillResult<SeckillExecution>(true,
					new SeckillExecution(itemId, SeckillStates.failed));
		}
	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public SeckillResult<Long> time() {
		System.out.println("time");
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
