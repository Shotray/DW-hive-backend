package cn.edu.tongji.dwhivebackend.Service.Impl;

import cn.edu.tongji.dwhivebackend.Service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO:此处写HiveServiceImpl类的描述
 *
 * @author shotray
 * @since 2021/12/17 16:47
 */

@Service
public class HiveServiceImpl implements HiveService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<String> getMovieNameByString(String movieString) {
        String sql = "select movie_title from movie where locate(\"" + movieString + "\",movie_title) = 1";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("movie_title"));
        return result;
    }

    @Override
    public List<String> getDirectorNameByString(String directorString) {
        String sql = "select name from person where locate(\""+ directorString +"\",person.name) = 1 and occupation = \"director\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("name"));
        return result;
    }

    @Override
    public List<String> getActorNameByStr(String actorString) {
        String sql = "select name from person where locate(\""+ actorString +"\",person.name) = 1 and occupation = \"actor\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("name"));
        return result;
    }
}

