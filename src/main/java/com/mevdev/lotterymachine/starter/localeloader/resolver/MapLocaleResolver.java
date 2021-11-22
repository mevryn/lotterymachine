package com.mevdev.lotterymachine.starter.localeloader.resolver;

import com.mevdev.lotterymachine.starter.localeloader.resolver.meta.LocaleConstants;

import java.util.*;

public class MapLocaleResolver implements LocaleResolver {

    Map<String, String> languageToCountryMap = Map.of("pl", "PL");

    @Override
    public Locale resolveLocale() {
        String language = System.getenv(LocaleConstants.LOCALE_ENV_VARIABLE);
        return Objects.nonNull(language) ? new Locale(language, languageToCountryMap.get(language)) : Locale.getDefault();
    }
}
