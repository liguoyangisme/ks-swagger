package com.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author 袁伟倩
 * @Date 2018-6-717:21
 * @Version 1.0
 */
@Configuration
public class BaseCont {
    @Value("${easymock.flg}")
    private Boolean flg;

    public Boolean getFlg() {
        return flg;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }
}
