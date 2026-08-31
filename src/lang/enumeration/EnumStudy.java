package lang.enumeration;

public class EnumStudy{
	public static void main(String[] args){
		System.out.println("1. Stirng type problem");
		stringTypeProblem();

		System.out.println("\n2. Type-safe enum pattern");
		typeSafePattern();

		System.out.println("\n3. Enum basic");
		enumBasic();

		System.out.println("\n4. Enum methods");
		enumMethods();

		System.out.println("\n5. Refactoring");
		refactoring();
	}

	static void stringTypeProblem(){
		int price = 10_000;

		int goldDiscount = calculateDiscountV1(price, "GOLD");

		int typoDiscount = calculateDiscountV1(price, "GLOD");

		int unknownDiscount = calculateDiscountV1(price, "VIP");

		System.out.println("GOLD discount = " + goldDiscount);
		System.out.println("GLOD discount = " + typoDiscount);
		System.out.println("VIP discount = " + unknownDiscount);
	}

	static int calculateDiscountV1(int price, String grade){
		int discountRate;

		if("BASIC".equals(grade))
			discountRate = 0;
		else if("SILVER".equals(grade))
			discountRate = 10;
		else if("GOLD".equals(grade))
			discountRate = 20;
		else
			discountRate = 0;

		return price * discountRate / 100;
	}

	static void typeSafePattern(){
		int price = 10_000;

		TypeSafeGrade grade = TypeSafeGrade.GOLD;

		int discount = grade.calculateDiscount(price);

		System.out.println("Grade = " + grade);
		System.out.println("Discount rate = " + grade.getDiscountRate());
		System.out.println("Discount = " + discount);
	}

	static void enumBasic(){
		Grade grade = Grade.GOLD;

		System.out.println("Grade = " + grade);
		System.out.println("Discount rate = " + grade.getDiscountRate());

		if(grade == Grade.GOLD){
			System.out.println("Gold member");
		}
	}

	static void enumMethods(){
		Grade[] grades = Grade.values();

		for(Grade grade : grades){
			System.out.println(
				"name = " + grade.name() 
					+ ", ordinal = " + grade.ordinal() 
					+ ", rate = " + grade.getDiscountRate());
		}

		Grade parsedGrade = Grade.valueOf("SILVER");

		System.out.println("Parsed grade = " + parsedGrade);

		try{
			Grade.valueOf("silver");
		} catch(IllegalArgumentException exception) {
			System.out.println("Invalid enum name");
		}
	}

	static void refactoring(){
		int price = 10_000;

		System.out.println("Version 1 = " + calculateDiscountV1(price, "GOLD"));

		System.out.println("Version 2 = " + calculateDiscountV2(price, Grade.GOLD));

		System.out.println("Version 3 = " + Grade.GOLD.calculateDiscount(price));
	}

	static int calculateDiscountV2(int price, Grade grade){
		int discountRate = switch(grade){
			case BASIC -> 0;
			case SILVER -> 10;
			case GOLD -> 20;
		};

		return price * discountRate / 100;
	}

	static final class TypeSafeGrade{
		public static final TypeSafeGrade BASIC = new TypeSafeGrade("BASIC", 0);

		public static final TypeSafeGrade SILVER = new TypeSafeGrade("SILVER", 10);

		public static final TypeSafeGrade GOLD = new TypeSafeGrade("GOLD", 20);

		private final String name;
		private final int discountRate;

		private TypeSafeGrade(String name, int discountRate){
			this.name = name;
			this.discountRate = discountRate;
		}

		public int getDiscountRate(){
			return discountRate;
		}

		public int calculateDiscount(int price){
			return price * discountRate / 100;
		}

		@Override
		public String toString(){
			return name;
		}
	}

	enum Grade{
		BASIC(0),
		SILVER(10),
		GOLD(20);

		private final int discountRate;

		Grade(int discountRate){
			this.discountRate = discountRate;
		}

		public int getDiscountRate(){
			return discountRate;
		}

		public int calculateDiscount(int price){
			return price * discountRate / 100;
		}
	}
}
