package com.example.example_mod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HammerItem extends ToolItem{
	private float attackDamage;
	private final float basicAttackDamage;
	private float basicAttackSpeed;
	private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	public HammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
		super(toolMaterial, settings);
		this.basicAttackDamage = (float)attackDamage + toolMaterial.getAttackDamage();
		this.basicAttackSpeed = attackSpeed;
		Builder(basicAttackDamage, basicAttackSpeed);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		Builder(basicAttackDamage, basicAttackSpeed);
		return true;
	}

	void Builder(float damage, float speed){
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(
			EntityAttributes.GENERIC_ATTACK_DAMAGE,
			new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)damage, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ATTACK_SPEED,
			new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)speed, EntityAttributeModifier.Operation.ADDITION)
		);
		this.attributeModifiers = builder.build();
	}

	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		if (user instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) user;
			int i = this.getMaxUseTime(stack) - remainingUseTicks;
			float f = getPullProgress(i);

			if (user.isSneaking()){
				user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5 * (int)(f * 10), 1), user);
				world.playSound(
					null,
					playerEntity.getX(),
					playerEntity.getY(),
					playerEntity.getZ(),
					SoundEvents.BLOCK_ANVIL_BREAK,
					SoundCategory.PLAYERS,
					1.5F,
					1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F
				);
			}else {
				boolean hit = false;
				Box box = new Box(new BlockPos(user.getBlockPos().getX(), user.getBlockPos().getY(), user.getBlockPos().getZ()));
				for(LivingEntity livingEntity : world.getNonSpectatingEntities(LivingEntity.class, box.expand(3.0, 1.0, 3.0))) {
					if (livingEntity != user &&
						(!(livingEntity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingEntity).isMarker())
						&& this.squaredDistanceTo(livingEntity.getPos(), user) < 9.0) {

							livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5 * (int)(f * 10), 1), user);
							hit = true;
						/*
						Vec3d vec3d = livingEntity.getVelocity();
						Vec3d vec3d2 = new Vec3d(0.0, livingEntity.getY(), 0.0).normalize().multiply(1f);
						livingEntity.setVelocity(vec3d.x, vec3d2.y, vec3d.z);
						*/
					}
				}
				playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
				if(hit){
					world.playSound(
						null,
						playerEntity.getX(),
						playerEntity.getY(),
						playerEntity.getZ(),
						SoundEvents.BLOCK_ANCIENT_DEBRIS_BREAK,
						SoundCategory.PLAYERS,
						1.5F,
						1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F
					);
				}
			}
		}
	}

	public static float getPullProgress(int useTicks) {
		float f = (float)useTicks / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		user.setCurrentHand(hand);
		return TypedActionResult.consume(itemStack);
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return  72000;
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}

	public double squaredDistanceTo(Vec3d vector, LivingEntity user) {
		double d = user.getX() - vector.x;
		double e = user.getY() - vector.y;
		double f = user.getZ() - vector.z;
		return d * d + e * e + f * f;
	}
}
