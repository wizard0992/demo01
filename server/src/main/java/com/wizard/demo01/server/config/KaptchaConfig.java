package com.wizard.demo01.server.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 *  Google Kaptcha验证码的自定义配置
 * @author wizard_0992
 * @date 2019/12/16 9:55
 */
public class KaptchaConfig {

   /* //  是否有边框  默认为true  我们可以自己设置yes，no
    kaptcha.border
    //边框颜色   默认为Color.BLACK
    kaptcha.border.color
    //  边框粗细度  默认为1
    kaptcha.border.thickness
    //   验证码生成器  默认为DefaultKaptcha
    kaptcha.producer.impl
    //   验证码文本生成器  默认为DefaultTextCreator
    kaptcha.textproducer.impl
    //   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
    kaptcha.textproducer.char.string
    //   验证码文本字符长度  默认为5
    kaptcha.textproducer.char.length
    //    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)  
    kaptcha.textproducer.font.names
    //   验证码文本字符大小  默认为40  
    kaptcha.textproducer.font.size
    //  验证码文本字符颜色  默认为Color.BLACK
    kaptcha.textproducer.font.color
    //  验证码文本字符间距  默认为2
    kaptcha.textproducer.char.space
    //    验证码噪点生成对象  默认为DefaultNoise
    kaptcha.noise.impl
    //   验证码噪点颜色   默认为Color.BLACK
    kaptcha.noise.color
    //   验证码样式引擎  默认为WaterRipple  
    kaptcha.obscurificator.impl
    //   验证码文本字符渲染   默认为DefaultWordRenderer
    kaptcha.word.impl
    //   验证码背景生成器   默认为DefaultBackground
    kaptcha.background.impl
    //   验证码背景颜色渐进   默认为Color.LIGHT_GRAY  
    kaptcha.background.clear.from
    //   验证码背景颜色渐进   默认为Color.WHITE
    kaptcha.background.clear.to
    //   验证码图片宽度  默认为200  
    kaptcha.image.width
    //  验证码图片高度  默认为50
    kaptcha.image.height*/

    /*@Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();

        //是否有图片边框
        properties.setProperty("kaptcha.border", "no");
        //边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        //图片宽
        properties.setProperty("kaptcha.image.width", "135");
        //图片高
        properties.setProperty("kaptcha.image.height", "30");
        //字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //session key
        properties.setProperty("kaptcha.session.key", "kaptchaCode");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        //使用那些字符生成验证码
        properties.setProperty("kaptcha.textproducer.char.string", "ACDEFHKPRSTWX345679");
        //使用哪些字体
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        //干扰线颜色
        properties.setProperty("kaptcha.noise.color", "black");
        //图片样式阴影
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }*/


    /**
     *
     * @return
     */
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
