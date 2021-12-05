package cn.jinronga.bank.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/bank1/de-duplication")
public class DeDuplicationController {
    @Value("${spring.registry.type}")
    private String name;



    @GetMapping("info")
    public String get()
    {
        return name;
    }
}
