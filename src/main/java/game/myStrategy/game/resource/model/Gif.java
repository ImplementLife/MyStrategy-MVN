package game.myStrategy.game.resource.model;

import java.awt.*;

public class Gif {
    private final Image[] images;
    private int currImgNum;

    public Gif(Image[] images) {
        this.images = images;
    }

    //region Has

    public boolean hasNext() {
        return !(currImgNum + 1 >= images.length);
    }

    public boolean hasPerv() {
        return currImgNum - 1 > 0;
    }

    //endregion

    //region Get

    public Image getLast() {
        currImgNum = images.length;
        return getCurr();
    }

    public Image getFirst() {
        currImgNum = 0;
        return getCurr();
    }

    public Image getNext() {
        currImgNum++;
        return getCurr();
    }

    public Image getCurr() {
        return images[currImgNum];
    }

    public Image getPerv() {
        currImgNum--;
        return getCurr();
    }

    //endregion

}
