package game.myStrategy.game.unit.squads;

import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.threads.bt.DT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Squad extends Unit {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.SQUAD;

    /*=======================================*/
    public static HashSet<Squad> squads = new HashSet<>();
    public static List<Squad> squadsInFocus() {
        List<Squad> res = new ArrayList<>();
        for (Squad s : squads) if (s.inFound()) res.add(s);
        return res;
    }

    /*=========     NoStatic     ============*/
    final SquadMembers members;
    final SquadMover mover;
    final SquadAttackManager attackManager;
    final SquadControlPanel controlPanel;
    final SquadAI ai;

    /*=======================================*/
    public Squad() {
        super(TYPE);
        squads.add(this);
        members = new SquadMembers(this);
        mover = new SquadMover(this);
        attackManager = new SquadAttackManager(this);
        controlPanel = new SquadControlPanel(this);
        ai = new SquadAI(this);
    }

    public void putMembers(Unit unit) {
        if (unit != this)  {
            members.put(unit.getId(), unit);
            controlPanel.setPos(getPos());
        }
    }
    /*=======================================*/
    //   Unit Overrides
    @Override
    public void setPlayer(int player) {
        super.setPlayer(player);
        controlPanel.setVisible(!isEnemy());
    }

    @Override
    public void attack(Unit unit) {
        attackManager.attack(unit);
    }

    @Override
    public boolean kill() {
        if (members.size() < 1) {
            squads.remove(this);
            super.delete();
            return true;
        }
        return false;
    }

    @Override
    public void delete() {
        super.delete();
        squads.remove(this);
    }

    /*=======================================*/
    @Override
    public boolean isMove() {
        return mover.isMove();
    }

    @Override
    public void moveTo(Vec2D pos) {
        mover.moveTo(pos);
    }

    @Override
    public Vec2D getPos() {
        return members.getPos();
    }

    @Override
    public float getSize() {
        return members.getMaxDist();
    }

    /*=======================================*/
    private boolean inFound() {
        return controlPanel.inFound();
    }

    @Override
    public void update(DT dt) {
        super.update(dt);
        mover.move();
        attackManager.update();
    }

    @Override
    public void draw(Drawer drawer) {
        mover.arrow.draw(drawer);
    }
}
