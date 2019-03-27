package com.funny.geek.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is ZhihuUrl
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface GankUrl {
}
