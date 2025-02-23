package endergeticexpansion.common.blocks;

import endergeticexpansion.core.registry.other.EETags;
import net.minecraft.block.FireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class EnderFireBlock extends FireBlock {

	public EnderFireBlock(Properties builder) {
		super(builder);
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return isEnderFireBase(worldIn.getBlockState(pos.down()).getBlock());
	}

	public static boolean isEnderFireBase(Block block) {
		return block.isIn(EETags.Blocks.ENDER_FIRE_BASE_BLOCKS);
	}

	public boolean canBurn(BlockState stateIn) {
		return true;
	}

	@Override
	public boolean isBurning(BlockState state, IBlockReader world, BlockPos pos) {
		return true;
	}
	
}