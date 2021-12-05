package cn.jinronga.rabbitmq01.contorller;

import cn.jinronga.rabbitmq01.bean.vo.OrderSaveVo;
import cn.jinronga.rabbitmq01.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName OrderController
 * @Author 郭金荣
 * @Date 2021/5/7 21:07
 * @Description OrderController
 * @Version 1.0
 */
@Controller
public class OrderController {
    @Resource
    private OrderService orderService;


    @RequestMapping("/fanoutOrder")
    @ResponseBody
    public String fanoutOrder(@RequestBody OrderSaveVo orderSaveVo) {
        orderService.fanoutOrder(orderSaveVo.getUserid(), orderSaveVo.getProductid(), orderSaveVo.getNum());
        return "fanoutOrder!";
    }

    @RequestMapping("/directOrder")
    @ResponseBody
    public String directOrder(@RequestBody OrderSaveVo orderSaveVo) {
        orderService.fanoutOrder(orderSaveVo.getUserid(), orderSaveVo.getProductid(), orderSaveVo.getNum());
        return "directOrder!";
    }

}
