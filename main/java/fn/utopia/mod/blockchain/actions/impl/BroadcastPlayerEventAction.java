package fn.utopia.mod.blockchain.actions.impl;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.server.FMLServerHandler;
import fn.utopia.mod.blockchain.BlockchainManager;
import fn.utopia.mod.blockchain.actions.AbstractAction;
import fn.utopia.mod.blockchain.data.BlockchainData.PlayerEventData;
import fn.utopia.mod.blockchain.messages.Messages;
import fn.utopia.mod.main.AtopiaMod;

public class BroadcastPlayerEventAction extends AbstractAction {
	
	protected PlayerEventData ped;
	
	public BroadcastPlayerEventAction(BlockchainManager bcm, PlayerEventData ped) {
		super(bcm);
		this.ped = ped;
	}

	@Override
	public boolean execute() {
		MinecraftServer mcs = FMLServerHandler.instance().getServer();
		List players = mcs.getConfigurationManager().playerEntityList;
		
		if (mcs == null || players.isEmpty()){
			return false;
		}
		
		Messages msg = bcm.getMessages();
		for (int i = 0; i < players.size(); i++)
		{
			EntityPlayerMP player = (EntityPlayerMP) players.get(i);
			
			player.addChatMessage(new ChatComponentTranslation(msg.playerEvents.event(ped.userName, ped.eventType)));
		}
		return true;
	}

}
