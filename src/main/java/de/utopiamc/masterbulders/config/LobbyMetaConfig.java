package de.utopiamc.masterbulders.config;

import de.utopiamc.framework.api.config.meta.UtopiaMetaConfig;
import de.utopiamc.framework.api.config.meta.UtopiaMetaConfiguration;
import de.utopiamc.framework.api.stereotype.Configuration;

@Configuration
public class LobbyMetaConfig implements UtopiaMetaConfiguration {

    public static String prefix = "§8[§aMasterBuilders§8] §7";

    public void configure(UtopiaMetaConfig config) {
        config.prefix("§8[§aMasterBuilders§8] §7");
        config.primaryColor('a');
        config.secondaryColor('b');
    }
}
