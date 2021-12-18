package cn.edu.tongji.dwhivebackend.Service.Impl;

import cn.edu.tongji.dwhivebackend.DTO.MovieInfoDto;
import cn.edu.tongji.dwhivebackend.Service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
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
        String sql = "select name from person where locate(\"" + directorString + "\",person.name) = 1 and occupation = \"director\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("name"));
        return result;
    }

    @Override
    public List<String> getActorNameByStr(String actorString) {
        String sql = "select name from person where locate(\"" + actorString + "\",person.name) = 1 and occupation = \"actor\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("name"));
        return result;
    }

    @Override
    public List<String> getCategoryNameByStr(String category) {
        String sql = "select style from movie where locate(\"" + category + "\",style) = 1";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> item : list) result.add((String) item.get("style"));
        return result;
    }

    @Override
    public List<String> getAllDirectorsByMovieAsin(String movieAsin) {
        String sql = "select directors from movie where asin = \"" + movieAsin + "\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        String str = (String) list.get(0).get("directors");
        if (str.equals("[]")) return null;
        str = str.substring(1, str.length() - 1);
        for (String temp : str.split(",")) {
            result.add(temp.substring(1,temp.length()-1));
        }
        return result;
    }

    @Override
    public List<String> getAllMainActorsByMovieAsin(String movieAsin) {
        String sql = "select main_actors from movie where asin = \"" + movieAsin + "\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        String str = (String) list.get(0).get("main_actors");
        if (str.equals("[]")) return null;
        str = str.substring(1, str.length() - 1);
        for (String temp : str.split(",")) {
            result.add(temp.substring(1,temp.length()-1));
        }
        return result;
    }

    @Override
    public List<String> getAllActorsByMovieAsin(String movieAsin) {
        String sql = "select actors from movie where asin = \"" + movieAsin + "\"";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        String str = (String) list.get(0).get("actors");
        if (str.equals("[]")) return null;
        str = str.substring(1, str.length() - 1);
        for (String temp : str.split(",")) {
            result.add(temp.substring(1,temp.length()-1));
        }
        return result;
    }

    @Override
    public HashMap<String, Object> getMaxCooperationTimeOfActors() {
//        String sql = "select * from actor_actor where movie_count = (select max(movie_count) from actor_actor)";
        String sql = "select * from actor_actor order by movie_count desc limit 1";
        HashMap<String,Object> result = new HashMap<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<String> actors = new ArrayList<>();
        actors.add((String) list.get(0).get("first_actor_name"));
        actors.add((String) list.get(0).get("second_actor_name"));
        result.put("actor",actors);
        result.put("number",list.get(0).get("movie_count"));
        return result;
    }

    @Override
    public HashMap<String, Object> getMaxCooperationTimeOfDirectors() {
        String sql = "select * from director_director where movie_count = (select max(movie_count) from director_director)";
//        String sql = "select * from director_director order by movie_count desc limit 1";
        HashMap<String,Object> result = new HashMap<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<String> directors = new ArrayList<>();
        directors.add((String) list.get(0).get("first_director_name"));
        directors.add((String) list.get(0).get("second_director_name"));
        result.put("director",directors);
        result.put("number",list.get(0).get("movie_count"));
        return result;
    }

    @Override
    public HashMap<String, Object> getMaxCooperationTimeOfActorsAndDirectors() {
//        String sql = "select * from actor_director where movie_count = (select max(movie_count) from actor_director)";
        String sql = "select * from actor_director order by movie_count desc limit 1";
        HashMap<String,Object> result = new HashMap<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        result.put("actor",list.get(0).get("actor_name"));
        result.put("director",list.get(0).get("director_name"));
        result.put("number",list.get(0).get("movie_count"));
        return result;
    }

    @Override
    public HashMap<String, Object> getMovieResultsByMutipleRules(MovieInfoDto movieInfoDto) {
        
        return null;
    }


}

