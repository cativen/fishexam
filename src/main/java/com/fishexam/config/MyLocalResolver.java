package com.fishexam.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {
    /**
     * Resolve the current locale via the given request.
     * Can return a default locale as fallback in any case.
     *
     * @param request the request to resolve the locale for
     * @return the current locale (never {@code null})
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中语言的参数
        String l = request.getParameter("l");
        //获取默认语言的参数
        Locale aDefault = Locale.getDefault();
        //判断请求语言是否为空
        if (!StringUtils.isEmpty(l)) {
            //分割参数，拿到语言参数，将语言参数赋值给默认语言
            String[] s = l.split("_");
            aDefault = new Locale(s[0],s[1]);
        }
        return aDefault;
    }

    /**
     * Set the current locale to the given one.
     *
     * @param request  the request to be used for locale modification
     * @param response the response to be used for locale modification
     * @param locale   the new locale, or {@code null} to clear the locale
     * @throws UnsupportedOperationException if the LocaleResolver
     *                                       implementation does not support dynamic changing of the locale
     */
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
