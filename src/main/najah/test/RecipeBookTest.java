package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeBookTest {

    static RecipeBook book;
    Recipe recipe1;
    Recipe recipe2;
    Recipe recipe3;

    @BeforeAll
    static void initAll() {
        book = new RecipeBook();
        System.out.println("RecipeBook setup start");
    }
    
    @BeforeEach
    void init() {
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe2 = new Recipe();
        recipe2.setName("Tea");
        recipe3 = new Recipe();
        recipe3.setName(null);
    }

//***************************************************************
    @Test
    @Order(1)
    @DisplayName("Add valid recipe")
    void testAddValidRecipe() {
        boolean added = book.addRecipe(recipe1);
        assertAll("Adding recipe",
                () -> assertTrue(added),
                () -> assertEquals(recipe1, book.getRecipes()[0])
        );
    }
//***************************************************************

    @Test
    @Order(2)
    @DisplayName("Add valid recipe2")
    void testAddValidRecipe2() {
        boolean added = book.addRecipe(recipe2);
        assertAll("Adding recipe",
                () -> assertTrue(added),
                () -> assertEquals(recipe2, book.getRecipes()[1])
        );
    }
//***************************************************************

    @Test
    @Order(3)
    @DisplayName("Add Invalid recipe3")
    void testAddValidRecipe3() {
        boolean added = book.addRecipe(recipe3);
        assertAll("Adding recipe",
                () -> assertTrue(added),
                () -> assertEquals(recipe3, book.getRecipes()[2])
        );
    }
//***************************************************************
 
    @Test
    @Order(4)
    @DisplayName("Add duplicate recipe should fail")
    void testAddRecipeDuplicate() {
        book.addRecipe(recipe1); 
        boolean added = book.addRecipe(recipe1);
        assertFalse(added);
    }
    
//***************************************************************
   
    @Test
    @Order(5)
    @DisplayName("Delete valid recipe")
    void testDeleteValidRecipe() {
        String deletedName = book.deleteRecipe(1);
        assertAll("Delete recipe",
                () -> assertEquals("Tea", deletedName),
                () -> assertNotNull(book.getRecipes()[1]) );}
//***************************************************************

    @Test
    @Order(6)
    @DisplayName("Delete invalid recipe empty slot")
    void testDeleteRecipeInvalid() {
        String deletedName = book.deleteRecipe(3); 
        assertNull(deletedName);
    }
   
    
//***************************************************************
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("Edit recipe with parameterized indices")
    void testEditRecipeParameterized(int index) {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("NewRecipe");
        if (book.getRecipes()[index] != null) {
            String oldName = book.editRecipe(index, newRecipe);
            assertEquals(book.getRecipes()[index], newRecipe);
            assertNotNull(oldName);
        } else {
            String oldName = book.editRecipe(index, newRecipe);
            assertNull(oldName);
        }
    }
//***************************************************************
    @Test
    @Timeout(500)
    @DisplayName("Timeout test for getRecipes")
    void testTimeout() {
        Recipe[] recipes = book.getRecipes();
        assertNotNull(recipes);
        assertEquals(4, recipes.length);//4 is true cuze the main length is 4 even if there is no recipes 
    }

//***************************************************************
   // @Disabled("Intentional failing test: editRecipe sets name to empty")
    @Test
    void testEditRecipeFailing() {
        Recipe r = new Recipe();
        r.setName("FailTest");
        String oldName = book.editRecipe(0, r);
        assertEquals("", oldName); }
}