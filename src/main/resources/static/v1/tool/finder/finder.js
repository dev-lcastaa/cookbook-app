// Function to make the API call
async function fetchRecipe() {

    //get token from sessionStorage
    const token = sessionStorage.getItem("token");

    //show loading spinner
    showLoadingSpinner();

    //scans html for the following IDs
    const ingredients = document.getElementById('ingredientsForFinder').value;
    const ethnicity = document.getElementById('ethnicity').value;


    try {
        //recipe API endpoint
        const apiUrl = '/api/v1/finder/recipe';

        //response from the fetch
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ethnicity: ethnicity,
                ingredients: ingredients
            }),
        });

        //if the response is a 200 ok
        if (response.ok) {
            const data = await response.json();

            // Create an empty object to store the parsed data
            const recipeObject = validateResponse(data);

            // Now, recipeObject contains the parsed data
            console.log('Parsed Recipe Object:', recipeObject);

            // You can access individual properties like recipeObject.name, recipeObject.ingredients, etc.
            document.getElementById('recipeName').textContent = recipeObject.name;
            document.getElementById('recipeIngredients').textContent = recipeObject.ingredients;
            document.getElementById('recipeSteps').textContent = recipeObject.steps;
        }

        // if the response is not a 200 ok
        // inside the modal it will display API call failed
        else { document.getElementById('recipeContent').innerHTML = 'API call failed.';}
    }

    // catches any error and logs it to the console and
    // inside the modal it will display API call failed
    catch (error) {
        console.error('API call error:', error);
        document.getElementById('recipeContent').innerHTML = 'API call failed.';
    }

    //last it will hide the loading spinner
    finally { hideLoadingSpinner(); }
}


// Function to validate response from OpenAi is of specified format
function validateResponse(response) {
    // If the response is already an object and has the required properties, return it as-is
    if (typeof response === 'object' && response.name && response.ingredients && response.steps) {
        return response;
    }

    // Try to parse the response as JSON
    try {
        const recipe = JSON.parse(response);

        // Check if the parsed response has the required properties
        if (recipe && recipe.name && recipe.ingredients && recipe.steps) {
            return recipe;
        } else {
            // If the parsed response is missing required properties, construct the object using the response's key-value pairs
            const recipeObject = {
                name: recipe.name || 'Recipe Name Not Available',
                ingredients: recipe.ingredients || 'Ingredients Not Available',
                steps: recipe.steps || 'Recipe Steps Not Available'
            };
            return recipeObject;
        }
    } catch (error) {
        // Create an empty object to store the parsed data
        const recipeObject = {};

        // Handle any errors in the response gracefully
        recipeObject.name = 'Recipe Name Not Available';
        recipeObject.ingredients = 'Ingredients Not Available';
        recipeObject.steps = 'Recipe Steps Not Available';

        return recipeObject;
    }
}


// Function to show the loading spinner and hide the modal content
function showLoadingSpinner() {
    document.getElementById('loadingSpinner').style.display = 'block';
    document.getElementById('recipeContent').style.display = 'none';
}


// Function to hide the loading spinner and display the modal content
function hideLoadingSpinner() {
    document.getElementById('loadingSpinner').style.display = 'none';
    document.getElementById('recipeContent').style.display = 'block';
}


// Attach a click event listener to the "Get Recipe" button
document.getElementById('fetchRecipeModalButton').addEventListener('click', fetchRecipe);


// Attach click event listeners to other buttons if needed
document.getElementById('getAnotherRecipeButton').addEventListener('click', () => {
    // Handle getting another recipe
});


document.getElementById('addRecipeToCookBookButton').addEventListener('click', () => {
    // Handle adding the recipe to the cookbook
});