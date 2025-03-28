package cn.lcgui.springbootlog.controller;

import cn.lcgui.springbootlog.mapper.LogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController(value = "log")
@RequestMapping("/log")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Resource
    private LogMapper logMapper;

    @GetMapping("/test")
    public void test() {
        // 日志级别：trace < debug < info < warn < error
        // 系统默认是info级别，所以trace和debug级别的日志不会输出，通过修改配置文件来改变日志级别。
        log.trace("这是trace级别的日志。");
        log.debug("这是debug级别的日志。");
        log.info("这是info级别的日志。");
        log.warn("这是warn级别的日志。");
        log.error("这是error级别的日志。");
    }

    @GetMapping("/testSQL")
    public String testSQL() {
        List<Map<String, Object>> maps = logMapper.selectAll();
        return maps.toString();
    }
}
