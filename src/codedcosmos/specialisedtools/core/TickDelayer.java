package codedcosmos.specialisedtools.core;

public class TickDelayer {
	private int ticksSince = 0;
	private int delay;
	
	public TickDelayer(int delay) {
		this.delay = delay;
		reset();
	}
	
	public void reset() {
		ticksSince = delay;
	}
	
	public void tick() {
		if (ticksSince > 0) ticksSince--;
	}
	
	public boolean shouldActivate() {
		return ticksSince == 0;
	}
}
