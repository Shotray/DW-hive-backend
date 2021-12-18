package cn.edu.tongji.dwhivebackend.Controller;

import cn.edu.tongji.dwhivebackend.Service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * TODO:此处写Controller类的描述
 *
 * @author shotray
 * @since 2021/12/16 0:06
 */

@RestController
@RequestMapping("/hive")
public class HiveController {

    @Resource
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource
    private HiveService hiveService;

    @RequestMapping("/list")
    public List<Map<String, Object>> list() {
        String sql = "select title from movie";
        long startTime = System.currentTimeMillis();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return list;
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMovieNameListByString(
            @RequestParam(value = "movieName") String movieName
    ) {
        return new ResponseEntity<>(hiveService.getMovieNameByString(movieName), HttpStatus.OK);
    }

    @RequestMapping(value = "/director", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getDirectorNameListByString(
            @RequestParam(value = "directorName") String directorName
    ) {
        return new ResponseEntity<>(hiveService.getDirectorNameByString(directorName), HttpStatus.OK);
    }

    @RequestMapping(value = "/actor",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getActorNameListByString(
            @RequestParam(value = "actorName")String actorName
    ){
        return new ResponseEntity<>(hiveService.getActorNameByStr(actorName), HttpStatus.OK);
    }

}
