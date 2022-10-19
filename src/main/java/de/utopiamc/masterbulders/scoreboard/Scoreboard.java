package de.utopiamc.masterbulders.scoreboard;

import com.google.inject.Inject;
import de.utopiamc.framework.api.service.ScoreboardFactory;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.ui.scoreboard.DynamicScoreboardLineBuilder;
import de.utopiamc.framework.api.ui.scoreboard.DynamicVariable;
import de.utopiamc.framework.api.ui.scoreboard.Subscribeables;
import lombok.Getter;

@Getter
@Controller
public class Scoreboard {

    private final DynamicVariable<String> timerTitle = new DynamicVariable<>("Startet in:");
    private final DynamicVariable<String> timer = new DynamicVariable<>("1:00");

    private final DynamicVariable<String> subjectTitle = new DynamicVariable<>("Map:");
    private final DynamicVariable<String> subject = new DynamicVariable<>("Japan");

    @Inject
    public void scoreboard(ScoreboardFactory factory) {
        DynamicScoreboardLineBuilder builder = factory.createScoreboard()
                .title("$p§lMasterBuilders")
                .titlePrefix("§a§l")

                .addDynamicLine()
                .setTitle("Spieler")
                .setContent(Subscribeables.playerCount())

                .and()
                .addDynamicLine();

                subjectTitle.bind(builder.setTitle());
                subject.bind(builder.setContent());

        builder = builder.and()
                .addDynamicLine();

                timerTitle.bind(builder.setTitle());
                timer.bind(builder.setContent());

        builder.build()
                .autoBind();
    }
}
