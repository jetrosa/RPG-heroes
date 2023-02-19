package rpgheroes.hero;

import static org.junit.jupiter.api.Assertions.*;

class HeroAttributeTest {

    @org.junit.jupiter.api.Test
    void addAttributes_shouldEqualTo_newObjectWithSameValues() {
        int attr1Str = 1;
        int attr1Dex = 2;
        int attr1Int = 3;

        int attr2Str = 4;
        int attr2Dex = 5;
        int attr2Int = 6;

        HeroAttribute expected = new HeroAttribute(attr1Str + attr2Str, attr1Dex + attr2Dex, attr1Int + attr2Int);
        int expectedStr = expected.getStrength();
        int expectedDex = expected.getDexterity();
        int expectedInt = expected.getIntelligence();

        HeroAttribute attr1 = new HeroAttribute(attr1Str, attr1Dex, attr1Int);
        HeroAttribute attr2 = new HeroAttribute(attr2Str, attr2Dex, attr2Int);
        attr1.addAttributes(attr2);
        int actualStr = attr1.getStrength();
        int actualDex = attr1.getDexterity();
        int actualInt = attr1.getIntelligence();

        assertEquals(expectedStr, actualStr);
        assertEquals(expectedDex, actualDex);
        assertEquals(expectedInt, actualInt);
    }

    @org.junit.jupiter.api.Test
    void addAttributes_shouldAdd_intPositive() {
        int attr1Str = 1;
        int attr1Dex = 2;
        int attr1Int = 3;

        int attr2Str = 4;
        int attr2Dex = 5;
        int attr2Int = 6;

        int expectedStr = attr1Str + attr2Str;
        int expectedDex = attr1Dex + attr2Dex;
        int expectedInt = attr1Int + attr2Int;

        HeroAttribute attr1 = new HeroAttribute(attr1Str, attr1Dex, attr1Int);
        HeroAttribute attr2 = new HeroAttribute(attr2Str, attr2Dex, attr2Int);
        attr1.addAttributes(attr2);
        int actualStr = attr1.getStrength();
        int actualDex = attr1.getDexterity();
        int actualInt = attr1.getIntelligence();

        assertEquals(expectedStr, actualStr);
        assertEquals(expectedDex, actualDex);
        assertEquals(expectedInt, actualInt);
    }

    @org.junit.jupiter.api.Test
    void addAttributes_shouldAdd_intZeroAndNegative() {
        int attr1Str = 1;
        int attr1Dex = 2;
        int attr1Int = -2;

        int attr2Str = -2;
        int attr2Dex = 0;
        int attr2Int = 1;

        int expectedStr = attr1Str + attr2Str;
        int expectedDex = attr1Dex + attr2Dex;
        int expectedInt = attr1Int + attr2Int;

        HeroAttribute attr1 = new HeroAttribute(attr1Str, attr1Dex, attr1Int);
        HeroAttribute attr2 = new HeroAttribute(attr2Str, attr2Dex, attr2Int);
        attr1.addAttributes(attr2);
        int actualStr = attr1.getStrength();
        int actualDex = attr1.getDexterity();
        int actualInt = attr1.getIntelligence();

        assertEquals(expectedStr, actualStr);
        assertEquals(expectedDex, actualDex);
        assertEquals(expectedInt, actualInt);
    }
}