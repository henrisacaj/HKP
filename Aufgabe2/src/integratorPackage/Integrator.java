package integratorPackage;
import radioZulieferer.Radio;
import usbZulieferer.USB;
import zuliefererInterface.Device;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import cdZulieferer.CD;
import fahrzeugHersteller.BoardComputer;

public class Integrator {
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CD cd = new CD();
		USB usb = new USB();
		Radio radio = new Radio();
		Device[] devices = new Device[3];
		devices[0] = cd;
		devices[1] = usb;
		devices[2] = radio;
		BoardComputer bc = new BoardComputer(devices);
		
		bc.changeDevice();
		bc.showOptions();
		bc.changeDevice();
		bc.showOptions();
		bc.changeDevice();
		bc.showOptions();
		bc.quieter(5);
		bc.louder(5);
		bc.play();
		while(true) {
			Scanner scan = new Scanner(System.in);
			int option = scan.nextInt();
			bc.enterOption(option);
		}
	}

}
