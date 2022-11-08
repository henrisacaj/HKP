package radioZulieferer;
import java.lang.reflect.*;
import zuliefererInterface.Device;


public class Radio implements Device{
	private int volume;
	private int frequency;
	private String frequencyType;
	
	public Radio() {
		this.volume = 50;
		this.frequency = 100;
		this.frequencyType = "AM";
	}
	@Override
	public void louder() {
		if (volume < 100) {
			volume++;
			System.out.println("Increased volume to " + volume);
		} else {
			System.out.println("Volume already reached its max value");
		}
	}

	@Override
	public void quieter() {
		if (volume > 0) {
			volume--;
			System.out.println("Decreased volume to " + volume);
		} else {
			System.out.println("Volume already reached its lowest value");
		}
	}

	@Override
	public int getVolume() {
		System.out.println(volume);
		return volume;
	}

	@Override
	public void next() {
		this.frequency++;
		System.out.println("New frequency: " + this.frequency);
	}

	@Override
	public void prev() {
		this.frequency--;
		System.out.println("New frequency: " + this.frequency);
	}

	@Override
	public String getInfoText() {
		String result = "Playing " + frequency + " " + frequencyType + " on volume " + volume;
		System.out.println(result);
		return result;
	}

	@Override
	public String[] getOptions() {
		Method[] methods = this.getClass().getDeclaredMethods();
		String[] options = new String[methods.length];
		int index = 0;
		for (Method m: methods) {
			options[index] = m.getName();
			index++;
		}
		return options;
	}

	@Override
	public void chooseOption(String opt) {
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method m: methods) {
			if (m.getName().equals("chooseOption")) {
				continue;
			} else if(m.getName().equals(opt)) {
				try {
					m.invoke(this);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				
			}
		}
	}

	@Override
	public String play() {
		System.out.println("Playing");
		return null;
	}
	
	public void changeFrequency() {
		if (frequencyType == "AM") {
			frequencyType = "FM";
			System.out.println("Changed frequencyType to " + frequencyType);
		} else{
			frequencyType = "AM";
			System.out.println("Changed frequencyType to " + frequencyType);
		}
	}
	
	public void turnOnNav() {
		System.out.println("Turning navigation system on...");
	}
	
	public void turnOffNav() {
		System.out.println("Turning navigation system off...");
	}
	
	public void showCurrentSong() {
		System.out.println("Current song is...");
	}

}
