package com.longyah.blog.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author RenQiang
 * @date 2019/6/12
 */
@Data
@Builder
public class UserVo {
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
}
