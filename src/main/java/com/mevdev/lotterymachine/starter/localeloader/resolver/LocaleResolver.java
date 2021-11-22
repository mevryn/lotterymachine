package com.mevdev.lotterymachine.starter.localeloader.resolver;

import java.util.Locale;

public interface LocaleResolver {

    /**
     * It resolves locales for a whole project.
     *
     * @return Resolved Locale
     */
    Locale resolveLocale();
}
