package com.spring.cms.service;

import com.spring.cms.domain.*;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.enums.MenuLinkTarget;
import com.spring.cms.enums.MenuType;
import com.spring.cms.exception.BoardManagerException;
import com.spring.cms.exception.ContentsException;
import com.spring.cms.exception.MenuException;
import com.spring.cms.repository.BoardManagerRepository;
import com.spring.cms.repository.ContentsRepository;
import com.spring.cms.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spring.cms.exception.BoardManagerException.BoardManagerExceptionType.BOARD_MANAGER_ID_IS_NULL;
import static com.spring.cms.exception.BoardManagerException.BoardManagerExceptionType.BOARD_MANAGER_NOT_FOUND;
import static com.spring.cms.exception.ContentsException.ContentsExceptionType.CONTENTS_ID_IS_NULL;
import static com.spring.cms.exception.ContentsException.ContentsExceptionType.CONTENTS_NOT_FOUND;
import static com.spring.cms.exception.MenuException.MenuExceptionType.NOT_FOUND_PARENT_MENU;
import static com.spring.cms.exception.MenuException.MenuExceptionType.NOT_FOUND_TOP_MENU;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final BoardManagerRepository boardManagerRepository;
    private final ContentsRepository contentsRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public MenuDto.QueryResponse createMenu(MenuDto.Create create) {
        Menu parentMenu = null;
        Menu topMenu = null;

        Long parentId = create.getParentId();
        if (parentId != null && topMenu != null) {
            parentMenu = menuRepository.findById(parentId)
                    .orElseThrow(() -> new MenuException(NOT_FOUND_PARENT_MENU));

            topMenu = menuRepository.findById(create.getTopId())
                    .orElseThrow(() -> new MenuException(NOT_FOUND_TOP_MENU));
        }
        Menu menu = Menu.createMenu(create, parentMenu, topMenu);

        MenuType menuType = MenuType.valueOf(create.getMenuType());
        if (menuType.equals(MenuType.BOARD)) {
            Long boardManagerId = create.getBoardManagerId();
            if (boardManagerId == null) {
                throw new BoardManagerException(BOARD_MANAGER_ID_IS_NULL);
            }

            BoardManager boardManager = boardManagerRepository.findById(boardManagerId)
                    .orElseThrow(() -> new BoardManagerException(BOARD_MANAGER_NOT_FOUND));

            MenuBoardManager.createMenuBoardManager(menu, boardManager);
        } else if (menuType.equals(MenuType.LINK)) {
            MenuLink.createMenuLink(create.getLink(), MenuLinkTarget.valueOf(create.getLinkTarget()), menu);
        } else if (menuType.equals(MenuType.CONTENTS)) {
            Long contentsId = create.getContentsId();
            if (contentsId == null) {
                throw new ContentsException(CONTENTS_ID_IS_NULL);
            }

            Contents contents = contentsRepository.findById(contentsId)
                    .orElseThrow(() -> new ContentsException(CONTENTS_NOT_FOUND));

            MenuContents.createMenuContents(menu, contents);
        }
        menuRepository.save(menu);

        return menuRepository.response(menu.getId());
    }

    public List<MenuDto.AllMenusQueryResponse> getAllMenus() {
        return menuRepository.findAllMenus();
    }

}
