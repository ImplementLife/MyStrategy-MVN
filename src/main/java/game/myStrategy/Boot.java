package game.myStrategy;

import game.myStrategy.game.context.Context;
import game.myStrategy.game.diplomacy.DiplomacyService;
import game.myStrategy.game.objects.IdService;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.ui.menu.FrameController;

public final class Boot {
    public static void main(String[] args) {
        firstInitContext();
        FrameController.get().setMainMenu();
    }

    private static void firstInitContext() {
        Context context = Context.context();
        context.setIdService(new IdService());
        context.setDiplomacyService(new DiplomacyService());

        ResourceService resourceService = ResourceService.get();
        resourceService.load();
        context.setResourceService(resourceService);
    }
}
