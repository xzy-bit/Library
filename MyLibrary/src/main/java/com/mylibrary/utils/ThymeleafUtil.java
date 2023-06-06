package com.mylibrary.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {
    private static final TemplateEngine engin;
    static {
        engin = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        engin.setTemplateResolver(r);
    }
    public static void process(String template, IContext iContext, Writer writer){
        engin.process(template, iContext, writer);
    }
}
