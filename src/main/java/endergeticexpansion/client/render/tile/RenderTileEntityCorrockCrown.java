package endergeticexpansion.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import endergeticexpansion.client.model.ModelCorrockCrownStanding;
import endergeticexpansion.client.model.ModelCorrockCrownWall;
import endergeticexpansion.common.blocks.BlockCorrockCrownStanding;
import endergeticexpansion.common.tileentities.TileEntityCorrockCrown;
import endergeticexpansion.core.EndergeticExpansion;
import endergeticexpansion.core.registry.EEBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityCorrockCrown extends TileEntityRenderer<TileEntityCorrockCrown> {
	public ModelCorrockCrownStanding standingModel;
	public ModelCorrockCrownWall wallModel;	
	private static final ResourceLocation[] TEXTURES = {
		new ResourceLocation(EndergeticExpansion.MOD_ID + ":textures/tile/corrock_crown_end.png"),
		new ResourceLocation(EndergeticExpansion.MOD_ID + ":textures/tile/corrock_crown_nether.png"),
		new ResourceLocation(EndergeticExpansion.MOD_ID + ":textures/tile/corrock_crown_overworld.png")
	};
	
	public RenderTileEntityCorrockCrown(TileEntityRendererDispatcher renderDispatcher) {
		super(renderDispatcher);
		this.standingModel = new ModelCorrockCrownStanding();
		this.wallModel = new ModelCorrockCrownWall();
	}
	
	@Override
	public void render(TileEntityCorrockCrown te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		BlockState state = te.getBlockState();
		boolean isStanding = state.getBlock() instanceof BlockCorrockCrownStanding;
		
		matrixStack.push();
		
		if(isStanding) {
			matrixStack.translate(0.5F, 1.5F, 0.5F);
			float angle = -((float) (state.get(StandingSignBlock.ROTATION) * 360) / 16.0F);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(angle));
		} else {
			matrixStack.translate(0.5F, 1.5F, 0.5F);
			float angle = -state.get(WallSignBlock.FACING).getHorizontalAngle();
			matrixStack.rotate(Vector3f.YP.rotationDegrees(angle));
			
			matrixStack.translate(0.0F, -1.0F, 0.05F);
		}
		
		if(isStanding && state.get(BlockCorrockCrownStanding.UPSIDE_DOWN)) {
			matrixStack.rotate(Vector3f.XP.rotationDegrees(180.0F));
			matrixStack.translate(0.0F, 2.0F, 0.0F);
			
			matrixStack.scale(1.0F, -1.0F, -1.0F);
		} else {
			matrixStack.scale(1.0F, -1.0F, -1.0F);
		}
		
		if(isStanding) {
			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.getEntityCutout(TEXTURES[this.getTexture(te)]));
			this.standingModel.renderAll(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
		} else {
			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.getEntityCutout(TEXTURES[this.getTexture(te)]));
			this.wallModel.renderAll(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
		}
		
		matrixStack.pop();
	}
	
	public int getTexture(TileEntityCorrockCrown te) {
		BlockState BlockState = te.getBlockState();
		if(BlockState.getBlock() == EEBlocks.CORROCK_CROWN_END_STANDING.get() | BlockState.getBlock() == EEBlocks.CORROCK_CROWN_END_WALL.get()) {
			return 0;
		} else if(BlockState.getBlock() == EEBlocks.CORROCK_CROWN_NETHER_STANDING.get() | BlockState.getBlock() == EEBlocks.CORROCK_CROWN_NETHER_WALL.get()) {
			return 1;
		} else {
			return 2;
		}
	}
}