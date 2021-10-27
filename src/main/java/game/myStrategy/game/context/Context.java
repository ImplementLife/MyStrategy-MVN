package game.myStrategy.game.context;

import game.myStrategy.game.GameService;
import game.myStrategy.game.diplomacy.DiplomacyService;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.game.audio.AudioService;
import game.myStrategy.game.draw.DrawService;
import game.myStrategy.game.map.MapService;
import game.myStrategy.game.net.NetService;
import game.myStrategy.game.objects.IdService;
import game.myStrategy.game.unit.UnitFactory;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.ui.UIService;
import game.myStrategy.ui.game.CursorService;
import game.myStrategy.ui.game.MouseService;

// TODO: 31.07.2021 продумать и внедрить сервисы
/**
 * Тут будут храниться все текущие сервисы
 */
public class Context {
    //region Singleton block

    private static Context instance;
    public static Context get() {
        if (instance == null) instance = new Context();
        return instance;
    }
    private Context() {}

    //endregion

    //region Fields

    private MouseService      mouseService;
    private AudioService      audioService;
    private UpdateService     updateService;
    private DrawService       drawService;

    private NetService        netService;
    private CursorService     cursorService;
    private UIService         uiService;
    private ResourceService   resourceService;

    private GameService       gameService;
    private IdService         idService;
    private MapService        mapService;
    private DiplomacyService  diplomacyService;

    private UnitFactory       unitFactory;

    //endregion

    //region Setters

    public void setDiplomacyService(DiplomacyService diplomacyService) {
        this.diplomacyService = diplomacyService;
        this.diplomacyService.setContext(this);
    }

    public void setMouseService(MouseService mouseService) {
        this.mouseService = mouseService;
        this.mouseService.setContext(this);
    }

    public void setAudioService(AudioService audioService) {
        this.audioService = audioService;
        this.audioService.setContext(this);
    }

    public void setDrawService(DrawService drawService) {
        this.drawService = drawService;
        this.drawService.setContext(this);
    }

    public void setMapService(MapService mapService) {
        this.mapService = mapService;
        this.mapService.setContext(this);
    }

    public void setNetService(NetService netService) {
        this.netService = netService;
        this.netService.setContext(this);
    }

    public void setCursorService(CursorService cursorService) {
        this.cursorService = cursorService;
        this.cursorService.setContext(this);
    }

    public void setIdService(IdService idService) {
        this.idService = idService;
        this.idService.setContext(this);
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
        this.resourceService.setContext(this);
    }

    //endregion

    //region Getters

    public DiplomacyService getDiplomacyService() {
        return diplomacyService;
    }

    public MouseService getMouseService() {
        return mouseService;
    }

    public AudioService getAudioService() {
        return audioService;
    }

    public DrawService getDrawService() {
        return drawService;
    }

    public MapService getMapService() {
        return mapService;
    }

    public NetService getNetService() {
        return netService;
    }

    public CursorService getCursorService() {
        return cursorService;
    }

    public UIService getUiService() {
        return uiService;
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    public GameService getGameService() {
        return gameService;
    }

    public IdService getIdService() {
        return idService;
    }

    public UpdateService getUpdateService() {
        return updateService;
    }

    //endregion

    //region Removers

    public void removeDiplomacyService() {
        diplomacyService = null;
    }

    public void removeMouseService() {
        mouseService = null;
    }

    public void removeAudioService() {
        audioService = null;
    }

    public void removeDrawService() {
        drawService = null;
    }

    public void removeMapService() {
        mapService = null;
    }

    public void removeNetService() {
        netService = null;
    }

    public void removeCursorService() {
        cursorService = null;
    }

    //endregion

}
