package fr.foxxen;

import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;

public class MainReportBug extends JavaPlugin{

	public ArrayList<String> bugReport = new ArrayList<>();
	
	public static MainReportBug instance;
	
	public static MainReportBug getInstance(){
		return instance;
	}
	
	
    public void enregistrerUneListe(ArrayList<String> liste) {
        instance.getConfig().set("report", liste);
        instance.saveConfig();
    }

    public ArrayList<String> obtenirUneListe() {
        return (ArrayList<String>) instance.getConfig().getStringList("report");
    }
    
    public String recupMsgConfig(String section, String msg){
    	return getConfig().get(section + "." + msg, msg).toString().replace("&", "§");
    }
	
	public void onEnable(){
		
		instance = this;
		
		saveDefaultConfig();
		
		bugReport.addAll(obtenirUneListe());
		
		getCommand("reportbug").setExecutor(new reportBugCommand());
	}
}
