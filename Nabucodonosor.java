package Diogo_Lindo;
import robocode.*;
import java.awt.Color;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;


import java.awt.*;

public class Nabucodonosor extends AdvancedRobot
{
	int count = 0;
	double gunTurnAmt;
	String trackName;

int roboDetectado = 0;
   
	public void run() {
	
		 setColors(Color.black,Color.magenta,Color.yellow);
		 trackName = null;
		 setAdjustGunForRobotTurn(true);
		 gunTurnAmt = 10;
		 execute();

		while(true) {
		 setTurnGunRight(gunTurnAmt);
		 count++;
		 execute();
		 if (count > 2) {
				gunTurnAmt = -10;
			}
			if (count > 5) {
				gunTurnAmt = 10;
			}
			if (count > 11) {
				trackName = null;
			}
		}
	}

	public void onScannedRobot(ScannedRobotEvent  e) {
	
		if (trackName != null && !e.getName().equals(trackName)) {
			return;
		}

		if (trackName == null) {
			trackName = e.getName();
		}

		count = 0;
		
		if (e.getDistance() > 150) {
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			setTurnGunRight(gunTurnAmt);
			setTurnRight(e.getBearing());
			setAhead(e.getDistance() - 140);
			setFire(3);
			execute();
			return;
		}

		double absoluteBearing = getHeading() + e.getBearing();
			double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
				if (Math.abs(bearingFromGun) <= 3){
					turnGunRight(bearingFromGun);
						if (getGunHeat() == 0) {
							setAhead(110);
							setTurnLeft(90);
							setTurnGunRight(4);
							setTurnGunLeft(4);
							setFire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
							execute();
	}
}
			else {
			turnGunRight(bearingFromGun);
		}
			if (bearingFromGun == 0) {
			scan();
			}
		scan();

		}

	public void onHitByBullet(HitByBulletEvent e) {
		setBack(35);
		setTurnLeft(45);
		execute();
	}

	public void onHitWall(HitWallEvent e) {
		setBack(25);
		setTurnLeft(45);
		execute();
	}	
}