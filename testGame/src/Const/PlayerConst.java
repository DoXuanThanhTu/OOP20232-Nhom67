package Const;

public class PlayerConst {
		
		public static class PlayerConstants{
			public static final char DOWN = 's';
			public static final char UP = 'w';
			public static final char RIGHT = 'd';
			public static final char LEFT = 'a';
			public static final int downIDLE = 0;
			public static final int leftIDLE = 1;
			public static final int upIDLE = 2;
			public static final int rightIDLE = 3;
			public static final int downRUN = 4;
			public static final int leftRUN = 5;
			public static final int upRUN = 6;
			public static final int rightRUN = 7;
			
			
			public static int GetSpriteAmount(int player_action) {
				switch(player_action){
				case downIDLE:
				case leftIDLE:
				case rightIDLE:
					return 3;
				case downRUN:
				case upRUN:
				case leftRUN:
				case rightRUN:
					return 10;
				default:
					return 1;
				}
			}
		}
}
