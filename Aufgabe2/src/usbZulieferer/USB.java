package usbZulieferer;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import zuliefererInterface.Device;


public class USB implements Device {
	
	private static int volume = 25;
	
	public USB() {
		volume = 20;
	}
	// Einen Anruf annehmen/ablehnen
	public void getCall() {
		System.out.println("Your USB device is receiving a call, do you want to accept the call?");
	}
	
	// Usb-Plug nutzen zum Aufladen
	public void charging() {
		System.out.println("Unit is charged via USB.");
	}
	
	// Usb-Plug für Mikrofon
	public void inVoice() {
		System.out.println("Device for voice input (microphone) was recognised!");
	}
	
	//Navi-Update über USB
	public void navigationSystemUpdate() {
		System.out.println("Navigationupdate via usb was successful!");
	}
	
	@Override
	public void louder() {
		volume++;
		System.out.println("Increasing your volume! Current volume is: " + volume);
	}

	@Override
	public void quieter() {
		volume--;
		System.out.println("Decreasing your volume! Current volume is: " + volume);
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return volume;
	}

	@Override
	public void next() {
		System.out.println(">> The next song is played.");
	}

	@Override
	public void prev() {
		System.out.println("<< The previous song is played.");
	}

	@Override
	public String getInfoText() {
		String infoText = "";
		String song = play();
		java.util.Date date = new java.util.Date();

		infoText = "-------------------------------------------------\n|		-- Boardcomputer --		|\n| Current Date: " + date + "	|"
		+ "\n| Current Song: " + song + "	|\n" + "| Volume: " + getVolume() 
		+ "					|\n-------------------------------------------------";
		
		return infoText;
	}

	@Override
	public String[] getOptions() {
		Method[] options = this.getClass().getDeclaredMethods();
		String[] strOption = new String[options.length];
		
		for(int i = 0; i < options.length; i++) {
			strOption[i] = options[i].getName();
		}
		
		//Arrays.sort(strOption);
		
		return strOption;
	}

	@Override
	public void chooseOption(String options) {
		Method[] meths = this.getClass().getDeclaredMethods();
		
		for(Method meth : meths) {
			if(options == meth.getName()) {
				try {
					Method actualMethod = this.getClass().getDeclaredMethod(options);
					
					System.out.println("You have selected the " + options + " option!");
					String str = "";
					if(actualMethod.invoke(this) != null) {
						str = (String) actualMethod.invoke(this);
						System.out.println(str);
					} 
					 
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}

	@Override
	public String play() {
		return "No Role Modelz by J. Cole";
	}
	
	
}
	
    
