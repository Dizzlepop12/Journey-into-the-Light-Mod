package net.journey.client;

import net.journey.JourneyItems;
import net.journey.blocks.container.tileentity.TileEntityKnowledgeTable;
import net.journey.blocks.container.tileentity.TileEntitySummoningTable;
import net.journey.client.render.RenderBoss;
import net.journey.client.render.RenderItemProjectile;
import net.journey.client.render.RenderModMob;
import net.journey.client.render.RenderProjectile;
import net.journey.client.render.RenderSizeable;
import net.journey.client.render.RenderStaffProjectile;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityBoilingPiercer;
import net.journey.entity.projectile.EntityBouncingProjectile;
import net.journey.entity.projectile.EntityBubbleProjectile;
import net.journey.entity.projectile.EntityConjuring;
import net.journey.entity.projectile.EntityCorbaPiercer;
import net.journey.entity.projectile.EntityDemonicBomb;
import net.journey.entity.projectile.EntityDepthsPiercer;
import net.journey.entity.projectile.EntityDetractor;
import net.journey.entity.projectile.EntityDoomsBringer;
import net.journey.entity.projectile.EntityEnlightenment;
import net.journey.entity.projectile.EntityEucaPiercer;
import net.journey.entity.projectile.EntityFireBall;
import net.journey.entity.projectile.EntityFireBomb;
import net.journey.entity.projectile.EntityForestPlasma;
import net.journey.entity.projectile.EntityFrostbittenPiercer;
import net.journey.entity.projectile.EntityFrostyPiercer;
import net.journey.entity.projectile.EntityFrozenPiercer;
import net.journey.entity.projectile.EntityGreenpace;
import net.journey.entity.projectile.EntityIceBall;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.entity.projectile.EntityNetherPlasma;
import net.journey.entity.projectile.EntityNethicPiercer;
import net.journey.entity.projectile.EntityOceanPlasma;
import net.journey.entity.projectile.EntityRockProjectile;
import net.journey.entity.projectile.EntityShimmererProjectile;
import net.journey.entity.projectile.EntityTempleBall;
import net.journey.entity.projectile.EntityWizardsStar;
import net.journey.util.Textures;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRendering {

	private static Textures tex;
	private static MobStats stat;

	public static void init() {
	/**Projectiles*/
		RenderingRegistry.registerEntityRenderingHandler(EntityBasicProjectile.class, new RenderStaffProjectile(Textures.basic, 1.0F, 0.2F, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDoomsBringer.class, new RenderStaffProjectile(Textures.basic, 1.2F, 0.2F, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityConjuring.class, new RenderStaffProjectile(Textures.basic, 0.1F, 1.0F, 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnlightenment.class, new RenderStaffProjectile(Textures.basic, 0.7F, 0.0F, 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGreenpace.class, new RenderStaffProjectile(Textures.basic, 0.6F, 1.0F, 0.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWizardsStar.class, new RenderStaffProjectile(Textures.basic, 1.0F, 1.0F, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBouncingProjectile.class, new RenderProjectile(Textures.bouncingProjectile));
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaFireball.class, new RenderProjectile(Textures.magmaBall));
		//RenderingRegistry.registerEntityRenderingHandler(EntityFlameArrow.class, new RenderModArrow());
		//RenderingRegistry.registerEntityRenderingHandler(EntityPoisonArrow.class, new RenderModArrow());
		//RenderingRegistry.registerEntityRenderingHandler(EntityDarknessArrow.class, new RenderModArrow());
		//RenderingRegistry.registerEntityRenderingHandler(EntityFrozenArrow.class, new RenderModArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFireBall.class, new RenderStaffProjectile(Textures.empty, 1F, 1F, 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceBall.class, new RenderStaffProjectile(Textures.empty, 1F, 1F, 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTempleBall.class, new RenderStaffProjectile(Textures.templeBall, 0F, 0F, 0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new RenderStaffProjectile(Textures.empty, 1F, 1F, 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRockProjectile.class, new RenderProjectile(Textures.rockChunk));
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherPlasma.class, new RenderProjectile(Textures.plasma));
		RenderingRegistry.registerEntityRenderingHandler(EntityOceanPlasma.class, new RenderProjectile(Textures.plasma));
		RenderingRegistry.registerEntityRenderingHandler(EntityForestPlasma.class, new RenderProjectile(Textures.plasma));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoilingPiercer.class, new RenderItemProjectile(JourneyItems.boilingPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityNethicPiercer.class, new RenderItemProjectile(JourneyItems.nethicPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenPiercer.class, new RenderItemProjectile(JourneyItems.frozenPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityEucaPiercer.class, new RenderItemProjectile(JourneyItems.eucaPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityDepthsPiercer.class, new RenderItemProjectile(JourneyItems.depthsPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityCorbaPiercer.class, new RenderItemProjectile(JourneyItems.corbaPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostbittenPiercer.class, new RenderItemProjectile(JourneyItems.frostbittenPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostyPiercer.class, new RenderItemProjectile(JourneyItems.frostyPiercer));
		RenderingRegistry.registerEntityRenderingHandler(EntityShimmererProjectile.class, new RenderProjectile(Textures.shimmererProjectile));
		//RenderingRegistry.registerEntityRenderingHandler(EntityDemonicBomb.class, new RenderItemProjectile(JourneyItems.demonicBomb));
		//RenderingRegistry.registerEntityRenderingHandler(EntityFireBomb.class, new RenderItemProjectile(JourneyItems.fireBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityBubbleProjectile.class, new RenderProjectile(Textures.bubble));
		RenderingRegistry.registerEntityRenderingHandler(EntityDetractor.class, new RenderProjectile(Textures.detractor));
		//RenderingRegistry.registerEntityRenderingHandler(EntityRoyalKnife.class, new RenderItemProjectile(JourneyItems.royalKnife));
		/**Overworld Mobs*/
		/*RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderModMob(new ModelRobot(), Textures.robot));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpikedBeast.class, new RenderModMob(new ModelSpikedBeast(), Textures.spikedBeast));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpyclops.class, new RenderSpyclops(new ModelSpyclops()));
		//RenderingRegistry.registerEntityRenderingHandler(EntityMagmaGiant.class, new RenderModMob(new ModelMagmaGiant(), tex.magmaGiant));
		//RenderingRegistry.registerEntityRenderingHandler(EntityInsecto.class, new RenderInsecto(new ModelInsecto()));
		//RenderingRegistry.registerEntityRenderingHandler(EntityPsyollom.class, new RenderPsyollom(new ModelPsyollom()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDepthsBeast.class, new RenderSizeable(new ModelDepthsBeast(), 0.8F, 1.5F, Textures.depthsBeast));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarknessCrawler.class, new RenderModMob(new ModelDarknessCrawler(), Textures.darknessCrawler));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoom.class, new RenderBoomBoom(new ModelBoomBoom(), Textures.boom));
		RenderingRegistry.registerEntityRenderingHandler(EntityBigHongo.class, new RenderModMob(new ModelBigHongo(), Textures.bigHongo));
		RenderingRegistry.registerEntityRenderingHandler(EntityMediumHongo.class, new RenderModMob(new ModelMediumHongo(), Textures.mediumHongo));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmallHongo.class, new RenderModMob(new ModelSmallHongo(), Textures.smallHongo));
		//RenderingRegistry.registerEntityRenderingHandler(EntityEucaFighter.class, new RenderEucaFighter(new ModelEucaFighter()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaBlaze.class, new RenderModMob(new ModelBlaze(), Textures.magmaBlaze));
		//RenderingRegistry.registerEntityRenderingHandler(EntityCrisp.class, new RenderModMob(new ModelDarknessCrawler(), tex.crisp));
		//RenderingRegistry.registerEntityRenderingHandler(EntityBurntMiner.class, new RenderModBiped(new ModelBiped(), tex.burntMiner));
		//RenderingRegistry.registerEntityRenderingHandler(EntityExposedFlame.class, new RenderModBiped(new ModelBiped(), tex.exposedFlame));
		RenderingRegistry.registerEntityRenderingHandler(EntityBurningLight.class, new RenderModBiped(new ModelBiped(), Textures.burningLight));
		//RenderingRegistry.registerEntityRenderingHandler(EntityEucaHopper.class, new RenderEucaHopper(new ModelEucaHopper(), tex.eucaHopper));
		//RenderingRegistry.registerEntityRenderingHandler(EntityAshHoarder.class, new RenderSizeable(new ModelDepthsBeast(), 0.8F, 1.5F, tex.ashHoarder));
		//RenderingRegistry.registerEntityRenderingHandler(EntityBurntAsh.class, new RenderSizeable(new ModelPsyollom(), 0.5F, 1.5F, tex.burntAsh));
		RenderingRegistry.registerEntityRenderingHandler(EntityReaper.class, new RenderReaper(new ModelReaper(), Textures.reaper));
		RenderingRegistry.registerEntityRenderingHandler(EntityDepthsHunter.class, new RenderSizeable(new ModelDepthsHunter(), 0.5F, 1.5F, Textures.depthsHunter));
		RenderingRegistry.registerEntityRenderingHandler(EntityEucaCharger.class, new RenderSizeable(new ModelEucaCharger(), 0.5F, 1.5F, Textures.eucaCharger));
		RenderingRegistry.registerEntityRenderingHandler(EntitySandCrawler.class, new RenderModMob(new ModelSandCrawler(), 0.5F, Textures.sandCrawler));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireMage.class, new RenderMage(new ModelMage(), Textures.fireMage));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceMage.class, new RenderMage(new ModelMageTransparent(), Textures.iceMage));
		RenderingRegistry.registerEntityRenderingHandler(EntityMage.class, new RenderMage(new ModelMage(), Textures.mage));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlacksmith.class, new RenderModBiped(new ModelBiped(), Textures.blacksmith));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenMerchant.class, new RenderModMob(new ModelFrozenMerchant(), Textures.frozenMerchant));
		RenderingRegistry.registerEntityRenderingHandler(EntityEscapedConvict.class, new RenderModMob(new ModelEscapedConvict(), Textures.escapedConvict));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoilTrader.class, new RenderModMob(new ModelBoilTrader(), Textures.boilTrader));
		RenderingRegistry.registerEntityRenderingHandler(EntityAlloyMender.class, new RenderModMob(new ModelAlloyMender(), Textures.alloyMender));
		RenderingRegistry.registerEntityRenderingHandler(EntityTordo.class, new RenderModMob(new ModelTordo(), Textures.greenTordo));
		RenderingRegistry.registerEntityRenderingHandler(EntityRedTordo.class, new RenderModMob(new ModelTordo(), Textures.redTordo));
		RenderingRegistry.registerEntityRenderingHandler(EntityDunewerm.class, new RenderModMob(new ModelDunewerm(), Textures.dunewerm));
		//RenderingRegistry.registerEntityRenderingHandler(EntityDragon.class, new RenderDragon());
		RenderingRegistry.registerEntityRenderingHandler(EntityOverseer.class, new RenderModMob(new ModelOverseer(), Textures.overseer));
		RenderingRegistry.registerEntityRenderingHandler(EntitySurfaceSeer.class, new RenderModMob(new ModelOverseer(), Textures.surfaceSeer));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverseerElder.class, new RenderModMob(new ModelOverseerElder(), Textures.overseerElder));
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudGhost.class, new RenderModMob(new ModelCloudGhost(), Textures.cloudGhost));
		RenderingRegistry.registerEntityRenderingHandler(EntityStaringGuardian.class, new RenderModMob(new ModelStaringGuardian(), Textures.staringGuardian));
		RenderingRegistry.registerEntityRenderingHandler(EntityTreeGolem.class, new RenderModMob(new ModelTreeGolem(), Textures.treeGolem));
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodCreature.class, new RenderModMob(new ModelWoodCreature(), Textures.woodCreature));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoc.class, new RenderModMob(new ModelRoc(), Textures.roc));
		RenderingRegistry.registerEntityRenderingHandler(EntityDynaster.class, new RenderModMob(new ModelDynaster(), Textures.dynaster));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolder.class, new RenderModMob(new ModelGolder(), Textures.golder));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrystalCluster.class, new RenderModMob(new ModelCrystalCluster(), Textures.crystalCluster));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolditeMage.class, new RenderModMob(new ModelMage(), Textures.golditeMage));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverbot.class, new RenderModMob(new ModelSilverbot(), Textures.tinbot));
		RenderingRegistry.registerEntityRenderingHandler(EntityShimmerer.class, new RenderModMob(new ModelShimmerer(), Textures.shimmerer));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderLeaper.class, new RenderModMob(new ModelEnderLeaper(), Textures.enderLeaper));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrightener.class, new RenderModMob(new ModelFrightener(), Textures.frightener));
		RenderingRegistry.registerEntityRenderingHandler(EntityHellwing.class, new RenderModMob(new ModelHellwing(), Textures.hellwing));
		RenderingRegistry.registerEntityRenderingHandler(EntityObserver.class, new RenderModMob(new ModelObserver(), Textures.observer));
		RenderingRegistry.registerEntityRenderingHandler(EntityScreamer.class, new RenderModMob(new ModelScreamer(), Textures.screamer));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderCrawler.class, new RenderModMob(new ModelEnderCrawler(), Textures.enderCrawler));
		RenderingRegistry.registerEntityRenderingHandler(EntityCaveMage.class, new RenderModMob(new ModelMage(), Textures.caveMage));
		RenderingRegistry.registerEntityRenderingHandler(EntityNatureMage.class, new RenderModMob(new ModelMage(), Textures.natureMage));
		RenderingRegistry.registerEntityRenderingHandler(EntityCavurn.class, new RenderModMob(new ModelCavurn(), Textures.cavurn));
		RenderingRegistry.registerEntityRenderingHandler(EntityCaveling.class, new RenderModMob(new ModelCaveling(), Textures.caveling));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldbot.class, new RenderModMob(new ModelGoldbot(), Textures.goldbot));
		RenderingRegistry.registerEntityRenderingHandler(EntityStonewalker.class, new RenderModMob(new ModelStonewalker(), Textures.stonewalker));
		RenderingRegistry.registerEntityRenderingHandler(EntityShiverwolf.class, new RenderModMob(new ModelShiverwolf(), Textures.shiverwolf));
		RenderingRegistry.registerEntityRenderingHandler(EntityShiveringShrieker.class, new RenderModMob(new ModelShiveringShrieker(), Textures.shiveringShrieker));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenFrostbiter.class, new RenderModMob(new ModelFrozenFrostbiter(), Textures.frozenFrostbiter));
		RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, new RenderModMob(new ModelWraith(), Textures.wraith));
		RenderingRegistry.registerEntityRenderingHandler(EntityPhoenix.class, new RenderModMob(new ModelRoc(), Textures.phoenix));
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudFlyer.class, new RenderModMob(new ModelRoc(), Textures.cloudFlyer));
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodpecker.class, new RenderModMob(new ModelRoc(), Textures.woodpecker));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldwing.class, new RenderModMob(new ModelRoc(), Textures.goldwing));
		RenderingRegistry.registerEntityRenderingHandler(EntityShiverwing.class, new RenderModMob(new ModelRoc(), Textures.shiverwing));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurducken.class, new RenderModMob(new ModelRoc(), Textures.turducken));
		RenderingRegistry.registerEntityRenderingHandler(EntityFloro.class, new RenderModMob(new ModelFloro(), Textures.floro));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkener.class, new RenderModMob(new ModelDarkener(), Textures.darkener));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarlightGolem.class, new RenderModMob(new ModelStarlightGolem(), Textures.starlightGolem));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightener.class, new RenderModMob(new ModelLightener(), Textures.lightener));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSorcerer.class, new RenderModMob(new ModelDarkSorcerer(), Textures.darkSorcerer));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarlightTransporter.class, new RenderModMob(new ModelStarlightTransporter(), Textures.starlightTransporter));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpectre.class, new RenderModMob(new ModelBiped(), Textures.spectre));
		RenderingRegistry.registerEntityRenderingHandler(EntityGreenHonglow.class, new RenderModMob(new ModelBigHongo(), Textures.greenHonglow));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueHonglow.class, new RenderModMob(new ModelBigHongo(), Textures.blueHonglow));
		RenderingRegistry.registerEntityRenderingHandler(EntityHonglow.class, new RenderModMob(new ModelBigHongo(), Textures.redHonglow));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarlightVillager.class, new RenderModMob(new ModelStarlightVillager(), Textures.starlightVillager));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarlightBlacksmith.class, new RenderModMob(new ModelStarlightVillager(), Textures.starlightBlacksmith));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkyEel.class, new RenderModMob(new ModelSkyEel(), Textures.skyEel));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerralight.class, new RenderModMob(new ModelTerralight(), Textures.terralight));
		RenderingRegistry.registerEntityRenderingHandler(EntitySwampFly.class, new RenderModMob(new ModelTerralight(), Textures.swampFly));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerranianTrader.class, new RenderModMob(new ModelTerranianTrader(), Textures.terranianTrader));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerranianEnchanter.class, new RenderModMob(new ModelTerranianTrader(), Textures.terranianEnchanter));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerraScatterer.class, new RenderModMob(new ModelTerraScatterer(), Textures.terraScatterer));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarlightWalker.class, new RenderModMob(new ModelStarlightWalker(), Textures.starlightWalker));
		RenderingRegistry.registerEntityRenderingHandler(EntityPurplian.class, new RenderModMob(new ModelPurplian(), Textures.purplian));
		RenderingRegistry.registerEntityRenderingHandler(EntityOvergrownMerchant.class, new RenderModMob(new ModelFrozenMerchant(), Textures.overgrownMerchant));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerraslug.class, new RenderModMob(new ModelTerraslug(), Textures.terraslug));
		RenderingRegistry.registerEntityRenderingHandler(EntityHellbot.class, new RenderModMob(new ModelGoldbot(), Textures.hellbot));
		RenderingRegistry.registerEntityRenderingHandler(EntityLavasnake.class, new RenderModMob(new ModelLavasnake(), Textures.lavasnake));
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherspine.class, new RenderModMob(new ModelWitherspine(), Textures.witherspine));
		RenderingRegistry.registerEntityRenderingHandler(EntityHellCow.class, new RenderModMob(new ModelHellCow(), Textures.hellCow));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerragrow.class, new RenderModMob(new ModelTerragrow(), Textures.terragrow));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerrashroom.class, new RenderModMob(new ModelBigHongo(), Textures.terrashroom));
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniGhast.class, new RenderModMob(new ModelMiniGhast(), Textures.miniGhast));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlizzard.class, new RenderModMob(new ModelBlizzard(), Textures.blizzard));
		RenderingRegistry.registerEntityRenderingHandler(EntityRockiteGolem.class, new RenderModMob(new ModelRockiteGolem(), Textures.rockiteGolem));
		RenderingRegistry.registerEntityRenderingHandler(EntityFerret.class, new RenderModMob(new ModelFerret(), Textures.ferret));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityJungleGolem.class, new RenderModMob(new ModelJungleGolem(), Textures.jungleGolem));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonEgg.class, new RenderDragonEgg(new ModelDragonEgg()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityShatterer.class, new RenderShatterer());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenTroll.class, new RenderModMob(new ModelFrozenTroll(), Textures.frozenTroll));
		RenderingRegistry.registerEntityRenderingHandler(EntityPermafraust.class, new RenderModMob(new ModelPermafraust(), Textures.permafraust));
		RenderingRegistry.registerEntityRenderingHandler(EntityShiveringBushwalker.class, new RenderModMob(new ModelShiveringBushwalker(), Textures.shiveringBushwalker));
		RenderingRegistry.registerEntityRenderingHandler(EntityLeafBlower.class, new RenderModMob(new ModelLeafBlower(), Textures.leafBlower));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTerranianProtector.class, new RenderBoss(new ModelTerranianProtector(), 0.5F, 2.0F, Textures.terranianProtector, "terranianProtector"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulWatcher.class, new RenderBoss(new ModelSoulWatcher(), 0.5F, 2.0F, Textures.soulWatcher, "soulWatcher"));
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherBeast.class, new RenderBoss(new ModelBeastOfTheNether(), 0.5F, 2.0F, Textures.netherBeast, "netherBeast"));
		RenderingRegistry.registerEntityRenderingHandler(EntityWitheringBeast.class, new RenderBoss(new ModelWitheringBeast(), 0.5F, 2.0F, Textures.witheringBeast, "witheringBeast"));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalcia.class, new RenderCalcia(new ModelClacia(), 0.5F, 2.0F, Textures.calcia, "calcia"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTempleGuardian.class, new RenderModBiped(new ModelBiped(), 1.5F, Textures.templeGuardian));
		RenderingRegistry.registerEntityRenderingHandler(EntityFourfa.class, new RenderFourfa());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlazier.class, new RenderBoss(new ModelBlazier(), 0.5F, 2.0F, Textures.blazier, "blazier"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySentryKing.class, new RenderBoss(new ModelSentryKing(), 0.5F, 2.0F, Textures.sentryKing, "sentryKing"));
		RenderingRegistry.registerEntityRenderingHandler(EntityThunderbird.class, new RenderBoss(new ModelRoc(), 0.5F, 2.0F, Textures.thunderbird, "thunderbird"));
		RenderingRegistry.registerEntityRenderingHandler(EntityLogger.class, new RenderBoss(new ModelLogger(), 0.5F, 2.0F, Textures.logger, "logger"));
		RenderingRegistry.registerEntityRenderingHandler(EntityCorallator.class, new RenderBoss(new ModelCorallator(), 0.5F, 2.0F, Textures.corallator, "corallator"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkyStalker.class, new RenderBoss(new ModelSkyStalker(), 0.5F, 2.0F, Textures.skyStalker, "skyStalker"));
		RenderingRegistry.registerEntityRenderingHandler(EntityEudor.class, new RenderBoss(new ModelEudor(), 0.5F, 2.0F, Textures.eudor, "eudor"));
		RenderingRegistry.registerEntityRenderingHandler(EntityScale.class, new RenderBoss(new ModelSoulWatcher(), 0.5F, 2.0F, Textures.scale, "scale"));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTameRoc.class, new RenderModMob(new ModelRoc(), Textures.tameRoc));
		//RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, new RenderBoss(new ModelWraith(), 0.5F, 1.0F, tex.wraith, stat.wraithBossID));
		
		//SlayerAPI.registerItemRenderer(EssenceBlocks.netherBeastStatue, new ItemRendererStatue("netherBeastStatue"));
		//SlayerAPI.registerItemRenderer(EssenceBlocks.witheringBeastStatue, new ItemRendererStatue("witheringBeastStatue"));
		//SlayerAPI.registerItemRenderer(EssenceBlocks.enderChampionStatue, new ItemRendererStatue("enderChampionStatue"));
		//SlayerAPI.registerItemRenderer(EssenceBlocks.wraithStatue, new ItemRendererStatue("wraithStatue"));
		//SlayerAPI.registerItemRenderer(EssenceBlocks.calciaStatue, new ItemRendererStatue("calciaStatue"));
		//SlayerAPI.registerItemRenderer(EssenceBlocks.eudorStatue, new ItemRendererStatue("eudorStatue"));
		
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(EssenceBlocks.calciaStatue);
		//TileEntityItemStackRenderer.instance = new ModeledBlockInventoryRenderer(TileEntityItemStackRenderer.instance);
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, new StatueRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrindstone.class, new GrindstoneRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKnowledgeTable.class, new KnowledgeTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySummoningTable.class, new SummoningTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySenterianPortal.class, new SenterianPortalRenderer());
		 */
	}

	public static MobStats getStat() {
		return stat;
	}

	public static void setStat(MobStats stat) {
		EntityRendering.stat = stat;
	}

	public static Textures getTex() {
		return tex;
	}

	public static void setTex(Textures tex) {
		EntityRendering.tex = tex;
	}
}