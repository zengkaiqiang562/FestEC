package com.diabin.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zengk on 2020/01/02
 */
public final class Latte { //使用 final 修饰， 不允许别人 修改 或 继承

    public static Configurator init(Context context) { //建议传入ApplicationContext
        //调用init方法，实现Configurator中配置项的设置
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
