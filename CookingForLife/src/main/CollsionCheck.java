package main;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Entity;

public class CollsionCheck {
	GamePanel gp;
	Entity entity;
	public CollsionCheck(GamePanel gp){
		this.gp = gp;
	}
	public void checkTile(Entity entity) {
		this.entity = entity;
		boolean inAScreen = true;
		int screenY = entity.screenY;
		int screenX = entity.screenX;
		int[] locationHitBox = entity.locationHitBox;
		int speed = entity.speed;
		String temp = entity.direction;
		int entityLeftscreenX =  entity.locationHitBox[0];
		int entityRightscreenX = entity.locationHitBox[0] + entity.locationHitBox[2];
		int entityTopscreenY =  entity.locationHitBox[1];
		int entityBottomscreenY = entity.locationHitBox[1] + entity.locationHitBox[3];
//		if (entityLeftscreenX  - entity.speed < 0 || 
//			entityRightscreenX + entity.speed < gp.screenWidth ||
//			entityTopscreenY  - entity.speed< 0 ||
//			entityBottomscreenY + entity.speed> gp.screenHeight) {
//			entity.collisionOn = true;
//			return entity.collisionOn;
//		}
		if (entityLeftscreenX < 0 || entityRightscreenX > gp.screenWidth || entityTopscreenY < 0 || entityBottomscreenY > gp.screenHeight) {
			inAScreen = false;
		}
		String tem1 = "";
		if (inAScreen == false) {
			entity.collisionOn = true;
			
			switch(temp) {
			case "up":
				if(tem1 == "down") entity.collisionOn = false;
				screenY -= speed;
				locationHitBox[1] = screenY + gp.tileSize * 2 + 32;
				break;
			case "down":
				if(tem1 == "up") entity.collisionOn = false;
				screenY += speed;
				locationHitBox[1] =screenY + gp.tileSize * 2 + 32;
				break;
			case "left":
				if(tem1 == "right") entity.collisionOn = false;
				screenX -= speed;
				locationHitBox[0] = screenX + gp.tileSize / 2 + 8;
				break;
			case "right":
				if(tem1 == "left") entity.collisionOn = false;
				screenX += speed;
				locationHitBox[0] = screenX + gp.tileSize / 2 + 8;
				break;
			default :
				break;
			}
			tem1 = entity.direction;

		}else {
		int entityLeftCol = (entity.locationHitBox[0])/gp.tileSize;
		int entityRightCol = (entity.locationHitBox[0] + entity.locationHitBox[2])/gp.tileSize;
		int entityTopRow = (entity.locationHitBox[1])/gp.tileSize;
		int entityBottomRow = (entity.locationHitBox[1] + entity.locationHitBox[3])/gp.tileSize;
		int tileNum1, tileNum2;
		switch(entity.direction) {
		case "up" :
			entityTopRow = (entityTopscreenY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down" :
			entityBottomRow = (entityBottomscreenY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left" :
			entityLeftCol = (entityLeftscreenX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right" :
			entityRightCol = (entityRightscreenX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
		}
	}
	public int checkObject(Entity entity, boolean isPlayer) {
		int index = 9999;
		
		for (int i = 0; i < gp.obj.size(); i++) {
			//lay vi tri hit boxcua entity
			int entityLeftscreenX =  entity.locationHitBox[0];
			int entityTopscreenY =  entity.locationHitBox[1];
			Rectangle entityHitBox = new Rectangle(entityLeftscreenX, entityTopscreenY, entity.locationHitBox[2], entity.locationHitBox[3]);
			//lay vi tri hit box cua object
			int objX = gp.obj.get(i).screenX;
			int objY = gp.obj.get(i).screenY;
			Rectangle hitBoxObject = new Rectangle(objX + gp.obj.get(i).hitBox[0],objY + gp.obj.get(i).hitBox[1], gp.obj.get(i).hitBox[2],gp.obj.get(i).hitBox[3]);
			
			switch(entity.direction) {
			case "up" :
				entityHitBox.y -= entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
					if(gp.obj.get(i).collison == true) {
						entity.collisionOn = true;
						System.out.println("Up collsion" + gp.obj.get(i).name);
					}
					if(isPlayer == true) {
						index = i;
					}
					
				}
				break;
			case "down" :
				entityHitBox.y += entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
					if(gp.obj.get(i).collison ==true) {
						entity.collisionOn = true;
						System.out.println("Down collsion" + gp.obj.get(i).name);
					}
					if(isPlayer == true) {
						index = i;
					}
				}
				break;
			case "left" :
				entityHitBox.x -= entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
					if(gp.obj.get(i).collison ==true) {
						entity.collisionOn = true;
						System.out.println("Left collsion"  + gp.obj.get(i).name);
					}
					if(isPlayer == true) {
						index = i;
					}
				}
				break;
			case "right" :
				entityHitBox.x += entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
					if(gp.obj.get(i).collison ==true) {
						entity.collisionOn = true;
						System.out.println("Right collsion" + gp.obj.get(i).name);
					}
					if(isPlayer == true) {
						index = i;
					}
				}
				break;
			}
		}
		return index;
	}
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 9999;
		
		for (int i = 0; i < target.length; i++) {
			if(target[i] != null) {
			//lay vi tri hit boxcua entity
			int entityLeftscreenX =  entity.locationHitBox[0];
			int entityTopscreenY =  entity.locationHitBox[1];
			Rectangle entityHitBox = new Rectangle(entityLeftscreenX, entityTopscreenY, entity.locationHitBox[2], entity.locationHitBox[3]);
			//lay vi tri hit box cua object
			int objX = target[i].locationHitBox[0];
			int objY = target[i].locationHitBox[1];
			Rectangle hitBoxObject = new Rectangle(objX ,objY , target[i].locationHitBox[2],target[i].locationHitBox[3]);
			
			switch(entity.direction) {
			case "up" :
				entityHitBox.y -= entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
						System.out.println("Up collsion" + target[i].name);
				}
				break;
			case "down" :
				entityHitBox.y += entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
						System.out.println("Down collsion" + target[i].name);
						index = i;
				}
				break;
			case "left" :
				entityHitBox.x -= entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
						System.out.println("Left collsion"  + target[i].name);
						index = i;
					}
				break;
			case "right" :
				entityHitBox.x += entity.speed;
				if(entityHitBox.intersects(hitBoxObject)) {
						System.out.println("Right collsion" + target[i].name);
						index = i;
					}
				break;
			}
		}
		}
		return index;
	}
	public void checkPlayer(Entity target, Entity entity ) {
		int entityLeftscreenX =  entity.locationHitBox[0];
		int entityTopscreenY =  entity.locationHitBox[1];
		Rectangle entityHitBox = new Rectangle(entityLeftscreenX, entityTopscreenY, entity.locationHitBox[2], entity.locationHitBox[3]);
		//lay vi tri hit box cua object
		int objX = target.locationHitBox[0];
		int objY = target.locationHitBox[1];
		Rectangle hitBoxObject = new Rectangle(objX ,objY , target.locationHitBox[2],target.locationHitBox[3]);
		
		switch(entity.direction) {
		case "up" :
			entityHitBox.y -= entity.speed;
			if(entityHitBox.intersects(hitBoxObject)) {
					System.out.println(target.name + "impact " );
			}
			break;
		case "down" :
			entityHitBox.y += entity.speed;
			if(entityHitBox.intersects(hitBoxObject)) {
				System.out.println(target.name + "impact " );
			}
			break;
		case "left" :
			entityHitBox.x -= entity.speed;
			if(entityHitBox.intersects(hitBoxObject)) {
				System.out.println(target.name + "impact " );
				}
			break;
		case "right" :
			entityHitBox.x += entity.speed;
			if(entityHitBox.intersects(hitBoxObject)) {
				System.out.println(target.name + "impact " );
				}
			break;
		}
	
	}
}
