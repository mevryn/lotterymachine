package com.mevdev.lotterymachine.starter.localeloader;


import com.mevdev.lotterymachine.starter.localeloader.resolver.LocaleResolver;
import com.mevdev.lotterymachine.starter.localeloader.resolver.MapLocaleResolver;

import java.util.Locale;

public class LocaleLoader {

    LocaleResolver localeResolver = new MapLocaleResolver();

    public Locale getProjectLocale() {
        return localeResolver.resolveLocale();
    }
}
