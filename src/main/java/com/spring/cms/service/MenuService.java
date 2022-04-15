package com.spring.cms.service;

import com.spring.cms.domain.BoardManager;
import com.spring.cms.domain.Contents;
import com.spring.cms.domain.Menu;
import com.spring.cms.domain.MenuLink;
import com.spring.cms.dto.menu.MenuDto;
import com.spring.cms.dto.menu.MenuQueryDto;
import com.spring.cms.enums.MenuLinkTarget;
import com.spring.cms.enums.MenuType;
import com.spring.cms.exception.BoardManagerException;
import com.spring.cms.exception.ContentsException;
import com.spring.cms.exception.MemberException;
import com.spring.cms.exception.MenuException;
import com.spring.cms.repository.BoardManagerRepository;
import com.spring.cms.repository.ContentsRepository;
import com.spring.cms.repository.menu.MenuLinkRepository;
import com.spring.cms.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.spring.cms.exception.BoardManagerException.BoardManagerExceptionType.BOARD_MANAGER_ID_IS_NULL;
import static com.spring.cms.exception.BoardManagerException.BoardManagerExceptionType.BOARD_MANAGER_NOT_FOUND;
import static com.spring.cms.exception.ContentsException.ContentsExceptionType.CONTENTS_ID_IS_NULL;
import static com.spring.cms.exception.ContentsException.ContentsExceptionType.CONTENTS_NOT_FOUND;
import static com.spring.cms.exception.MenuException.MenuExceptionType.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final BoardManagerRepository boardManagerRepository;
    private final ContentsRepository contentsRepository;
    private final MenuLinkRepository menuLinkRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public MenuDto.CreateResponse createMenus(MenuDto.Create create) {
        Menu parentMenu = null;
        Menu topMenu = null;
        BoardManager boardManager = null;
        MenuLink menuLink = null;
        Contents contents = null;

        Long parentId = create.getParentId();
        if (parentId != null && topMenu != null) {
            parentMenu = menuRepository.findById(parentId)
                    .orElseThrow(() -> new MenuException(NOT_FOUND_PARENT_MENU));

            topMenu = menuRepository.findById(create.getTopId())
                    .orElseThrow(() -> new MenuException(NOT_FOUND_TOP_MENU));
        }

        MenuType menuType = MenuType.valueOf(create.getMenuType());
        if (menuType.equals(MenuType.BOARD)) {
            Long boardManagerId = create.getBoardManagerId();
            if (boardManagerId == null) {
                throw new BoardManagerException(BOARD_MANAGER_ID_IS_NULL);
            }
            boardManager = boardManagerRepository.findById(boardManagerId)
                    .orElseThrow(() -> new BoardManagerException(BOARD_MANAGER_NOT_FOUND));
        } else if (menuType.equals(MenuType.LINK)) {
            menuLink = MenuLink.createMenuLink(create.getLink(), MenuLinkTarget.valueOf(create.getLinkTarget()));
            menuLinkRepository.save(menuLink);
        } else if (menuType.equals(MenuType.CONTENTS)) {
            Long contentsId = create.getContentsId();
            if (contentsId == null) {
                throw new ContentsException(CONTENTS_ID_IS_NULL);
            }
            contents = contentsRepository.findById(contentsId)
                    .orElseThrow(() -> new ContentsException(CONTENTS_NOT_FOUND));
        }

        Menu menu = Menu.createMenu(create, parentMenu, topMenu, boardManager, menuLink, contents);
        menuRepository.save(menu);

        //return menuRepository.findCreatedMenu(menu.getId());
        return modelMapper.map(menu, MenuDto.CreateResponse.class);
    }

    public List<MenuDto.AllMenusResponse> getAllMenus() {
        return menuRepository.findByParentOrderByOrd(null)
                .orElseThrow(() -> new MenuException(NOT_FOUND_MENU))
                .stream()
                .map(MenuDto.AllMenusResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * ToOne 관계들을 먼저 조회하고, 여기서 얻은 식별자 menuId로 ToMany 관계인 child를 한꺼번에 조회
     * MAP을 사용해서 매칭 성능 향상(O(1))
     */
    public List<MenuQueryDto.AllMenusResponseQuery> getAllMenusOpti() {
        List<MenuQueryDto.AllMenusResponseQuery> menus = menuRepository.findMenus(true, null);
        setChildMenusByRecursive(menus);
        return menus;
    }

    public void setChildMenusByRecursive(List<MenuQueryDto.AllMenusResponseQuery> menus) {
        if (menus == null || menus.isEmpty()) {
            return;
        }

        List<MenuQueryDto.AllMenusResponseQuery> childAllMenus = new ArrayList<>();

        Map<Long, List<MenuQueryDto.AllMenusResponseQuery>> menusChildMap = findMenusChildMap(toMenusIds(menus));
        menus.forEach(m -> {
            List<MenuQueryDto.AllMenusResponseQuery> childMenus = menusChildMap.get(m.getId());
            m.setChildMenus(childMenus);
            if (childMenus != null) {
                childAllMenus.addAll(childMenus);
            }
        });
        setChildMenusByRecursive(childAllMenus);
    }

    public Map<Long, List<MenuQueryDto.AllMenusResponseQuery>> findMenusChildMap(List<Long> menuIds) {
        return menuRepository.findMenus( false, menuIds).stream()
                .collect(Collectors.groupingBy(allMenusResponseQuery -> allMenusResponseQuery.getParentId()));
    }

    private List<Long> toMenusIds(List<MenuQueryDto.AllMenusResponseQuery> menus) {
        return menus.stream()
                .map(m -> m.getId())
                .collect(Collectors.toList());
    }

    public MenuDto.MenuDetailResponse getMenuDetail(Long menuId) {
        Menu findMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuException(NOT_FOUND_MENU));
        return modelMapper.map(findMenu, MenuDto.MenuDetailResponse.class);
    }

    @Transactional
    public MenuDto.UpdateResponse updateMenu(Long menuId, MenuDto.Update update) {
        BoardManager boardManager = null;
        MenuLink menuLink = null;
        Contents contents = null;

        Menu findMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuException(NOT_FOUND_MENU));

        MenuType menuType = MenuType.valueOf(update.getMenuType());
        if (!findMenu.getMenuType().equals(menuType)) {
            if (menuType.equals(MenuType.BOARD)) {
                Long boardManagerId = update.getBoardManagerId();
                if (boardManagerId == null) {
                    throw new BoardManagerException(BOARD_MANAGER_ID_IS_NULL);
                }
                boardManager = boardManagerRepository.findById(boardManagerId)
                        .orElseThrow(() -> new BoardManagerException(BOARD_MANAGER_NOT_FOUND));
            } else if (menuType.equals(MenuType.LINK)) {
                menuLink = MenuLink.createMenuLink(update.getLink(), MenuLinkTarget.valueOf(update.getLinkTarget()));
                menuLinkRepository.save(menuLink);
            } else if (menuType.equals(MenuType.CONTENTS)) {
                Long contentsId = update.getContentsId();
                if (contentsId == null) {
                    throw new ContentsException(CONTENTS_ID_IS_NULL);
                }
                contents = contentsRepository.findById(contentsId)
                        .orElseThrow(() -> new ContentsException(CONTENTS_NOT_FOUND));
            }
        }
        findMenu.updateMenu(update, boardManager, menuLink, contents);

        return modelMapper.map(findMenu, MenuDto.UpdateResponse.class);
    }

    @Transactional
    public void deleteMenu(Long menuId) {
        Menu findMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuException(NOT_FOUND_MENU));

        Integer childCount = menuRepository.countByParent(findMenu);
        if (childCount > 0) {
            throw new MenuException(CHILD_MENU_EXISTS_CANNOT_DELETE);
        }

        //TODO 삭제 처리
        menuRepository.delete(findMenu);
    }
}
