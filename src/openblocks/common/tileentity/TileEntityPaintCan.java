package openblocks.common.tileentity;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import openblocks.OpenBlocks;
import openblocks.common.api.IActivateAwareTile;
import openblocks.common.api.IPlaceAwareTile;
import openblocks.common.block.BlockPaintCan;
import openblocks.sync.ISyncableObject;
import openblocks.sync.SyncableInt;

public class TileEntityPaintCan extends SyncedTileEntity implements IPlaceAwareTile, IActivateAwareTile {

	private SyncableInt color;
	private SyncableInt amount;

	@Override
	public void onSynced(Set<ISyncableObject> changes) {}

	@Override
	protected void createSyncedFields() {
		color = new SyncableInt();
		amount = new SyncableInt();
	}

	@Override
	public void onBlockPlacedBy(EntityPlayer player, ForgeDirection side, ItemStack stack, float hitX, float hitY, float hitZ) {
		color.setValue(BlockPaintCan.getColorFromNBT(stack));
		amount.setValue(BlockPaintCan.getAmountFromNBT(stack));
	}

	@Override
	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!worldObj.isRemote) {
			ItemStack heldStack = player.getHeldItem();
			if (heldStack != null && heldStack.getItem().equals(OpenBlocks.Items.paintBrush)) {
				NBTTagCompound tag = heldStack.getTagCompound();
				if (tag == null) {
					tag = new NBTTagCompound();
					heldStack.setTagCompound(tag);
				}
				tag.setInteger("color", color.getValue());
				heldStack.setItemDamage(0);
				worldObj.playSoundAtEntity(player, "liquid.swim", 0.1F, 1.2F);
			}
		}
		return false;
	}

	public int getColor() {
		return color.getValue();
	}

}