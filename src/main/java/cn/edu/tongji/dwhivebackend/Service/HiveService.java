package cn.edu.tongji.dwhivebackend.Service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:此处写HiveService类的描述
 *
 * @author shotray
 * @since 2021/12/17 16:41
 */

@Service
public interface HiveService {
    List<String> getMovieNameByString(String movieString);

    List<String> getDirectorNameByString(String directorString);

    List<String> getActorNameByStr(String actorString);

    List<String> getCategoryNameByStr(String category);

    List<String> getAllDirectorsByMovieAsin(String movieAsin);

    List<String> getAllMainActorsByMovieAsin(String movieAsin);

    List<String> getAllActorsByMovieAsin(String movieAsin);

}

