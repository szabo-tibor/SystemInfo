import java.net.*;
import java.util.*;
import java.io.File;
import java.lang.management.*;

public class Info {

	public static HashMap<String,String> osInfo() {

		HashMap<String,String> info = new HashMap<>();

		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		String name = System.getProperty("os.name");
		String username = System.getProperty("user.name");

		info.put("arch",arch);
		info.put("version",version);
		info.put("name",name);
		info.put("username",username);

		return info;

	}

	public static ArrayList<ArrayList> networkInfo() throws SocketException {

		ArrayList<ArrayList> interfaces = new ArrayList<ArrayList>();

		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

		for (NetworkInterface netint : Collections.list(nets)) {
			if (netint.isUp() && !netint.isLoopback()) {

				//add GUI option to show loopback, down interfaces

				ArrayList<String> iface = new ArrayList<String>();
				
				Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
				
				String ip = Collections.list(inetAddresses).get(0).toString();
				String displayName = netint.getDisplayName();
				String name = netint.getName();

				iface.add(displayName);
				iface.add(name);
				iface.add(ip);

				interfaces.add(iface);
			}
		}

		return interfaces;
	}

	public static long memoryInfo() {

		// /1024 /1024 + "MB\n");
		
		long memorySize = ((com.sun.management.OperatingSystemMXBean)
			ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();

		return memorySize;
	}

	public static ArrayList<ArrayList<String>> storangeInfo() {

		//add option to show memory in GB or MB in GUI
		// /1024 /1024 /1024 for GB, /1024 /1024 for MB
		//JProgressBar

		ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

		File[] drives = File.listRoots();

		for (File drive : drives) {
			ArrayList<String> drive_info = new ArrayList<String>();
			
			String letter = drive.toString();
			File file = new File(letter);

			String totalSpace = Long.toString(file.getTotalSpace());
			String usableSpace = Long.toString(file.getUsableSpace());
			String freeSpace = Long.toString(file.getFreeSpace());

			drive_info.add(letter);
			drive_info.add(totalSpace);
			drive_info.add(usableSpace);
			drive_info.add(freeSpace);

			info.add(drive_info);

		}

		return info;
	}
}