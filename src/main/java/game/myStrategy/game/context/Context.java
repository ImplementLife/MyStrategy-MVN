package game.myStrategy.game.context;

import game.myStrategy.Boot;
import game.myStrategy.game.GameService;
import game.myStrategy.game.audio.AudioService;
import game.myStrategy.game.diplomacy.DiplomacyService;
import game.myStrategy.game.draw.DrawService;
import game.myStrategy.game.map.MapService;
import game.myStrategy.game.net.NetService;
import game.myStrategy.game.objects.IdService;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.game.unit.UnitFactory;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.ui.UIService;
import game.myStrategy.ui.game.cursor.CursorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Context {
    public static Context context() {
        return Boot.getBean(Context.class);
    }

    @Autowired private AudioService      audioService;
    @Autowired private UpdateService     updateService;
    @Autowired private DrawService       drawService;
    @Autowired private NetService        netService;

//    @Autowired private CursorService     cursorService;
    @Autowired private UIService         uiService;
    @Autowired private ResourceService   resourceService;
    @Autowired private GameService       gameService;

    @Autowired private IdService         idService;
    @Autowired private MapService        mapService;
    @Autowired private DiplomacyService  diplomacyService;
    @Autowired private UnitFactory       unitFactory;

    public void setDrawService(DrawService drawService) {
        this.drawService = drawService;
    }

    public DiplomacyService getDiplomacyService() {
        return diplomacyService;
    }
    public DrawService getDrawService() {
        return drawService;
    }
    public IdService getIdService() {
        return idService;
    }
    public UpdateService getUpdateService() {
        return updateService;
    }
}
