package de.utopiamc.masterbulders.themevote;

import com.google.inject.Inject;
import de.utopiamc.framework.api.service.FrameworkPlayerService;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.ui.inventory.InventoryFactory;
import de.utopiamc.framework.api.ui.inventory.InventoryPresentable;
import de.utopiamc.framework.api.ui.inventory.button.ButtonInventoryBuilder;
import de.utopiamc.framework.api.ui.inventory.button.InventoryButton;
import de.utopiamc.masterbulders.Enums.Subjects;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@Controller
public class ThemeVoting {

    private final InventoryFactory inventoryFactory;

    private final FrameworkPlayerService playerService;

    private final Map<UUID, Subjects> playerVotes = new HashMap<>();

    private Subjects Thema;

    @Inject
    public ThemeVoting(InventoryFactory inventoryFactory, FrameworkPlayerService playerService) {
        this.inventoryFactory = inventoryFactory;
        this.playerService = playerService;
    }

    public static Set<Subjects> generateThemes(){
        List<Subjects> values = new java.util.ArrayList<>(List.of(Subjects.values()));
        Set<Subjects> subjects;
        Random r = new Random();
        subjects = IntStream.range(0, 5)
                .map(i -> r.nextInt(values.size() - 1))
                .mapToObj(values::remove)
                .collect(Collectors.toSet());
        return subjects;
    }

    public void openInvAll(){
        ButtonInventoryBuilder buttonInventory = inventoryFactory.createButtonInventory("§6Voting..");

        for (Subjects generateTheme : generateThemes()) {
            buttonInventory.addButton(InventoryButton.builder()
                    .title("§6" + generateTheme.name())
                    .material(Material.PAPER)
                    .action(player -> {
                        player.sendMessage("§aDu hast für das Thema §6" + generateTheme.name() + " §agestimmt!");
                        playerVotes.put(player.getUniqueId(), generateTheme);
                    })
                    .build());
        }

        InventoryPresentable inv = buttonInventory.disableCloseOnAction().build();

        playerService.getOnlinePlayers().forEach(inv::present);
    }

}
