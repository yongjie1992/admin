/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.guoyiguan.admin.util;

import com.guoyiguan.admin.bean.WecharMessageTextEntity;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

import org.springframework.util.StringUtils;

/**
 * xml与bean的互转
 * @author zhangyj
 * @version V1.0
 * @since 2019-09-02
 */
public class XstreamUtil {
    /**
     * xsttream扩展，bean转xml自动加上![CDATA[]]
     * @return
     */
    public static XStream getXstream() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                  //对所有xml节点都增加CDATA标记
                  boolean cdata = true;

                  @Override
                  public void startNode(String name, Class clazz) {
                      super.startNode(name, clazz);
                  }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
    }

    /**
     * xml转bean泛型方法
     * @param resultXml
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T xmlToBean(String resultXml, Class clazz) {
        XStream stream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(stream);
        stream.allowTypes(new Class[]{clazz});
        stream.processAnnotations(new Class[]{clazz});
        stream.setMode(XStream.NO_REFERENCES);
        stream.alias("xml", clazz);
        return (T) stream.fromXML(resultXml);
    }

    public static void main(String[] args) {
        WecharMessageTextEntity wecharMessageTextEntity = new WecharMessageTextEntity();
        wecharMessageTextEntity.setFromUserName("zhangyj");
        wecharMessageTextEntity.setToUserName("zouwenjuan");
        wecharMessageTextEntity.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000L));
        wecharMessageTextEntity.setMsgType("1");
        wecharMessageTextEntity.setContent("I love you");

        XStream xStream = getXstream();
        xStream.alias("xml", wecharMessageTextEntity.getClass());
        xStream.processAnnotations(WecharMessageTextEntity.class);
        String xml = xStream.toXML(wecharMessageTextEntity);
        if (!StringUtils.isEmpty(xml)) {
            System.out.println(xml);
        } else {
            System.out.println("bean转xml失败");
        }

        WecharMessageTextEntity newEntity = xmlToBean(xml, WecharMessageTextEntity.class);
        System.out.println(newEntity);
    }
}
