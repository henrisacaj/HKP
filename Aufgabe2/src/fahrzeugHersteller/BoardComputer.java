package fahrzeugHersteller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.Scanner;

import usbZulieferer.USB;
import cdZulieferer.CD;
import radioZulieferer.Radio;
import zuliefererInterface.Device;

public class BoardComputer {
	//gefüllt nach dem Einlesen der Config
	private static String[] deviceNames;
	private Device[] installedDevices;
	private Device playingDevice;
	private int currentDevice = 0;
	
	public BoardComputer(Device[] devices) {
		installedDevices = devices;
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		/*bc.readConfig();
		bc.setDevices();
		bc.changeDevice();
		bc.showOptions();
		bc.changeDevice();
		bc.quieter(5);
		bc.louder(5);
		bc.play();
		while(true) {
			Scanner scan = new Scanner(System.in);
			int option = scan.nextInt();
			bc.enterOption(option);
		}
		*/
	}

	private void readConfig() {
		String configFilePath = "src/Geraete.config";
		FileInputStream config = null;
		try {
			config = new FileInputStream(configFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scan = new Scanner(config);
		String[] deviceName;
		String line = "";
		String devices = "";
		int installedDevices = 0;
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			String[] parts = line.split("\s");
			devices += parts[2] + ", ";
			installedDevices++;
		}
		devices = devices.substring(0, devices.length() - 2);
		System.out.println("Available devices: " + devices);
		String devicesParts[] = devices.split(", ");
		deviceNames = new String[installedDevices];
		for (int i = 0; i < installedDevices; i++) {
			deviceNames[i] = devicesParts[i];
		}

	}

	//Integration durch Reflektion
	private void setDevices() throws ClassNotFoundException {
		int index = 0;
		installedDevices = new Device[deviceNames.length];
		for(String deviceName: deviceNames) {
			
			try {
				Class<?> randomClass = Class.forName(deviceName);
				Constructor<?> constructor = randomClass.getConstructor();
				Device device = (Device) constructor.newInstance();
				installedDevices[index] = device;
				index++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		playingDevice = installedDevices[currentDevice];

		System.out.println("Started playing " + playingDevice.getClass().getSimpleName());
	}
	
	/* Direkte Abhängigkeit
	private void setDevices() throws ClassNotFoundException {
		int index = 0;
		installedDevices = new Device[3];
		USB usb = new USB();
		CD cd = new CD();
		Radio radio = new Radio();
		
		installedDevices[0] = radio;
		installedDevices[1] = cd;
		installedDevices[2] = usb;

		playingDevice = installedDevices[currentDevice];

		System.out.println("Started playing " + playingDevice.getClass().getSimpleName());
	}*/

	public void shutdown() {
		playingDevice = null;
		System.exit(0);
	}
	
	//Das nächste Device auswählen
	public void changeDevice() {
		if (currentDevice < installedDevices.length - 1) {
			currentDevice++;
		} else {
			currentDevice = 0;
		}
		playingDevice = installedDevices[currentDevice];
		System.out.println("Changed device to " + playingDevice.getClass().getSimpleName());
	}
	
	//per Introspection
	public void showOptions() {
		String[] options = playingDevice.getOptions();
		String result = "";
		int index = 0;
		for (String option: options) {
			result += index + " " + option + " | ";
			index++;
		}
		System.out.println(result.substring(0, result.length() - 3));
	}

	public void enterOption(int choice) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = playingDevice.getClass().getDeclaredMethods();
		Method methodToCall = methods[choice];
		String methodName = methodToCall.getName();
		playingDevice.chooseOption(methodName);
	}
	
	//mehrere Schritte lauter
	public void louder(int volume) {
		for(int i = 0; i < volume; i++) playingDevice.louder();
	}
	
	public void showVolume() {
		System.out.println(playingDevice.getVolume());
	}
	
	public void quieter(int volume) {
		
		for(int i = 0; i < volume; i++) playingDevice.quieter();
	}
	
	public void next() {
		playingDevice.next();
	}
	
	public void prev() {
		playingDevice.prev();
	}
	
	public void play() {
		playingDevice.play();
	}
}