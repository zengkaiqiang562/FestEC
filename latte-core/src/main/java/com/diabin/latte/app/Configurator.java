package com.diabin.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zengk on 2020/01/02
 *
 * 单例类
 * 存储、获取配置信息
 */
public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();//对于生命周期伴随整个Application的配置项，不能使用WeakHashMap存储

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        //执行此构造函数说明全局配置刚刚开始，且配置未完成，所有CONFIG_READY设为false
        //枚举类的name()返回枚举对象的字符串形式，即CONFIG_READY.name()返回"CONFIG_READY"
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //执行此方法表示配置完成，CONFIG_READY置为true
    public final void configure() {
        //因为字体图标是全局通用的，所有可以将对字体图标的初始化 放在进行全局配置时 完成
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //添加配置信息可通过一系列的 withXxx 方法实现

    //添加对APP_HOST的配置
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //初始化字体图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i=1; i<ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //添加字体图标配置
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //检查配置是否完成
    //在应用程序获取配置之前调用，保证在获取时已完成配置，否则抛出异常
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure()");
        }
    }

    //获取配置项
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
