package com.hasaker.face.service.post;

import com.hasaker.common.vo.PageInfo;
import com.hasaker.face.vo.request.RequestAggregationVo;
import com.hasaker.face.vo.request.RequestMessageReadVo;
import com.hasaker.face.vo.request.RequestPostSearchVo;
import com.hasaker.face.vo.response.ResponseHotTopicsAggVo;
import com.hasaker.face.vo.response.ResponseHotUsersAggVo;
import com.hasaker.face.vo.response.ResponseHotWordsAggVo;
import com.hasaker.face.vo.response.ResponsePostVo;

import java.util.List;

/**
 * @package com.hasaker.face.service
 * @author 余天堂
 * @create 2020/3/4 11:24
 * @description PostService
 */
public interface PostService {

    PageInfo<ResponsePostVo> page(RequestPostSearchVo pageVo);

    ResponsePostVo getById(Long postId);

    List<ResponseHotWordsAggVo> getHotWords(RequestAggregationVo aggregationVo);

    List<ResponseHotTopicsAggVo> getHotTopics(RequestAggregationVo aggregationVo);

    List<ResponseHotUsersAggVo> getHotUsers(RequestAggregationVo aggregationVo);

    List<String> getUserWords(Long userId);

    void readMessage(RequestMessageReadVo readVo);

    void indexAll();
}
