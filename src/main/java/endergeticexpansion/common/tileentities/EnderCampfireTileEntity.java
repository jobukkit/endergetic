package endergeticexpansion.common.tileentities;

import endergeticexpansion.core.registry.EETileEntities;

import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class EnderCampfireTileEntity extends CampfireTileEntity {

	public EnderCampfireTileEntity() {
		super();
	}
	
	public TileEntityType<?> getType() {
		return EETileEntities.ENDER_CAMPFIRE.get();
	}
}
