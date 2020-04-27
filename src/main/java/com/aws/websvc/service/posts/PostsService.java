package com.aws.websvc.service.posts;

import com.aws.websvc.domain.posts.Posts;
import com.aws.websvc.domain.posts.PostsRepository;
import com.aws.websvc.web.dto.PostsResponseDto;
import com.aws.websvc.web.dto.PostsSaveRequestDto;
import com.aws.websvc.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
