package openblocks.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ModelGhost extends ModelBiped {
	
	
	public ModelRenderer tailBody;
	public ModelRenderer tailEnd;
	
	/**
	 * What is this float?
	 * @param f No idea what this is
	 */
	public ModelGhost(float f){
		super(f);
		// Tail Body
		this.tailBody = new ModelRenderer(this, 0, 16);
		this.tailBody.addBox(-3.5f, 0f, -2f, 7, 8, 4);
		this.tailBody.setRotationPoint(0f, 12f, 0f);
		// Tail End
		this.tailEnd = new ModelRenderer(this, 0, 16);
		this.tailEnd.addBox(0f, 0f, -1f, 4, 3, 2);
		this.tailEnd.setRotationPoint(-2f,8f,-4f - 1.5f - 4f);
		this.tailBody.addChild(tailEnd);
	}
	
	 public void render(Entity ent, float par2, float par3, float par4, float par5, float par6, float par7) {
		 this.setRotationAngles(par2, par3, par4, par5, par6, par7, ent);
		 
		 float scaryAngle = 60;
		 
		 GL11.glPushMatrix();
		 if((ent.getDataWatcher().getWatchableObjectByte(11) & 0x1) == 0x1) {
			 GL11.glRotatef(scaryAngle, 1, 0, 0);
			 this.bipedHead.rotateAngleX -= Math.toRadians(scaryAngle);
			 this.bipedLeftArm.rotateAngleX -= Math.toRadians(scaryAngle);
			 this.bipedRightArm.rotateAngleX -= Math.toDegrees(scaryAngle);
		 }
		 GL11.glTranslatef(0f, -(float)(MathHelper.cos(par4 * 0.066f)) * 0.2f ,0f);
		 
         this.bipedBody.render(par7);
         this.bipedHead.render(par7);
         this.bipedRightArm.render(par7);
         this.bipedLeftArm.render(par7);
         
         this.tailBody.render(par7);
         //this.tailEnd.render(par7);
         
         this.bipedHeadwear.render(par7);
         
         GL11.glPopMatrix();
	 }
	 
	 public void setRotationAngles(float par1, float par2, float par3, float headYaw, float par5, float par6, Entity ent) {
		 super.setRotationAngles(par1, par2, par3, headYaw, par5, par6, ent);
		 this.tailEnd.setRotationPoint(-2f,8f,-1f);//1.5f);
		 this.tailBody.rotateAngleX = /* MathHelper.cos(par1 * 0.3662F) * 0.7F * par2 + */ (float)Math.toRadians(60);
		 //this.tailEnd.rotateAngleX = MathHelper.cos(par1 * 0.1662F); // * 1.4F * par2 * 0.2f;// + (float)Math.toRadians(60);
		 
		 this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * par2 * 0.1F;
	     this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * par2 * 0.1F;

	     this.bipedRightArm.rotateAngleZ = MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
	     this.bipedLeftArm.rotateAngleZ = -MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
	     this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
	     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
		 
		 this.bipedLeftArm.rotateAngleX -= Math.toRadians(90);
		 this.bipedRightArm.rotateAngleX -= Math.toRadians(90);
		 this.tailBody.rotateAngleZ = this.tailEnd.rotateAngleZ = 0f;
	     this.tailBody.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.3F;
	     this.tailEnd.rotateAngleX = -MathHelper.sin(par3 * 0.067F) * 0.5F;
	 }
}