package endergeticexpansion.client.particle;

import endergeticexpansion.core.EndergeticExpansion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EEParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, EndergeticExpansion.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> POISE_BUBBLE = createBasicParticleType(true, "poise_bubble");
	public static final RegistryObject<BasicParticleType> SHORT_POISE_BUBBLE = createBasicParticleType(true, "short_poise_bubble");
	public static final RegistryObject<BasicParticleType> ENDER_FLAME = createBasicParticleType(true, "ender_flame");

	private static RegistryObject<BasicParticleType> createBasicParticleType(boolean alwaysShow, String name) {
		RegistryObject<BasicParticleType> particleType = PARTICLES.register(name, () -> new BasicParticleType(alwaysShow));
		return particleType;
	}
	
	@EventBusSubscriber(modid = EndergeticExpansion.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {
		
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			if(checkForNonNullWithReflectionCauseForgeIsBaby(POISE_BUBBLE)) {
				Minecraft.getInstance().particles.registerFactory(POISE_BUBBLE.get(), PoiseBubbleParticle.Factory::new);
			}
			if(checkForNonNullWithReflectionCauseForgeIsBaby(SHORT_POISE_BUBBLE)) {
				Minecraft.getInstance().particles.registerFactory(SHORT_POISE_BUBBLE.get(), PoiseBubbleParticle.ShortFactory::new);
			}
			if (checkForNonNullWithReflectionCauseForgeIsBaby(ENDER_FLAME)) {
				Minecraft.getInstance().particles.registerFactory(ENDER_FLAME.get(), FlameParticle.Factory::new);
			}
		}
		
	}

	private static boolean checkForNonNullWithReflectionCauseForgeIsBaby(RegistryObject<BasicParticleType> registryObject) {
		return ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, registryObject, "value") != null;
	}
}