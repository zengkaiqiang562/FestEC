package com.diabin.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by zengk on 2020/01/02
 */
public enum EcIcons implements Icon {
    icon_ali_pay('\ue60f'),
    icon_arrow_left('\ue63c'),
    icon_arrow_right('\ue63d'),
    icon_scan('\ue60b');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
