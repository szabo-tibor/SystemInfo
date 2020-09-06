import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.net.*;
import java.util.*;

//TODO: Implement interface settings menu

public class Window {
	public static void main(String[] args) throws SocketException{

		HashMap os = Info.osInfo();
		ArrayList<ArrayList> network = Info.networkInfo();
		long memory_mb = Info.memoryInfo() /1024 /1024;
		long memory_gb = memory_mb /1024;
		ArrayList<ArrayList<String>> storage = Info.storangeInfo();

		ArrayList<ArrayList<JLabel>> storageInfoLabels = new ArrayList<ArrayList<JLabel>>();
		ArrayList<ArrayList<Long>> labelLongs = new ArrayList<ArrayList<Long>>();
		
		JFrame frame = new JFrame("System Info");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4,1,0,0));


		//OS PANEL
		JPanel osPanel = new JPanel(new GridLayout(1,2,0,0));
		osPanel.setBounds(10,10,400,120);
		osPanel.setBorder(BorderFactory.createTitledBorder("Operating System"));
		

		//OSPANEL LEFT
		JPanel osPanelLeft = new JPanel(new GridLayout(4,1,0,0));
		
		osPanelLeft.add(new JLabel("Operating System:"));
		osPanelLeft.add(new JLabel("Architecture:"));
		osPanelLeft.add(new JLabel("Version:"));
		osPanelLeft.add(new JLabel("Username:"));
		osPanel.add(osPanelLeft);

		//OS PANEL RIGHT
		JPanel osPanelRight = new JPanel(new GridLayout(4,1,0,0));
		
		osPanelRight.add(new JLabel(os.get("name").toString()));
		osPanelRight.add(new JLabel(os.get("arch").toString()));
		osPanelRight.add(new JLabel(os.get("version").toString()));
		osPanelRight.add(new JLabel(os.get("username").toString()));
		osPanel.add(osPanelRight);


		//NETWORK PANEL
		JPanel networkPanel = new JPanel(new GridLayout(1,2,0,0));
		//networkPanel.setBounds(10,10,400,120);
		networkPanel.setBorder(BorderFactory.createTitledBorder("Network Interfaces"));
		//networkPanel.setBackground(Color.orange);

		int rows = network.size();
		JPanel networkPanelLeft = new JPanel(new GridLayout(rows*2,1,0,0));
		JPanel networkPanelRight = new JPanel(new GridLayout(rows*2,1,0,0));

		for (ArrayList l : network) {
			networkPanelLeft.add(new JLabel(l.get(0).toString()));
			networkPanelLeft.add(new JLabel());
			networkPanelRight.add(new JLabel(l.get(1).toString()));
			networkPanelRight.add(new JLabel(l.get(2).toString()));
		}

		networkPanel.add(networkPanelLeft);
		networkPanel.add(networkPanelRight);
		

		//MEMORY PANEL
		JPanel memoryPanel = new JPanel(new GridLayout(1,2,0,0));
		memoryPanel.setBorder(BorderFactory.createTitledBorder("Memory"));

		JPanel memoryPanelLeft = new JPanel(new GridLayout(1,2,0,0));
		JPanel memoryPanelRight = new JPanel(new GridLayout(1,2,0,0));
		
		memoryPanelLeft.add(new JLabel("Memory Capacity:"));
		JLabel memory_capacity_label = new JLabel(Long.toString(memory_gb) + " GB");
		memoryPanelRight.add(memory_capacity_label);

		memoryPanel.add(memoryPanelLeft);
		memoryPanel.add(memoryPanelRight);

		
		//STORAGE INFO
		JPanel storagePanel = new JPanel(new GridLayout(1,2,0,0));
		storagePanel.setBorder(BorderFactory.createTitledBorder("Storage"));

		rows = storage.size();

		JPanel storagePanelLeft = new JPanel(new GridLayout(rows*2,1,0,0));
		JPanel storagePanelRight = new JPanel(new GridLayout(rows*2,1,0,0));

		for (ArrayList<String> l : storage) { // For each drive

			storagePanelLeft.add(new JLabel(l.get(0).toString()));
			storagePanelLeft.add(new JLabel());

			ArrayList<JLabel> interfaceLabels = new ArrayList<JLabel>();
			ArrayList<Long> interfaceLongs = new ArrayList<Long>();
			
			long total = Long.valueOf(l.get(1)) /1024 /1024 /1024;
			long ava = Long.valueOf(l.get(2)) /1024 /1024 /1024;

			JLabel t = new JLabel("Total: " + Long.toString(total) + " GB");
			JLabel a = new JLabel("Availible: " + Long.toString(ava) + " GB");

			interfaceLabels.add(t);
			interfaceLabels.add(a);

			interfaceLongs.add(Long.valueOf(l.get(1)));
			interfaceLongs.add(Long.valueOf(l.get(2)));

			storageInfoLabels.add(interfaceLabels);
			labelLongs.add(interfaceLongs);

			storagePanelRight.add(t);
			storagePanelRight.add(a);

		}

		storagePanel.add(storagePanelLeft);
		storagePanel.add(storagePanelRight);

		//System.out.println(storage);

		//MENU BAR
		JMenuBar menuBar = new JMenuBar();
		
		//OPTIONS MENU
		JMenu options = new JMenu("Options");
		
		//STORAGE SUBMENU
		JMenu storage_unit_submenu = new JMenu("Storage units");
		JMenuItem mb = new JMenuItem(new AbstractAction("Show in MB") {
    		public void actionPerformed(ActionEvent e) {

    			//TODO: Create method, remove repetitive code
        		
        		memory_capacity_label.setText(Long.toString(memory_mb) + " MB");

        		for (int i = 0; i < storageInfoLabels.size(); i++) {

        			Long totalNumber = labelLongs.get(i).get(0) /1024 /1024;
        			String totalLabelText = "Total: " + Long.toString(totalNumber) + " MB";
        			storageInfoLabels.get(i).get(0).setText(totalLabelText);

        			Long availibleNumber = labelLongs.get(i).get(1) /1024 /1024;
        			String availibleLabelText = "Availible: " + Long.toString(availibleNumber) + " MB";
        			storageInfoLabels.get(i).get(1).setText(availibleLabelText);
        		}
    		}
		});
		
		JMenuItem gb = new JMenuItem(new AbstractAction("Show in GB") {
    		public void actionPerformed(ActionEvent e) {

    			//TODO: Create method, remove repetitive code

        		memory_capacity_label.setText(Long.toString(memory_gb) + " GB");

        		for (int i = 0; i < storageInfoLabels.size(); i++) {

        			Long totalNumber = labelLongs.get(i).get(0) /1024 /1024 /1024;
        			String totalLabelText = "Total: " + Long.toString(totalNumber) + " GB";
        			storageInfoLabels.get(i).get(0).setText(totalLabelText);

        			Long availibleNumber = labelLongs.get(i).get(1) /1024 /1024 /1024;
        			String availibleLabelText = "Availible: " + Long.toString(availibleNumber) + " GB";
        			storageInfoLabels.get(i).get(1).setText(availibleLabelText);
        		}
			}
		});
		

		
		storage_unit_submenu.add(mb);
		storage_unit_submenu.add(gb);
		
		//INTERFACE SUBMENU
		JMenu interface_submenu = new JMenu("Network Interface Options");
		JMenuItem show_loopback = new JMenuItem("Include Loopback interfaces");
		JMenuItem show_down = new JMenuItem("Include down interfaces");
		interface_submenu.add(show_loopback);
		interface_submenu.add(show_down);


		options.add(storage_unit_submenu);
		options.add(interface_submenu);
		menuBar.add(options);




		frame.setJMenuBar(menuBar);
		frame.add(osPanel);
		frame.add(networkPanel);
		frame.add(memoryPanel);
		frame.add(storagePanel);

		frame.setVisible(true);
		frame.setSize(500,500);
	}
}
