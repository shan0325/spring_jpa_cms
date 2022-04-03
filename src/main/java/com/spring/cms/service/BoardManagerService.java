package com.spring.cms.service;

import com.spring.cms.domain.BoardManager;
import com.spring.cms.dto.BoardManagerDto;
import com.spring.cms.enums.BoardType;
import com.spring.cms.repository.BoardManagerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardManagerService {

    private final BoardManagerRepository boardManagerRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BoardManagerDto.Response createBoardManager(BoardManagerDto.Create create) {
        BoardManager boardManager = BoardManager.createBoardManager(create.getName(), create.getDescription(), BoardType.valueOf(create.getBoardType())
                , create.getBoardUseYn(), create.getCommentUseYn());

        boardManagerRepository.save(boardManager);

        return modelMapper.map(boardManager, BoardManagerDto.Response.class);
    }
}
