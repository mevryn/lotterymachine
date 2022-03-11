package com.mevdev.lotterymachine.starter.localeloader.resolver;

import com.mevdev.lotterymachine.starter.localeloader.resolver.meta.LocaleConstants;

import java.util.*;

public class MapLocaleResolver implements LocaleResolver {

    private final Map<String, String> LANGUAGE_TO_COUNTRY = Map.of("pl", "PL");

    @Override
    public Locale resolveLocale() {
        String language = System.getenv(LocaleConstants.LOCALE_ENV_VARIABLE);
        return Objects.nonNull(language) ? new Locale(language, LANGUAGE_TO_COUNTRY.get(language)) : Locale.getDefault();
    }
}
