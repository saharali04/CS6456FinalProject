public class ShapeInfo {
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public ShapeInfo(int width, int height, int startX, int startY, int endX, int endY) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public String toString() {
        return "width: " + Integer.toString(this.width) + "\n" + "height: " + Integer.toString(this.height) + "\n" + "startX: " + Integer.toString(this.startX) + "\n" +
            "endX: " + Integer.toString(this.endX)+ "\n" + "startY: " + Integer.toString(this.startY) + "\n" + "endY: " + Integer.toString(this.endY) + "\n";
    }


}