package fr.foxxen;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reportBugCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if(sender instanceof Player){
			
			Player p = (Player) sender;
			ArrayList<String> bugReport = MainReportBug.getInstance().bugReport;
			
			if(cmd.getName().equalsIgnoreCase("reportbug")){
				
				if(args.length == 0){
					
					p.sendMessage(MainReportBug.getInstance().recupMsgConfig("message", "bug-use"));
					return true;
				
				}
				
				if(p.hasPermission("reportbug.admin")){
				
					if(args[0].equalsIgnoreCase("list")){
						
						int bugReportSize = bugReport.size();
						
						for(int i = 0; i<bugReport.size();i++){
							String nb = Integer.toString(i);
						       p.sendMessage(MainReportBug.getInstance().recupMsgConfig("message", "list-bug").replace("%buglist%", bugReport.get(i)).replace("%nb%", nb));
						       if(bugReport.size() > 1){
						    	   p.sendMessage(" ");
						       }
							}
						
						if(bugReportSize == 0){
							p.sendMessage(MainReportBug.getInstance().recupMsgConfig("message", "no-bug"));
						}
						return true;
					}
					
					if(args[0].equalsIgnoreCase("remove")){
							
							bugReport.remove(Integer.parseInt(args[1]));
							p.sendMessage(MainReportBug.getInstance().recupMsgConfig("message", "bug-remove"));
							MainReportBug.getInstance().enregistrerUneListe(bugReport);
							return true;
						
					}
				}
				
				if(args.length >= 1){
					
					StringBuilder bc = new StringBuilder();
					for(String parts : args){
						bc.append(parts + " ");
					}
					
					String name = p.getName();
					MainReportBug.getInstance().bugReport.add(MainReportBug.getInstance().recupMsgConfig("message", "bug-save").replace("%username%", name).replace("%msgbug%", bc.toString()));
					p.sendMessage(MainReportBug.getInstance().recupMsgConfig("message", "bug-send"));
					MainReportBug.getInstance().enregistrerUneListe(bugReport);
					
					return true;
					
				}
											
			}
			
		}
		
		return false;
	}

}
