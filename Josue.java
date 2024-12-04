package JosueBot;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Josue - a robot by Creituu
 */

public class Josue extends AdvancedRobot{
	/**
	 * run: Josue's default behavior 
	 */
	
int roboDetectado = 0;

	public void run() {
		setColors(Color.black,Color.magenta,Color.yellow); // body,gun,radar

		// Robot main loop
		while(true) {
			if(roboDetectado == 0){
			setAhead(100);
			setTurnRight(90);
			setTurnGunRight(360);
			execute();
			} else {
				setAhead(100);
				setTurnLeft(90);
				setTurnGunRight(4);
				setTurnGunLeft(4);
				execute();
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		roboDetectado = 1;
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		setBack(25);
		setTurnLeft(45);
		execute();
	}	
}
