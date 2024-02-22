package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.threads.bt.DT;

import java.util.LinkedList;

public class RollingStock extends GameObject {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.ROLLING_STOCK;

    //=======================================//

    private Wagon firstWagon;                      //Первый вагон состава
    private LinkedList<Wagon> intermediateWagons;  //Промежуточные вагоны состава
    private Wagon lastWagon;                       //Последний вагон состава






    public RollingStock() {
        super(TYPE);



    }

    @Override
    public void update(DT dt) {

    }

    @Override
    public void draw(Drawer drawer) {

    }
}
