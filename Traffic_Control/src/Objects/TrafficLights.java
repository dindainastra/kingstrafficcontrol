
public class TrafficLights implements Terrain{

    public static final int Red = 0;
    public static final int Yellow = 1;
    public static final int Green = 2;
    private int currentColour = Red;
    private final static int RED_SECS = 15;
    private final static int YELLOW_SECS = 5;
    private final static int GREEN_SECS = 15;
    private Thread runner;

    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Green;
                System.out.println("Green");

                break;
            case Yellow:
                currentColour = Red;
                System.out.println("Red");

                break;
            case Green:
                currentColour = Yellow;
                System.out.println("Yellow");

                break;
        }
        return currentColour;
    }

    public int getCurrentColour() {
        return currentColour;
    }

    public void run() {

        // Make sure the traffic light starts out as red
        while (this.currentColour != Red) {
            this.change();
        }

        for (;;) {

            //System.out.println(this.currentColour);
            System.out.println(this.currentColour);
            try {
                Thread.sleep(getSecs());
            } catch (InterruptedException e) {
            }

            this.change();
        }
    }

    private int getSecs() {

        switch (currentColour) {

            case Red:
            default:

                return RED_SECS * 100;

            case Yellow:

                return YELLOW_SECS * 100;

            case Green:

                return GREEN_SECS * 100;
        }
    }

    public void start() {

        runner = new Thread((Runnable) this);
        runner.start();
    }

    public void stop() {

        if (runner != null) {

            runner.stop();
            //runner = null;
        }
    }


    @Override
	public void setInWhichNodeLocated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInWhichNodeLocated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPerson() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPerson() {
		// TODO Auto-generated method stub
		
	}

}




