package com.wenba.common.base;

import lombok.*;

/**
 * @ Author
 * @ Date       ：Created in 5:43 PM 2018/10/24
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：基类
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
    Integer code;
    String msg;
}
