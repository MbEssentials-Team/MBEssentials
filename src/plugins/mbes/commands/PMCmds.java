package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class PMCmds implements CommandExecutor{

	Server server;
	
	public PMCmds(Server server) {
		this.server = server;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("pm"))
		{
			if(sender.hasPermission("mbes.pm"))
			{
				if(args.length < 2)
				{
					Player send;
					
					send = server.getPlayer(args[0]);
					
					if(send == null)
						sender.sendMessage("Player '" + args[0] + "' was not found!");
					
					else
					{
						boolean blocked = send.getMetaData("MBES:PMBLOCK:" + sender.getName(),false);
						
						if(blocked || send.getMetaData("MBES:PMBLOCKALL",false))
							sender.sendMessage("Player '" + send.getDisplayName() + "' has blocked you from sending them pm's!");
						
						else
						{
							String msg = "";
						
							for(int a = 1; a < args.length;a++)
							{
								msg = msg + args[a];
							}
						
							send.sendMessage("From '" + sender.getName() + "': " + msg);
						}
					}
				}
				
				else
					sender.sendMessage("Syntax:/pm <sentTo> <message>");
			}
			
			else
				sender.sendMessage("You don't have permission to pm");
		}
		
		else if(command.equals("block"))
		{
			if(sender.hasPermission("mbes.pm"))
			{
				if(!(sender instanceof Player))
					sender.sendMessage("This command can only be executed as a player!");
			
				else if(args.length != 0)
				{
					Player player = server.getPlayer(args[0]),p = (Player)sender;
				
					if(player == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
				
					else
					{
						if(p.getMetaData("MBES:PMBLOCK:" + player.getDisplayName(),false))
							sender.sendMessage("You have already blocked '" + player.getDisplayName() + "'!");
					
						else
							p.setMetaData("MBES:PMBLOCK:" + player.getDisplayName(),true);
					}
				}
				
				else
					sender.sendMessage("Syntax:/unblock <name>");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("unblock"))
		{
			if(sender.hasPermission("mbes.pm"))
			{
				if(!(sender instanceof Player))
					sender.sendMessage("This command can only be executed as a player!");
			
				else if(args.length != 0)
				{
					Player player = server.getPlayer(args[0]),p = (Player)sender;
				
					if(player == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
				
					else
					{
						if(!p.getMetaData("MBES:PMBLOCK:" + player.getDisplayName(),false))
							sender.sendMessage("The player '" + player.getDisplayName() + "' is not blocked!");
					
						else
							p.setMetaData("MBES:PMBLOCK:" + player.getDisplayName(),false);
					}
				}
				
				else
					sender.sendMessage("Syntax:/unblock <name>");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("blockall"))
		{
			if(!(sender instanceof Player))
				sender.sendMessage("This command can only be executed as a player!");
			
			else
			{
				if(sender.hasPermission("mbes.pm"))
				{
					Player p = (Player)sender;
					p.setMetaData("MBES:PMBLOCKALL",true);
					sender.sendMessage("You have blocked everyone from sending you pm's");
				}
				else
					sender.sendMessage("You don't have permission to use this command!");
			}
		}
		
		else if(command.equals("unblockall"))
		{
			if(!(sender instanceof Player))
				sender.sendMessage("This command can only be executed as a player!");
			
			else
			{
				if(sender.hasPermission("mbes.pm"))
				{
					Player p = (Player)sender;
					p.setMetaData("MBES:PMBLOCKALL",false);
					sender.sendMessage("You have unblocked everyone from sending you pm's");
				}
				else
					sender.sendMessage("You don't have permission to use this command!");
			}
		}
		
	}

}