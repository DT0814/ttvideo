package com.xawl.ttvideo.utils;

import org.springframework.data.domain.Page;

public class PageUtils<T> {
    public PageInfo<T> getPageInfo(Page<T> all) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setTotals(all.getTotalElements());
        pageInfo.setData(all.getContent());
        pageInfo.setCurrentNum(all.getNumber() + 1);
        pageInfo.setFirstPage(all.isFirst());
        pageInfo.setLastPage(all.isLast());
        pageInfo.setNextPage(pageInfo.getCurrentNum() + 1);
        pageInfo.setProPage(pageInfo.getCurrentNum() - 1);
        if (all.getTotalPages() >= 5) {
            if (pageInfo.getCurrentNum() <= 2) {
                pageInfo.setStart(1);
                pageInfo.setEnd(5);

            } else if (pageInfo.getCurrentNum() + 2 >= all.getTotalPages()) {
                pageInfo.setStart(all.getTotalPages() - 4);
                pageInfo.setEnd(all.getTotalPages());

            } else {
                pageInfo.setStart(pageInfo.getCurrentNum() - 2);
                pageInfo.setEnd(pageInfo.getCurrentNum() + 2);

            }


            int[] navigatepageNums = new int[5];
            for (int i = pageInfo.getStart(), j = 0; i <= pageInfo.getEnd(); i++, j++) {
                navigatepageNums[j] = i;
            }
            pageInfo.setNavigatepageNums(navigatepageNums);
        } else {
            pageInfo.setStart(1);
            pageInfo.setEnd(all.getTotalPages());
            int[] navigatepageNums = new int[all.getTotalPages()];
            for (int i = pageInfo.getStart(), j = 0; i <= pageInfo.getEnd(); i++, j++) {
                navigatepageNums[j] = i;
            }
            pageInfo.setNavigatepageNums(navigatepageNums);
        }
        return pageInfo;
    }
}
