Jsoup:
	Jsoup 是一款 Java 的 HTML 解析器，可直接解析某个 URL 地址、HTML 文本内容。
	提供了一套非常省力的API，可通过DOM，CSS 以及类似于 JQuery 的操作方法来取出和操作数据

	特点:
	·HTML\XML\自定义DOM格式文本解析
    ·可操作 HTML 元素、属性、文本
    ·适用于采集解析网站 HTML
    ·DOM 解析功能强大

jsoup-1.8.1.jar

/**
 
 */

Jsoup 的基本结点解析用法

    ·分析 HTML 或 Dom 文本的结点标签结构
    ·寻找解析的那个内容部分所在的结点标签
    ·调用 Jsoup API 解析结点内容

代码演示:
	使用 Jsoup 载入 HTML 数据
	使用 Jsoup 载入 解析并提取 HTML 元素
	使用 Jsoup 修改数据
	使用 Jsoup HTML 文档清理

	可以使用 Jsoup 解析 HTML、 EPUB 、自定义格式文本

/**
 
 */


package com.example.fangyi.jsoup.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fangyi.jsoup.R;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Jsoup extends AppCompatActivity {

    private String html = "<html><head><title>Jsoup用法</title></head>"
            + "<body><p><a href='http://baidu.com'>这里是 jsoup 项目的相关文章</a></p></body></html>";
    private String url = "http://baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        /**
         * html文本，url，本地html
         */

        //html文本
        Document doc = org.jsoup.Jsoup.parse(html);
        doc.title();//获取html文本中的title标签下的内容

        Elements eles = doc.getElementsByTag("a");//提取元素的集合:a标签的列表储存一个集合
        for (Element link:eles) {
            String linkHref = link.attr("href");//可以取出a标签的href的值
            String text = link.text();//取出A标签下的文本内容
        }


        Elements elements = doc.select("a[href]"); //元素的检索功能:html中所有带href的a标签检索出来
        Elements elements1 = doc.select("img[src$=.png]");//可以对img 带图像链接的png标签进行检索
        Element element2 = doc.select("div.className").first();//由这个元素名的div.className的div标签

        //对元素的修改属性
        doc.select("div.className").attr("key","value");//添加属性
        doc.select("div.className").addClass("myclass");//class="myclass"
        doc.select("img").removeAttr("onclick");//移除图片点击

        //文档清理功能：不安全的html属性，进行安全过滤，
        String htmls = "";// 不安全的
        String safe = org.jsoup.Jsoup.clean(htmls, Whitelist.basic());// 安全的
        
        
        //get方式
        try {
            Document doc2 = org.jsoup.Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //post方式
        try {
            Document doc3 = org.jsoup.Jsoup.connect(url).data("key","value").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        //本地html
//        File input = new File(Environment.getExternalStorageDirectory() + "/index.html");
//        Document doc4 = org.jsoup.Jsoup.parse("本地路径", "UTF-8", "htt[:baidu.com");
//        //第三个参数作用 ../baidu.png -> http://baidu.com/baidu.png
    }
}



/**
 
 */


使用 Jsoup 实现 Html 解析和 Epub 解析

实现效果:
1.使用 Jsoup 实现对某网站新闻列表的采集解析
2.使用 Jsoup 对 Epub3 的目录结点解析

/**
 
 */




package com.example.fangyi.jsoup.jsoupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.fangyi.jsoup.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class JsoupDemo extends AppCompatActivity {

    private String url = "http://mobile.csdn.net/";

    /**
     <div class="unit">
     <h1><a href="http://www.csdn.net/article/2016-03-25/2826613" target="_blank" >王小川、蒋涛、张南雄……，50+新硬件创新领袖齐聚WARE 2016</a></h1>
     <h4>发表于<span class="ago">2016-03-31 17:27</span>|<span class="view_time">1022次阅读</span>|<span class="num_recom">2条评论</span></h4>
     <dl>
     <dt>
     <a href="http://www.csdn.net/article/2016-03-25/2826613" target="_blank"><img src="http://img.ptcms.csdn.net/article/201604/01/56fde9ab225da_thumb.jpg" alt="" /></a>
     </dt>
     <dd>本次大会以“Fusion . Future . Friends”为主题，期待着通过社区的力量，集结创新意见领袖的洞见，融合创变者的声音，预见可以洞悉的未来，会友、交友、聚友，共创新硬件生态繁荣！</dd>
     </dl>
     <div class="tag"><a href="http://www.csdn.net/tag/%E7%A1%AC%E4%BB%B6%E5%88%9B%E6%96%B0/news" target="_blank">硬件创新</a><a href="http://www.csdn.net/tag/ware%202016/news" target="_blank">WARE 2016</a></div>
     </div>


     <div class="unit">
     <h1><a href="http://www.csdn.net/article/2016-01-30/2826593" target="_blank" >开发者福利：开放、聚合的百度全站技术服务体系</a></h1>
     <h4>发表于<span class="ago">2016-01-30 16:39</span>|<span class="view_time">2113次阅读</span>|<span class="num_recom">2条评论</span></h4>
     <dl>
     <dt>
     <a href="http://www.csdn.net/article/2016-01-30/2826593" target="_blank"><img src="http://img.ptcms.csdn.net/article/201601/30/56ac970f8fd0f_thumb.jpg" alt="" /></a>
     </dt>
     <dd>百度开发者中心汇聚了百度所有对外开放的技术、平台和服务，并举办各种类型的创新大赛、活动，为开发者谋福利。2016年，百度开发者中心还将为开发者提供创业孵化、投资等资源，助力开发者创业成功。</dd>
     </dl>

     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup_demo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                parseHtml();
                parseEpub();
            }
        }).start();
    }

    private  void parseHtml() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.unit");
            for (Element ele:elements) {
                String title = ele.getElementsByTag("h1").first().text();
                String href = ele.getElementsByTag("h1").first().getElementsByTag("a").first().attr("href");
                Log.i("info", title + ":" + href);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //电子书
    private void parseEpub() {
        try {
            InputStream is = getAssets().open("fb.ncx");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String epubText = new String(buffer, "utf-8");
            Document doc = Jsoup.parse(epubText);
            String docTitle = doc.getElementsByTag("docTitle").first().text();
            Log.i("info", docTitle);
            Elements elements = doc.getElementsByTag("navPoint");
            for (Element ele : elements) {
                String title = ele.text();
                String href = ele.getElementsByTag("content").first()
                        .attr("src");
                Log.i("info", title + ":" + href);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
