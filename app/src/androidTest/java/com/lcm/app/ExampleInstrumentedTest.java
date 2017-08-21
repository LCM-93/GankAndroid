package com.lcm.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.data.entity.ExcerptBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lcm.app", appContext.getPackageName());
    }


    @Test
    public void rmTest() throws Exception{
        Document document = Jsoup.connect("http://www.52rkl.cn/jiecao/12131093S2015.html").get();
        Element header = document.removeClass("header");
        Element element = document.removeClass("relates panel");
        document.toString();
    }

    @Test
    public void jpTest() throws Exception{
        List<ExcerptBean> excerptList = new ArrayList<>();
        Document document = Jsoup.connect("http://www.52rkl.cn/sansanyougeng/").get();
        Elements excerpts = document.getElementsByClass("excerpt");
        LogUtils.e("lcm",excerpts.toString());
        for (Element excerpt : excerpts) {
            ExcerptBean excerptBean = new ExcerptBean();

            Element a = excerpt.select("a").first();

            String url = a.attr("href");

            String img = a.select("img").first().attr("src");

            String title = excerpt.select("h2").first().select("a").text();

            String time = excerpt.select("time").first().text();

            String cat = excerpt.getElementsByClass("cat").first().text();

            String note = excerpt.select("p").first().select("a").first().text();

            excerptBean.setUrl(url);
            excerptBean.setImg(img);
            excerptBean.setTitle(title);
            excerptBean.setUpdateTime(time);
            excerptBean.setCat(cat);
            excerptBean.setNote(note);
            excerptList.add(excerptBean);
        }

        LogUtils.e("lcm", excerptList.toString());

    }


    @Test
    public void jouspTest() throws Exception {
        String content = " <p><img alt=\"\" src=\"https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg\" /></p>\n" +
                "<h3>iOS</h3>\n" +
                "<ul>\n" +
                "    <li><a href=\"https://github.com/LeonardoCardoso/SectionedSlider\" target=\"_blank\">仿 iOS 11 控制中心滑块效果。</a>&nbsp;(代码家)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/LeonardoCardoso/SectionedSlider\" target=\"_blank\"><img src=\"http://img.gank.io/b2e5c2a8-69d3-49a7-9d48-f218dfeefbdb\" title=\"仿 iOS 11 控制中心滑块效果。\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/ahmetws/UnsplashExplorer-CoreML\" target=\"_blank\">基于 Unsplash API 实现的 CoreML 框架 Demo</a>&nbsp;(代码家)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/ahmetws/UnsplashExplorer-CoreML\" target=\"_blank\"><img src=\"http://img.gank.io/d817d681-1774-4341-8a1e-1960641318b2\" title=\"基于 Unsplash API 实现的 CoreML 框架 Demo\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/farice/ARShooter\" target=\"_blank\">在 iOS 实现的 AR 增强现实 Demo</a>&nbsp;(S)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/farice/ARShooter\" target=\"_blank\"><img src=\"http://img.gank.io/0ca13673-fae8-4daa-8805-b0659e123894\" title=\"在 iOS 实现的 AR 增强现实 Demo\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "<h3>Android</h3>\n" +
                "<ul>\n" +
                "    <li><a href=\"https://pqpo.me/2017/06/08/c-11-learning-notes/\" target=\"_blank\">面向Java开发者的C++11学习指南</a>&nbsp;(Linmin Qiu)\n" +
                "        <ul> </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"http://www.importnew.com/18126.html\" target=\"_blank\">Java并发编程：volatile关键字解析（非常棒的文章）</a>&nbsp;(咕咚)\n" +
                "        <ul> </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"http://www.jianshu.com/p/4e1e96fe6d26\" target=\"_blank\">简单高效的实现Android App全局字体替换</a>&nbsp;(黎赵太郎)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"http://www.jianshu.com/p/4e1e96fe6d26\" target=\"_blank\"><img src=\"http://img.gank.io/116bb496-79cb-4e31-8823-4389bfa6b629\" title=\"简单高效的实现Android App全局字体替换\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/550609334/Twobbble\" target=\"_blank\">使用Kotlin开发的Dribbble客户端</a>&nbsp;(Jason)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/550609334/Twobbble\" target=\"_blank\"><img src=\"http://img.gank.io/325f5635-47e4-4262-8499-d2b4b63cfc69\" title=\"使用Kotlin开发的Dribbble客户端\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"http://url.cn/4ACGlEk\" target=\"_blank\">Android之自定义View的死亡三部曲之Layout</a>&nbsp;(陈宇明)\n" +
                "        <ul> </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/tumblr/Graywater\" target=\"_blank\">Tumblr 出品：解耦 RecyclerView，提高滑动性能。</a>&nbsp;(代码家)\n" +
                "        <ul> </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/Arasthel/SpannedGridLayoutManager\" target=\"_blank\">支持不同形态的 Span Grid Layout。</a>&nbsp;(代码家)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/Arasthel/SpannedGridLayoutManager\" target=\"_blank\"><img src=\"http://img.gank.io/425b030c-e730-4d70-8be0-361cdeae4b68\" title=\"支持不同形态的 Span Grid Layout。\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "<h3>前端</h3>\n" +
                "<ul>\n" +
                "    <li><a href=\"http://www.jianshu.com/p/3e4d06b9e0fd\" target=\"_blank\">杂篇 - Vue豆瓣系列文章</a>&nbsp;(yazhe wang)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"http://www.jianshu.com/p/3e4d06b9e0fd\" target=\"_blank\"><img src=\"http://img.gank.io/c7b622b5-796d-4922-84ce-651e1c964db5\" title=\"杂篇 - Vue豆瓣系列文章\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li><a href=\"https://github.com/Werb/Pixel-Web\" target=\"_blank\">使用 Vue 撸一个微博客户端！</a>&nbsp;(Werb)\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a href=\"https://github.com/Werb/Pixel-Web\" target=\"_blank\"><img src=\"http://img.gank.io/c32fac8c-2602-4ef6-b860-0205ca16eb3c\" title=\"使用 Vue 撸一个微博客户端！\" /></a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "<h3>休息视频</h3>\n" +
                "<ul>\n" +
                "    <li><a href=\"http://www.bilibili.com/video/av10905778/\" target=\"_blank\">【问舰】10分钟解读《我，机器人》，机器人狂虐人类，人类要如何反败为胜</a>&nbsp;(LHF)\n" +
                "        <ul> </ul>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "<p>感谢所有默默付出的编辑们，愿大家有美好一天。</p>\n";

        List<DailyContentBean> list = new ArrayList<>();
        Document document = Jsoup.parse(content);


        Elements h3 = document.getElementsByTag("h3");
        String attr = document.select("p").select("img").attr("src");
        DailyContentBean dailyContentBean1 = new DailyContentBean();
        dailyContentBean1.setSrc(attr);
        dailyContentBean1.setType("福利");
        list.add(dailyContentBean1);

        for (int i = 0; i < h3.size(); i++) {
            Element element = h3.get(i);
            Element ulElement = element.nextElementSibling();
            Elements li = ulElement.children();
            for (int j = 0; j < li.size(); j++) {
                DailyContentBean dailyContentBean = new DailyContentBean();
                dailyContentBean.setType(element.text());

                Element liElement = li.get(j);
                dailyContentBean.setUrl(liElement.select("a").attr("href"));
                dailyContentBean.setText(liElement.select("a").text());

                Elements ul = liElement.select("ul");
                Elements children = ul.get(0).children();
                if (children.size() != 0) {
                    Element element1 = children.get(0);
                    dailyContentBean.setSrc(element1.select("img").attr("src"));
                }

                System.out.println(dailyContentBean.toString());
            }
        }
    }
}
