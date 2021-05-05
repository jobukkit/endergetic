package com.minecraftabnormals.endergetic.common.world.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class SparseCorrockSurfaceBuilder extends DefaultSurfaceBuilder {
	private static final double SPECKLED_THRESHOLD = 1.275F;

	public SparseCorrockSurfaceBuilder(Codec<SurfaceBuilderConfig> config) {
		super(config);
	}

	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		if (noise > 1.9F) {
			if (noise <= 2.1F) {
				SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, random.nextFloat() < 0.35F ? EESurfaceBuilders.Configs.SPECKLED_END_STONE_CONFIG.get() : EESurfaceBuilders.Configs.CORROCK_CONFIG.get());
			} else {
				SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.Configs.CORROCK_CONFIG.get());
			}
		} else if (noise > SPECKLED_THRESHOLD && random.nextFloat() <= Math.abs(noise - SPECKLED_THRESHOLD)) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.Configs.SPECKLED_END_STONE_CONFIG.get());
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.Configs.END_STONE_CONFIG.get());
		}
	}
}